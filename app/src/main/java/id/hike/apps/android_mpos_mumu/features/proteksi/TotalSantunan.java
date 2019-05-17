package id.hike.apps.android_mpos_mumu.features.proteksi;

import androidx.appcompat.app.AppCompatActivity;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.pin_verifikasi.InFrameWalletActivity;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.googlecode.mp4parser.authoring.Edit;

public class TotalSantunan extends BaseActivity {

    Button buttonBayar;
    EditText subTotal, biayaAdmin, total;
    int totalBayarSantunan, biayaAdminSantunan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_santunan);

        subTotal = findViewById(R.id.subtotalSantunan);
        subTotal.setEnabled(false);

        biayaAdmin = findViewById(R.id.biayaAdminSantunan);
        biayaAdmin.setEnabled(false);

        total = findViewById(R.id.totalBayarSantunan);
        total.setEnabled(false);

        /*subTotal.setText(getSecPref().getString(Cfg.NAMA_MUSTAHIQ_SANTUNAN, Cfg.NAMA_MUSTAHIQ_SANTUNAN));
        biayaAdmin.setText(getSecPref().getString(Cfg.TANGGAL_MUSTAHIQ_SANTUNAN, Cfg.TANGGAL_MUSTAHIQ_SANTUNAN));
        total.setText(getSecPref().getString(Cfg.JENIS_KELAMIN_MUSTAHIQ, Cfg.JENIS_KELAMIN_MUSTAHIQ));*/

        subTotal.setText(getSecPref().getString(Cfg.TOTAL_BAYAR_MUSTAHIQ, Cfg.TOTAL_BAYAR_MUSTAHIQ));

        biayaAdminSantunan = 2500;
        int a = Integer.parseInt(String.valueOf(biayaAdminSantunan));
        totalBayarSantunan = Integer.parseInt(subTotal.getText().toString());

        biayaAdmin.setText(Integer.toString(biayaAdminSantunan));
        total.setText(Integer.toString(totalBayarSantunan + a));

        buttonBayar = findViewById(R.id.buttonBayarSantunan);
        buttonBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TotalSantunan.this, InFrameWalletActivity.class);
                startActivity(intent);
            }
        });
    }
}
