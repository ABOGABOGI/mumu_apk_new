package id.hike.apps.android_mpos_mumu.features.donasi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import id.hike.apps.android_mpos_mumu.R;

public class AkadDonasiActivity extends AppCompatActivity {

    private CheckBox checkDonasi;
    private Button btnLanjut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akad_donasi);

        checkDonasi = findViewById(R.id.checkMembaca_donasi);
        btnLanjut = findViewById(R.id.btnLanjutDonasi);
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkDonasi.isChecked()){
                    Toast.makeText(AkadDonasiActivity.this, "Tolong centang jika anda telah membaca", Toast.LENGTH_SHORT).show();
                }else {
                    Intent goToLanding = new Intent(AkadDonasiActivity.this,TotalDonasiActivity.class);
                    startActivity(goToLanding);
                }
            }
        });

        checkDonasi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!checkDonasi.isChecked()){
                    btnLanjut.setEnabled(false);
                }
                else {
                    btnLanjut.setEnabled(true);
                }
            }
        });

    }
}
