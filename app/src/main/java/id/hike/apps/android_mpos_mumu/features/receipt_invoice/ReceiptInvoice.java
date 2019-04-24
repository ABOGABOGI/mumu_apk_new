package id.hike.apps.android_mpos_mumu.features.receipt_invoice;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.aktifitas.AktifitasHistoryActivity;
import id.hike.apps.android_mpos_mumu.features.landing_page.LandingPage;
import id.hike.apps.android_mpos_mumu.features.receipt_invoice.model.ProdukItem;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class ReceiptInvoice extends BaseActivity {

    List<id.hike.apps.android_mpos_mumu.features.receipt_invoice.ModelListBelanja> mm = new ArrayList<>();
    TextView tvDiskon, tvTanggalTransaksi, tvTransQueue,
            tvKontak, tvTotalHarga, tvTunai,
            tvKembalian, tvTanggalDanNamaKasir, tvNamaGerai,
            tvAlamatGerai, tvTeleponGerai, tvTotalItem, tvInfo;

    EditText etEmailPelanggan;
    LinearLayout strukContainer;

    String namaOutlet, alamatGerai, teleponGerai, kembalian, nomorTelepon, kontak, nomorTransaksi, uangTunai, diskon;

    private Boolean isPpob;


    Button btnSelesai;
    Button btnPrint;
    ImageView imgTempStruk;
    LinearLayout strukLayout;
    LinearLayout rootView;


    // android built in classes for bluetooth operations
    BluetoothAdapter mBluetoothAdapter;
    BluetoothSocket mmSocket;
    BluetoothDevice mmDevice;

    // needed for communication to bluetooth device / network
    OutputStream mmOutputStream;
    InputStream mmInputStream;
    Thread workerThread;

    String productStruk;

    byte[] readBuffer;
    int readBufferPosition;
    volatile boolean stopWorker;
    
    Boolean isFromDetailTransaksi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_invoice);

        btnSelesai = (Button) findViewById(R.id.btnSelesai);
        btnPrint = (Button)findViewById(R.id.btnPrint);
        strukLayout = (LinearLayout)findViewById(R.id.strukContainer);
        imgTempStruk = (ImageView) findViewById(R.id.imgStruk);


        // Set window fullscreen and remove title bar, and force landscape orientation
        /*this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        strukContainer = (LinearLayout) findViewById(R.id.strukContainer);
        // add View
        LayoutInflater factory = LayoutInflater.from(this);
        View myView = factory.inflate(R.layout.struk_produk, null);
        strukContainer.addView(myView);

        rootView = (LinearLayout) findViewById(R.id.laySS);
        etEmailPelanggan = (EditText) findViewById(R.id.etEmailPelanggan);
        etEmailPelanggan.setText(getSecPref().getString(Cfg.prefsEmailKostumer_Str, ""));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvStruk = (RecyclerView) findViewById(R.id.glStruk);
        rvStruk.setLayoutManager(layoutManager);
        rvStruk.setItemAnimator(new DefaultItemAnimator());
        dummyAdapter();

        rvStruk.setAdapter(new RvListBelanja(this, mm));

        onComponent();

        isPpob = false;
        Gson gson = new Gson();

        String resCekTransaksiPulsaS = getIntent().getExtras().getString(Cfg.resCekTransaksiPulsaKey);

        isFromDetailTransaksi = getIntent().getExtras().getBoolean(Cfg.IS_FROM_DETAIL_TRANS_KEY);
        
        if (getIntent().getExtras()!=null) {
            String transType = getIntent().getExtras().getString(Cfg.transactionTypeKey);
            if(transType!=null && transType.equals(Cfg.pulsaTypeVal)){
                isPpob = true;
            }
        }

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeBT();
                if(isPpob){
                }else{
                    sendStruk();
                }

            }
        });

        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setProductStruk();
                if(mmSocket==null || !mmSocket.isConnected()){
                    findBT();
                    openBT();
                }
                sendData();

            }
        });

        dfLoading.show(getSupportFragmentManager(),this.getClass().getCanonicalName());
        dfLoading.setCancelable(false);



    }



    private void setProductStruk(){
        productStruk = "";
        if(mm!=null && !mm.isEmpty()){
            for(int counter = 0; counter < mm.size(); counter++){
                String productName = mm.get(counter).getNamaProduk();
                Integer qty = mm.get(counter).getJumlah();
                String harga = UnitConversion.format2Rupiah(Long.parseLong(mm.get(counter).getHarga()));
                productStruk = productStruk+productName+"  ("+qty+")   "+harga+"\n";
            }
        }
        Log.e(Cfg.TAG_COMMON, "isi produk "+productStruk);
    }

    private String generateStruk(){
        String geraiName =""+ namaOutlet+"\n";
        String geraiAddress =""+ alamatGerai.trim()+"\n";
        String noTelp = ""+teleponGerai+"\n";
        String dot1 = "--------------------------------\n";
        String tanggal = tvTanggalTransaksi.getText().toString();
        String tanggalLbl = "Tanggal";
        String tglLabel = "    "+tanggal+"\n";
        String nomorLbl = "Nomor";
        String nomor = "      "+tvTransQueue.getText().toString()+"\n";
        String pelanggangLbl = "Pelanggan";
        String pelanggan =  "  : "+getSecPref().getString(Cfg.prefsKostumerName_STR, Cfg.defaultKostumerName)+"\n";
        String dot2 = "--------------------------------\n";
        String products = productStruk;
        String dot3 = "--------------------------------\n";
        String totalPrice = tvTotalItem.getText().toString();
        String total = "\t"+tvTotalHarga.getText().toString()+"\n";
        String ppn = "PPN "+"\t\tRp 0\n";
        String diskon = "Diskon \t\t"+tvDiskon.getText().toString()+"\n";
        String tunai = "Tunai \t\t"+tvTunai.getText().toString()+" "+uangTunai+"\n";
        String kembalian = "Kembali \t"+tvKembalian.getText().toString()+"\n";
        String dot4 = "--------------------------------\n";
        String tglTrans = tvTanggalDanNamaKasir.getText().toString()+"\n";
        String dot5 = "--------------------------------\n";
        String msg = tvInfo.getText().toString()+"\n\n\n";
        String dot6 = "--------------------------------\n";



        String printed = "\n"+geraiName + geraiAddress + noTelp
                + dot1 + tanggalLbl + tglLabel
                + nomorLbl + nomor + pelanggangLbl
                + pelanggan + dot2 + products + dot3
                + totalPrice + total + ppn
                + diskon + tunai + kembalian + dot4
                + tglTrans + dot5 + msg + dot6;
        Log.d(Cfg.TAG_COMMON, printed);
        return printed;

    }

    // this will find a bluetooth printer device
    void findBT() {

        try {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

            if(mBluetoothAdapter == null) {
                Toast.makeText(this, "Printer Bluetooth tidak tersedia", Toast.LENGTH_SHORT).show();
            }

            if(!mBluetoothAdapter.isEnabled()) {
                Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBluetooth, 0);
            }

            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

            if(pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {

                    // printer001 is the name of the bluetooth printer device
                    // we got this name from the list of paired devices
                    if (device.getName().equals("printer001")) {
                        mmDevice = device;
                        break;
                    }
                }
            }

            //myLabel.setText("Bluetooth device found.");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // tries to open a connection to the bluetooth printer device
    void openBT(){
        try {
            // Standard SerialPortService ID
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
            mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
            mmSocket.connect();
            mmOutputStream = mmSocket.getOutputStream();
            mmInputStream = mmSocket.getInputStream();
            beginListenForData();


            //myLabel.setText("Bluetooth Opened");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
 * after opening a connection to bluetooth printer device,
 * we have to listen and check if a data were sent to be printed.
 */
    void beginListenForData() {
        try {
            final Handler handler = new Handler();

            // this is the ASCII code for a newline character
            final byte delimiter = 10;

            stopWorker = false;
            readBufferPosition = 0;
            readBuffer = new byte[1024];

            workerThread = new Thread(new Runnable() {
                public void run() {

                    while (!Thread.currentThread().isInterrupted() && !stopWorker) {

                        try {

                            int bytesAvailable = mmInputStream.available();

                            if (bytesAvailable > 0) { // jumlah koneksi bluetooth tersedia

                                byte[] packetBytes = new byte[bytesAvailable];
                                mmInputStream.read(packetBytes);

                                for (int i = 0; i < bytesAvailable; i++) {

                                    byte b = packetBytes[i];
                                    if (b == delimiter) {

                                        byte[] encodedBytes = new byte[readBufferPosition];
                                        System.arraycopy(
                                                readBuffer, 0,
                                                encodedBytes, 0,
                                                encodedBytes.length
                                        );

                                        // specify US-ASCII encoding
                                        final String data = new String(encodedBytes, "US-ASCII");
                                        readBufferPosition = 0;

                                        // tell the user data were sent to bluetooth printer device
//                                        handler.post(new Runnable() {
//                                            public void run() {
//
//
//                                            }
//                                        });

                                    } else {
                                        readBuffer[readBufferPosition++] = b;
                                    }
                                }
                            }

                        } catch (IOException ex) {
                            stopWorker = true;
                        }

                    }
                }
            });

            workerThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // close the connection to bluetooth printer.
    void closeBT(){
        try {
            stopWorker = true;
            mmOutputStream.close();
            mmInputStream.close();
            mmSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // this will send text data to be printed by the bluetooth printer
    void sendData(){
        try {
            // the text typed by the user
            String msg = generateStruk();
            msg += "\n";
            mmOutputStream.write(msg.getBytes());
            Toast.makeText(this, "Struk tercetak", Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    
    private void goToPrevPage(){
        if(!isFromDetailTransaksi){
            Intent generalIntent = new Intent(ReceiptInvoice.this, LandingPage.class);
            generalIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(generalIntent);
        }else {
            Intent aktifitasIntent = new Intent(ReceiptInvoice.this, AktifitasHistoryActivity.class);
            aktifitasIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(aktifitasIntent);
        }

    }

    private void onComponent() {
        tvTanggalDanNamaKasir = (TextView) findViewById(R.id.tvTanggalDanNamaKasir);
        tvKembalian = (TextView) findViewById(R.id.tvKembalian);
        tvKontak = (TextView) findViewById(R.id.tvKontak);
        tvTanggalTransaksi = (TextView) findViewById(R.id.tvTanggalTransaksi);
        tvTransQueue = (TextView) findViewById(R.id.tvTransQueue);
        tvTunai = (TextView) findViewById(R.id.tvTunai);
        tvTotalHarga = (TextView) findViewById(R.id.tvTotalHarga);
        tvDiskon = (TextView) findViewById(R.id.tvDiskon);
        tvAlamatGerai = (TextView) findViewById(R.id.tvAlamatGerai);
        tvTeleponGerai = (TextView) findViewById(R.id.tvTeleponGerai);
        tvNamaGerai = (TextView) findViewById(R.id.tvNamaGerai);
        tvTotalItem = (TextView) findViewById(R.id.tvTotalItem);
        tvInfo = (TextView) findViewById(R.id.tvInfo);

        namaOutlet = getSecPref().getString(Cfg.prefsNamaOutlet_Str, "");
        alamatGerai = getSecPref().getString(Cfg.prefsAlamatOutlet_Str, "");
        teleponGerai = getSecPref().getString(Cfg.prefsTeleponOutlet_Str, "");
        kembalian = UnitConversion.format2Rupiah(getSecPref().getLong(Cfg.prefsKembalian_Long, 0));
        nomorTelepon = getSecPref().getString(Cfg.prefsTeleponKostumerStr, "").isEmpty() ? "" : " (" + getSecPref().getString(Cfg.prefsTeleponKostumerStr, null) + ")";
        kontak = ": " + getSecPref().getString(Cfg.prefsKostumerName_STR, Cfg.defaultKostumerName) + nomorTelepon;
        nomorTransaksi = ": " + getSecPref().getString(Cfg.prefsRecentTransIdStr, "");
        uangTunai = getSecPref().getString(Cfg.prefsTunaiStr, "");
        diskon = UnitConversion.format2Rupiah(Long.parseLong(getSecPref().getString(Cfg.prefsNegoDisc_Str, "0")));

        tvNamaGerai.setText(getSecPref().getString(Cfg.prefsNamaOutlet_Str, ""));
        tvAlamatGerai.setText(getSecPref().getString(Cfg.prefsAlamatOutlet_Str, ""));
        tvTeleponGerai.setText(getSecPref().getString(Cfg.prefsTeleponOutlet_Str, ""));

        //pelanggan:
        String nomorTelepon = getSecPref().getString(Cfg.prefsTeleponKostumerStr, "").isEmpty() ? "" : " (" + getSecPref().getString(Cfg.prefsTeleponKostumerStr, null) + ")";
        tvKontak.setText(": " +
                getSecPref().getString(Cfg.prefsKostumerName_STR, Cfg.defaultKostumerName) + nomorTelepon
        );

        //nomor:
        tvTransQueue.setText(": " + getSecPref().getString(Cfg.prefsRecentTransIdStr, ""));

    }


    //Untuk screenshot layar
    public static Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }

    public void store(Bitmap bm, String fileName) {
        final String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Screenshots";
        File dir = new File(dirPath);
        if (!dir.exists())
            dir.mkdirs();
        File file = new File(dirPath, fileName);
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG, 85, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    RecyclerView rvStruk;

    private void dummyAdapter() {
        rvStruk.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    ModelStruk modelStruk = null;

    public void getStrukInfo() {
            }

    void sendStruk() {
        List<id.hike.apps.android_mpos_mumu.features.receipt_invoice.model.ProdukItem> produkItems = new ArrayList<>();
        for (int i = 0; i < mm.size(); i++) {
            produkItems.add(new ProdukItem(mm.get(i).getNamaProduk(),
                    String.valueOf(mm.get(i).getJumlah()),
                    mm.get(i).getHarga()));
        }

    }

    @Override
    public void onBackPressed() {
        //nothing
    }
}
