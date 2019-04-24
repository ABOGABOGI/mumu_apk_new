package id.hike.apps.android_mpos_mumu.features.donasi;

import id.hike.apps.android_mpos_mumu.features.donasi.model.Donation;
import id.hike.apps.android_mpos_mumu.features.donasi.model.MsgResponse;
import id.hike.apps.android_mpos_mumu.features.donasi.model.Transaction;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface DonasiService {
    @Headers("Content-Type: application/json")
    @POST("donasi/denomfromwallet")
    public Call<MsgResponse<Transaction>> denomfromwallet(@Header("Authorization") String auth,
                                                          @Body Donation donation);
}
