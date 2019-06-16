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

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;

public class HasilScanQrCode extends BaseActivity {

    //private Button buttonScan;
    //private TextView textViewNama, textViewTinggi;
    TextView saldoMumu, idMerchant, namaMerchant, alamatMerchant;
    EditText totalBayar;
    Button proses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_scan_qr_code);

        /*buttonScan = (Button) findViewById(R.id.buttonScan);
        textViewNama = (TextView) findViewById(R.id.textViewNama);
        textViewTinggi = (TextView) findViewById(R.id.textViewTinggi);

        String nama = getIntent().getStringExtra("nama");
        String tinggi = getIntent().getStringExtra("tinggi");

        textViewNama.setText(nama);
        textViewTinggi.setText(tinggi);*/

        saldoMumu = findViewById(R.id.saldoMumu);
        idMerchant = findViewById(R.id.idMerchant);
        namaMerchant = findViewById(R.id.namaMerchant);
        alamatMerchant = findViewById(R.id.alamatMerchant);
        totalBayar = findViewById(R.id.jumlahBayar);
        proses = findViewById(R.id.proses);

        int saldo = getSecPref().getInt(Cfg.prefsWalletSaldo, 0);
        saldoMumu.setText("Rp. " + UnitConversion.format2Rupiah2(saldo));

        proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor secPref = getSecPref().edit();
                secPref.putString(Cfg.JUMLAH_BAYAR_BY_QR_CODE, String.valueOf(totalBayar.getText().toString()));

                int totalBayarDua = getSecPref().getInt(Cfg.JUMLAH_BAYAR_BY_QR_CODE, 0);

                int sisaSaldo = Integer.parseInt(String.valueOf(saldo)) - Integer.parseInt(String.valueOf(totalBayarDua));
                secPref.putString(Cfg.SISA_SALDO, String.valueOf(sisaSaldo));
                secPref.apply();

                Intent intent = new Intent(HasilScanQrCode.this, KonfirmasiPembayaranByQrCode.class);
                startActivity(intent);
            }
        });
    }
}
