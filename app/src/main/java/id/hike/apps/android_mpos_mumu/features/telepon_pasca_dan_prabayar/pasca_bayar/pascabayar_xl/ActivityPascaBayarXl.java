package id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.pascabayar_xl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.pascabayar_indosat.ActivityDetailPascaBayarIndosat;
import id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.pascabayar_indosat.ActivityPascaBayarIndosat;

public class ActivityPascaBayarXl extends AppCompatActivity {

    EditText inputNoXl;
    Button button;

    String nomor = "081812345678";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasca_bayar_xl);

        inputNoXl = findViewById(R.id.inputNoXl);

        button = findViewById(R.id.btnNextPascaBayarXl);
        button.setOnClickListener(v -> {
            if (inputNoXl.getText().toString().equals(nomor)) {
                Intent intent = new Intent(ActivityPascaBayarXl.this, ActivityDetailPascaBayarXl.class);
                startActivity(intent);
            } else if (inputNoXl.getText().toString().equals("")) {
                inputNoXl.setError("Mohon isi nomor telepon anda");
            } else {
                //Toast.makeText(ActivityPascaBayarIndosat.this, "Data nomor tidak ditemukan", Toast.LENGTH_SHORT).show();
                inputNoXl.setError("Data nomor tidak ditemukan");
            }
        });
    }
}
