package id.hike.apps.android_mpos_mumu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.hike.apps.android_mpos_mumu.base.BaseActivity;

public class NextRegistrasiLinkAja extends BaseActivity {

    EditText passwordRegis;
    TextView nomorHpRegis;
    Button lanjutRegis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_registrasi_link_aja);

        passwordRegis = findViewById(R.id.passwordLinkAja);
        nomorHpRegis = findViewById(R.id.nomorHpLinkAja);
        lanjutRegis = findViewById(R.id.btnNextRegisLinkAja);

        nomorHpRegis.setText(getSecPref().getString(Cfg.NOMOR_HANDPHONE, Cfg.NOMOR_HANDPHONE));

        lanjutRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor sharedPref = getSecPref().edit();

                sharedPref.putString(Cfg.PASSWORD_LINK_AJA, String.valueOf(passwordRegis.getText().toString()));
                sharedPref.apply();
                Toast.makeText(NextRegistrasiLinkAja.this, Cfg.PASSWORD_LINK_AJA, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(NextRegistrasiLinkAja.this, NextStepRegisLinkAja.class);
                startActivity(intent);
            }
        });
    }
}
