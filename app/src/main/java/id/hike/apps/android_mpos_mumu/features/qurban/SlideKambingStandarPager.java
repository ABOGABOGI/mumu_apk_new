package id.hike.apps.android_mpos_mumu.features.qurban;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import id.hike.apps.android_mpos_mumu.FragmentPulangPergi;
import id.hike.apps.android_mpos_mumu.FragmentSekaliJalan;
import id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.FragmentOnlinePaymentKurban;
import id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.FragmentQurbanSapi;
import id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.FragmentTransferBankKurban;

public class SlideKambingStandarPager extends FragmentPagerAdapter {

    private int numOfTabs;

    public SlideKambingStandarPager(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentTransferBankKurban();
            case 1:
                return new FragmentOnlinePaymentKurban();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
