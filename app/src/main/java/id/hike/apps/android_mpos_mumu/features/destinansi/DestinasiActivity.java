package id.hike.apps.android_mpos_mumu.features.destinansi;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.R;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DestinasiActivity extends AppCompatActivity {

    @BindView(R.id.backButton)
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinasi);
        ButterKnife.bind(this);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
