package id.hike.apps.android_mpos_mumu.features.wakaf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import id.hike.apps.android_mpos_mumu.R;


public class PesantrenProduktifSmartHafidzVillage extends AppCompatActivity {
    Button goToNWakaf;
    ImageView btnBack,backBtn_santren;
    EditText editTextWakaf;
    TextView namaProgram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesantren_produktif_smart_hafidz_village);

        goToNWakaf = findViewById(R.id.btnMulaiWakaf);
        btnBack = findViewById(R.id.backBtn_rs);
        editTextWakaf = findViewById(R.id.editTextWakaf);
        namaProgram = findViewById(R.id.namaProgram);
        backBtn_santren = findViewById(R.id.backBtn_santren);

        backBtn_santren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        goToNWakaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PesantrenProduktifSmartHafidzVillage.this, PembayaranWakaf.class);
                intent.putExtra("name", namaProgram.getText().toString()) ;
                startActivity(intent);
            }
        });

        backBtn_santren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
