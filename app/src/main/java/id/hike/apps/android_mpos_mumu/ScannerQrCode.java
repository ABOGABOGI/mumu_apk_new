package id.hike.apps.android_mpos_mumu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;

import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.destinansi.DestinasiActivity;

public class ScannerQrCode extends BaseActivity implements View.OnClickListener {

    /*@BindView(R.id.backButton)
    ImageView backButton;*/

private Button buttonScan;
private TextView textViewNama, textViewTinggi;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr_code);

        //ButterKnife.bind(this);


        /*backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/

        // initialize object
        buttonScan = (Button) findViewById(R.id.buttonScan);
        textViewNama = (TextView) findViewById(R.id.textViewNama);
        textViewTinggi = (TextView) findViewById(R.id.textViewTinggi);

        // attaching onclickListener
        buttonScan.setOnClickListener(this);
        }

// Mendapatkan hasil scan



@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        /*if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this, "Hasil tidak ditemukan", Toast.LENGTH_SHORT).show();
            }else{

                // jika qrcode berisi data
                try{*/
        // converting the data json
        SharedPreferences.Editor secPref = getSecPref().edit();
        //JSONObject object = new JSONObject(result.getContents());
        // atur nilai ke textviews
        Intent intent = new Intent(ScannerQrCode.this, HasilScanQrCode.class);
        //intent.putExtra("nama", object.getString("nama"));
        //intent.putExtra("tinggi", object.getString("tinggi"));
        startActivity(intent);

        //textViewNama.setText(object.getString("nama"));
        //secPref.putString(Cfg.NAMA_QR, String.valueOf(textViewNama.getText().length()));
        //textViewTinggi.setText(object.getString("tinggi"));
        //secPref.putString(Cfg.TINGGI_QR, String.valueOf(textViewTinggi.getText().length()));
                /*}catch (JSONException e){
                    e.printStackTrace();
                    // jika format encoded tidak sesuai maka hasil
                    // ditampilkan ke toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }*/
        }

public void onClick(View v) {
        // inisialisasi IntentIntegrator(scanQR)
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();
        }

@Override
public void onPointerCaptureChanged(boolean hasCapture) {

        }
}
