package id.hike.apps.android_mpos_mumu.features.proteksi;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ProteksiActivity extends BaseActivity {

    @BindView(R.id.backButton)
    ImageView backButton;

    @BindView((R.id.btnMulaiSantunan))
    Button btnMulaiSantunan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proteksi);
        ButterKnife.bind(this);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnMulaiSantunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProteksiActivity.this, SantunanDhuafaBalance.class);
                startActivity(intent);
            }
        });
    }
}
