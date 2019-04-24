package id.hike.apps.android_mpos_mumu.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.securepreferences.SecurePreferences;


import id.hike.apps.android_mpos_mumu.R;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

/**
 * Created by root on 20/06/17.
 */

public class BaseApplication extends Application {

    public static SharedPreferences secPrefs;
    private String password = "mposSmartFren2017";
    public String LOG=this.getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        //agar pake kamera di nougat ke atas bisa
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/MuseoSans-500.otf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());

        /*CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/MuseoSans-500.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );*/

        secPrefs = new SecurePreferences(getBaseContext(), password, "mpos_smartfren.xml");
        secPrefs.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                Log.d(LOG,"change prefs");
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}