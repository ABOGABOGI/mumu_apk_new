package id.hike.apps.android_mpos_mumu.features.wakaf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.wakaf.PembayaranWakaf;
import id.hike.apps.android_mpos_mumu.model.MetaInfo;

public class WakafDetailActivity extends AppCompatActivity {
    Button goToNWakaf;
    EditText editTextWakaf;
    TextView namaProgram, description;
    ImageView backBtn_univ, imageView;

    //    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    private MetaInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dompet_dhuafa_univeristy);
        goToNWakaf = findViewById(R.id.btnMulaiWakaf);
        editTextWakaf = findViewById(R.id.editTextWakaf);
        namaProgram = findViewById(R.id.namaProgram);
        //description = findViewById(R.id.description);
        backBtn_univ = findViewById(R.id.backBtn_univ);
        imageView = findViewById(R.id.imageView);
        //Cfg.gs_WakafInfo_MetaKey = info.getMetakey();
        //Cfg.gs_WakafInfo_KodeProduk = info.getMetakodeproduk();
        //Log.i("BOWOTAG", info.getMetakodeproduk());

        //      getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){

            if(bundle.containsKey("info")){
                info = (MetaInfo) bundle.getSerializable("info");

                try {
                    JSONObject obj = new JSONObject(info.getMetavalue());

                    Picasso.get().load(Cfg.BASE_ASSET_URL + obj.getString("img")).into(imageView);

                    namaProgram.setText(obj.getString("title"));
                    //description.setText(obj.getString("keterangan"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }





        backBtn_univ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        goToNWakaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WakafDetailActivity.this, PembayaranWakaf.class);
                intent.putExtra("name", namaProgram.getText().toString()) ;
                startActivity(intent);

            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentSinopsis(), "Sinopsis");
        adapter.addFragment(new FragmentDonatur(), "Donatur");
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
