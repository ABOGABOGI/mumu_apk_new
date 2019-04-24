package id.hike.apps.android_mpos_mumu.features.profil;

import androidx.fragment.app.FragmentActivity;
import id.hike.apps.android_mpos_mumu.BuildConfig;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.login.api.LoginApi;
import id.hike.apps.android_mpos_mumu.features.pin_verifikasi.InFrameWalletActivity;
import id.hike.apps.android_mpos_mumu.model.OauthToken;
import id.hike.apps.android_mpos_mumu.model.Response;
import id.hike.apps.android_mpos_mumu.model.User;
import id.hike.apps.android_mpos_mumu.model.WalletCRP;
import id.hike.apps.android_mpos_mumu.model.WalletInfo;
import id.hike.apps.android_mpos_mumu.regstep.api.RegistrasiApi;
import id.hike.apps.android_mpos_mumu.util.MyUtils;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import okhttp3.MultipartBody;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class LoginGantiPin extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    Button btnNext;
    EditText emailPin, passwordPin;
    private static final int RC_PHONESTATE = 13;
    final static String[] permissions = {Manifest.permission.READ_PHONE_STATE};
    private RegistrasiApi registrasiApi = new RegistrasiApi();
    private String token = "";
    String ls_norekening = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ganti_pin);

        emailPin = (EditText) findViewById(R.id.emailGantiPin);
        emailPin.setEnabled(false);

        passwordPin = (EditText) findViewById(R.id.passwordGantiPin);
        getProfil();

        Button btnNext = (Button) findViewById(R.id.btnNextPin);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(Cfg.TAG_COMMON, "fygu");
                try{

                    if (emailPin.getText().toString().isEmpty() || passwordPin.getText().toString().isEmpty()) {
                        Toast.makeText(LoginGantiPin.this, getString(R.string.mohon_lengkapi_form), Toast.LENGTH_SHORT).show();
                        return;
                    }


                    loginAuth();

                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }


    void getProfil(){
//        emailPin.setText(getSecPref().getString(Cfg.USERNAME_LOGIN_KEY, Cfg.USERNAME_LOGIN_KEY));
        emailPin.setText(getSecPref().getString(Cfg.prefsUserEmail,""));
        Log.d("test", getSecPref().getString(Cfg.USERNAME_LOGIN_KEY, ""));
    }

    void loginAuth() {

        dfLoading.show(getSupportFragmentManager(), "");
        dfLoading.setCancelable(false);

        String username = emailPin.getText().toString() + "|" + BuildConfig.appHeader;

        LoginApi.create().loginOauth(username, passwordPin.getText().toString())
                .flatMap(new Function<OauthToken, ObservableSource<Response<User>>>() {
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
                }).subscribe(new Observer<Response<User>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(id.hike.apps.android_mpos_mumu.model.Response<User> userResponse) {
                dfLoading.dismiss();

                User user = userResponse.getData();



                // save username and password
                /*getSecPref().edit().remove(Cfg.USERNAME_LOGIN_KEY).apply();
                if (ckRemember.isChecked()) {
                    getSecPref().edit().putBoolean(Cfg.REMEMBER_ME_STATUS_KEY, true).apply();
                    getSecPref().edit().putString(Cfg.USERNAME_LOGIN_KEY, user.getUsername()).apply();
                }*/



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

                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

                Random r = new Random();
                int i1 = r.nextInt(99999 - 100) + 100;

                String ret = formatter.format(new Date());

                Intent intent = new Intent(LoginGantiPin.this, InFrameWalletActivity.class);

                WalletCRP crp = new WalletCRP();
                crp.setTrx_type("CRP");
                crp.setAccount_no(getSecPref().getString(Cfg.prefsWalletRekening,""));
                //crp.setAccount_no(userResponse.getData().getNomor_rekening());
                ls_norekening = getSecPref().getString(Cfg.prefsWalletRekening,"");
                Log.d(Cfg.prefsWalletRekening, "waw");

                crp.setTrx_date_time(ret);
                crp.setSystem_trace_audit(String.valueOf(i1));
                crp.setPos_terminal_type("6017");
                crp.setEnc_pin("123123");
                crp.setJenis_crp("N");
                crp.setOtp("819607");
                crp.setSign("OTR03000010542019021110200016017Mpassword2019-02-10keys");

                intent.putExtra("infoCrp", crp);
                intent.putExtra("url","/hh")                                                                            ;
                intent.putExtra("title","Buat PIN");

                startActivity(intent);
                finish();
            }

            @Override
            public void onError(Throwable e) {
                dfLoading.dismiss();

                Toast.makeText(LoginGantiPin.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
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
                            MyUtils.getDeviceId(LoginGantiPin.this),
                            getIMEI());
                    loginAuth();
                    break;
            }
        } else {
//            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
        }

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            if (EasyPermissions.hasPermissions(LoginGantiPin.this, permissions)) {
                Toast.makeText(LoginGantiPin.this, R.string.returned_from_app_settings_to_activity, Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(LoginGantiPin.this, R.string.returned_from_app_settings_to_activity_notactive, Toast.LENGTH_SHORT)
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

    void gantiPin(){

        MultipartBody.Builder body = new MultipartBody.Builder();
        ProgressDialog dialog = new ProgressDialog(LoginGantiPin.this);

        body.addFormDataPart("access_token", token);
        body.addFormDataPart("jenis_identitas", "1");
        registrasiApi.createWallet2(body.build()).subscribe(new Observer<Response<WalletInfo>>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<WalletInfo> userResponse) {

                dialog.dismiss();

                //uploadImage(images, ktpNo);

                new AlertDialog.Builder(LoginGantiPin.this)
                        .setMessage("Selamat, Wallet anda telah di aktifkan. Silahkan daftarkan pin untuk dapat bertransaksi")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                finish();


                                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

                                Random r = new Random();
                                int i1 = r.nextInt(99999 - 100) + 100;

                                String ret = formatter.format(new Date());

                                Intent intent = new Intent(LoginGantiPin.this, InFrameWalletActivity.class);

                                WalletCRP crp = new WalletCRP();
                                crp.setTrx_type("CRP");
                                crp.setAccount_no(getSecPref().getString(Cfg.prefsWalletRekening, ""));
                                crp.setAccount_no(userResponse.getData().getNomor_rekening());
                                crp.setTrx_date_time(ret);
                                crp.setSystem_trace_audit(String.valueOf(i1));
                                crp.setPos_terminal_type("6017");
                                crp.setEnc_pin("123123");
                                crp.setJenis_crp("N");
                                crp.setOtp("819607");
                                crp.setSign("OTR03000010542019021110200016017Mpassword2019-02-10keys");

                                intent.putExtra("infoCrp", crp);
                                intent.putExtra("url", "/hh");
                                intent.putExtra("title", "Buat PIN");

                                startActivity(intent);
                            }
                        }).show();
            }

            @Override
            public void onError(Throwable e) {
                Log.i("BOWOTAG", e.getMessage());
                dialog.dismiss();
            }

            @Override
            public void onComplete() {

            }
        });


    }
}
