package id.hike.apps.android_mpos_mumu.features.summary;

import android.content.SharedPreferences;

import java.util.List;

import id.hike.apps.android_mpos_mumu.features.summary.model.ReqBuyProduct;
import id.hike.apps.android_mpos_mumu.global.global_model.ModelAppInfo;
import id.hike.apps.android_mpos_mumu.global.global_model.ModelTransaksi;
import id.hike.apps.android_mpos_mumu.global.global_model.ModelTransaksiPpob;

/**
 * Created by root on 21/09/17.
 */

public interface SummaryContract {
    interface SummaryInteractor {
        List<ModelTransaksi> getTransactionList(SharedPreferences secPrefs);

        List<ReqBuyProduct> getTransactionModel(SharedPreferences secPrefs);

        void deleteTransactionItem(List<Long> productId, SharedPreferences secPrefs);

        Long getItemCountOnTrans(SharedPreferences secPrefs);

        void updateJumlah(int jumlahBeli, SharedPreferences secPrefs, int productId);

        void deleteTransaksi(List<Long> productId, SharedPreferences secPrefs);

        void updateLocalSalesPrice(int transId, long savedSalesPrice);

        Long getLocalSalesPrice(int transId);

        Integer getBaselineStockByTransId(int transaId);

        Integer getStockByTransId(int transaId);

        void updateStock(Long stock,SharedPreferences secPrefs, Long productId);

        void updateStockDanJumlah(Long stock, Long jumlahBeli, SharedPreferences secPrefs, Long productId);

        ModelAppInfo getModelAppInfo();

        List<ModelTransaksiPpob> getPpobTransactionList(SharedPreferences secPrefs);

        ModelTransaksiPpob getPpobTransaction();

        ModelTransaksiPpob getPpobTransactionById(String id);

        Boolean checkExistingPpob();
    }

    interface SummaryInteractorInPres {

    }
}
