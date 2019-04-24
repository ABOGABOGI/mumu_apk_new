package id.hike.apps.android_mpos_mumu.features.proteksi;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ProteksiActivity extends BaseActivity {

    @BindView(R.id.backButton)
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proteksi);
        ButterKnife.bind(this);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
