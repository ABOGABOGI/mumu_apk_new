package id.hike.apps.android_mpos_mumu.features.login;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import androidx.fragment.app.FragmentActivity;
import id.hike.apps.android_mpos_mumu.BuildConfig;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.landing_page.LandingPage;
import id.hike.apps.android_mpos_mumu.features.login.api.LoginApi;
import id.hike.apps.android_mpos_mumu.model.OauthToken;
import id.hike.apps.android_mpos_mumu.model.User;
import id.hike.apps.android_mpos_mumu.regstep.LupaPasswordActivity;
import id.hike.apps.android_mpos_mumu.regstep.RegStepSimpleActivity;
import id.hike.apps.android_mpos_mumu.util.MyUtils;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class LoginActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    private static final int RC_PHONESTATE = 13;
    EditText etName, etPin;
    CheckBox ckRemember;
    TextView btn_to_register;

    final static String[] permissions = {Manifest.permission.READ_PHONE_STATE};

    String mPosId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_login);

        mPosId="SMARTPOS_MOBILE";
        onComponent();

        btn_to_register = findViewById(R.id.btn_to_register);
        btn_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToRegister = new Intent(LoginActivity.this, RegStepSimpleActivity.class);
                startActivity(goToRegister);
                finish();
            }
        });

        final TextInputLayout labelErrorEmailLogin = (TextInputLayout) findViewById(R.id.textInputLayout7);
        labelErrorEmailLogin.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String email = etName.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if(email.matches(emailPattern) && editable.length() > 0){
                    labelErrorEmailLogin.setError(null);
                    labelErrorEmailLogin.setErrorEnabled(false);
                }else {
                    labelErrorEmailLogin.setError("Invalid Email Address");
                    labelErrorEmailLogin.setErrorEnabled(true);
                }

            }
        });

    }

    private void onComponent() {
        etName = (EditText) findViewById(R.id.editText_email_login);
        etPin = findViewById(R.id.editText_password);
        ckRemember = (CheckBox) findViewById(R.id.ckRemember);

        String usr = getSecPref().getString(Cfg.prefsUserEmail,"");

        if(!usr.isEmpty()){
            etName.setText(usr);
        }

        Button btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(v -> {

            Log.d(Cfg.TAG_COMMON, "fygu");
            try{

            if (etName.getText().toString().isEmpty() || etPin.getText().toString().isEmpty()) {
                Toast.makeText(LoginActivity.this, getString(R.string.mohon_lengkapi_form), Toast.LENGTH_SHORT).show();
                return;
            }

            loginAuth();

            Log.d("dadada", getSecPref().getString(Cfg.USERNAME_LOGIN_KEY,""));


            }catch(Exception ex){
                ex.printStackTrace();
            }
        });


        findViewById(R.id.btnLupaPin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DfLupaPIN dfLupaPIN = new DfLupaPIN();
//                dfLupaPIN.show(getSupportFragmentManager(), "");


                Intent intent = new Intent(LoginActivity.this, LupaPasswordActivity.class);
                startActivity(intent);

            }
        });

        boolean cbRememberStatus = getSecPref().getBoolean(Cfg.REMEMBER_ME_STATUS_KEY, false);
        ckRemember.setChecked(cbRememberStatus);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    void loginAuth() {

        dfLoading.show(getSupportFragmentManager(), "");
        dfLoading.setCancelable(false);

        String username = etName.getText().toString() + "|" + BuildConfig.appHeader;

        LoginApi.create().loginOauth(username, etPin.getText().toString())
                .flatMap(new Function<OauthToken, ObservableSource<id.hike.apps.android_mpos_mumu.model.Response<User>>>() {
                    @Override
                    public ObservableSource<id.hike.apps.android_mpos_mumu.model.Response<User>> apply(OauthToken oauthToken) throws Exception {


                        Gson gson = new Gson();

                        Long tsLong = System.currentTimeMillis()/1000;
                        tsLong = tsLong + oauthToken.getExpires_in();


                        getSecPref().edit().putString(Cfg.oauthDataKey, oauthToken.getScope()).apply();
                        getSecPref().edit().putLong(Cfg.tokenExpirationKey, tsLong).apply();
                        getSecPref().edit().putString(Cfg.oauthRefreshToken, oauthToken.getRefresh_token()).apply();
                        getSecPref().edit().putString(Cfg.oauthAccessToken, oauthToken.getAccess_token()).apply();
                        getSecPref().edit().putString(Cfg.oauthTokenBasic, gson.toJson(oauthToken)).apply();

                        return LoginApi.create().getUser(oauthToken.getAccess_token());
                    }
                }).subscribe(new Observer<id.hike.apps.android_mpos_mumu.model.Response<User>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(id.hike.apps.android_mpos_mumu.model.Response<User> userResponse) {
                dfLoading.dismiss();

                User user = userResponse.getData();



                // save username and password
                getSecPref().edit().remove(Cfg.USERNAME_LOGIN_KEY).apply();
                if (ckRemember.isChecked()) {
                    getSecPref().edit().putBoolean(Cfg.REMEMBER_ME_STATUS_KEY, true).apply();
                    getSecPref().edit().putString(Cfg.USERNAME_LOGIN_KEY, user.getUsername()).apply();
                }



                Gson gson = new Gson();

                getSecPref().edit().putLong(Cfg.prefsKasirId_INT, 1).apply();
                getSecPref().edit().putLong(Cfg.prefsOutletId_STR, 1).apply();
                getSecPref().edit().putString(Cfg.prefUserdata_USER,gson.toJson(user)).apply();
                getSecPref().edit().putString(Cfg.prefsNamaKasir_STR, user.getUsername()).apply();
                getSecPref().edit().putString(Cfg.prefsUserName, user.getNamaAgen()).apply();
                //getSecPref().edit().putString(Cfg.gs_kode_agen, user.getKdAgen()).apply();
                Cfg.gs_kode_agen =  user.getKdAgen();
                //Log.i("DEBUGTAG", "LoginActivity");
                //Log.i("DEBUGTAG", Cfg.gs_kode_agen);
                //Log.i("DEBUGTAG", Cfg.prefsUserName);
                //Log.i("DEBUGTAG", "LoginActivity");

                getSecPref().edit().putString(Cfg.prefsUserEmail, user.getEmail() ).apply();
                getSecPref().edit().putString(Cfg.prefsAlamatOutlet_Str, user.getAlamat()).apply(); //Alamat outlet
//                getSecPref().edit().putString(Cfg.prefsTeleponOutlet_Str, "123456789").apply();
//                getSecPref().edit().putString(Cfg.prefsNamaOutlet_Str, "toko ale").apply();
                getSecPref().edit().putInt(Cfg.prefsStoreId_INT, 1).apply();

                Intent intent = new Intent(LoginActivity.this, LandingPage.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(Throwable e) {
                dfLoading.dismiss();

                Toast.makeText(LoginActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onComplete() {

            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == FragmentActivity.RESULT_OK) {
            switch (requestCode) {
                case RC_PHONESTATE:
                    dbTransaction.insertAppsId(
                            generateAppsId(),
                            MyUtils.getDeviceId(LoginActivity.this),
                            getIMEI());
                    loginAuth();
                    break;
            }
        } else {
//            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
        }

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            if (EasyPermissions.hasPermissions(LoginActivity.this, permissions)) {
                Toast.makeText(LoginActivity.this, R.string.returned_from_app_settings_to_activity, Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(LoginActivity.this, R.string.returned_from_app_settings_to_activity_notactive, Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    int TIMEOUT = 5000;

    String generateAppsId() {
        String appsId = "";
        try {
            appsId = MyUtils.SHA1(getIMEI() + System.currentTimeMillis());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return appsId;
    }

    String getIMEI() {
        if (EasyPermissions.hasPermissions(this, permissions)) {
            return MyUtils.getIMEI(this);
        } else {
            // Request one permission
            EasyPermissions.requestPermissions(this, getString(R.string.read_phone_state_permission_rationale),
                    RC_PHONESTATE, permissions);
            return null;
        }
    }

    private final long LIMIT = 1000000L;


}
