package id.hike.apps.android_mpos_mumu.features.qurban;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.DestinasiPager;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;

public class TransaksiKurbanKambingStandar extends BaseActivity {

    TextView saldoMumu;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView btnBack;
    SlideKambingStandarPager pageAdapter;
    TabItem tabChats;
    TabItem tabStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi_kurban_kambing_standar);

        saldoMumu = findViewById(R.id.totalSaldo);
        int saldo = getSecPref().getInt(Cfg.prefsWalletSaldo, 0);
        saldoMumu.setText("Rp. " + UnitConversion.format2Rupiah2(saldo));

        toolbar = findViewById(R.id.toolbar_akad);
        //toolbar.setTitle(getResources().getString(R.string.app_name));
        //setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tablayout);
        tabChats = findViewById(R.id.tabTransferBank);
        tabStatus = findViewById(R.id.tabOnlinePayment);
        //tabCalls = findViewById(R.id.tabCalls);
        viewPager = findViewById(R.id.viewPager);

        pageAdapter = new SlideKambingStandarPager(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        btnBack = findViewById(R.id.backButton);
        btnBack.setOnClickListener(v -> finish());

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
