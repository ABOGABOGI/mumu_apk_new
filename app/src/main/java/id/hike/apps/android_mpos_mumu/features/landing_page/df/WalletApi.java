package id.hike.apps.android_mpos_mumu.features.landing_page.df;

import android.util.Log;

import id.hike.apps.android_mpos_mumu.base.RetroInstance;
import id.hike.apps.android_mpos_mumu.model.Response;
import id.hike.apps.android_mpos_mumu.model.TopupModel;
import id.hike.apps.android_mpos_mumu.model.WalletInfo;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class WalletApi extends RetroInstance {


    public static WalletApi create(){
        return new WalletApi();
    }

    interface WalletService{

        @GET("/resource-service/wallet/info")
        Call<Response<WalletInfo>> checkWallet(@Query("access_token") String token);

        @POST("/resource-service/wallet/topup")
        Call<Response<TopupModel>> topup(@Query("nominal") String nominal);

    }


    public WalletService getService(){
        return getInstance(WalletService.class);
    }


    public Observable<Response<TopupModel>> topup(final String nominal){

        return Observable.create(new ObservableOnSubscribe<Response<TopupModel>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<TopupModel>> emitter) throws Exception {

                getService().topup(nominal).enqueue(new Callback<Response<TopupModel>>() {
                    @Override
                    public void onResponse(Call<Response<TopupModel>> call, retrofit2.Response<Response<TopupModel>> response) {

                        Log.i("DEBUGTAG", "WalletService.toput.onResponse");
                        Log.i("DEBUGTAG", response.message());

                        if(response.isSuccessful()){

                            emitter.onNext(response.body());

                        }else{
                            emitter.onError(new Exception(response.message()));
                        }

                    }

                    @Override
                    public void onFailure(Call<Response<TopupModel>> call, Throwable t) {
                        Log.i("DEBUGTAG", "WalletService.toput.onFailure");
                        Log.i("DEBUGTAG", t.getMessage());
                        t.printStackTrace();
                        emitter.onError(t);
                    }
                });

            }
        });

    }

    public Observable<Response<WalletInfo>> walletInfo(final String token){

        return Observable.create(new ObservableOnSubscribe<Response<WalletInfo>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<WalletInfo>> emitter) throws Exception {

                getService().checkWallet(token).enqueue(new Callback<Response<WalletInfo>>() {
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

}
