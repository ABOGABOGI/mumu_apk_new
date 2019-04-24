package id.hike.apps.android_mpos_mumu.features.home;


/**
 * Created by root on 28/09/17.
 */

public interface HomeContract {
    interface HomeInteractor{
        String getTotalHarga(int kostumerId, String recentTransId);
    }
}
