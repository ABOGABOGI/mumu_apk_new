package id.hike.apps.android_mpos_mumu.features.login.api;

import android.util.Log;

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.base.RetroInstance;
import id.hike.apps.android_mpos_mumu.model.OauthToken;
import id.hike.apps.android_mpos_mumu.model.Response;
import id.hike.apps.android_mpos_mumu.model.User;
import id.hike.apps.android_mpos_mumu.util.ApiClient;
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

public class LoginApi extends RetroInstance {

    public static LoginApi create(){
        return new LoginApi();
    }

    interface LoginService{

        @POST("/oauth/token")
        Call<OauthToken> loginOauth(@Body MultipartBody body);

        @GET("/resource-service/user/")
        Call<Response<User>> getUser(@Query("access_token") String token);
    }


    private LoginService getService(){
        return getInstance(LoginService.class);
    }


    public Observable<OauthToken> loginOauth(final String username, final String password){

        LoginService service = ApiClient.serviceGeneratorOauth(Cfg.BASE_URL_DEV_TOKEN).create(LoginService.class);

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("username", username);
        builder.addFormDataPart("password", password);
        builder.addFormDataPart("grant_type", "password");
        builder.addFormDataPart("client_id", Cfg.oauthClientId);

        return Observable.create(new ObservableOnSubscribe<OauthToken>() {
            @Override
            public void subscribe(ObservableEmitter<OauthToken> emitter) throws Exception {

                service.loginOauth(builder.build()).enqueue(new Callback<OauthToken>() {
                    @Override
                    public void onResponse(Call<OauthToken> call, retrofit2.Response<OauthToken> response) {

                        Log.i("BOWOTAG", response.message());
                        Log.i("BOWOTAG", response.toString());

                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<OauthToken> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        });

    }


    public Observable<Response<User>> getUser(String token){
        return Observable.create(new ObservableOnSubscribe<Response<User>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<User>> emitter) throws Exception {

                getService().getUser(token).enqueue(new Callback<Response<User>>() {
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
