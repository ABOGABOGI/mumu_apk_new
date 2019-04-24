package id.hike.apps.android_mpos_mumu.features.aktifitas;

import id.hike.apps.android_mpos_mumu.features.aktifitas.model.ResAktifitasNew;
import id.hike.apps.android_mpos_mumu.features.receipt_invoice.ModelStruk;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by root on 01/08/17.
 */

public interface AktifitasService {
    @POST("transaksi/activity")
    Call<ResAktifitasNew> getAktifitas(@Query("outletId") String outletId);

    @POST("transaksi/struk")
    Call<ModelStruk> getStrukData(@Query("transid") String transid, @Query("outletid") String outletId);
}
