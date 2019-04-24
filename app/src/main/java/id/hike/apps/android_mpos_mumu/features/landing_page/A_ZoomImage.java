package id.hike.apps.android_mpos_mumu.features.landing_page;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;

public class A_ZoomImage extends BaseActivity {

    private String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image);

        image = getIntent().getStringExtra("pictUrl");

        ((TextView)findViewById(R.id.abTvTitle)).setText(getString(R.string.title_promo_hari_ini));

        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        Picasso.get().load(image).into(photoView);

        ImageView btnBack= (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
