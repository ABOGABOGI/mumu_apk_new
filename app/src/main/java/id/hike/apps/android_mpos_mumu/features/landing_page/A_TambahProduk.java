package id.hike.apps.android_mpos_mumu.features.landing_page;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import id.hike.apps.android_mpos_mumu.R;

public class A_TambahProduk extends AppCompatActivity {

    WebView wvTambahProduk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_tambah_produk);

        ((TextView)findViewById(R.id.abTvTitle)).setText("Tambah Produk");
        ((ImageView)findViewById(R.id.btnBack)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        wvTambahProduk= (WebView) findViewById(R.id.wvTambahProduk);
        wvTambahProduk.getSettings().setLoadsImagesAutomatically(true);
        wvTambahProduk.getSettings().setJavaScriptEnabled(true);
        wvTambahProduk.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wvTambahProduk.loadUrl("http://apps.smltech.co.id:7180/MPOS/tambahproduk/v1");


//        wvTambahProduk.loadUrl("https://www.google.com");
    }
}
