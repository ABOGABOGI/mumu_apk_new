package id.hike.apps.android_mpos_mumu.regstep.fragment;

import android.os.Bundle;
import android.text.TextUtils;
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
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class InputUsernamePassword extends FragmentForm {


    @BindView(R.id.username_field)
    TextInputEditText usernameField;

    @BindView(R.id.username_field_layout)
    TextInputLayout usernameLayout;

    @BindView(R.id.password_field)
    TextInputEditText passwordField;

    @BindView(R.id.password_fielf_layout)
    TextInputLayout passwordLayout;

    @Override
    public Observable<Map<String, Object>> isFormComplete(){

        return Observable.create(new ObservableOnSubscribe<Map<String, Object>>() {
            @Override
            public void subscribe(ObservableEmitter<Map<String, Object>> emitter) throws Exception {


                int error = 0;
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();


                if(TextUtils.isEmpty(username)){
                    usernameLayout.setError("Nama akun tidak bisa kosong");

                    error += 1;
                }else if(!username.matches("^[a-zA-Z0-9._-]{3,}$")){
                    usernameLayout.setError("Nama akun tidak bisa ada spasi");

                    error += 1;
                }else {
                    usernameLayout.setError(null);
                }


                if(TextUtils.isEmpty(password)){
                    passwordLayout.setError("Kata sandi tidak bisa kosong");

                    error += 1 ;
                }


                if(error == 0){

                    Map<String, Object> forms = new HashMap<>();
                    forms.put("username", username);
                    forms.put("password", password);


                    emitter.onNext(forms);
                }else{

                    emitter.onError(new Exception("Total Error " + error));

                }

            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){

        View v = inflater.inflate(R.layout.fragment_input_username, container, false);

        ButterKnife.bind(this, v);

        return v;

    }


    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);



    }





}
