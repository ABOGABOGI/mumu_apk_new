package id.hike.apps.android_mpos_mumu.features.summary;

import android.content.SharedPreferences;

import java.util.List;

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.features.summary.model.ReqBuyProduct;
import id.hike.apps.android_mpos_mumu.global.global_model.ModelAppInfo;
import id.hike.apps.android_mpos_mumu.global.global_model.ModelTransaksi;
import id.hike.apps.android_mpos_mumu.global.global_model.ModelTransaksiPpob;
import id.hike.apps.android_mpos_mumu.util.DBTransaction;

/**
 * Created by root on 24/09/17.
 */

public class SummaryInteractorImpl implements SummaryContract.SummaryInteractor{

    DBTransaction dbTransaction;

    public SummaryInteractorImpl(DBTransaction dbTransaction) {
        this.dbTransaction = dbTransaction;
    }

    @Override
    public List<ModelTransaksi> getTransactionList(SharedPreferences secPrefs) {
        return dbTransaction.getTransactionList(secPrefs.getString(Cfg.prefsRecentTransIdStr, null));
    }

    @Override
    public List<ReqBuyProduct> getTransactionModel(SharedPreferences secPrefs) {
        return dbTransaction.getTransactionModel(secPrefs.getString(Cfg.prefsRecentTransIdStr, null), "");
    }

    @Override
    public void deleteTransactionItem(List<Long> productId, SharedPreferences secPrefs){
        dbTransaction.deleteTransaksi(productId, secPrefs.getString(Cfg.prefsRecentTransIdStr, null));
    }

    @Override
    public Long getItemCountOnTrans(SharedPreferences secPrefs) {
        return dbTransaction.getItemCountOnTrans(secPrefs.getString(Cfg.prefsRecentTransIdStr, null));
    }

    @Override
    public void updateJumlah(int jumlahBeli, SharedPreferences secPrefs, int productId) {
        dbTransaction.updateJumlah(jumlahBeli, secPrefs.getString(Cfg.prefsRecentTransIdStr, null), productId);
    }

    @Override
    public void deleteTransaksi(List<Long> productId, SharedPreferences secPrefs) {
        dbTransaction.deleteTransaksi(productId, secPrefs.getString(Cfg.prefsRecentTransIdStr, null));
    }

    @Override
    public void updateLocalSalesPrice(int transId, long savedSalesPrice) {
        dbTransaction.updateLocalSalesPrice(transId, savedSalesPrice);
    }

    @Override
    public Long getLocalSalesPrice(int transId) {
        return dbTransaction.getLocalSalesPrice(transId);
    }

    @Override
    public Integer getBaselineStockByTransId(int transaId) {
        return dbTransaction.getBaselineStockByTransId(transaId);
    }

    @Override
    public Integer getStockByTransId(int transaId) {
        return dbTransaction.getStockByTransId(transaId);
    }

    @Override
    public void updateStock(Long stock, SharedPreferences secPrefs, Long productId) {
        dbTransaction.updateStock(stock,secPrefs.getString(Cfg.prefsRecentTransIdStr,null),
                productId);
    }

    @Override
    public void updateStockDanJumlah(Long stock, Long jumlahBeli, SharedPreferences secPrefs, Long productId) {
        dbTransaction.updateStockDanJumlah(stock,
                jumlahBeli,
                secPrefs.getString(Cfg.prefsRecentTransIdStr,null),
                productId);
    }

    @Override
    public ModelAppInfo getModelAppInfo() {
        return dbTransaction.getAppInfo();
    }

    @Override
    public List<ModelTransaksiPpob> getPpobTransactionList(SharedPreferences secPrefs) {
        return dbTransaction.getPpobTransactionList(secPrefs.getString(Cfg.pulsaTransIdKey, null));
    }

    @Override
    public ModelTransaksiPpob getPpobTransaction() {
        return dbTransaction.getOnePpobTransaction();
    }

    @Override
    public Boolean checkExistingPpob(){
        return dbTransaction.checkExistingPpob();
    }

    @Override
    public ModelTransaksiPpob getPpobTransactionById(String id) {
        return dbTransaction.getOnePpobTransactionById(id);
    }
}
