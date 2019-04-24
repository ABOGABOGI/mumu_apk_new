package id.hike.apps.android_mpos_mumu.features.profil.api;

import id.hike.apps.android_mpos_mumu.base.RetroInstance;
import id.hike.apps.android_mpos_mumu.model.Response;
import id.hike.apps.android_mpos_mumu.model.User;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class UserApi extends RetroInstance {

    interface UserService{

        @POST("/resource-service/user/changePassword")
        Call<Response<User>> changePassword(@Query("password") String password, @Query("old_password") String oldPassword);

    }


    UserService getService(){
        return getInstance(UserService.class);
    }


    public Observable<Response<User>> changePassword(final String password, final String oldPassword){

        return Observable.create(new ObservableOnSubscribe<Response<User>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<User>> emitter) throws Exception {

                getService().changePassword(password, oldPassword).enqueue(new Callback<Response<User>>() {


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
