package id.hike.apps.android_mpos_mumu.regstep.api;

import java.util.List;

import id.hike.apps.android_mpos_mumu.base.RetroInstance;
import id.hike.apps.android_mpos_mumu.model.Kecamatan;
import id.hike.apps.android_mpos_mumu.model.Kelurahan;
import id.hike.apps.android_mpos_mumu.model.Kodya;
import id.hike.apps.android_mpos_mumu.model.Propinsi;
import id.hike.apps.android_mpos_mumu.model.Response;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MasterApi extends RetroInstance {



    public static MasterApi create(){
        return new MasterApi();
    }

    interface MasterService{

        @GET("/resource-service/mmas/propinsi")
        Call<Response<List<Propinsi>>> getPropinsi(@Query("access_token") String accessToken);

        @GET("/resource-service/mmas/kodya")
        Call<Response<List<Kodya>>> getKodya(@Query("propinsi") String propinsi , @Query("access_token") String accessToken);

        @GET("/resource-service/mmas/kecamatan")
        Call<Response<List<Kecamatan>>> getKecamatan(@Query("kodya") String kodya, @Query("access_token") String accessToken);

        @GET("/resource-service/mmas/kelurahan")
        Call<Response<List<Kelurahan>>> getKelurahan(@Query("kecamatan") String kelurahan, @Query("access_token") String accessToken);


    }


    private MasterService getService(){
        return getInstance(MasterService.class);
    }


    public Observable<Response<List<Kelurahan>>> getKelurahan(final String kecamatan, final String token){
        return Observable.create(new ObservableOnSubscribe<Response<List<Kelurahan>>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<List<Kelurahan>>> emitter) throws Exception {

                getService().getKelurahan(kecamatan, token).enqueue(new Callback<Response<List<Kelurahan>>>() {
                    @Override
                    public void onResponse(Call<Response<List<Kelurahan>>> call, retrofit2.Response<Response<List<Kelurahan>>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }

                    }

                    @Override
                    public void onFailure(Call<Response<List<Kelurahan>>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        });
    }

    public Observable<Response<List<Kecamatan>>> getKecamatan(final String kodya, final String token){
        return Observable.create(new ObservableOnSubscribe<Response<List<Kecamatan>>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<List<Kecamatan>>> emitter) throws Exception {

                getService().getKecamatan(kodya, token).enqueue(new Callback<Response<List<Kecamatan>>>() {
                    @Override
                    public void onResponse(Call<Response<List<Kecamatan>>> call, retrofit2.Response<Response<List<Kecamatan>>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }

                    }

                    @Override
                    public void onFailure(Call<Response<List<Kecamatan>>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        });
    }

    public Observable<Response<List<Propinsi>>> getPropinsi(final String token){

        return Observable.create(new ObservableOnSubscribe<Response<List<Propinsi>>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<List<Propinsi>>> emitter) throws Exception {

                getService().getPropinsi(token).enqueue(new Callback<Response<List<Propinsi>>>() {
                    @Override
                    public void onResponse(Call<Response<List<Propinsi>>> call, retrofit2.Response<Response<List<Propinsi>>> response) {

                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }

                    }

                    @Override
                    public void onFailure(Call<Response<List<Propinsi>>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        });

    }


    public Observable<Response<List<Kodya>>> getKodya(final String propinsi, final String token){
        return Observable.create(new ObservableOnSubscribe<Response<List<Kodya>>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<List<Kodya>>> emitter) throws Exception {

                getService().getKodya(propinsi, token).enqueue(new Callback<Response<List<Kodya>>>() {
                    @Override
                    public void onResponse(Call<Response<List<Kodya>>> call, retrofit2.Response<Response<List<Kodya>>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<List<Kodya>>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        });
    }

}
