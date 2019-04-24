package id.hike.apps.android_mpos_mumu.features.landing_page.api;

import java.util.List;

import id.hike.apps.android_mpos_mumu.base.RetroInstance;
import id.hike.apps.android_mpos_mumu.model.MetaInfo;
import id.hike.apps.android_mpos_mumu.model.Response;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

public class WakafApi extends RetroInstance {

    interface WakafService{
        @GET("resource-service/wakaf/")
        public Call<Response<List<MetaInfo>>> index();

        @GET("resource-service/wakaf/filtered/")
        public Call<Response<List<MetaInfo>>> filtered();
    }

    WakafService getService(){
        return getInstance(WakafService.class);
    }

    public Observable<List<MetaInfo>> index(){
        return Observable.create(new ObservableOnSubscribe<List<MetaInfo>>() {
            @Override
            public void subscribe(ObservableEmitter<List<MetaInfo>> emitter) throws Exception {
                getService().index().enqueue(new Callback<Response<List<MetaInfo>>>() {
                    @Override
                    public void onResponse(Call<Response<List<MetaInfo>>> call, retrofit2.Response<Response<List<MetaInfo>>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body().getData());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }
                    }
                    @Override
                    public void onFailure(Call<Response<List<MetaInfo>>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });
            }
        });
    }
    public Observable<List<MetaInfo>> filtered(){
        return Observable.create(new ObservableOnSubscribe<List<MetaInfo>>() {
            @Override
            public void subscribe(ObservableEmitter<List<MetaInfo>> emitter) throws Exception {
                getService().filtered().enqueue(new Callback<Response<List<MetaInfo>>>() {
                    @Override
                    public void onResponse(Call<Response<List<MetaInfo>>> call, retrofit2.Response<Response<List<MetaInfo>>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body().getData());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }
                    }
                    @Override
                    public void onFailure(Call<Response<List<MetaInfo>>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });
            }
        });
    }

}
