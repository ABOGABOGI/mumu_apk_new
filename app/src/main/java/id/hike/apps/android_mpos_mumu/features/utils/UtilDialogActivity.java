package id.hike.apps.android_mpos_mumu.features.utils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.landing_page.LandingPage;

public class UtilDialogActivity extends AppCompatActivity {
    public String ls_text_Title = "appTitle";
    public String ls_text_Header = "appMessage";
    public String ls_text_Content = "appContent";
    //FrameLayout parentLayout;

    @BindView(R.id.backButton)
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        TextView tvtext_title;
        TextView tvtext_header;
        TextView tvtext_content;

        setContentView(R.layout.utils_dialog);
        ButterKnife.bind(this);

        ls_text_Title = extras.getString("text_Title");
        ls_text_Header = extras.getString("text_Header");
        ls_text_Content = extras.getString("text_Content");

        tvtext_title = (TextView) findViewById(R.id.text_title);
        tvtext_title.setText(ls_text_Title);

        tvtext_header = (TextView) findViewById(R.id.text_header);
        tvtext_header.setText(ls_text_Header);

        tvtext_content = (TextView) findViewById(R.id.text_isi);
        tvtext_content.setText(ls_text_Content);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtilDialogActivity.this, LandingPage.class);
                startActivity(intent);
                finishAffinity();
            }
        });
        /*ImageView backBtn = toolbar.findViewById(R.id.btnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InFrameWalletActivity.this, LandingPage.class);
                startActivity(intent);

                finishAffinity();
            }
        });*/

    }
}
