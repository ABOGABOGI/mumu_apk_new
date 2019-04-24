package id.hike.apps.android_mpos_mumu.features.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseFragment;
import id.hike.apps.android_mpos_mumu.features.home.fragment_child.ListrikPrepaid;

public class ListrikFragment extends BaseFragment {


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

        FragmentPagerItems items = FragmentPagerItems.with(getContext())
//                .add("TAGIHAN", ListrikPostpaid.class)
                .add("TOKEN", ListrikPrepaid.class)
                .create();

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getChildFragmentManager(), items);
        viewPager.setAdapter(adapter);

        tabLayout.setViewPager(viewPager);
    }

}
