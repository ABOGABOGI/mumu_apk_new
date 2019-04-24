package id.hike.apps.android_mpos_mumu.api;

//import android.util.Log;
//import com.google.gson.Gson;

import java.util.List;

import id.hike.apps.android_mpos_mumu.base.RetroInstance;
import id.hike.apps.android_mpos_mumu.model.Response;
import id.hike.apps.android_mpos_mumu.model.TransaksiInfo;
import id.hike.apps.android_mpos_mumu.model.TransaksiTagihan;
import id.hike.apps.android_mpos_mumu.model.TrxWallet;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class TransaksiApi extends RetroInstance {

    interface TransaksiService{

        @POST("/resource-service/transaksi/inquiry")
        Call<Response<TransaksiTagihan>> inquiry(@Query("access_token") String token, @Query("kode_produk") String kode_produk, @Query("id_pelanggan") String id_pelanggan);
        //@POST("/resource-service/transaksi/buy")
        //Call<Response<Transaksi>> transaction(@Query("access_token") String token, @Query("kode_produk") String kode_produk, @Query("id_pelanggan") String id_pelanggan);

        @POST("/resource-service/transaksi/logging")
        Call<Response<TrxWallet>> logging(@Query("kode_transaksi") String kodeTransaksi, @Query("kode_produk") String kodeProduk, @Query("id_pelanggan") String idPelanggan, @Query("nilai") String nilai);

        @POST("/resource-service/transaksi/history")
        Call<Response<List<TransaksiInfo>>> getHistory();

    }


    private TransaksiService getService(){
        return getInstance(TransaksiService.class);
    }


    public Observable<Response<List<TransaksiInfo>>> getHistory(){

        return Observable.create(new ObservableOnSubscribe<Response<List<TransaksiInfo>>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<List<TransaksiInfo>>> emitter) throws Exception {

                getService().getHistory().enqueue(new Callback<Response<List<TransaksiInfo>>>() {
                    @Override
                    public void onResponse(Call<Response<List<TransaksiInfo>>> call, retrofit2.Response<Response<List<TransaksiInfo>>> response) {
                        if(response.isSuccessful()){

                            //Gson gson = new Gson();
                            //String json = gson.toJson(response.body());
                            //Log.i("DEBUGTAG", json);

                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<List<TransaksiInfo>>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        });

    }

    public Observable<Response<TrxWallet>> logging(final String kodeTransaksi, final String kodeProduk, final String idPelanggan, final String nilai){

        return Observable.create(new ObservableOnSubscribe<Response<TrxWallet>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<TrxWallet>> emitter) throws Exception {

                getService().logging(kodeTransaksi, kodeProduk, idPelanggan, nilai).enqueue(new Callback<Response<TrxWallet>>() {
                    @Override
                    public void onResponse(Call<Response<TrxWallet>> call, retrofit2.Response<Response<TrxWallet>> response) {

                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }

                    }

                    @Override
                    public void onFailure(Call<Response<TrxWallet>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        });

    }

    public Observable<Response<TransaksiTagihan>> inquiry(final String token, final String kodeProduk, final String idPelanggan){

        return Observable.create(new ObservableOnSubscribe<Response<TransaksiTagihan>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<TransaksiTagihan>> emitter) throws Exception {


                getService().inquiry(token, kodeProduk, idPelanggan).enqueue(new Callback<Response<TransaksiTagihan>>() {
                    @Override
                    public void onResponse(Call<Response<TransaksiTagihan>> call, retrofit2.Response<Response<TransaksiTagihan>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<TransaksiTagihan>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        });
    }
    /*
    public Observable<Response<Transaksi>> transaction(final String token, final String kodeProduk, final String idPelanggan){
        return Observable.create(new ObservableOnSubscribe<Response<Transaksi>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<Transaksi>> emitter) throws Exception {
                getService().transaction(token, kodeProduk, idPelanggan).enqueue(new Callback<Response<Transaksi>>() {
                    @Override
                    public void onResponse(Call<Response<Transaksi>> call, retrofit2.Response<Response<Transaksi>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<Transaksi>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });
            }
        });
    }
    */

}
