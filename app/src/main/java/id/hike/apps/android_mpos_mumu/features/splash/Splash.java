package id.hike.apps.android_mpos_mumu.features.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.WindowManager;

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.landing_page.LandingPage;
import id.hike.apps.android_mpos_mumu.features.login.LoginActivity;

public class Splash extends BaseActivity {

    final static Long DELAY=2000L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(!getSecPref().getString(Cfg.prefsUserName, "").equals("")){

                    Long tsLong = System.currentTimeMillis()/1000;

                    Long expiresToken = getSecPref().getLong(Cfg.tokenExpirationKey, 0);

                    if(tsLong <= expiresToken){
                        Intent intent=new Intent(Splash.this, LandingPage.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                        return;
                    }
                }


                Intent intent=new Intent(Splash.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();



            }
        }, DELAY);
    }
}
