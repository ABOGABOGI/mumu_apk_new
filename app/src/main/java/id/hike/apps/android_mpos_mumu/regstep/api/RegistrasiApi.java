package id.hike.apps.android_mpos_mumu.regstep.api;

import java.util.Map;

import id.hike.apps.android_mpos_mumu.base.RetroInstance;
import id.hike.apps.android_mpos_mumu.model.OtpModel;
import id.hike.apps.android_mpos_mumu.model.Response;
import id.hike.apps.android_mpos_mumu.model.User;
import id.hike.apps.android_mpos_mumu.model.UserDetail;
import id.hike.apps.android_mpos_mumu.model.WalletInfo;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class RegistrasiApi extends RetroInstance {


    interface RegisterService{

        @POST("/resource-service/user/register")
        Call<Response<User>> createUser(@Body MultipartBody body);

        @POST("/resource-service/user/createUser")
        Call<Response<UserDetail>> createWallet(@Body MultipartBody body);

        @POST("/resource-service/user/createUser")
        Call<Response<WalletInfo>> createWallet2(@Body MultipartBody body);

        @GET("/resource-service/user/checkEmail")
        Call<Response<Map<String,Boolean>>> checkEmail(@Query("email") String email);

        @GET("/resource-service/user/checkPhone")
        Call<Response<Map<String,Boolean>>> checkPhone(@Query("phone") String phone);

        @GET("/resource-service/user/sendOtp")
        Call<Response<OtpModel>> sendOtp(@Query("email") String email);

        @GET("/resource-service/user/checkOtp")
        Call<Response<OtpModel>> checkOtp(@Query("email") String email, @Query("otp") String otp);

        @GET("/resource-service/user/sendOtpPassword")
        Call<Response<OtpModel>> sendOtpPassword(@Query("email") String email);

        @GET("/resource-service/user/checkOtpPassword")
        Call<Response<OtpModel>> checkOtpPassword(@Query("email") String email, @Query("otp") String otp, @Query("password") String password);
    }


    private RegisterService getService(){
        return this.getInstance(RegisterService.class);
    }

    public Observable<Response<OtpModel>> checkOtpPassword(String email, String otp, String password){
        return Observable.create(new ObservableOnSubscribe<Response<OtpModel>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<OtpModel>> emitter) throws Exception {
                getService().checkOtpPassword(email, otp, password).enqueue(new Callback<Response<OtpModel>>() {
                    @Override
                    public void onResponse(Call<Response<OtpModel>> call, retrofit2.Response<Response<OtpModel>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<OtpModel>> call, Throwable t) {

                        emitter.onError(t);

                    }
                });
            }
        });
    }

    public Observable<Response<OtpModel>> checkOtp(String email, String otp){
        return Observable.create(new ObservableOnSubscribe<Response<OtpModel>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<OtpModel>> emitter) throws Exception {
                getService().checkOtp(email, otp).enqueue(new Callback<Response<OtpModel>>() {
                    @Override
                    public void onResponse(Call<Response<OtpModel>> call, retrofit2.Response<Response<OtpModel>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<OtpModel>> call, Throwable t) {

                        emitter.onError(t);

                    }
                });
            }
        });
    }

    public Observable<Response<OtpModel>> sendOtpPassword(String email){
        return Observable.create(new ObservableOnSubscribe<Response<OtpModel>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<OtpModel>> emitter) throws Exception {

                getService().sendOtpPassword(email).enqueue(new Callback<Response<OtpModel>>() {
                    @Override
                    public void onResponse(Call<Response<OtpModel>> call, retrofit2.Response<Response<OtpModel>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<OtpModel>> call, Throwable t) {

                        emitter.onError(t);

                    }
                });


            }
        });
    }

    public Observable<Response<OtpModel>> sendOtp(String email){
        return Observable.create(new ObservableOnSubscribe<Response<OtpModel>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<OtpModel>> emitter) throws Exception {

                getService().sendOtp(email).enqueue(new Callback<Response<OtpModel>>() {
                    @Override
                    public void onResponse(Call<Response<OtpModel>> call, retrofit2.Response<Response<OtpModel>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<OtpModel>> call, Throwable t) {

                        emitter.onError(t);

                    }
                });


            }
        });
    }


    public Observable<Response<UserDetail>> createWallet(@Body MultipartBody body){

        return Observable.create(new ObservableOnSubscribe<Response<UserDetail>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<UserDetail>> emitter) throws Exception {

                getService().createWallet(body).enqueue(new Callback<Response<UserDetail>>() {
                    @Override
                    public void onResponse(Call<Response<UserDetail>> call, retrofit2.Response<Response<UserDetail>> response) {

                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }

                    }

                    @Override
                    public void onFailure(Call<Response<UserDetail>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        });

    }

    public Observable<Response<WalletInfo>> createWallet2(@Body MultipartBody body){

        return Observable.create(new ObservableOnSubscribe<Response<WalletInfo>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<WalletInfo>> emitter) throws Exception {

                getService().createWallet2(body).enqueue(new Callback<Response<WalletInfo>>() {
                    @Override
                    public void onResponse(Call<Response<WalletInfo>> call, retrofit2.Response<Response<WalletInfo>> response) {

                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }

                    }

                    @Override
                    public void onFailure(Call<Response<WalletInfo>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        });

    }

    public Observable<Response<Map<String, Boolean>>> checkPhone(final String phone){
        return Observable.create(new ObservableOnSubscribe<Response<Map<String, Boolean>>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<Map<String, Boolean>>> emitter) throws Exception {

                getService().checkPhone(phone).enqueue(new Callback<Response<Map<String, Boolean>>>() {
                    @Override
                    public void onResponse(Call<Response<Map<String, Boolean>>> call, retrofit2.Response<Response<Map<String, Boolean>>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<Map<String, Boolean>>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        });
    }

    public Observable<Response<Map<String, Boolean>>> checkEmail(final String email){
        return Observable.create(new ObservableOnSubscribe<Response<Map<String, Boolean>>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<Map<String, Boolean>>> emitter) throws Exception {

                getService().checkEmail(email).enqueue(new Callback<Response<Map<String, Boolean>>>() {
                    @Override
                    public void onResponse(Call<Response<Map<String, Boolean>>> call, retrofit2.Response<Response<Map<String, Boolean>>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<Map<String, Boolean>>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        });
    }


    public Observable<Response<User>> createUser(final MultipartBody body){

        return Observable.create(new ObservableOnSubscribe<Response<User>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<User>> emitter) throws Exception {
                getService().createUser(body).enqueue(new Callback<Response<User>>() {
                    @Override
                    public void onResponse(Call<Response<User>> call, retrofit2.Response<Response<User>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<User>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });
            }
        });

    }


}
