package id.hike.apps.android_mpos_mumu.features.donasi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.landing_page.LandingPage;


public class donasi extends AppCompatActivity {

    private Button btn_zakat,btn_donasi;
    private ImageView btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_donasi);

        btn_zakat = findViewById(R.id.btn_zakat);
        btn_donasi = findViewById(R.id.btn_donasi);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent killAny = new Intent(donasi.this, LandingPage.class);
                killAny.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
            }
        });

        btn_donasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToDonasi = new Intent(donasi.this, DonasiSecondActivityBaru.class);
                startActivity(goToDonasi);
            }
        });

        btn_zakat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToZakat = new Intent(donasi.this, ZakatSecondActivityBaru.class);
                startActivity(goToZakat);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent killAny = new Intent(this, LandingPage.class);
        killAny.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        finish();
    }
}
