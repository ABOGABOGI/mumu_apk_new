package id.hike.apps.android_mpos_mumu.features.profil;
;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by root on 10/08/17.
 */

public interface SvProfil {
    @FormUrlEncoded
    @POST("employee/getuserbyid")
    Call<ModelProfil> getProfil(@Field("userId") int userId);

    @Multipart
    @POST("employee/update")
    Call<Void> updateProfilWithPhoto(
            @Part("json") RequestBody json,
            @Part MultipartBody.Part file
    );

    @Multipart
    @POST("employee/update")
    Call<Void> updateProfilNoPhoto(
            @Part("json") RequestBody json
    );

    @POST("modal/kasir/modalkirim")
    Call<ModalMsgStatus> modalKirim(@Body ReqModalKirim reqModalKirim);

    @POST("modal/kasir/modalterima")
    Call<ResponseModalTerima> modalTerima(@Query("outletId") String outletId);

    @POST("modal/kasir/modalkonfirmterima")
    Call<ResponseModalKonfirmTerima> modalKonfirmTerima(@Body ReqModalKonfirmTerima reqModalKonfirmTerima);

    @POST("modal/kasir/modalsummarytotal")
    Call<ResponseSummaryTotal> summaryTotal(@Query("outletId") String outletId);

    @POST("modal/kasir/modalsetor")
    Call<ResponseModalSetor> modalSetor(@Body ReqModalSetor reqModalSetor);
}
