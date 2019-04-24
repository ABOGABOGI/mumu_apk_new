package id.hike.apps.android_mpos_mumu.features.summary;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.api.TransaksiApi;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.base.CodePartingActivity;
import id.hike.apps.android_mpos_mumu.features.landing_page.AktivasiWalletActivity;
import id.hike.apps.android_mpos_mumu.features.pelanggan.DFTambahPelanggan;
import id.hike.apps.android_mpos_mumu.features.pelanggan.PelangganSearchResult;
import id.hike.apps.android_mpos_mumu.features.pin_verifikasi.InFrameWalletActivity;
import id.hike.apps.android_mpos_mumu.features.summary.adapter.RVAdapterListBelanja;
import id.hike.apps.android_mpos_mumu.features.summary.df.DfEditTotalDiskon;
import id.hike.apps.android_mpos_mumu.features.summary.df.DfEditTotalPayment;
import id.hike.apps.android_mpos_mumu.model.Produk;
import id.hike.apps.android_mpos_mumu.model.Response;
import id.hike.apps.android_mpos_mumu.model.TrxWallet;
import id.hike.apps.android_mpos_mumu.model.WalletReqRes;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SummaryActivity extends BaseActivity implements CodePartingActivity {

    RecyclerView rvListBelanja;
    TextView tvSubtotal;
    TextView tvPPN;
    TextView abvTitle;
    public TextView etTotal;
//    public EditText edDiskon;
    public TextView edDiskon;
    long totalPaymentAfterDisc = 0;
    String recentTransactionId = "";
    public AutoCompleteTextView actvCariPelanggan;
    ImageView btnTambahPelanggan;
    DFTambahPelanggan dfTambahPelanggan;
    LinearLayout layTambah;
    public RVAdapterListBelanja rvAdapterListBelanja;
    TextView tvUsername;
    public SummaryPresenter summaryPresenter;

    @BindView(R.id.totalBayar)
    TextView totalBayar;

    Boolean isPpob;

    String msidn;
    String kdProduk;
    String total;
    private String idPelanggan = "-";

    private Produk produk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_summary);
        ButterKnife.bind(this);


        boolean isWalletActive = getSecPref().getBoolean(Cfg.prefsWalletEnabled, false);

        if(isWalletActive){
            partFindComponent();
        }else{

            new AlertDialog.Builder(this)
                    .setMessage("Wallet belum aktif, anda ingin aktifkan wallet sekarang?")
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();


                            Intent intent = new Intent(SummaryActivity.this, AktivasiWalletActivity.class);
                            startActivity(intent);

                            finish();

                        }
                    }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            }).show();

        }



        /*partFirstInit();
        partComponentEvent();*/
    }

    @Override
    public void partFindComponent() {
        recentTransactionId = getSecPref().getString(Cfg.prefsRecentTransIdStr, null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvListBelanja = (RecyclerView) findViewById(R.id.rvListBelanja);
        rvListBelanja.setLayoutManager(layoutManager);
        rvListBelanja.setItemAnimator(new DefaultItemAnimator());
        dummyAdapter();
        actvCariPelanggan = (AutoCompleteTextView) findViewById(R.id.actvCariPelanggan);
        btnTambahPelanggan = (ImageView) findViewById(R.id.btnTambahPelanggan);
        tvSubtotal = (TextView) findViewById(R.id.tvSubtotal);
        etTotal = (TextView) findViewById(R.id.etTotal);
        tvPPN = (TextView) findViewById(R.id.tvPPN);
        edDiskon = (TextView) findViewById(R.id.edDiskon);
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        dfTambahPelanggan = new DFTambahPelanggan();
        layTambah= (LinearLayout) findViewById(R.id.layTambah);
        abvTitle = (TextView) findViewById(R.id.abTvTitle);
        abvTitle.setText("SummaryActivity Pembayaran");


        Bundle bundle = getIntent().getExtras();

        if(bundle != null && bundle.containsKey("produk")){
            msidn = getIntent().getExtras().getString("msidn");
            kdProduk = getIntent().getExtras().getString("kdProduk");

            produk = (Produk) getIntent().getExtras().getSerializable("produk");


            etTotal.setText("Rp. " + UnitConversion.format2Rupiah2(produk.getHarga()));
            tvSubtotal.setText("Rp. " + UnitConversion.format2Rupiah2(produk.getHarga()));
        }

        if(bundle != null && bundle.containsKey("idPelanggan")){
            idPelanggan = bundle.getString("idPelanggan");
        }


    }


    @Override
    public void partComponentEvent() {
        ((ImageView)findViewById(R.id.btnBack)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layTambah.performClick();
            }
        });

        actvCariPelanggan.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Intent intent = new Intent(SummaryActivity.this, PelangganSearchResult.class);
                    intent.putExtra("namaPelanggan", actvCariPelanggan.getText().toString());
                    intent.putExtra("origin", "summaryActivity");
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        /*edDiskon.addTextChangedListener(summaryPresenter.etDiskonTextwatcher);

        edDiskon.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (edDiskon.getText().toString().isEmpty()){
                        edDiskon.setText("0");
                        edDiskon.setSelection(edDiskon.getText().length());
                    }
                    hideKeyInput(edDiskon);
                    return true;
                }
                return false;
            }
        });*/

        edDiskon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DfEditTotalDiskon dfEditTotalDiskon=new DfEditTotalDiskon();
                String tag = "";
                if(isPpob){
                    tag = Cfg.ppobCategory;
                }
                dfEditTotalDiskon.show(getSupportFragmentManager(),tag);
            }
        });

        if (actvCariPelanggan.length() == 0){
            btnTambahPelanggan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dfTambahPelanggan.show(getSupportFragmentManager(), "summaryActivity");

                }
            });
        } else {
            btnTambahPelanggan.setImageDrawable(getResources().getDrawable(R.drawable.ic_close));
            btnTambahPelanggan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    actvCariPelanggan.setText("");
                    getSecPref().edit().remove(Cfg.prefsKostumerName_STR).commit();
                    getSecPref().edit().remove(Cfg.prefsTeleponKostumerStr).commit();
                    actvCariPelanggan.setEnabled(true);
                    btnTambahPelanggan.setImageDrawable(getResources().getDrawable(R.drawable.ic_plus));
                    btnTambahPelanggan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dfTambahPelanggan.show(getSupportFragmentManager(), "summaryActivity");
                        }
                    });
                }
            });
        }

        etTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DfEditTotalPayment dfEditTotalPayment=new DfEditTotalPayment();
                String tag = "";
                if(isPpob){
                    tag = Cfg.ppobCategory;
                }
                dfEditTotalPayment.show(getSupportFragmentManager(),tag);
            }
        });
    }

    @Override
    public void partFirstInit() {

    }

    @OnClick({R.id.ivBatal, R.id.layBatal, R.id.tvBatal})
    public void batalBayar(View view) {
        /*if(isPpob){
            summaryPresenter.batalBayarPpob();
        }else{
            summaryPresenter.batalBayar();
        }*/
        finish();

    }

    @OnClick({R.id.ivTambah, R.id.layTambah, R.id.tvTambah})
    public void tambahProduk(View view) {
        summaryPresenter.tambahProduk();
    }


    @OnClick({R.id.ivBayar, R.id.layBayar})
    public void bayarTransaksi(View view) {

        int saldo = getSecPref().getInt(Cfg.prefsWalletSaldo, 0);

        if(saldo < produk.getHarga()){

            new AlertDialog.Builder(this)
                    .setMessage("Saldo anda tidak cukup untuk memproses transaksi")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                            finish();
                        }
                    }).show();


        }else{

            String kodeProduk = TextUtils.isEmpty(produk.getKode_produk()) ? "takterdeteksi":produk.getKode_produk();
            String kodeTransaksi = "501000";

            ProgressDialog dialog = new ProgressDialog(this);

            dialog.setMessage("Mohon Tunggu...");
            dialog.setCancelable(false);

            dialog.show();

            TransaksiApi api = new TransaksiApi();

            api.logging(kodeTransaksi, kodeProduk, idPelanggan, String.valueOf(produk.getHarga()))
                    .subscribe(new Observer<Response<TrxWallet>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Response<TrxWallet> trxWalletResponse) {

                            TrxWallet wallet = trxWalletResponse.getData();

                            dialog.dismiss();

                            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

                            Random r = new Random();
                            int i1 = r.nextInt(99999999 - 100) + 100;

                            String ret = formatter.format(new Date());

                            Intent intent = new Intent(SummaryActivity.this, InFrameWalletActivity.class);



                            WalletReqRes res = new WalletReqRes();
                            res.setType("transfer");
                            res.setAccountType("B2B");
                            res.setAccount("0000000018");
                            res.setInstitutionCode(wallet.getInstitution_code());
                            res.setProduct("4105");

                            String ls_billnumber;
                            String ls_invoice_no;
                            ls_invoice_no = "000000" + wallet.getSystem_trace_audit();
                            ls_invoice_no = ls_invoice_no.substring(ls_invoice_no.length() -6);
                            ls_invoice_no = wallet.getSystem_date_time() + ls_invoice_no;

                            ls_billnumber = getSecPref().getString(Cfg.prefsWalletRekening,"");
                            ls_billnumber = ls_billnumber + ";" + wallet.getInstitution_account();
                            ls_billnumber = ls_billnumber + ";" +  produk.getHarga();
                            ls_billnumber = ls_billnumber + ";" + ls_invoice_no;
                            ls_billnumber = ls_billnumber + ";" + kodeProduk;
                            ls_billnumber = ls_billnumber + ";" + idPelanggan;
                            res.setBillNumber(ls_billnumber);
                            res.setTrxId(wallet.getSystem_trace_audit());
                            res.setRetrieval(wallet.getSystem_date_time());
                            res.setTrxCode(wallet.getTrx_code());
                            res.setSign("93190f8cfdf6398f487116e6ad0e879e");
                            intent.putExtra("info", res);
                            intent.putExtra("title","Pin Wallet");

                            Gson gson = new Gson();

                            String json = gson.toJson(res);

                            Log.i("DEBUGTAG", json);

                            startActivity(intent);

                        }

                        @Override
                        public void onError(Throwable e) {
                            dialog.dismiss();

                            Toast.makeText(SummaryActivity.this, "Gagal mendapatkan wallet info", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });


        }



    }

    private void dummyAdapter() {
        rvListBelanja.setAdapter(new RecyclerView.Adapter() {
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

    @Override    public void onBackPressed() {
        //nothing
    }

    public void hideKeyInput(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    @Override
    protected void onDestroy() {
        summaryPresenter=null;
        super.onDestroy();
    }
}
