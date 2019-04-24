package id.hike.apps.android_mpos_mumu.base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import id.hike.apps.android_mpos_mumu.app.BaseApplication;
import id.hike.apps.android_mpos_mumu.features.login.df.DfLoading;
import id.hike.apps.android_mpos_mumu.util.DBTransaction;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

/**
 * Created by root on 20/06/17.
 */

public class BaseActivity extends AppCompatActivity {


    public DBTransaction dbTransaction;
    public DfLoading dfLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onComponent();
    }

    void onComponent() {
        dfLoading =new DfLoading();
        getLogName();
        dbTransaction=new DBTransaction(this);
    }

    public SharedPreferences getSecPref(){
        return ((BaseApplication)getApplicationContext()).secPrefs;
    }

    public void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(
                Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public String LOG;
    public void getLogName(){
        LOG=getClass().getSimpleName();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
