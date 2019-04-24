package id.hike.apps.android_mpos_mumu.features.landing_page;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.BuildConfig;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.api.HomeApi;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.aktifitas.AktifitasHistoryActivity;
import id.hike.apps.android_mpos_mumu.features.donasi.donasi;
import id.hike.apps.android_mpos_mumu.features.home.Home;
import id.hike.apps.android_mpos_mumu.features.landing_page.adapter.BannerPagerAdapter;
import id.hike.apps.android_mpos_mumu.features.landing_page.adapter.RvAdapterMenuPager;
import id.hike.apps.android_mpos_mumu.features.landing_page.view.WalletBannerView;
import id.hike.apps.android_mpos_mumu.features.login.api.LoginApi;
import id.hike.apps.android_mpos_mumu.features.profil.profilConto;
import id.hike.apps.android_mpos_mumu.features.top_up.TopUpActivity;
import id.hike.apps.android_mpos_mumu.features.wakaf.ProgramWakafActivity;
import id.hike.apps.android_mpos_mumu.model.MetaInfo;
import id.hike.apps.android_mpos_mumu.model.User;
import id.hike.apps.android_mpos_mumu.util.DBTransaction;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LandingPage extends BaseActivity implements View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {

    FrameLayout parentLayout;

    public DBTransaction dbTransaction;

    ImageView btnDevices;
    private boolean btnDevicesTrig;

    TextView tvNoPromo;

    private RecyclerView rvPromoHariIni;
    private FrameLayout flTambahTransaksi;
    private LinearLayoutManager layoutManager;
    private Timer timer;
    private int cursor = 0;

    private boolean isWalletActive = false;

    @BindView(R.id.vpBanner)
    ViewPager bannerPager;

    @BindView(R.id.walletBanner)
    WalletBannerView walletBanner;

    @BindView(R.id.tabIndicator)
    TabLayout bannerPageIndicator;

    @BindView(R.id.rvMenuPager)
    RecyclerView rvMenuPager;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.a_landing_page);
        ButterKnife.bind(this);

        BottomNavigationView bottomMenu = (BottomNavigationView) findViewById(R.id.navigation);
        //BottomNavigationHelper.disableShiftMode(bottomMenu);
        bottomMenu.setOnNavigationItemSelectedListener(this);

