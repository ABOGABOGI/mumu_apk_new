
package id.hike.apps.android_mpos_mumu.features.wakaf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import id.hike.apps.android_mpos_mumu.R;


public class RsHasyimAsyariUntukDhuafa extends AppCompatActivity {
    Button goToNWakaf;
    TextView namaProgram;
    ImageView backBtn_rs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rs_hasyim_asyari_untuk_dhuafa);

        goToNWakaf = findViewById(R.id.btnMulaiWakaf);
        namaProgram = findViewById(R.id.namaProgram);
        backBtn_rs = findViewById(R.id.backBtn_rs);


        goToNWakaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RsHasyimAsyariUntukDhuafa.this, PembayaranWakaf.class);
                intent.putExtra("name", namaProgram.getText().toString()) ;
                startActivity(intent);
            }
        });

        backBtn_rs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}

