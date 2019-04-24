package id.hike.apps.android_mpos_mumu.features.payment;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.pelanggan.ModelPelanggan;
import id.hike.apps.android_mpos_mumu.features.pelanggan.SvPelanggan;
import id.hike.apps.android_mpos_mumu.util.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Payment extends BaseActivity {

    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ((TextView)findViewById(R.id.abTvTitle)).setText("Metode Pembayaran");
        String tag = "f_payment";
        if (getIntent().getExtras()!=null) {
            String transType = getIntent().getExtras().getString(Cfg.transactionTypeKey);
            if(transType!=null && transType.equals(Cfg.pulsaTypeVal)){
                tag = Cfg.pulsaTypeVal;
            }
        }

        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.activity_payment,new F_Payment(),tag).commit();

        if (!getSecPref().getString(Cfg.prefsKostumerName_STR,Cfg.defaultKostumerName).equalsIgnoreCase(Cfg.defaultKostumerName)){
//            getCustomerDetail();
        } else {
            //nothing
        }

        tvUsername = (TextView) findViewById(R.id.tvUsername);
        btnBack= (ImageView) findViewById(R.id.btnBack);

    }

    void getCustomerDetail(){
        SvPelanggan svPelanggan= ApiClient.serviceGenerator(Cfg.BASEURL_IDENTITY).create(SvPelanggan.class);
        Call<ModelPelanggan> call=svPelanggan.searchPelanggan(getSecPref().getString(Cfg.prefsKostumerName_STR,null),
                getSecPref().getString(Cfg.prefsOutletId_STR,null));
        call.enqueue(new Callback<ModelPelanggan>() {
            @Override
            public void onResponse(Call<ModelPelanggan> call, Response<ModelPelanggan> response) {
                ModelPelanggan mm=response.body();
                F_Payment f_payment= (F_Payment) getSupportFragmentManager().findFragmentByTag("f_payment");
                if (mm.getData().get(0).getPhone()!=null){
                    f_payment.tvContact.setText(
                            getSecPref().getString(Cfg.prefsKostumerName_STR,Cfg.defaultKostumerName)+" ("+
                                    mm.getData().get(0).getPhone()+")"
                    );
                } else {
                    f_payment.tvContact.setText(
                            getSecPref().getString(Cfg.prefsKostumerName_STR,Cfg.defaultKostumerName)
                    );
                }
                getSecPref().edit().putString(Cfg.prefsTeleponKostumerStr,mm.getData().get(0).getPhone()).apply();
            }

            @Override
            public void onFailure(Call<ModelPelanggan> call, Throwable t) {
                Log.d(getClass().getSimpleName(),t.getCause().toString());
            }
        });
    }

    TextView tvUsername, tvLokasi;

    @Override
    public void onBackPressed() {
        //nothing. must pass from pres button
    }
}