package id.hike.apps.android_mpos_mumu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;

import id.hike.apps.android_mpos_mumu.model.WalletInfo;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;

public class NextStepRegisLinkAja extends AppCompatActivity {

    TextView namaUser, saldoUser;
    EditText verifikasiSatu, verifikasiDua, verifikasiTiga, verifikasiEmpat, verifikasiLima, verifikasiEnam;
    private WalletInfo info;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_step_regis_link_aja);

        namaUser = findViewById(R.id.namaUser);
        saldoUser = findViewById(R.id.saldoUser);
        verifikasiSatu = findViewById(R.id.verifikasiSatu);
        verifikasiDua = findViewById(R.id.verifikasiDua);
        verifikasiTiga = findViewById(R.id.verifikasiTiga);
        verifikasiEmpat = findViewById(R.id.verifikasiEmpat);
        verifikasiLima = findViewById(R.id.verifikasiLima);
        verifikasiEnam = findViewById(R.id.verifikasiEnam);
        btnNext = findViewById(R.id.btnAktivasiDirectDebit);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NextStepRegisLinkAja.this, RegisLinkAjaBerhasil.class);
                startActivity(intent);
            }
        });
//        namaUser.setText(info.getNm_agen());
  //      saldoUser.setText("Rp." + UnitConversion.format2Rupiah2(info.getSaldo()));


    }
}
