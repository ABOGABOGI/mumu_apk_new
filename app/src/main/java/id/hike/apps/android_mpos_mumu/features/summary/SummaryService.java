package id.hike.apps.android_mpos_mumu.features.summary;

import com.google.gson.JsonObject;

import id.hike.apps.android_mpos_mumu.features.payment.model.ResDataSuccess;
import id.hike.apps.android_mpos_mumu.features.summary.model.ReqBatchDeleteItem;
import id.hike.apps.android_mpos_mumu.features.summary.model.ReqGlobal;
import id.hike.apps.android_mpos_mumu.features.summary.model.ResCheckListStock;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by root on 10/07/17.
 */

public interface SummaryService {
    @POST("transaksi/bataltransaksi")
    Call<ResDataSuccess> batalTransaksi(@Body ReqBatchDeleteItem reqBatchDeleteItem);

    @POST("global/globalitemtransaksi")
    Call<ResCheckListStock> sendItemTransaction(@Body ReqGlobal reqGlobal);

    @POST("global/globalcekitemtransaksi")
    Call<ResCheckListStock> checkGlobalStockItem(@Body ReqGlobal reqGlobal);

    @FormUrlEncoded
    @POST("createproc")
    Call<JsonObject> createTransaksi(
            @Header("Authorization") String authKey,
            @Field("kdBank") String kdBank,
            @Field("kdProduk")String kdProduk,
            @Field("msidn") String msidn
    );
}