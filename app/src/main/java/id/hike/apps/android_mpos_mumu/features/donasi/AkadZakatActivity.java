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

public class AkadZakatActivity extends AppCompatActivity {

    private CheckBox checkZakat;
    private Button btnLanjutZakat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akad_zakat);

        checkZakat = findViewById(R.id.checkMembacaZakat);
        btnLanjutZakat = findViewById(R.id.btnLanjutZakat);

        btnLanjutZakat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkZakat.isChecked()){
                    Toast.makeText(AkadZakatActivity.this, "Tolong centang jika anda telah membaca", Toast.LENGTH_SHORT).show();
                }else{
                    Intent goToLanding = new Intent(AkadZakatActivity.this,TotalZakatActivity.class);
                    startActivity(goToLanding);
                }
            }
        });

        checkZakat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!checkZakat.isChecked()){
                    btnLanjutZakat.setEnabled(false);
                }else {
                    btnLanjutZakat.setEnabled(true);
                }
            }
        });
    }
}
