package id.hike.apps.android_mpos_mumu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import id.hike.apps.android_mpos_mumu.model.WalletInfo;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;

public class NextStepRegisLinkAja extends AppCompatActivity {

    TextView namaUser, saldoUser;
    EditText verifikasiSatu, verifikasiDua, verifikasiTiga, verifikasiEmpat, verifikasiLima, verifikasiEnam;
    private WalletInfo info;

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

//        namaUser.setText(info.getNm_agen());
  //      saldoUser.setText("Rp." + UnitConversion.format2Rupiah2(info.getSaldo()));
    }
}
