package id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.tagihan_telepon_rumah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.pdam.ActivityStrukBayarPdam;
import id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.pascabayar_three.ActivityDetailPascaBayarThree;

public class DetailTagihanTeleponRumah extends AppCompatActivity {

    TextView periodePascaBayarTelkomsel, samaDenganPediode, samaDenganPemakaian, pemakaianPascaBayarTelkomsel, textLihatDetail, bulanTagihan, totalTagihan;

    ConstraintLayout lihatDetail;
    ImageView gambarLihatDetail;
    Button btnBayar, btnBatal;

    private int a = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tagihan_telepon_rumah);

        periodePascaBayarTelkomsel = findViewById(R.id.textView75);
        samaDenganPediode = findViewById(R.id.samaDenganSatu);
        pemakaianPascaBayarTelkomsel = findViewById(R.id.textView76);
        samaDenganPemakaian = findViewById(R.id.samaDenganDua);

        lihatDetail = findViewById(R.id.lihatDetail);
        gambarLihatDetail = findViewById(R.id.gambarLihatDetail);
        textLihatDetail = findViewById(R.id.textView78);

        bulanTagihan = findViewById(R.id.bulanTagihan);
        totalTagihan = findViewById(R.id.totalTagihan);

        btnBayar = findViewById(R.id.btnBayarPdam);
        btnBayar.setOnClickListener(v -> {
            Intent intent = new Intent(DetailTagihanTeleponRumah.this, ActivityStrukBayarPdam.class);
            startActivity(intent);
        });

        btnBatal = findViewById(R.id.btnBatalPdam);
        btnBatal.setOnClickListener(v -> {
            finish();
        });

        periodePascaBayarTelkomsel.setVisibility(View.GONE);
        samaDenganPediode.setVisibility(View.GONE);
        pemakaianPascaBayarTelkomsel.setVisibility(View.GONE);
        samaDenganPemakaian.setVisibility(View.GONE);
        bulanTagihan.setVisibility(View.GONE);
        totalTagihan.setVisibility(View.GONE);

        lihatDetail.setOnClickListener(v -> {

            if (a == 1) {
                periodePascaBayarTelkomsel.setVisibility(View.VISIBLE);
                samaDenganPediode.setVisibility(View.VISIBLE);
                pemakaianPascaBayarTelkomsel.setVisibility(View.VISIBLE);
                samaDenganPemakaian.setVisibility(View.VISIBLE);
                bulanTagihan.setVisibility(View.VISIBLE);
                totalTagihan.setVisibility(View.VISIBLE);

                textLihatDetail.setText("Tutup");
                gambarLihatDetail.setImageResource(R.drawable.ic_uparrow);

                a = 0;
            }else{
                periodePascaBayarTelkomsel.setVisibility(View.GONE);
                samaDenganPediode.setVisibility(View.GONE);
                pemakaianPascaBayarTelkomsel.setVisibility(View.GONE);
                samaDenganPemakaian.setVisibility(View.GONE);
                bulanTagihan.setVisibility(View.GONE);
                totalTagihan.setVisibility(View.GONE);

                textLihatDetail.setText("Lihat Detail");
                gambarLihatDetail.setImageResource(R.drawable.ic_downarrow);
                a = 1;
            }
        });
    }
}