package id.hike.apps.android_mpos_mumu.features.kode_otp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.login.LoginActivity;

public class KodeOtpActivity extends AppCompatActivity {

    EditText editText_otp;
    Button btn_resend_otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kode_otp);

        editText_otp = findViewById(R.id.editText_otp);
        btn_resend_otp = findViewById(R.id.btn_resend_otp);

        // set resend otp button
        btn_resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KodeOtpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
