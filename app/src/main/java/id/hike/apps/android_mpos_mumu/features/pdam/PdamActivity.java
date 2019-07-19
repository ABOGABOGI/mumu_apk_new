package id.hike.apps.android_mpos_mumu.features.pdam;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.pascabayar_telkomsel.ActivityDetailPascabayarTelkomsel;
import id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.pascabayar_telkomsel.ActivityPascabayarTelkomsel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class PdamActivity extends AppCompatActivity {

    @BindView(R.id.backButton)
    ImageView backButton;

    @BindView(R.id.btnNextPdam)
    Button buttonNext;

    @BindView(R.id.inputNoPelangganPdam)
    EditText inputNoPelangganPdam;

    String nomor = "0123456789";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdam);
        ButterKnife.bind(this);

        buttonNext.setOnClickListener(v -> {
            if (inputNoPelangganPdam.getText().toString().equals(nomor)) {
                Intent intent = new Intent(PdamActivity.this, ActivityDetailTagihanPdam.class);
                startActivity(intent);
            } else if (inputNoPelangganPdam.getText().toString().equals("")) {
                inputNoPelangganPdam.setError("Mohon isi nomor telepon anda");
            } else {
                //Toast.makeText(ActivityPascaBayarIndosat.this, "Data nomor tidak ditemukan", Toast.LENGTH_SHORT).show();
                inputNoPelangganPdam.setError("Data nomor tidak ditemukan");
            }
        });

        backButton.setOnClickListener(v -> finish());
    }
}
