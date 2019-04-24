package id.hike.apps.android_mpos_mumu.features.payment.df;

import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseDialogFragment;
import id.hike.apps.android_mpos_mumu.features.payment.F_Payment;
import id.hike.apps.android_mpos_mumu.features.payment.Payment;
import id.hike.apps.android_mpos_mumu.util.MCastleClass;
import pl.droidsonroids.gif.GifTextView;
import swi.swilibrary.android.bluetoothchat.BluetoothChatService;
import swi.swilibrary.android.bluetoothchat.Constants;
import swi.swilibrary.tools.Konversi;
import swi.swilibrary.tools.Misc;
import swi.swilibrary.tools.MutualAuthentic;
import swi.swilibrary.tools.StringTools;
import swi.swilibrary.trx.Process;
import tw.com.castech.ctpaylibrary.CastlesClass;

import static swi.swilibrary.android.bluetoothchat.Constants.BCD_LENGTH_FMT;
import static swi.swilibrary.android.bluetoothchat.Constants.BT_CONN;
import static swi.swilibrary.android.bluetoothchat.Constants.TCP_CONN;
import static swi.swilibrary.trx.TagList.TAG_MUTUAL;
import static swi.swilibrary.trx.TagList.TAG_SALE;

/**
 * Created by root on 07/09/17.
 */

public class DfDebitCard extends BaseDialogFragment {
    CastlesClass ctpayAPI;
    static boolean isConnBluetooth = false;
    static ImageView imgBtnDevices;
    static boolean isTrigPay = false;
    private BluetoothAdapter mBluetoothAdapter = null;
    Button btnDebitBayar, btnDebitKonfirmasi, btnBatal;
    TextView tvSuggest;
    Payment payment;
    F_Payment fPayment;

    public static final byte TAGTRX = TAG_SALE;
    private Process oMyProcess;

    private String amount;
    private MutualAuthentic NewMutualAuthentic;

    private static Handler mHandler = null;
    private SharedPreferences sp;

    private LinearLayout linConnectionStatus;
    private TextView tvConnectionStatus;
    private GifTextView gifDot;

