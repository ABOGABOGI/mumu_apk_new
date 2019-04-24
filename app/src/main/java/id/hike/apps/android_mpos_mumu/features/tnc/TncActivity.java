package id.hike.apps.android_mpos_mumu.features.tnc;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import id.hike.apps.android_mpos_mumu.R;

public class TncActivity extends AppCompatActivity {

    Button btn_tnc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tnc);

        btn_tnc = findViewById(R.id.btn_lanjut_tnc);

        //set btn lanjut tnc
        btn_tnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
