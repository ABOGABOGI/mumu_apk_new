package id.hike.apps.android_mpos_mumu.features.pelanggan;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by getwiz on 13/06/17.
 */

public interface SvPelanggan {
    @POST("customer/msavecustomer/")
    Call<ModelAddPelanggan> tambahKostumer(@Query("outletId") String outletId,
                                           @Body RqPelanggan.RqTambahPelanggan tambahPelanggan);

    @POST("customer/getall")
    Call<ModelPelanggan> getPelanggan(@Query("outletId") String outletId);

    @POST("customer/getcustomer")
    Call<ModelPelanggan> searchPelanggan(@Query("customer") String namaCustomer,
                                         @Query("outletId") String outletId);


    @POST("token")
    Call<ModelGenerateToken> getGeneratedToken(@Header("Authorization") String consumenKeySecret,@Query("grant_type") String grantType,@Query("kasir") String userName,@Query("password") String password/*,@Body RqPelanggan.RqGenerateToken rqGenerateToken*/);

    /*@POST("mpos/produk/1.0/data")
    Call<> getResponLogin3Data(@Headers("Authorization") String accessToken,)*/

}
