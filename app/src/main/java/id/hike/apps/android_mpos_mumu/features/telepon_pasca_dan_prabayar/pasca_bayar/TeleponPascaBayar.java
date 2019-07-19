package id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;

import id.hike.apps.android_mpos_mumu.R;

public class TeleponPascaBayar extends AppCompatActivity {

    CardView pascaBayarTelkomsel, pascaBayarXl, pascaBayarIndosat, pascaBayarThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telepon_pasca_bayar);

        pascaBayarTelkomsel = findViewById(R.id.cardView11);
        pascaBayarXl = findViewById(R.id.pascaBayarXl);
    }
}
