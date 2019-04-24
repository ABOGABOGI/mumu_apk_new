package id.hike.apps.android_mpos_mumu.features.payment;

import id.hike.apps.android_mpos_mumu.features.landing_page.model.ResEditTrans;
import id.hike.apps.android_mpos_mumu.features.payment.model.ResDataSuccess;
import id.hike.apps.android_mpos_mumu.features.payment.model.ResPaymentType;
import id.hike.apps.android_mpos_mumu.features.payment.model.RqPaidTrans;
import id.hike.apps.android_mpos_mumu.global.global_model.ReqStok;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by root on 24/07/17.
 */

public interface PaymentService {
    @POST("transaksi/edittrans")
    Call<ResEditTrans> editTrans(@Query("transId") String transId);

    @POST("transaksi/paidtransaksi")
    Call<ResDataSuccess> paidTrans(@Body RqPaidTrans rqPaidTrans);

    @POST("transaksi/paidtransaksi2")
    Call<ResDataSuccess> paidTrans2(
            @Query("transId") String transId,
            @Query("change") double change,
            @Query("negoDisc") double negoDisc,
            @Query("custPay") double custPay,
            @Query("totalDisc") double totalDisc,
            @Query("totalTrans") double totalTrans,

            @Query("totalItem") double totalItem,
            @Query("totalPay") double totalPayment,
            @Query("totalPrice") double totalPrice,
            @Query("itemStatus") String itemStatus,
            @Body ReqStok reqStok);

    @GET("transaksi/paymenttype")
    Call<ResPaymentType> getPaymentType();
}