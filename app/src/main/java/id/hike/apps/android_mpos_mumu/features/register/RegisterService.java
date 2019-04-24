package id.hike.apps.android_mpos_mumu.features.register;

import id.hike.apps.android_mpos_mumu.features.register.model.RegisterData;
import id.hike.apps.android_mpos_mumu.features.register.model.ResponseUser;
import id.hike.apps.android_mpos_mumu.features.register.model.TokenResponse;
import id.hike.apps.android_mpos_mumu.features.register.model.UserPIN;
import id.hike.apps.android_mpos_mumu.features.register.model.WalletResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by root on 24/07/17.
 */

public interface RegisterService {

    @POST("registration/registeruser")
    public Call<ResponseUser> registerUser(@Body RegisterData userData);

    @POST("token")
    @FormUrlEncoded
    public Call<TokenResponse> getToken(@Header("Authorization") String auth,
                                        @Field("username") String username,
                                        @Field("password") String password,
                                        @Field("client_id") String clientId,
                                        @Field("grant_type") String grantType);

    @Headers("Content-Type: application/json")
    @POST("deposit/adddemodeposit")
    public Call<WalletResponse> addDemoDeposit(@Header("Authorization") String auth,
                                               @Body UserPIN userpin);
}