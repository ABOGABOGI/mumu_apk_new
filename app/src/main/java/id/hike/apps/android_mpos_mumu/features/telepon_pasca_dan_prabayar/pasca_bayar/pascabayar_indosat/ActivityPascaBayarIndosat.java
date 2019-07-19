package id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.pascabayar_indosat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.qurban.ActivityNextKambingPremium;

public class ActivityPascaBayarIndosat extends AppCompatActivity {

    Button button;
    Context context;
    EditText inputNoIndosat;
//    String[] nomor = {"0857112233445", "085812345678", "08570976543"};
    String nomor = "085712345678";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasca_bayar_indosat);

        inputNoIndosat = findViewById(R.id.inputNoIndosat);

        button = findViewById(R.id.btnNextPascaBayarIndosat);
        button.setOnClickListener(v -> {

                if (inputNoIndosat.getText().toString().equals(nomor)) {
                    Intent intent = new Intent(ActivityPascaBayarIndosat.this, ActivityDetailPascaBayarIndosat.class);
                    startActivity(intent);
                } else if (inputNoIndosat.getText().toString().equals("")) {
                    inputNoIndosat.setError("Mohon isi nomor telepon anda");
                } else {
                    //Toast.makeText(ActivityPascaBayarIndosat.this, "Data nomor tidak ditemukan", Toast.LENGTH_SHORT).show();
                    inputNoIndosat.setError("Data nomor tidak ditemukan");
                }
        });
    }
}
