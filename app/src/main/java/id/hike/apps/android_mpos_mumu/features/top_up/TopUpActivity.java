package id.hike.apps.android_mpos_mumu.features.top_up;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.BuildConfig;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.landing_page.LandingPage;
import id.hike.apps.android_mpos_mumu.model.WalletInfo;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;

public class TopUpActivity extends BaseActivity {
    private static final String TAG = "TopUpActivity";

    private Button btn_topUp;
    private ImageView btn_back;
    private EditText edtTopUpAmount;
    private WalletInfo info;

    @BindView(R.id.saldoCurrent)
    TextView saldoCurrent;

    @BindView(R.id.textView34)
    TextView dompetName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_topup);

        ButterKnife.bind(this);

        btn_topUp = findViewById(R.id.topup_btn1);
        btn_back = findViewById(R.id.btn_back_topUp);
        edtTopUpAmount = findViewById(R.id.edtTopUpAmount);

        Bundle bundle = getIntent().getExtras();

        dompetName.setText(getString(R.string.dompet_name, BuildConfig.FLAVOR.toUpperCase()));


        if(bundle != null && bundle.containsKey("wallet")){
            info = (WalletInfo) bundle.getSerializable("wallet");

            saldoCurrent.setText("Rp. " + UnitConversion.format2Rupiah2(info.getSaldo()));
        }

        btn_topUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int value = 0;

                try{
                    value = Integer.parseInt(edtTopUpAmount.getText().toString());
                }catch(Exception e){
                    e.printStackTrace();
                }


                if(value == 0){
                    Toast.makeText(TopUpActivity.this, "Isi nominal terlebih dahulu", Toast.LENGTH_SHORT).show();
                }else if(value < 50000) {
                    Toast.makeText(TopUpActivity.this, "Minimal nominal Rp. 50.000,-", Toast.LENGTH_SHORT).show();
                }else if(value  % 10000 != 0){
                    Toast.makeText(TopUpActivity.this, "Kelipatan harus dari Rp. 10.000,-", Toast.LENGTH_SHORT).show();
                }else{
                    Intent topUp2Intent = new Intent(TopUpActivity.this,TopUpActivity2.class);
                    topUp2Intent.putExtra("topUpAmount", value);
                    startActivity(topUp2Intent);
                    finish();
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent killAny = new Intent(TopUpActivity.this,LandingPage.class);
                killAny.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(killAny);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent killAny = new Intent(this,LandingPage.class);
//        killAny.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(killAny);
        finish();
    }
}
