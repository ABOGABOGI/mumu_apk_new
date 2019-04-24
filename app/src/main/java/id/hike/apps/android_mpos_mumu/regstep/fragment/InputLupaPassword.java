package id.hike.apps.android_mpos_mumu.regstep.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.model.OtpModel;
import id.hike.apps.android_mpos_mumu.model.Response;
import id.hike.apps.android_mpos_mumu.regstep.api.RegistrasiApi;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class InputLupaPassword extends FragmentForm {

    @BindView(R.id.email_field_layout)
    TextInputLayout emailFieldLayout;

    @BindView(R.id.email_field)
    TextInputEditText emailField;

    private RegistrasiApi registrasiApi = new RegistrasiApi();

    @Override
    public Observable<Map<String, Object>> isFormComplete() {
        return Observable.create(new ObservableOnSubscribe<Map<String, Object>>() {
            @Override
            public void subscribe(ObservableEmitter<Map<String, Object>> emitter) throws Exception {

                int error = 0;
                String email = emailField.getText().toString();

                if(TextUtils.isEmpty(email)){

                    emailFieldLayout.setError("Isi dahulu emailnya");
                    error += 1;

                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailFieldLayout.setError("Email yang dimasukan harus valid");
                    error += 1;
                }else{
                    emailFieldLayout.setError(null);
                }


                if(error == 0) {

                    ProgressDialog dialog = new ProgressDialog(getContext());
                    dialog.setMessage("Mengirim OTP ke Email");
                    dialog.setCancelable(false);

                    dialog.show();

                    registrasiApi.checkEmail(email).flatMap(new Function<Response<Map<String, Boolean>>, ObservableSource<Response<OtpModel>>>() {
                        @Override
                        public ObservableSource<Response<OtpModel>> apply(Response<Map<String, Boolean>> mapResponse) throws Exception {

                            Boolean isEmailExists = mapResponse.getData().get("email");

                            if(isEmailExists.booleanValue()){
                                emailFieldLayout.setError(null);
                                emailFieldLayout.setErrorEnabled(false);
                                return registrasiApi.sendOtpPassword(email);
                            }

                            emitter.onError(new Exception("Email tidak terdaftar"));
                            emailFieldLayout.setError("Email tidak terdaftar");
                            emailFieldLayout.setErrorEnabled(true);

                            return null;
                        }
                    }).subscribe(new Observer<Response<OtpModel>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Response<OtpModel> otpModelResponse) {
                            OtpModel otp = otpModelResponse.getData();

                            if(otp.getH39().equals("00")){
                                emailFieldLayout.setError(null);
                                emailFieldLayout.setErrorEnabled(false);

                                getFragSecPrefs().edit().putString("email_lupa_password", email).apply();

                                emitter.onNext(new HashMap<>());
                            }else{
                                emailFieldLayout.setError(otp.getH62());
                                emailFieldLayout.setErrorEnabled(true);
                                emitter.onError(new Exception(otp.getH62()));
                            }

                            dialog.dismiss();
                        }

                        @Override
                        public void onError(Throwable e) {

                            dialog.dismiss();
                        }

                        @Override
                        public void onComplete() {
                            dialog.dismiss();
                        }
                    });
                }else{
                    emitter.onError(new Exception("Total Error" + error));
                }

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_input_lupa_password, container, false);

        ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);


    }

}
