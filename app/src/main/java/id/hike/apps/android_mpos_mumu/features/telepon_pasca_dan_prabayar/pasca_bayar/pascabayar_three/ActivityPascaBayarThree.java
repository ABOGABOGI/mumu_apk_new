package id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.pascabayar_three;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.pascabayar_indosat.ActivityDetailPascaBayarIndosat;
import id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.pascabayar_indosat.ActivityPascaBayarIndosat;

public class ActivityPascaBayarThree extends AppCompatActivity {

    EditText inputNoTri;
    Button button;

    String nomor = "089612345678";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasca_bayar_three);

        inputNoTri = findViewById(R.id.inputNoTri);

        button = findViewById(R.id.btnNextPascaBayarTri);
        button.setOnClickListener(v -> {
            if (inputNoTri.getText().toString().equals(nomor)) {
                Intent intent = new Intent(ActivityPascaBayarThree.this, ActivityDetailPascaBayarThree.class);
                startActivity(intent);
            } else if (inputNoTri.getText().toString().equals("")) {
                inputNoTri.setError("Mohon isi nomor telepon anda");
            } else {
                //Toast.makeText(ActivityPascaBayarIndosat.this, "Data nomor tidak ditemukan", Toast.LENGTH_SHORT).show();
                inputNoTri.setError("Data nomor tidak ditemukan");
            }
        });
    }
}
