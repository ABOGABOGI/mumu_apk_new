package id.hike.apps.android_mpos_mumu.features.qurban;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import id.hike.apps.android_mpos_mumu.DestinasiPager;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.FragmentQurbanKambingPremium;
import id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.FragmentQurbanKambingStandar;
import id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.FragmentQurbanSapi;
import id.hike.apps.android_mpos_mumu.features.wakaf.FragmentDonatur;
import id.hike.apps.android_mpos_mumu.features.wakaf.FragmentSinopsis;
import id.hike.apps.android_mpos_mumu.features.wakaf.WakafDetailActivity;

public class MumuQurban extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mumu_qurban);

        btnBack =findViewById(R.id.backButton);
        btnBack.setOnClickListener(v -> finish());

        viewPager = findViewById(R.id.vpBanner);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabIndicator);
        tabLayout.setupWithViewPager(viewPager);
        //mumuQurbanPager = new MumuQurbanPager(getSupportFragmentManager(), tabLayout.getTabCount());
        //viewPager.setAdapter(mumuQurbanPager);
        //tabLayout.setOnTouchListener();
        //tabLayout.setupWithViewPager(viewPager, true);
       // viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void setupViewPager(ViewPager viewPager) {
        MumuQurban.ViewPagerAdapter adapter = new MumuQurban.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentQurbanKambingStandar());
        adapter.addFragment(new FragmentQurbanKambingPremium());
        adapter.addFragment(new FragmentQurbanSapi());
        viewPager.setAdapter(adapter);
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        //private final List<String> mFragmentTitleList = new ArrayList<>();

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

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
            //mFragmentTitleList.add(title);
        }
    }
}
