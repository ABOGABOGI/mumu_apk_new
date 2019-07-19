package id.hike.apps.android_mpos_mumu.features.pdam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.destinansi.DestinasiActivity;

public class ActivityDetailTagihanPdam extends AppCompatActivity {

    TextView periodePdam, samaDenganPediode, samaDenganPemakaian, pemakaianPdam, textLihatDetail, periodePdamDua, pemakaianPdamDua;

    ConstraintLayout lihatDetail;
    ImageView gambarLihatDetail;
    Button btnBayar, btnBatal;

    private int a = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tagihan_pdam);

        periodePdam = findViewById(R.id.textView75);
        samaDenganPediode = findViewById(R.id.samaDenganSatu);
        pemakaianPdam = findViewById(R.id.textView76);
        samaDenganPemakaian = findViewById(R.id.samaDenganDua);

        lihatDetail = findViewById(R.id.lihatDetail);
        gambarLihatDetail = findViewById(R.id.gambarLihatDetail);
        textLihatDetail = findViewById(R.id.textView78);

        periodePdamDua = findViewById(R.id.periodePdam);
        pemakaianPdamDua = findViewById(R.id.pemakaianPdam);

        btnBayar = findViewById(R.id.btnBayarPdam);
        btnBayar.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityDetailTagihanPdam.this, ActivityStrukBayarPdam.class);
            startActivity(intent);
        });

        btnBatal = findViewById(R.id.btnBatalPdam);
        btnBatal.setOnClickListener(v -> {
            finish();
        });

        periodePdam.setVisibility(View.GONE);
        samaDenganPediode.setVisibility(View.GONE);
        pemakaianPdam.setVisibility(View.GONE);
        samaDenganPemakaian.setVisibility(View.GONE);
        periodePdamDua.setVisibility(View.GONE);
        pemakaianPdamDua.setVisibility(View.GONE);

        lihatDetail.setOnClickListener(v -> {

            if (a == 1) {
                periodePdam.setVisibility(View.VISIBLE);
                samaDenganPediode.setVisibility(View.VISIBLE);
                pemakaianPdam.setVisibility(View.VISIBLE);
                samaDenganPemakaian.setVisibility(View.VISIBLE);
                periodePdamDua.setVisibility(View.VISIBLE);
                pemakaianPdamDua.setVisibility(View.VISIBLE);

                textLihatDetail.setText("Tutup");
                gambarLihatDetail.setImageResource(R.drawable.ic_uparrow);

                a = 0;
            }else{
                periodePdam.setVisibility(View.GONE);
                samaDenganPediode.setVisibility(View.GONE);
                pemakaianPdam.setVisibility(View.GONE);
                samaDenganPemakaian.setVisibility(View.GONE);
                periodePdamDua.setVisibility(View.GONE);
                pemakaianPdamDua.setVisibility(View.GONE);

                textLihatDetail.setText("Lihat Detail");
                gambarLihatDetail.setImageResource(R.drawable.ic_downarrow);
                a = 1;
            }
        });
    }
}
