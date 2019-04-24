package id.hike.apps.android_mpos_mumu.features.profil;

import java.util.List;

import id.hike.apps.android_mpos_mumu.base.RetroInstance;
import id.hike.apps.android_mpos_mumu.model.Response;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ProdukApiDua extends RetroInstance {

    public static ProdukApiDua create(){
        return new ProdukApiDua();
    }

    interface ProdukService {

        @GET("/resource-service/produk/byPrefix")
        Call<Response<List<List<String>>>> getByPrefix(@Query("access_token") String token, @Query("prefix") String prefix, @Query("kategori") String kategori);

        @GET("/resource-service/produk/byKategori")
        Call<Response<List<List<String>>>> getByKategori(@Query("access_token") String token, @Query("kategori") String kategori, @Query("sub_kategori") String sub_kategori);

        @GET("/resource-service/produk/zakat")
        Call<Response<List<List<String>>>> getProdukZakat(@Query("access_token") String token);

        @GET("/resource-service/produk/zakatSub")
        Call<Response<List<List<String>>>> getProdukZakatSub(@Query("access_token") String token, @Query("kategori") String kategori);

        @GET("/resource-service/produk/donasi")
        Call<Response<List<List<String>>>> getProdukDonasi(@Query("access_token") String token);

        @GET("/resource-service/produk/donasiSub")
        Call<Response<List<List<String>>>> getProdukDonasiSub(@Query("access_token") String token, @Query("kategori") String kategori);

    }

    private ProdukService getService(){
        return getInstance(ProdukService.class);
    }

    public Observable<Response<List<List<String>>>> getByPrefix(final String token, final String prefix, String kategori){
        return Observable.create(new ObservableOnSubscribe<Response<List<List<String>>>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<List<List<String>>>> emitter) throws Exception {

                getService().getByPrefix(token,prefix, kategori).enqueue(new Callback<Response<List<List<String>>>>() {
                    @Override
                    public void onResponse(Call<Response<List<List<String>>>> call, retrofit2.Response<Response<List<List<String>>>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }

                    }

                    @Override
                    public void onFailure(Call<Response<List<List<String>>>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        });
    }

    public Observable<Response<List<List<String>>>> getByKategori(final String token, final String kategori, String sub_kategori){
        return Observable.create(new ObservableOnSubscribe<Response<List<List<String>>>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<List<List<String>>>> emitter) throws Exception {

                getService().getByKategori(token, kategori, sub_kategori).enqueue(new Callback<Response<List<List<String>>>>() {
                    @Override
                    public void onResponse(Call<Response<List<List<String>>>> call, retrofit2.Response<Response<List<List<String>>>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }

                    }

                    @Override
                    public void onFailure(Call<Response<List<List<String>>>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        });
    }

    public Observable<Response<List<List<String>>>> getProdukZakat(String token){
        return Observable.create(new ObservableOnSubscribe<Response<List<List<String>>>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<List<List<String>>>> emitter) throws Exception {

                getService().getProdukZakat(token).enqueue(new Callback<Response<List<List<String>>>>() {
                    @Override
                    public void onResponse(Call<Response<List<List<String>>>> call, retrofit2.Response<Response<List<List<String>>>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }

                    }

                    @Override
                    public void onFailure(Call<Response<List<List<String>>>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        });
    }

    public Observable<Response<List<List<String>>>> getProdukZakatSub(final String token, final String kategori){
        return Observable.create(new ObservableOnSubscribe<Response<List<List<String>>>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<List<List<String>>>> emitter) throws Exception {

                getService().getProdukZakatSub(token, kategori).enqueue(new Callback<Response<List<List<String>>>>() {
                    @Override
                    public void onResponse(Call<Response<List<List<String>>>> call, retrofit2.Response<Response<List<List<String>>>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }

                    }

                    @Override
                    public void onFailure(Call<Response<List<List<String>>>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        });
    }

    public Observable<Response<List<List<String>>>> getProdukDonasi(final String token){
        return Observable.create(new ObservableOnSubscribe<Response<List<List<String>>>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<List<List<String>>>> emitter) throws Exception {

                getService().getProdukDonasi(token).enqueue(new Callback<Response<List<List<String>>>>() {
                    @Override
                    public void onResponse(Call<Response<List<List<String>>>> call, retrofit2.Response<Response<List<List<String>>>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }

                    }

                    @Override
                    public void onFailure(Call<Response<List<List<String>>>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        });
    }

    public Observable<Response<List<List<String>>>> getProdukDonasiSub(final String token, final String kategori){
        return Observable.create(new ObservableOnSubscribe<Response<List<List<String>>>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<List<List<String>>>> emitter) throws Exception {

                getService().getProdukDonasiSub(token, kategori).enqueue(new Callback<Response<List<List<String>>>>() {
                    @Override
                    public void onResponse(Call<Response<List<List<String>>>> call, retrofit2.Response<Response<List<List<String>>>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }

                    }

                    @Override
                    public void onFailure(Call<Response<List<List<String>>>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        });
    }
}
