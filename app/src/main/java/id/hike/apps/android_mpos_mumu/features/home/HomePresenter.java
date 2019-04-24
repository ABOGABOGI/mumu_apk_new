package id.hike.apps.android_mpos_mumu.features.home;

import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.util.DBTransaction;

/**
 * Created by root on 28/09/17.
 */

public class HomePresenter {
    Home home;
    SharedPreferences secPrefs;
    HomeContract.HomeInteractor homeInteractor;

    public HomePresenter(Home home, SharedPreferences secPrefs) {
        this.home = home;
        this.secPrefs = secPrefs;
        homeInteractor = new HomeInteractorImpl(new DBTransaction(home));
    }



    void setAbMainHide(boolean hide) {
        if (hide) {
            home.abMain.setVisibility(View.GONE);
        } else {
            home.abMain.setVisibility(View.VISIBLE);
        }
    }

    void setSearchTabHide(boolean mode) {
        if (mode) {
            home.tabSearch.setVisibility(View.GONE);
        } else {
            home.tabSearch.setVisibility(View.VISIBLE);
        }
    }

    void setToolbarTitle(String title) {
        ((TextView) home.findViewById(R.id.abTvTitle)).setText(title);
    }

    public void showSnackbar() {
        LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        margin.setMargins(0, 0, 0, home.snackBarBeli.getHeight());
        home.transLayout.setLayoutParams(margin);
        home.snackBarBeli.animate().alpha(1).y(home.homeContainer.getHeight() - (home.snackBarBeli.getHeight()));

        setPriceOnSnackbar();
    }

    void setPriceOnSnackbar() {
        if (isRegisteredUserChoosen()) {
            //if registered prefsKostumerName_STR exist

            home.tvTotalPriceOnSnackbar.setText(homeInteractor.getTotalHarga(
                    secPrefs.getInt(Cfg.prefsKostumerId_Long, Cfg.defaultKostumerId),
                    secPrefs.getString(Cfg.prefsRecentTransIdStr, null
                    )));
        } else {
            //if no exist
            home.tvTotalPriceOnSnackbar.setText(homeInteractor.getTotalHarga(
                    Cfg.defaultKostumerId,
                    secPrefs.getString(Cfg.prefsRecentTransIdStr, null
                    )));
        }
    }

    boolean isRegisteredUserChoosen() {
        if (!secPrefs.getString(Cfg.prefsKostumerName_STR, Cfg.defaultKostumerName).equalsIgnoreCase(Cfg.defaultKostumerName)) {
            return true;
        }
        return false;
    }
}