    private final String TAG = "transaksiDebit";



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.TransactionListDialog);
        sp = PreferenceManager.getDefaultSharedPreferences(getContext());

        // hardcode shared preference connect bluetooth
        sp.edit().putString("IP", "10.32.3.7").apply(); // ip server
        sp.edit().putString("PORT", "1300").apply(); // port server
        sp.edit().putString("MAC", "MPOS-64998245").apply(); // EDC name
        sp.edit().putInt("HOSTLEN", BCD_LENGTH_FMT).apply(); /// bcd


        View view = getActivity().getLayoutInflater().inflate(R.layout.df_debit, null);
        amount = String.valueOf(fPayment.totalHarga);
        linConnectionStatus = (LinearLayout)view.findViewById(R.id.linConnectionStatus);
        tvConnectionStatus = (TextView) view.findViewById(R.id.tvConnectionStatus);
        gifDot = (GifTextView)view.findViewById(R.id.gifDot);

        btnBatal = (Button) view.findViewById(R.id.btnBatal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnDebitBayar = (Button) view.findViewById(R.id.btnDebitBayar);
        btnDebitBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBayar();
            }
        });

        tvSuggest= (TextView) view.findViewById(R.id.tvSuggest);

        btnDebitKonfirmasi = (Button) view.findViewById(R.id.btnDebitKonfirmasi);
        btnDebitKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goConfirmBayar();
            }
        });

        // Get local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not supported
        if (mBluetoothAdapter == null) {
            Toast.makeText(getActivity(), getString(R.string.bluetooth_tidak_tersedia), Toast.LENGTH_LONG).show();
        }

        oMyProcess = new Process(TAGTRX);
        oMyProcess.Host_lenISO_mode = sp.getInt("HOSTLEN", 1);
        initHandler();

        builder.setView(view);
        initBtAndTcp();
        return builder.create();
    }

    int ret;

    //=======================================CARD READER=====================================
    void connectCardReader() {

        if (!isConnBluetooth) {
            // Kalo konek
            Toast.makeText(getActivity(), getString(R.string.bluetooth_connecting),
                    Toast.LENGTH_LONG).show();

            ctpayAPI= MCastleClass.getInstance();
            ret = ctpayAPI.ctpConnectBluetoothDevice("MP200-0566");//TODO sul : hardcode dulu , should from database
            if (ret == Cfg.BT_RETURN_SUCCESS || ret == Cfg.BT_ALREADY_CONNECT) {
                isConnBluetooth = true;
            } else {
                Toast.makeText(getActivity(), getString(R.string.pastikan_bluetooth_dan_cardreader_menyala),
                        Toast.LENGTH_LONG).show();
            }
        } else {
            // Kalo tidak konek
            Toast.makeText(getActivity(), getString(R.string.bluetooth_disconnecting),
                    Toast.LENGTH_LONG).show();
//            ret = ctpayAPI.ctpDisconnectDevice();

//            onStart();
           /* if (ret == Cfg.BT_RETURN_SUCCESS || ret == Cfg.BT_ALREADY_CONNECT) {
                btnDebitBayar.setEnabled(true);
                btnDebitBayar.setBackgroundResource(R.drawable.btn_style);


                isConnBluetooth = false;
                *//*((Payment)getActivity()).btnFindDevice.setImageResource(R.drawable.ic_icon_card_reader_on);
                ((Payment)getActivity()).btnFindDevice.setColorFilter(ContextCompat.getColor(getActivity(), R.color.md_white_1000), android.graphics.PorterDuff.Mode.MULTIPLY);*//*
            } else {
                ctpayAPI.ctpDisconnectDevice();
                Toast.makeText(getActivity(), "failed to disconnect",
                        Toast.LENGTH_LONG).show();
            }*/
        }
    };

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(Cfg.TAG_COMMON, "onStart");
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.d(Cfg.TAG_COMMON, "onStop");

    }

    @Override
    public void onDestroy() {
        oMyProcess.StopBT();
        Log.d(Cfg.TAG_COMMON, "onDestroy");
        super.onDestroy();
    }

    private void initBtAndTcp(){
        String namaBT = sp.getString("MAC", "");
        Log.e(Cfg.TAG_COMMON, "nama bluetooth = "+ namaBT);
        Boolean isBayarEnabled = true;

        // Initialize the BluetoothChatService to perform bluetooth connections
        if (!oMyProcess.initBT(getContext(), mHandler)){
            Toast.makeText(getContext(), "menghubungkan bluetooth gagal. tidak terhubung dengan : "+namaBT, Toast.LENGTH_SHORT).show();
            isBayarEnabled = false;
        } else {
            showSuccessToast("menghubungkan bluetooth", true);
        }

        // Initialize the TCP to perform connection to Host
        try{
            oMyProcess.initTCP(getContext(), mHandler);
        } catch (Exception ex){
            Log.e(Cfg.TAG_COMMON, ex.getLocalizedMessage());
            showSuccessToast("menghubungkan ke server", false);
            isBayarEnabled = false;
        }

        if(isBayarEnabled){
            enableBtnBayar();
        } else {
            disableBtnBayar();
        }
    }


    private void initHandler(){
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                byte[] dataToSend;
                byte trxTag;
                byte subTrxTag;
                String stReceive;

                switch (msg.what) {
                    case Constants.MESSAGE_STATE_CHANGE:
                        switch (msg.arg1) {
                            case BluetoothChatService.STATE_CONNECTING:
                                break;
                            case BluetoothChatService.STATE_LISTEN:
                            case BluetoothChatService.STATE_NONE:
                                break;
                        }
                        break;
                    case Constants.MESSAGE_WRITE: // nulis informasi ke edc
                        byte[] writeBuf = (byte[]) msg.obj;

                        String stData = Konversi.bin2hex(writeBuf, writeBuf.length);
                        Log.d(TAG, "Constants.MESSAGE_WRITE" + stData);
                        break;

                    case Constants.MESSAGE_MUTUAL_OK:
                        Log.d(TAG, "Constants.MESSAGE_MUTUAL_OK");
                        oMyProcess.mutualOK(amount);
                        break;
                    case Constants.MESSAGE_READ: // nerima informasi dari edc

                        if (oMyProcess.SessionTrx <= 0)
                            break;

                        byte[] readBuf = (byte[]) msg.obj;
                        trxTag = readBuf[2];
                        subTrxTag = readBuf[3];

                        stReceive = Konversi.bin2hex(readBuf, readBuf.length);
                        Log.d(TAG, "Data Receive : " + stReceive);

                        switch (oMyProcess.SessionTrx) {
                            case 1:
                                if (trxTag != TAG_MUTUAL)
                                {
                                    Log.d(TAG, "Not Mutual event");
                                    break;
                                }

                                if (subTrxTag == 0x02) {
                                    try {
                                        dataToSend = NewMutualAuthentic.process_3(readBuf);
                                        oMyProcess.sendData(dataToSend, BT_CONN);
                                        NewMutualAuthentic.set_key_Session();
                                    } catch (Exception x) {
                                        oMyProcess.SessionTrx = 0;
                                        Log.d(TAG, "Failed Mutual Process 3 : " + x.getMessage());
                                    }

                                    mHandler.obtainMessage(Constants.MESSAGE_MUTUAL_OK)
                                            .sendToTarget();

                                    break;
                                }
                            case 2:

                                if (trxTag != TAGTRX) {
                                    Log.d(TAG, "Not Sale transaction");
                                    break;
                                }

                                if (subTrxTag == 0xff) {
                                    dismiss();
                                    break;
                                }

                                // Decrypt data
                                try{
                                    byte [] tmp = oMyProcess.DecryptMsgWithMutual(readBuf, NewMutualAuthentic.keySession);//newDes.decrypt(dataEncrypted);
                                    byte [] dataPlain = oMyProcess.DataToHost(tmp);
                                    oMyProcess.sendData(dataPlain, TCP_CONN);
                                }
                                catch(Exception ex){
                                    Log.d(TAG, "exception  : " + ex.getMessage());
                                    Toast.makeText(getContext(), "error karena : "+ex.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    dismiss();
                                }

                                break;
                        }

                        break;

                    case Constants.MESSAGE_READ_TCP:
                        Log.d(TAG, "Handler MESSAGE_READ_TCP ");
                        byte[] buf = (byte[]) msg.obj;
                        int lenReceive = (int) msg.arg1;

                        try {
                            /*Change len format */
                            stReceive = oMyProcess.DataFromHost(buf, lenReceive);
                            Log.d(TAG, "Data Receive from host : " + stReceive);

                            //encrypt data from host
                            String tmp = oMyProcess.EncryptMsgWithMutual(stReceive, NewMutualAuthentic.keySession);  //  newDes.encrypt(stReceive, true);
                            Log.d(TAG, "Encrypt data from host : " + tmp);

                            try {
                                //Create TLV
                                byte[] tmp2 = Konversi.hex2bin(tmp);

                                Misc oMisc = new Misc();
                                byte[] tlv = oMisc.createTLV((byte) TAGTRX, (byte) 0x01, tmp2.length, tmp2);
                                int lenTlv = tlv.length;

                                byte[] x = oMisc.createTLVPlusLength(lenTlv, tlv);
                                Log.d(TAG, "dataTosend to BT : " + Konversi.bin2hex(x,x.length));
                                oMyProcess.sendData(x, BT_CONN);
                            } catch (Exception ex) {
                                Log.d(TAG, "Constants.MESSAGE_READ_TCP  ex: " + ex.getMessage());
                            }

                        }
                        catch(Exception ex)
                        {
                            Log.d(TAG, "exception  : " + ex.getLocalizedMessage());
                            dismiss();
                        }

                        break;

                }
            }
        };
    }

    public void goBayar() {
        disableBtnBayar();
        oMyProcess.SessionTrx = 0;
        Log.d(Cfg.TAG_COMMON, "mProcessSale  : ");
        if (amount.length() <= 0)
        {
            Log.d(Cfg.TAG_COMMON, "mProcessSale, Please Input Amount");
            Toast.makeText(getContext(), "Please Input Amount!", Toast.LENGTH_SHORT).show();
            return;
        }

        amount = StringTools.padLeft(amount,12,'0');
        Log.d(Cfg.TAG_COMMON, "mProcessSale, amount : " + amount);

        startProcess();

//        if (!isTrigPay) {
//            ctpayAPI= MCastleClass.getInstance();
//            ret=ctpayAPI.ctpGetStatus();
//
//            long amount = fPayment.totalHarga * 100;//total pembayaran x 100
//            if (ret == Cfg.BT_RETURN_SUCCESS||ret == Cfg.BT_ALREADY_CONNECT) {
//
//                byte[] pbInTlvListData1 = {(byte) 0xDF, (byte) 0xBE, (byte) 0x1A, (byte) 0x02, (byte) 0x52, (byte) 0x70, //rupiah
//                        (byte) 0xDF, (byte) 0xAE, (byte) 0x10, (byte) 0x01, (byte) 0x00, //hilangkan decimal
//                        (byte) 0xDF, (byte) 0xAE, (byte) 0x11, (byte) 0x01, (byte) 0x02}; //thousand sparator
//                short usInTlvListLen1 = (short) pbInTlvListData1.length;
//                ctpayAPI.ctpWriteData(usInTlvListLen1, pbInTlvListData1);
//
//                ret = ctpayAPI.ctpTriggerPayment(ctpayAPI.TXN_TYPE_SALE, amount, false);
//
//                if (ret == Cfg.BT_RETURN_SUCCESS) {
//                    isTrigPay = true;
//                    btnDebitKonfirmasi.setEnabled(true);
//                    btnDebitKonfirmasi.setBackgroundResource(R.drawable.btn_style);
//                    tvSuggest.setVisibility(View.VISIBLE);
//                    btnDebitBayar.setText("Batal");
//                }
//            }
//        } else {
//
//            int ret = ctpayAPI.ctpCancelPayment();
//            if (ret == Cfg.BT_RETURN_SUCCESS) {
//                isTrigPay = false;
//                tvSuggest.setVisibility(View.GONE);
//                btnDebitBayar.setText("Bayar");
//
//                btnDebitKonfirmasi.setEnabled(false);
//                btnDebitKonfirmasi.setBackgroundResource(R.drawable.darkgray_button);
//
//                isConnBluetooth = false;
//            }
//        }
    }

    public void goConfirmBayar() {
        String msg = "Continue Payment ";

        byte[] tlvListData = {(byte) 0xDF, (byte) 0xAE, (byte) 0x5A};
        byte[] data = ctpayAPI.ctpReadData(tlvListData.length, tlvListData);
        if (data != null) {
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < data.length; i++) {
                int halfbyte = (data[i] >>> 4) & 0x0F;
                int two_halfs = 0;
                do {
                    if ((0 <= halfbyte) && (halfbyte <= 9))
                        buf.append((char) ('0' + halfbyte));
                    else
                        buf.append((char) ('a' + (halfbyte - 10)));
                    halfbyte = data[i] & 0x0F;
                } while (two_halfs++ < 1);
            }
            Log.d("Ctpay", "return: " + buf.toString());
        } else {
            Log.d("Ctpay", "data null");
        }

        byte callBackValue = ctpayAPI.ctpCbTxnResult();

        byte[] pbInTlvListData = {(byte) 0x8A, (byte) 0x02, (byte) 0x30, (byte) 0x30};
        short usInTlvListLen = (short) pbInTlvListData.length;


        int ret = ctpayAPI.ctpContinuePayment(usInTlvListLen, pbInTlvListData);

        if (ret == Cfg.BT_RETURN_SUCCESS||ret == Cfg.BT_ALREADY_CONNECT) {

            msg += Byte.toString(callBackValue);

            isTrigPay = false;

            tvSuggest.setVisibility(View.GONE);
            btnDebitBayar.setText("Bayar");
            btnDebitKonfirmasi.setEnabled(false);
            btnDebitKonfirmasi.setBackgroundResource(R.drawable.darkgray_button);

            fPayment.getExistPaymentType(Cfg.PAYMENT_METHOD_DEBIT);
            isConnBluetooth = false;
            fPayment.paidTrans(Cfg.PAYMENT_METHOD_DEBIT);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        payment = ((Payment) activity);
        fPayment = (F_Payment) payment.getSupportFragmentManager().findFragmentById(R.id.activity_payment);
    }

    private void showProgressInThread(final String msg, final boolean isGone){
        if(getActivity()!=null && tvConnectionStatus!=null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvConnectionStatus.setText(msg);
                    if(isGone){
                        gifDot.setVisibility(View.GONE);
                    } else {
                        gifDot.setVisibility(View.VISIBLE);
                    }
                }
            });

        }
    }

    private void startProcess()
    {
        linConnectionStatus.setVisibility(View.VISIBLE);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                showProgressInThread("Menghubungkan bluetooth", false);
                if(!oMyProcess.ConnectedBT(30)){
                    showProgressInThread("Gagal menghubungkan bluetooth", true);
                    return;
                } else {
                    showProgressInThread("Berhasil menghubungkan Bluetooth", true);
                }
                NewMutualAuthentic = new MutualAuthentic();

                showProgressInThread("Menghubungkan ke server", false);
                if(!oMyProcess.ConnectedTCP()){ // 10 detik
                    showProgressInThread("Gagal menghubungkan ke server", true);
                } else {
                    showProgressInThread("Berhasil menghubungkan ke server", true);
                    try {
                        byte [] dataToSend = NewMutualAuthentic.process_1();
                        /*send dataToSend Via blueTooth and wait receive from EDC */
                        oMyProcess.SessionTrx = 1;
                        oMyProcess.sendData(dataToSend, BT_CONN);
                    }
                    catch (Exception x)
                    {
                        Log.e(Cfg.TAG_COMMON, x.getLocalizedMessage());
                        oMyProcess.SessionTrx = 0;
                    }
                }
            }
        });

    }

    private void showSuccessToast(String process, boolean isSuccess){
        if(isSuccess){
            Toast.makeText(getContext(), process+" berhasil", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), process+" gagal", Toast.LENGTH_SHORT).show();
        }

    }


    private void disableBtnBayar(){
        btnDebitBayar.setEnabled(false);
        btnDebitBayar.setBackgroundResource(R.drawable.darkgray_button);
    }

    private void enableBtnBayar(){
        btnDebitBayar.setEnabled(true);
        btnDebitBayar.setBackgroundResource(R.drawable.btn_style);

    }


}


