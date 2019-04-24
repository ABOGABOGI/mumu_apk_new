package id.hike.apps.android_mpos_mumu.features.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import de.hdodenhof.circleimageview.CircleImageView;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.kode_otp.KodeOtpActivity;
import id.hike.apps.android_mpos_mumu.features.login.LoginActivity;
import id.hike.apps.android_mpos_mumu.features.profil.PosCamera;
import id.hike.apps.android_mpos_mumu.features.register.model.RegisterData;
import id.hike.apps.android_mpos_mumu.features.register.model.ResponseUser;
import id.hike.apps.android_mpos_mumu.features.register.model.TokenResponse;
import id.hike.apps.android_mpos_mumu.features.register.model.User;
import id.hike.apps.android_mpos_mumu.features.register.model.UserPIN;
import id.hike.apps.android_mpos_mumu.features.register.model.WalletResponse;
import id.hike.apps.android_mpos_mumu.util.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "RegisterActivity";
    TextView btn_to_login;
    CircleImageView btnTakePhoto, btnTakePhoto2;
    EditText editText_name,editText_phone,editText_email,editText_password,txtRegisterPin,txtRegisterConfirmPin,txt_confirm_password;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText_name = findViewById(R.id.editText_name);
        editText_phone = findViewById(R.id.editText_phone);
        editText_email = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editText_password);
        btn_register = findViewById(R.id.btn_register);
        btn_to_login = findViewById(R.id.btn_to_login);
        //txtRegisterPin = findViewById(R.id.txtRegisterPin);
        //txtRegisterConfirmPin = findViewById(R.id.txtRegisterConfirmPin);
        txt_confirm_password = findViewById(R.id.txt_confirm_password);

        btn_to_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);

        btnTakePhoto = findViewById(R.id.btnTakeFoto);
        btnTakePhoto2 = findViewById(R.id.btnTakeFoto2);

        btnTakePhoto.setOnClickListener(this);
        btnTakePhoto2.setOnClickListener(this);

        final TextInputLayout labelErrorEmail = (TextInputLayout) findViewById(R.id.textInputLayout9);
        labelErrorEmail.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String email = editText_email.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if(email.matches(emailPattern) && editable.length() > 0){
                    labelErrorEmail.setError(null);
                    labelErrorEmail.setErrorEnabled(false);
                }else{
                    labelErrorEmail.setError("Invalid Email Address");
                    labelErrorEmail.setErrorEnabled(true);
                }

            }
        });

        final TextInputLayout labelErrorPhone = (TextInputLayout) findViewById(R.id.textInputLayout8);
        labelErrorPhone.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int phoneLenght = editText_phone.getText().toString().length();
                if(phoneLenght < 11 ){
                    labelErrorPhone.setError("Tidak boleh kurang dari 11 digit");
                    labelErrorPhone.setErrorEnabled(true);
                }else if(phoneLenght > 13){
                    labelErrorPhone.setError("Tidak boleh lebih dari 12 digit");
                    labelErrorPhone.setErrorEnabled(true);
                }else{
                    labelErrorPhone.setError(null);
                    labelErrorPhone.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        final TextInputLayout labelError = (TextInputLayout) findViewById(R.id.textInputLayout4);
        labelError.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!txt_confirm_password.getText().toString().equals(editText_password.getText().toString())){
                    labelError.setError("Password tidak sama");
                    labelError.setErrorEnabled(true);
                }else{
                    labelError.setError(null);
                    labelError.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        /*final TextInputLayout labelErrorPin = (TextInputLayout) findViewById(R.id.textInputLayout6);
        labelErrorPin.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!txtRegisterConfirmPin.getText().toString().equals(txtRegisterPin.getText().toString())){
                    labelErrorPin.setError("Pin tidak sama");
                    labelErrorPin.setErrorEnabled(true);
                }else{
                    labelErrorPin.setError(null);
                    labelErrorPin.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                //registerUser(getUserInfo());

                Intent intent = new Intent(RegisterActivity.this, KodeOtpActivity.class);
                Log.d("About to : ", "Start KodeOtp Activity");
                startActivity(intent);
                finish();
                Log.d("Finish start : ", "Landing KodeOtp Activity");

                break;
            case R.id.btn_to_login:
                goToLogin();
                break;

            case R.id.btnTakeFoto:
                Intent intentFoto = new Intent(RegisterActivity.this, PosCamera.class);
                startActivity(intentFoto);
                finish();
                break;

            case R.id.btnTakeFoto2:
                Intent intentFoto2 = new Intent(RegisterActivity.this, PosCamera.class);
                startActivity(intentFoto2);
                finish();
                break;
        }
    }

    private RegisterData getUserInfo() {
        return new RegisterData().setFullname(editText_name.getText().toString())
                .setEmail(editText_email.getText().toString())
                .setPhone(editText_phone.getText().toString())
                .setPassword(editText_password.getText().toString());
    }



    private void registerUser(RegisterData userData) {

        dfLoading.show(getSupportFragmentManager(), "");
        dfLoading.setCancelable(false);

        RegisterService menulist = ApiClient.serviceGenerator(Cfg.BASEURL_REGISTER).create(RegisterService.class);

        try{
            Call<ResponseUser> call = menulist.registerUser(userData);
            call.enqueue(new Callback<ResponseUser>() {
                @Override
                public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                    dfLoading.dismiss();
                    if (response.isSuccessful()) {
                        try {
                            User userInfo = response.body().getUser();
                            getSecPref().edit().putString(Cfg.prefsUserId, userInfo.getUserId() + "");
                            getSecPref().edit().putString(Cfg.prefsUserName, userInfo.getUsername());
                            getSecPref().edit().putString(Cfg.prefsUserEmail, userInfo.getEmail());
                            getSecPref().edit().putString(Cfg.prefsUserPhone, userInfo.getPhone());
                            getSecPref().edit().putString(Cfg.prefsUserStoreId, userInfo.getStoreId());
                            getToken(userInfo.getUsername(), editText_password.getText().toString());
                        } catch (Exception ex) {
                            Log.e(TAG, ex.getMessage(), ex);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseUser> call, Throwable t) {
                    Log.e(TAG, t.getMessage(), t);
                    dfLoading.dismiss();
                }
            });

        }catch(Exception ex){
            Log.e(TAG, ex.getMessage(), ex);
        }
    }

    private void getToken(String username, String password) {
        RegisterService service = ApiClient.serviceGenerator(Cfg.BASEURL_TOKEN_OAUTH)
                .create(RegisterService.class);

        try {
            Call<TokenResponse> call = service.getToken(Cfg.oauthTokenBasic,
                    username, password, Cfg.oauthClientId, "password");
            call.enqueue(new Callback<TokenResponse>() {
                @Override
                public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                    try {
                        addDemoDeposit("Bearer " + response.body().getAccess_token(),
                                txtRegisterPin.getText().toString(),
                                txtRegisterConfirmPin.getText().toString());
                    } catch (Exception ex) {
                        Log.e(LOG, ex.getMessage(), ex);
                    }
                }

                @Override
                public void onFailure(Call<TokenResponse> call, Throwable t) {
                    Log.d(TAG, t.getMessage(), t);
                }
            });
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage(), ex);
        }
    }

    private void addDemoDeposit(String auth, String pin, String confirmPin) {
        RegisterService service = ApiClient.serviceGenerator(Cfg.BASEURL_EWALLET)
                .create(RegisterService.class);

        try {
            Call<WalletResponse> call = service.addDemoDeposit(auth, new UserPIN(pin, confirmPin));
            call.enqueue(new Callback<WalletResponse>() {
                @Override
                public void onResponse(Call<WalletResponse> call, Response<WalletResponse> response) {
                    try {
                        WalletResponse wallet = response.body();
                        getSecPref().edit().putString(Cfg.prefsUserWalletId, wallet.getId());
                        getSecPref().edit().putString(Cfg.prefsUserDepositId, wallet.getId());
                    } catch (Exception ex) {
                        Log.e(TAG, ex.getMessage(), ex);
                    }

                    successMessages();
                }

                @Override
                public void onFailure(Call<WalletResponse> call, Throwable t) {
                    Log.e(TAG, t.getMessage(), t);
                }
            });
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage(), ex);
        }
    }

    private void successMessages() {
        Toast.makeText(getApplicationContext(),"Berhasil mendaftar, silahkan login!",Toast.LENGTH_SHORT).show();
        goToLogin();
    }

    private void goToLogin() {
        Intent goToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(goToLogin);
        finish();
    }
}
