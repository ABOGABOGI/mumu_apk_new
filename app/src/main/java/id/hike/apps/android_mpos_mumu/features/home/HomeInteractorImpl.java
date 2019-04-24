package id.hike.apps.android_mpos_mumu.features.home;

import id.hike.apps.android_mpos_mumu.util.DBTransaction;

/**
 * Created by root on 28/09/17.
 */

public class HomeInteractorImpl implements HomeContract.HomeInteractor {
    DBTransaction dbTransaction;

    public HomeInteractorImpl(DBTransaction dbTransaction) {
        this.dbTransaction = dbTransaction;
    }

    @Override
    public String getTotalHarga(int kostumerId, String recentTransId) {
        return dbTransaction.getTotalHarga(kostumerId, recentTransId);
    }
}