//        ImageButton btnPayPulsa = (ImageButton) findViewById(R.id.btnPayPulsa);
//        btnPayPulsa.setOnClickListener(this);
//
//        ImageButton btnPayListrik = (ImageButton) findViewById(R.id.btnPayListrik);
//        btnPayListrik.setOnClickListener(this);
//
//        ImageButton btnPayPdam = (ImageButton) findViewById(R.id.btnPayPdam);
//        btnPayPdam.setOnClickListener(this);
//
//        ImageButton btnPayTelco = (ImageButton) findViewById(R.id.btnPayTelco);
//        btnPayTelco.setOnClickListener(this);


        parentLayout = (FrameLayout) findViewById(R.id.parentLayout);
        parentLayout.setOnClickListener(null);
        parentLayout.setVisibility(View.GONE);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4, RecyclerView.VERTICAL, false);
        RvAdapterMenuPager menuPager = new RvAdapterMenuPager();

        rvMenuPager.setLayoutManager(gridLayoutManager);
        rvMenuPager.setAdapter(menuPager);


        switch(BuildConfig.appHeader){
            case "appDompetDhuafa":
                menuPager.setItem(new id.hike.apps.android_mpos_mumu.features.landing_page.model.MenuItem(R.drawable.pulsa_umu_bordered, "Pulsa"));
                menuPager.setItem(new id.hike.apps.android_mpos_mumu.features.landing_page.model.MenuItem(R.drawable.ic_proteksi, "Proteksi"));
                menuPager.setItem(new id.hike.apps.android_mpos_mumu.features.landing_page.model.MenuItem(R.drawable.ic_donasi, "Donasi"));
                menuPager.setItem(new id.hike.apps.android_mpos_mumu.features.landing_page.model.MenuItem(R.drawable.ic_wakaf, "Wakaf"));
                menuPager.setItem(new id.hike.apps.android_mpos_mumu.features.landing_page.model.MenuItem(R.drawable.pln_umu, "Token Listrik"));
                menuPager.setItem(new id.hike.apps.android_mpos_mumu.features.landing_page.model.MenuItem(R.drawable.pdam_umu, "PDAM"));
                menuPager.setItem(new id.hike.apps.android_mpos_mumu.features.landing_page.model.MenuItem(R.drawable.telkom_umu, "Tagihan Telepon"));
                menuPager.setItem(new id.hike.apps.android_mpos_mumu.features.landing_page.model.MenuItem(R.drawable.airplane, "Destinasi"));
                break;


            case "appUangMuhammadiyah":
                menuPager.setItem(new id.hike.apps.android_mpos_mumu.features.landing_page.model.MenuItem(R.drawable.pulsa_umu_bordered, "Pulsa"));
                menuPager.setItem(new id.hike.apps.android_mpos_mumu.features.landing_page.model.MenuItem(R.drawable.pln_umu, "Token Listrik"));
                menuPager.setItem(new id.hike.apps.android_mpos_mumu.features.landing_page.model.MenuItem(R.drawable.pdam_umu, "PDAM"));
//                menuPager.setItem(new id.co.smltech.apps.android_mpos_mumu.features.landing_page.model.MenuItem(R.drawable.telkom_umu, "PSTN"));
                menuPager.setItem(new id.hike.apps.android_mpos_mumu.features.landing_page.model.MenuItem(R.drawable.ticket_umu, "Tiket"));
                break;
        }





        final BannerPagerAdapter bannerPagerAdapter = new BannerPagerAdapter(getSupportFragmentManager());

        HomeApi homeApi = new HomeApi();
        homeApi.index().subscribe(new Observer<List<MetaInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<MetaInfo> metaInfos) {

                List<String> strings = new ArrayList<>();

                for(MetaInfo metaInfo : metaInfos){

                    strings.add(Cfg.BASE_ASSET_URL + metaInfo.getMetavalue());

                }

                bannerPagerAdapter.setImagesUrl(strings.toArray(new String[strings.size()]));


            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        bannerPager.setAdapter(bannerPagerAdapter);
        bannerPageIndicator.setupWithViewPager(bannerPager, true);
        bannerPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPage.this, ProgramWakafActivity.class);
                startActivity(intent);
            }
        });

        setUseAutoScroll(bannerPager, 4000, 6000);

        String token = getSecPref().getString(Cfg.oauthAccessToken, "");

        walletBanner.refresh(token);

    }

    //Press back twice to exit the app
    boolean backTwice = false;

    @Override
    public void onBackPressed() {
        if(backTwice){
            super.onBackPressed();
            return;
        }

        this.backTwice = true;
        Toast.makeText(this,"Tekan Back dua kali untuk keluar",Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backTwice = false;
            }
        },2000);
    }

    private void updateUser(){


        String token = getSecPref().getString(Cfg.oauthAccessToken, "");

        LoginApi.create().getUser(token).subscribe(new Observer<id.hike.apps.android_mpos_mumu.model.Response<User>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(id.hike.apps.android_mpos_mumu.model.Response<User> userResponse) {

                Gson gson = new Gson();
                getSecPref().edit().putString(Cfg.prefUserdata_USER, gson.toJson(userResponse.getData())).apply();

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }


    private void setImageButtonEnabled(Context ctxt, boolean enabled,
                                       ImageButton item, int iconResId) {
        //item.setEnabled(enabled);
        Drawable originalIcon = ctxt.getResources().getDrawable(iconResId);
        Drawable icon = enabled ? originalIcon : convertDrawableToGrayScale(originalIcon);
        item.setImageDrawable(icon);
    }

    private Drawable convertDrawableToGrayScale(Drawable drawable) {
        if (drawable == null)
            return null;

        Drawable res = drawable.mutate();
            res.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
        return res;
    }

    private void setAsUnavailableService(int imgButtonId, int iconResId) {
        ImageButton btn = (ImageButton) findViewById(imgButtonId);
        btn.setOnClickListener(this);
        //setImageButtonEnabled(this, false, btn, iconResId);
    }

    private void setUseAutoScroll(final ViewPager pager, int delay, int period) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            private Handler handler = new Handler();

            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (pager.getCurrentItem() < pager.getAdapter().getCount() - 1) {
                            pager.setCurrentItem(pager.getCurrentItem() + 1);
                        } else {
                            pager.setCurrentItem(0, true);
                        }
                    }
                });
            }
        }, delay, period);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent intent = new Intent(getBaseContext(), LandingPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.action_my_account:
                intent = new Intent(getBaseContext(), profilConto.class);
                startActivity(intent);
                break;
            case R.id.action_history:
                intent = new Intent(getBaseContext(), AktifitasHistoryActivity.class);
                startActivity(intent);

//                Toast.makeText(this, "On Development", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_wakafku:
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("Unavailable Service");
//                builder.setMessage("Fitur sedang dalam pengembangan");
//                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // continue with delete
//                            }
//                        })
//                        .setIcon(android.R.drawable.ic_dialog_info)
//                        .show();

//                goToWakaf();

                Intent goToWakafIntent = new Intent(getBaseContext(), ProgramWakafActivity.class);
                startActivity(goToWakafIntent);

                //Toast.makeText(this, "On Development", Toast.LENGTH_LONG).show();
                break;
            default:
                break;

        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPayPulsa:
                startHomeActivity(view, Cfg.TAG_PULSA);
                break;

            case R.id.btnPayListrik:
                startHomeActivity(view, Cfg.TAG_LISTRIK);
                break;
            case R.id.btnPayPdam:
                startHomeActivity(view, Cfg.TAG_PDAM);
                break;
            case R.id.btnPayTelco:
                startHomeActivity(view, Cfg.TAG_TELKOM);
                break;
            default:
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(this);
                }
                builder.setTitle("Unavailable Service")
                        .setMessage("Fitur sedang dalam pengembangan")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
                break;
        }
    }


    private void aktivasiWallet(){
        Intent intent = new Intent(this, AktivasiWalletActivity.class);
        startActivity(intent);
    }

    private void goToDonasi() {
        if(isWalletActive){
            Intent goToDonasiIntent = new Intent(LandingPage.this, donasi.class);
            startActivity(goToDonasiIntent);
        }else{
            aktivasiWallet();
        }
    }

    private void goToWakaf() {
//        if(isWalletActive){
            Intent goToWakafIntent = new Intent(LandingPage.this, ProgramWakafActivity.class);
            startActivity(goToWakafIntent);
//        }else{
//            aktivasiWallet();
//        }
    }

    private void goToTopUp() {

        if(isWalletActive){
            Intent topIntent = new Intent(LandingPage.this, TopUpActivity.class);
            startActivity(topIntent);
        }else{
            aktivasiWallet();
        }


    }

    private void startHomeActivity(View view, String paymentTag) {
        Intent intent = new Intent(view.getContext(), Home.class);
        intent.putExtra("payment", paymentTag);
        view.getContext().startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void reloadDepositBalance() {
//        tvDepositBalance.setText("");
        getDepositBalance();
    }

    private void getDepositBalance(){



    }



    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Cfg.BT_CONNECT_BT:
                    // Nothing
                    dismissLoadingFragment();
                    break;
                case Cfg.BT_NOT_SUPPORTED:
                    getSecPref().edit().putBoolean(Cfg.prefsCardReaderConnected, false).apply();
                    btnDevices.setImageResource(R.drawable.ic_credit_card_terminal_nok);
                    Toast.makeText(LandingPage.this, getString(R.string.bluetooth_tidak_support), Toast.LENGTH_SHORT).show();
                    dismissLoadingFragment();
                    break;
                case Cfg.BT_RETURN_SUCCESS:
                case Cfg.BT_ALREADY_CONNECT:
                    getSecPref().edit().putBoolean(Cfg.prefsCardReaderConnected, true).apply();
                    Toast.makeText(LandingPage.this, getString(R.string.bluetooth_berhasil_connect), Toast.LENGTH_SHORT).show();
                    btnDevices.setImageResource(R.drawable.ic_icon_card_reader_on);
                    dismissLoadingFragment();
                    break;
                case Cfg.BT_CONNECT_CR_FAILED:
                    getSecPref().edit().putBoolean(Cfg.prefsCardReaderConnected, false).apply();
                    Toast.makeText(LandingPage.this, getString(R.string.bluetooth_connect_failed), Toast.LENGTH_SHORT).show();
                    btnDevices.setImageResource(R.drawable.ic_credit_card_terminal_nok);
                    dismissLoadingFragment();
                    break;
            }
        }
    };

    private void dismissLoadingFragment() {
        if (btnDevicesTrig) {
            dfLoading.dismiss();
            btnDevicesTrig = false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void intervalAnim(final ViewAnimator viewAnimator) {
        final Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        viewAnimator.showNext();
                        handler.postDelayed(this, 10000);
                    }
                }, 10000
        );
    }


    private class CustomLinearLayoutManager extends LinearLayoutManager {
        private CustomLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        @Override
        public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
            final LinearSmoothScroller scroller = new LinearSmoothScroller(recyclerView.getContext()){
                private static final float MILLISECONDS_PER_INCH = 50f;
                @Override
                public PointF computeScrollVectorForPosition(int targetPosition) {
                    return LandingPage.CustomLinearLayoutManager.this
                            .computeScrollVectorForPosition(targetPosition);
                }

                @Override
                protected float calculateSpeedPerPixel
                        (DisplayMetrics displayMetrics) {
                    return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
                }
            };

            scroller.setTargetPosition(position);
            startSmoothScroll(scroller);
        }
    }

    private void startTimer(){
        timer = new Timer();
        int itemCount = rvPromoHariIni.getAdapter().getItemCount();
        // start timer

        //no delay,  5 detik
        long period = (long) 5 * 1000;
        timer.schedule(new RvTimerTask(itemCount),0, period);
    }

    private class RvTimerTask extends TimerTask {
        // untuk berapa jumlah buahnya. final karena biar ga berubah2
        final int count;

        public RvTimerTask(int count) {
            this.count = count;
        }

        @Override
        public void run() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d(Cfg.TAG_COMMON, "cursor sebelum validasi = "+ cursor + " countnya = "+count);
                    if(cursor >= 0){ // avoid go to index -1
                        rvPromoHariIni.smoothScrollToPosition(cursor);
                    }

                    cursor = cursor + 2;
                    if(cursor >= count){
                        cursor = 0;
                    }
                    Log.d(Cfg.TAG_COMMON, "cursor sekarang = "+ cursor);

                }
            });
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        String message = getIntent().getStringExtra("message");
        Log.d("BAKKA", "message content: " + message);
        if (message != null && !message.isEmpty()) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            getIntent().putExtra("message", "");
        }

        updateUser();

        String token = getSecPref().getString(Cfg.oauthAccessToken, "");

        walletBanner.refresh(token);
    }
}