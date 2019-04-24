package id.hike.apps.android_mpos_mumu.features.wakaf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.model.MetaInfo;

public class WakafDetailActivity extends AppCompatActivity {
    Button goToNWakaf;
    EditText editTextWakaf;
    TextView namaProgram, description;
    ImageView backBtn_univ, imageView;

    private MetaInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dompet_dhuafa_univeristy);
        goToNWakaf = findViewById(R.id.btnMulaiWakaf);
        editTextWakaf = findViewById(R.id.editTextWakaf);
        namaProgram = findViewById(R.id.namaProgram);
        description = findViewById(R.id.description);
        backBtn_univ = findViewById(R.id.backBtn_univ);
        imageView = findViewById(R.id.imageView);
        //Cfg.gs_WakafInfo_MetaKey = info.getMetakey();
        //Cfg.gs_WakafInfo_KodeProduk = info.getMetakodeproduk();
        //Log.i("BOWOTAG", info.getMetakodeproduk());

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){

            if(bundle.containsKey("info")){
                info = (MetaInfo) bundle.getSerializable("info");

                try {
                    JSONObject obj = new JSONObject(info.getMetavalue());

                    Picasso.get().load(Cfg.BASE_ASSET_URL + obj.getString("img")).into(imageView);

                    namaProgram.setText(obj.getString("title"));
                    description.setText(obj.getString("keterangan"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }





        backBtn_univ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        goToNWakaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WakafDetailActivity.this, PembayaranWakaf.class);
                intent.putExtra("name", namaProgram.getText().toString()) ;
                startActivity(intent);

            }
        });
    }
    
}
