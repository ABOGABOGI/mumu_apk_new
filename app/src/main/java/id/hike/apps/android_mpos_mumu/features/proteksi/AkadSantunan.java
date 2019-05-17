package id.hike.apps.android_mpos_mumu.features.proteksi;

import androidx.appcompat.app.AppCompatActivity;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.pin_verifikasi.InFrameWalletActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class AkadSantunan extends AppCompatActivity {

    CheckBox checkBox;
    Button btnLanjut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akad_santunan);

        checkBox = findViewById(R.id.checkSantunanDhuafa);
        btnLanjut = findViewById(R.id.btnLanjutSantunan);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!checkBox.isChecked()){
                    checkBox.setEnabled(true);
                }else{
                    checkBox.setEnabled(true);
                }
            }
        });

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkBox.isChecked()){
                    Toast.makeText(AkadSantunan.this, "Tolong di centang terlebih dahulu", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(AkadSantunan.this, TotalSantunan.class);
                    startActivity(intent);
                }
            }
        });
    }
}
