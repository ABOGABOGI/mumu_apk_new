package id.hike.apps.android_mpos_mumu.features.payment;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by M Alvin Syahrin on 4/10/2018.
 */

public interface DigitalProductPaymentService {

    @FormUrlEncoded
    @POST("transaction/paymentConfirm")
    Call<ResponseBody> paymentConfirm(
            @Header("Authorization") String authKey,
            @Field("id_transaksi") String transactionId,
            @Field("customer_pay")String customerPay,
            @Field("sales_price")String salesPrice,
            @Field("change")String change
            );
}
