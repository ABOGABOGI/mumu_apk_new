package id.hike.apps.android_mpos_mumu.features.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseFragment;
import id.hike.apps.android_mpos_mumu.features.home.fragment_child.ListrikPrepaid;
import id.hike.apps.android_mpos_mumu.features.pln.FragmentListrikPascaBayar;
import id.hike.apps.android_mpos_mumu.features.wakaf.FragmentDonatur;
import id.hike.apps.android_mpos_mumu.features.wakaf.FragmentSinopsis;
import id.hike.apps.android_mpos_mumu.features.wakaf.WakafDetailActivity;

public class ListrikFragment extends Fragment {


    @BindView(R.id.smartTab)
    SmartTabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){

        View v = inflater.inflate(R.layout.frag_listrik2, container, false);

        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);

        ((Home) getActivity()).homePresenter.setToolbarTitle(getString(R.string.title_pembelian_listrik));

        /*FragmentPagerItems items = FragmentPagerItems.with(getContext())
//                .add("TAGIHAN", ListrikPostpaid.class)
                .add("TOKEN", ListrikPrepaid.class)
                .create();*/

        //FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getChildFragmentManager(), items);
        //viewPager.setAdapter(adapter);

        setupViewPager(viewPager);
        tabLayout.setViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new ListrikPrepaid(), "TOKEN");
        adapter.addFragment(new FragmentListrikPascaBayar(), "PASCA BAYAR");
        viewPager.setAdapter(adapter);
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
