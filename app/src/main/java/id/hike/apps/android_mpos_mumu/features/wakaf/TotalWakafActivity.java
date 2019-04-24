package id.hike.apps.android_mpos_mumu.features.wakaf;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.api.TransaksiApi;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.pin_verifikasi.InFrameWalletActivity;
import id.hike.apps.android_mpos_mumu.features.utils.UtilDialogActivity;
import id.hike.apps.android_mpos_mumu.model.Response;
import id.hike.apps.android_mpos_mumu.model.TrxWallet;
import id.hike.apps.android_mpos_mumu.model.WalletReqRes;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TotalWakafActivity extends BaseActivity {

    private Button btn_bayar_wakaf;
    private TextView txtWakafSubtotal;
    private TextView txtWakafTotal;
    private String idPelanggan = "-";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_wakaf);

        btn_bayar_wakaf = findViewById(R.id.btn_bayar_wakaf);
        txtWakafSubtotal = findViewById(R.id.txt_wakaf_subtotal);
        txtWakafTotal = findViewById(R.id.txt_wakaf_total);
        txtWakafSubtotal.setText(UnitConversion.format2Rupiah(
                getSecPref().getInt(Cfg.prefDonasiAmount, 0)));
        txtWakafTotal.setText(UnitConversion.format2Rupiah(
                getSecPref().getInt(Cfg.prefDonasiAmount, 0) + /*2500*/ 0));

        Bundle bundle = getIntent().getExtras();

        if(bundle != null && bundle.containsKey("idPelanggan")){
            idPelanggan = bundle.getString("idPelanggan");
        }

        btn_bayar_wakaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int saldo = getSecPref().getInt(Cfg.prefsWalletSaldo, 0);
                int number = getSecPref().getInt(Cfg.prefDonasiAmount, 0);
                if(saldo < number){
                    new AlertDialog.Builder(TotalWakafActivity.this)
                            .setMessage("Saldo anda tidak cukup untuk memproses transaksi")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                    finish();
                                }
                            }).show();
                }else{
                    ProgressDialog dialog = new ProgressDialog(TotalWakafActivity.this);
                    dialog.setMessage("Mohon Tunggu...");
                    dialog.setCancelable(false);
                    dialog.show();
                    TransaksiApi api = new TransaksiApi();
                    api.logging("501000", Cfg.gs_WakafInfo_KodeProduk, Cfg.gs_WakafInfo_Muzakki, txtWakafTotal.getText().toString())
                            .subscribe(new Observer<Response<TrxWallet>>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                }

                                @Override
                                public void onNext(Response<TrxWallet> trxWalletResponse) {
                                    dialog.dismiss();
                                    TrxWallet wallet = trxWalletResponse.getData();
                                    String rcode = wallet.getH39();
                                    String rcTEXT = wallet.getH62();
                                    if ((!rcode.equals("00")) && (!rcode.equals("68"))) {
                                        Intent intent_dialog = new Intent(TotalWakafActivity.this, UtilDialogActivity.class);
                                        intent_dialog.putExtra("text_Title", "Transaksi Wakaf");
                                        intent_dialog.putExtra("text_Header", "Transaksi Wakaf Tidak Berhasil.");
                                        intent_dialog.putExtra("text_Content", rcTEXT);
                                        startActivity(intent_dialog);
                                    } else {
                                        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                                        Random r = new Random();
                                        int i1 = r.nextInt(99999999 - 100) + 100;
                                        String ret = formatter.format(new Date());
                                        Intent intent = new Intent(TotalWakafActivity.this, InFrameWalletActivity.class);
                                        int number = getSecPref().getInt(Cfg.prefDonasiAmount, 0);
                                        WalletReqRes res = new WalletReqRes();
                                        String ls_billnumber = "";
                                        String ls_norekening = "";

                                        res.setInstitutionCode(wallet.getInstitution_code());
                                        res.setType("transfer");
                                        res.setAccountType("B2B");
                                        res.setProduct("4105");
                                        res.setAccount("0000000018");
                                        String ls_invoice_no;
                                        ls_invoice_no = "000000" + wallet.getSystem_trace_audit();
                                        ls_invoice_no = ls_invoice_no.substring(ls_invoice_no.length() -6);
                                        ls_invoice_no = wallet.getSystem_date_time() + ls_invoice_no;
                                        ls_norekening = getSecPref().getString(Cfg.prefsWalletRekening,"");
                                        ls_billnumber = ls_norekening;
                                        ls_billnumber = ls_billnumber + ";" + wallet.getInstitution_account();
                                        ls_billnumber = ls_billnumber + ";" + number;
                                        ls_billnumber = ls_billnumber + ";" + ls_invoice_no;
                                        ls_billnumber = ls_billnumber + ";" + Cfg.gs_WakafInfo_KodeProduk;
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
                                        Log.i("BOWOTAG", json);
                                        startActivity(intent);
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    dialog.dismiss();

                                    Toast.makeText(TotalWakafActivity.this, "Gagal mendapatkan wallet info", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }
            }
        });


    }
}
