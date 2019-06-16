package id.hike.apps.android_mpos_mumu.features.destinansi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.DestinasiPager;
import id.hike.apps.android_mpos_mumu.FragmentPulangPergi;
import id.hike.apps.android_mpos_mumu.FragmentSekaliJalan;
import id.hike.apps.android_mpos_mumu.R;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class DestinasiActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    DestinasiPager pageAdapter;
    TabItem tabChats;
    TabItem tabStatus;
    TabItem tabCalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinasi);

        toolbar = findViewById(R.id.toolbar_akad);
        //toolbar.setTitle(getResources().getString(R.string.app_name));
        //setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tablayout);
        tabChats = findViewById(R.id.tabChats);
        tabStatus = findViewById(R.id.tabStatus);
        //tabCalls = findViewById(R.id.tabCalls);
        viewPager = findViewById(R.id.viewPager);

        pageAdapter = new DestinasiPager(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                /*try {
                    if (tab.getPosition() == 1) {
                        toolbar.setBackgroundColor(ContextCompat.getColor(DestinasiActivity.this,
                                R.color.colorAccent));
                        tabLayout.setBackgroundColor(ContextCompat.getColor(DestinasiActivity.this,
                                R.color.colorAccent));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(ContextCompat.getColor(DestinasiActivity.this,
                                    R.color.colorAccent));
                        }
                    } else {
                        toolbar.setBackgroundColor(ContextCompat.getColor(DestinasiActivity.this,
                                android.R.color.darker_gray));
                        tabLayout.setBackgroundColor(ContextCompat.getColor(DestinasiActivity.this,
                                android.R.color.darker_gray));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(ContextCompat.getColor(DestinasiActivity.this,
                                    android.R.color.darker_gray));
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }*/
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}
