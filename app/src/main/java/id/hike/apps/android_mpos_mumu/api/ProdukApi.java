package id.hike.apps.android_mpos_mumu.api;

import java.util.List;

import id.hike.apps.android_mpos_mumu.base.RetroInstance;
import id.hike.apps.android_mpos_mumu.model.Produk;
import id.hike.apps.android_mpos_mumu.model.Response;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ProdukApi extends RetroInstance {

    interface ProdukService{

        @GET("/resource-service/produk/byPrefix")
        Call<Response<List<Produk>>> getByPrefix(@Query("access_token") String token, @Query("prefix") String prefix, @Query("kategori") String kategori);

        @GET("/resource-service/produk/byKategori")
        Call<Response<List<Produk>>> getByKategori(@Query("access_token") String token, @Query("kategori") String kategori, @Query("sub_kategori") String sub_kategori);

    }


    private ProdukService getService(){
        return getInstance(ProdukService.class);
    }


    public Observable<Response<List<Produk>>> getByPrefix(final String accessToken, final String prefix, final String kategori){

        return Observable.create(new ObservableOnSubscribe<Response<List<Produk>>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<List<Produk>>> emitter) throws Exception {

                getService().getByPrefix(accessToken, prefix, kategori).enqueue(new Callback<Response<List<Produk>>>() {
                    @Override
                    public void onResponse(Call<Response<List<Produk>>> call, retrofit2.Response<Response<List<Produk>>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<List<Produk>>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        });

    }


    public Observable<Response<List<Produk>>> getByKategori(final String accessToken, final String kategori, final String sub_kategori){

        return Observable.create(new ObservableOnSubscribe<Response<List<Produk>>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<List<Produk>>> emitter) throws Exception {

                getService().getByKategori(accessToken, kategori, sub_kategori).enqueue(new Callback<Response<List<Produk>>>() {
                    @Override
                    public void onResponse(Call<Response<List<Produk>>> call, retrofit2.Response<Response<List<Produk>>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<List<Produk>>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        });

    }


}
