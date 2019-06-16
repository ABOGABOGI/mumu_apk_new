package id.hike.apps.android_mpos_mumu.features.tagihan_telepon;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.TotalTagihanTelepon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TagihanTeleponActivity extends AppCompatActivity {

    @BindView(R.id.backButton)
    ImageView backButton;

    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tagihan_telepon);
        ButterKnife.bind(this);

        btnNext = findViewById(R.id.btnBayarTotalTagihanTelepon);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TagihanTeleponActivity.this, TotalTagihanTelepon.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
