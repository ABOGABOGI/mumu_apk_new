package id.hike.apps.android_mpos_mumu.features.qurban;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.FragmentOnlinePaymentKambingPremium;
import id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.FragmentOnlinePaymentKurban;
import id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.FragmentOnlinePaymentKurbanSapi;
import id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.FragmentTransferBankKurban;
import id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.FragmentTransferBankKurbanKambingPremium;
import id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.FragmentTransferBankSapi;

public class SlideSapiPager extends FragmentPagerAdapter {

    private int numOfTabs;

    public SlideSapiPager(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentTransferBankSapi();
            case 1:
                return new FragmentOnlinePaymentKurbanSapi();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
