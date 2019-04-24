package id.hike.apps.android_mpos_mumu.features.profil.activity;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.profil.api.UserApi;
import id.hike.apps.android_mpos_mumu.model.Response;
import id.hike.apps.android_mpos_mumu.model.User;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class ChangePasswordActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    View toolbar;

    @BindView(R.id.passwordLayout)
    TextInputLayout passwordLayout;

    @BindView(R.id.oldPasswordLayout)
    TextInputLayout oldPasswordLayout;

    @BindView(R.id.oldPasswordField)
    EditText oldPasswordField;

    @BindView(R.id.confirmPasswordLayout)
    TextInputLayout confirmPasswordLayout;

    @BindView(R.id.passwordField)
    EditText passwordField;

    @BindView(R.id.confirmPasswordField)
    EditText confirmPasswordField;

    @BindView(R.id.btnSimpan)
    Button btnSimpan;

    private boolean isError = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);

        TextView titleView = toolbar.findViewById(R.id.abTvTitle);
        titleView.setText("Ganti Password");

        ImageView btnBack = toolbar.findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLengthPassword();
                if(!isError){
                    checkConfirmPassword();

                    if(!isError){

                        ProgressDialog dialog = new ProgressDialog(ChangePasswordActivity.this);
                        dialog.setMessage("Mengganti Kata sandi...");
                        dialog.setCancelable(false);
                        dialog.show();

                        UserApi userApi = new UserApi();

                        userApi.changePassword(passwordField.getText().toString(), oldPasswordField.getText().toString())
                                .subscribe(new Observer<Response<User>>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(Response<User> userResponse) {
                                        dialog.dismiss();

                                        if(userResponse.getStatus() == 200){
                                            new AlertDialog.Builder(ChangePasswordActivity.this)
                                                    .setMessage("Ganti Kata Sandi Berhasil!")
                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            dialog.dismiss();
                                                            finish();
                                                        }
                                                    }).show();
                                        }else{
                                            new AlertDialog.Builder(ChangePasswordActivity.this)
                                                    .setMessage("Password lama tidak cocok!")
                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            dialog.dismiss();
                                                        }
                                                    }).show();
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        dialog.dismiss();
                                        Toast.makeText(ChangePasswordActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });

                    }
                }


            }
        });


        passwordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                checkLengthPassword();

            }
        });

        confirmPasswordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                checkConfirmPassword();

            }
        });
    }

    private void checkConfirmPassword(){
        String password = passwordField.getText().toString();
        String confirmPassword = confirmPasswordField.getText().toString();

        if(!confirmPassword.equals(password)){
            isError = true;
            confirmPasswordLayout.setError("Konfirmasi password harus sama dengan password");
            confirmPasswordLayout.setErrorEnabled(true);
        }else{
            isError = false;
            confirmPasswordLayout.setError(null);
            confirmPasswordLayout.setErrorEnabled(false);
        }
    }

    private void checkLengthPassword(){
        String password = passwordField.getText().toString();

        if(password.length() < 8){
            isError = true;

            passwordLayout.setError("Panjang password minimal 8");
            passwordLayout.setErrorEnabled(true);

        }else{
            isError = false;
            passwordLayout.setErrorEnabled(false);
            passwordLayout.setError(null);
        }
    }
}
