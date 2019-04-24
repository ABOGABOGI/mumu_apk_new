package id.hike.apps.android_mpos_mumu.features.top_up;

import id.hike.apps.android_mpos_mumu.features.top_up.model.TopUpResponse;
import id.hike.apps.android_mpos_mumu.features.top_up.model.TransferInfo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TopUpService {
    @Headers("Content-Type: application/json")
    @POST("deposit/topup")
    public Call<TopUpResponse> initiateTopUpRequest(@Header("Authorization") String auth,
                                                    @Body TransferInfo transferInfo);
}
