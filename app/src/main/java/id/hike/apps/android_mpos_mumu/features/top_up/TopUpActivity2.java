package id.hike.apps.android_mpos_mumu.features.top_up;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.landing_page.LandingPage;
import id.hike.apps.android_mpos_mumu.features.landing_page.df.WalletApi;
import id.hike.apps.android_mpos_mumu.features.top_up.model.TopUpResponse;
import id.hike.apps.android_mpos_mumu.features.top_up.model.TransferInfo;
import id.hike.apps.android_mpos_mumu.features.utils.UtilDialogActivity;
import id.hike.apps.android_mpos_mumu.model.TopupModel;
import id.hike.apps.android_mpos_mumu.util.ApiClient;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopUpActivity2 extends BaseActivity {
    private static final String LOG = "TopUpActivity2";
    ImageView btnCopy,btnCopyRek;
    Button btn_mengerti;
    TextView txt_nominal,txt_nominal_rek, txtTransferAmount;
    ConstraintLayout mainLayout;

    private int ammountValue = 0;

    @BindView(R.id.expireDate)
    TextView expireDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_topup2);

        // di-hide dulu.... kalo manggil webservicenya berhasil baru di show....
        mainLayout = findViewById(R.id.mainLayout);
        mainLayout.setVisibility(View.GONE);

        ButterKnife.bind(this);


        Bundle bundle = getIntent().getExtras();



        btn_mengerti = findViewById(R.id.btn_mengerti);
        btn_mengerti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToHome = new Intent(TopUpActivity2.this, LandingPage.class);
                goToHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(goToHome);
                finish();
            }
        });

        final ClipboardManager myClipBoard;
        myClipBoard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

        txt_nominal = findViewById(R.id.topup2_textview_transfer_amount);
        btnCopy = findViewById(R.id.btn_copy);
        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = txt_nominal.getText().toString();

                ClipData clipData = ClipData.newPlainText("text",text);
                myClipBoard.setPrimaryClip(clipData);

                Toast.makeText(TopUpActivity2.this,"Nominal Tersalin",Toast.LENGTH_SHORT).show();

            }
        });

        txt_nominal_rek = findViewById(R.id.txt_rek);
        btnCopyRek = findViewById(R.id.btn_copy_rek);
        btnCopyRek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = txt_nominal_rek.getText().toString();

                ClipData clipData = ClipData.newPlainText("tex",text);
                myClipBoard.setPrimaryClip(clipData);

                Toast.makeText(TopUpActivity2.this,"Rekening Tersalin", Toast.LENGTH_SHORT).show();
            }
        });

        txtTransferAmount = (TextView) findViewById(R.id.topup2_textview_transfer_amount);

        if(bundle != null && bundle.containsKey("topUpAmount")){


            Log.i("BOWOTAG", String.valueOf(bundle.getInt("topUpAmount")));

            ammountValue = bundle.getInt("topUpAmount");
            refreshDompet();
        }

        //updateTransferAmount();
    }

    private void refreshDompet(){


//        if(ammountValue != 0){

            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setMessage("Mohon tunggu...");
            dialog.setCancelable(false);
            dialog.show();
            Log.i("DEBUGTAG", "refreshDompet");

            WalletApi.create().topup(String.valueOf(ammountValue))
                .subscribe(new Observer<id.hike.apps.android_mpos_mumu.model.Response<TopupModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(id.hike.apps.android_mpos_mumu.model.Response<TopupModel> topupModelResponse) {
                        Log.i("DEBUGTAG", "refreshDompet.onNext1");
                        dialog.dismiss();


                        TopupModel topupModel = topupModelResponse.getData();

                        String rupiah = UnitConversion.format2Rupiah2(topupModel.getNilai_transfer());

                        Log.i("DEBUGTAG", "refreshDompet.onNext");
                        Log.i("DEBUTAG", rupiah);

                        txtTransferAmount.setText("Rp. " + rupiah);

                        expireDate.setText("Batas Akhir " + topupModel.getExpired_date());
                        mainLayout = findViewById(R.id.mainLayout);
                        mainLayout.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss();
                        //Toast.makeText(TopUpActivity2.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        Log.i("BOWOTAG", e.getLocalizedMessage());
                        Log.i("DEBUGTAG", "refreshDompet.onError");
                        finish();
                        Intent intent_dialog = new Intent(TopUpActivity2.this, UtilDialogActivity.class);
                        intent_dialog.putExtra("text_Title", "Permintaan TopUp Saldo");
                        intent_dialog.putExtra("text_Header", "Permintaan TopUp Saldo Tidak Berhasil.");
                        intent_dialog.putExtra("text_Content", "Terjadi Gangguan Service TopUP.");
                        startActivity(intent_dialog);
                    }

                    @Override
                    public void onComplete() {
                        Log.i("DEBUGTAG", "refreshDompet.onComplete");
                        dialog.dismiss();
                    }
                });

//        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent killAny = new Intent(this,LandingPage.class);
        killAny.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(killAny);
        finish();
    }

    private void updateTransferAmount() {
        int topUpAmount = getIntent().getIntExtra("topUpAmount", 0);
        initiateTopUpRequest(topUpAmount, "1200008844453");
    }

    private void initiateTopUpRequest(final int amount, final String accountDest) {
        final int[] count = new int[]{0};
        TopUpService service = ApiClient.serviceGenerator(Cfg.BASEURL_EWALLET).create(TopUpService.class);
        Log.i("DEBUGTAG","ERROR TopUpActivity2.java : before try");

        try{
            Log.i("DEBUGTAG","ERROR TopUpActivity2.java : before call");
            Call<TopUpResponse> call = service.initiateTopUpRequest(
                    "Bearer " + getSecPref().getString(Cfg.oauthAccessToken, ""),
                    new TransferInfo(amount, accountDest)
            );
            Log.i("DEBUGTAG","ERROR TopUpActivity2.java : before call response");
            call.enqueue(new Callback<TopUpResponse>() {
                @Override
                public void onResponse(Call<TopUpResponse> call, Response<TopUpResponse> response) {
                    if (response.isSuccessful()) {
                        try {
                            TopUpResponse topUpResponse = response.body();
                            int topUpTransfer = response.body().getNominaltrf();
                            NumberFormat nf = NumberFormat.getInstance(new Locale("id", "ID"));
                            txtTransferAmount.setText("Rp " + nf.format(topUpTransfer));
                        } catch (Exception ex2) {
                            Log.e(LOG, ex2.getMessage());
                            Log.i("DEBUGTAG","ERROR TopUpActivity2.java : 2");
                            Log.i("DEBUGTAG",ex2.getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Call<TopUpResponse> call, Throwable t) {
                    Log.e(LOG, t.getMessage(), t);
                    if (t instanceof IOException) {
                    }
                }
            });
            Log.i("DEBUGTAG","ERROR TopUpActivity2.java : x");

        }catch(Exception ex){
            Log.e(LOG, ex.getMessage(), ex);
            Log.i("DEBUGTAG","ERROR TopUpActivity2.java : 1");
            Log.i("DEBUGTAG",ex.getMessage());
        }
        Log.i("DEBUGTAG","ERROR TopUpActivity2.java : xx");
    }

}
