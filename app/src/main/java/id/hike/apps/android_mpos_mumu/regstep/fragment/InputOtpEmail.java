package id.hike.apps.android_mpos_mumu.regstep.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class InputOtpEmail extends FragmentForm {

    private String emailRegistered;

    private boolean isVerified = false;
    private RegistrasiApi registrasiApi = new RegistrasiApi();

    @Override
    public Observable<Map<String, Object>> isFormComplete(){

        return Observable.create(new ObservableOnSubscribe<Map<String, Object>>() {
            @Override
            public void subscribe(ObservableEmitter<Map<String, Object>> emitter) throws Exception {


                String otp = otpField.getText().toString();

                if(TextUtils.isEmpty(otp)){
                    Toast.makeText(getContext(), "Otp tidak bisa kosong!", Toast.LENGTH_LONG).show();
                }else if(otp.length() < 6){
                    Toast.makeText(getContext(), "Otp harus 6 angka!", Toast.LENGTH_LONG).show();
                }else{

                    otpField.setEnabled(false);

                    ProgressDialog dialog = new ProgressDialog(getContext());
                    dialog.setMessage("Validasi OTP");
                    dialog.setCancelable(false);
                    dialog.show();

                    registrasiApi.checkOtp(emailRegistered, otp).subscribe(new Observer<Response<OtpModel>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Response<OtpModel> otpModelResponse) {
                            dialog.dismiss();

                            String message = null;

                            if(otpModelResponse.getStatus() == 200){

                                OtpModel otp = otpModelResponse.getData();

                                if(otp.getH39().equals("00")){
                                    kirimUlangButton.setEnabled(false);

                                    emitter.onNext(new HashMap<String, Object>());

                                    otpFieldLayout.setErrorEnabled(false);
                                    otpFieldLayout.setError(null);

                                }else{
                                    message = otp.getH62();

                                }

                            }else{
                                message = otpModelResponse.getMessage();
                            }


                            if(message != null){
                                emitter.onError(new Exception(message));
                                otpField.setEnabled(true);
                                otpFieldLayout.setErrorEnabled(true);
                                otpFieldLayout.setError(message);
                            }


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

                }


            }
        });

    }

    @BindView(R.id.otp_field_layout)
    TextInputLayout otpFieldLayout;

    @BindView(R.id.otp_field)
    EditText otpField;

    @BindView(R.id.kirimUlangButton)
    Button kirimUlangButton;

    @BindView(R.id.emailField)
    TextView emailField;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){

        View v = inflater.inflate(R.layout.fragment_input_otp_email, container, false);

        ButterKnife.bind(this, v);

        return v;

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser){
            emailRegistered = getFragSecPrefs().getString("email_register", "");

            emailField.setText(emailRegistered);

            sendOtp();
        }

    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);



        kirimUlangButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendOtp();

            }
        });
    }

    private void sendOtp(){

        if(emailRegistered != null){

            ProgressDialog dialog = new ProgressDialog(getContext());
            dialog.setMessage("Mengirim OTP ke email");
            dialog.setCancelable(false);
            dialog.show();

            registrasiApi.sendOtp(emailRegistered)
                    .subscribe(new Observer<Response<OtpModel>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Response<OtpModel> otpModelResponse) {
                            dialog.dismiss();

                            String message = "";

                            if(otpModelResponse.getStatus() == 200){

                                OtpModel otp = otpModelResponse.getData();

                                if(otp.getH39().equals("00")){
                                    message = "OTP berhasil dikirim ke email";
                                }else{
                                    message = otp.getH62();
                                }

                            }else{
                                message = otpModelResponse.getMessage();
                            }


                            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onError(Throwable e) {
                            dialog.dismiss();
                            Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onComplete() {
                            dialog.dismiss();
                        }
                    });

        }

    }

}
