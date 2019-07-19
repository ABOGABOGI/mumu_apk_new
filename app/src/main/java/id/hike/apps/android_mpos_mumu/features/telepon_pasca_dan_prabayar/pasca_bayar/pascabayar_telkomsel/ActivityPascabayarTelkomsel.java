package id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.pascabayar_telkomsel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.pascabayar_indosat.ActivityDetailPascaBayarIndosat;
import id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.pascabayar_indosat.ActivityPascaBayarIndosat;

public class ActivityPascabayarTelkomsel extends AppCompatActivity {

    EditText inputNomorTelkom;
    Button button;
    String nomor = "081212345678";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pascabayar_telkomsel);

        inputNomorTelkom = findViewById(R.id.inputNomorTelkom);

        button = findViewById(R.id.btnNextPascaBayarTelkomsel);
        button.setOnClickListener(v -> {
            if (inputNomorTelkom.getText().toString().equals(nomor)) {
                Intent intent = new Intent(ActivityPascabayarTelkomsel.this, ActivityDetailPascabayarTelkomsel.class);
                startActivity(intent);
            } else if (inputNomorTelkom.getText().toString().equals("")) {
                inputNomorTelkom.setError("Mohon isi nomor telepon anda");
            } else {
                //Toast.makeText(ActivityPascaBayarIndosat.this, "Data nomor tidak ditemukan", Toast.LENGTH_SHORT).show();
                inputNomorTelkom.setError("Data nomor tidak ditemukan");
            }
        });
    }
}
