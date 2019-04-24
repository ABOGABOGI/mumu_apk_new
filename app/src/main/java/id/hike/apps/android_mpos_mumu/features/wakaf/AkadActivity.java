package id.hike.apps.android_mpos_mumu.features.wakaf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import id.hike.apps.android_mpos_mumu.R;

public class AkadActivity extends AppCompatActivity {

    Button btnLanjut;
    CheckBox checkAkad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akad);

        btnLanjut = findViewById(R.id.btnLanjut);
        checkAkad = findViewById(R.id.checkBox_akad);
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkAkad.isChecked()){
                    Toast.makeText(AkadActivity.this, "Tolong centang jika anda telah membaca", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(AkadActivity.this,TotalWakafActivity.class);
                    startActivity(intent);
                }
            }
        });

        checkAkad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!checkAkad.isChecked()){
                    btnLanjut.setEnabled(false);
                }
                else {
                    btnLanjut.setEnabled(true);
                }
            }
        });

    }

}
