package id.hike.apps.android_mpos_mumu.regstep.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import id.hike.apps.android_mpos_mumu.model.Response;
import id.hike.apps.android_mpos_mumu.regstep.api.RegistrasiApi;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class InputEmailHP extends FragmentForm {


    @BindView(R.id.email_field_layout)
    TextInputLayout emailFieldLayout;

    @BindView(R.id.email_field)
    TextInputEditText emailField;

    @BindView(R.id.phone_field_layout)
    TextInputLayout phoneFieldLayout;

    @BindView(R.id.phone_field)
    TextInputEditText phoneField;

    private RegistrasiApi registrasiApi = new RegistrasiApi();


    @Override
    public Observable<Map<String, Object>> isFormComplete(){

        return Observable.create(new ObservableOnSubscribe<Map<String, Object>>() {
            @Override
            public void subscribe(ObservableEmitter<Map<String, Object>> emitter) throws Exception {


                int error = 0;
                String email = emailField.getText().toString();
                String phone = phoneField.getText().toString();

                if(TextUtils.isEmpty(email)){

                    emailFieldLayout.setError("Isi dahulu emailnya");
                    error += 1;

                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailFieldLayout.setError("Email yang dimasukan harus valid");
                    error += 1;
                }else{
                    emailFieldLayout.setError(null);
                }


                if(TextUtils.isEmpty(phone)){

                    phoneFieldLayout.setError("Isi nomor handphone dahulu");

                    error += 1;
                }else{
                    phoneFieldLayout.setError(null);
                }







                if(error == 0){

                    ProgressDialog dialog = new ProgressDialog(getContext());
                    dialog.setMessage("Cek email dan nomor handphone");
                    dialog.setCancelable(false);

                    dialog.show();

                    registrasiApi.checkEmail(email).flatMap(new Function<Response<Map<String, Boolean>>, ObservableSource<Response<Map<String, Boolean>>>>() {
                        @Override
                        public ObservableSource<Response<Map<String, Boolean>>> apply(Response<Map<String, Boolean>> mapResponse) throws Exception {

                            Boolean isEmailExists = mapResponse.getData().get("email");

                            Log.i("BOWOTAG", mapResponse.getData().toString());

                            if(isEmailExists.booleanValue()){
                                dialog.dismiss();
                                emailFieldLayout.setError("Email sudah terdaftar");
                                return null;
                            }

                            getFragSecPrefs().edit().putString("email_register", email).apply();

                            return registrasiApi.checkPhone(phone);
                        }
                    }).subscribe(new Observer<Response<Map<String, Boolean>>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Response<Map<String, Boolean>> mapResponse) {
                            dialog.dismiss();

                            Log.i("BOWOTAG", mapResponse.getData().toString());

                            Boolean isPhoneExists = mapResponse.getData().get("phone");

                            if(isPhoneExists.booleanValue()){

                                phoneFieldLayout.setError("Nomor Handphone sudah terdaftar");

                                return;
                            }

                            Map<String, Object> data = new HashMap<>();
                            data.put("email", email);
                            data.put("no_hp", phone);

                            emitter.onNext(data);

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });


                }else{
                    emitter.onError(new Exception("Total Error" + error));
                }



            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_input_email_hp, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

}
