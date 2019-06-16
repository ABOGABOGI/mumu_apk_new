package id.hike.apps.android_mpos_mumu.features.landing_page.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.landing_page.AktivasiWalletActivity;
import id.hike.apps.android_mpos_mumu.features.landing_page.df.WalletApi;
import id.hike.apps.android_mpos_mumu.features.top_up.RegisterLinkAja;
import id.hike.apps.android_mpos_mumu.features.top_up.TopUpActivity;
import id.hike.apps.android_mpos_mumu.features.wakaf.WakafDetailActivity;
import id.hike.apps.android_mpos_mumu.model.Response;
import id.hike.apps.android_mpos_mumu.model.User;
import id.hike.apps.android_mpos_mumu.model.WalletInfo;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class WalletBannerView extends LinearLayout {


    @BindView(R.id.imageView6)
    CircleImageView profileImage;

    @BindView(R.id.tvUsername)
    TextView username;

    @BindView(R.id.tvDepositBalance)
    TextView depositBalance;

    @BindView(R.id.walletContainer)
    LinearLayout walletContainer;

    @BindView(R.id.btnReloadSaldo)
    Button reloadButtonSaldo;

    @BindView(R.id.btnReloadSaldoLinkAja)
    Button reloadButtonSaldoLinkAja;

    @BindView(R.id.dompetContainer)
    LinearLayout dompetContainer;

    @BindView(R.id.imageView8)
    ImageView iconDompet;

    @BindView(R.id.linkAja)
    ImageView iconDompetLinkAja;

    @BindView(R.id.shimmerLayout)
    ShimmerFrameLayout shimmer;

    private boolean isActive = false;
    private WalletInfo info;

    public WalletBannerView(Context context) {
        super(context);
        init();
    }

    public WalletBannerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WalletBannerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public WalletBannerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){

        View v = inflate(getContext(), R.layout.profile_highlight, this);

        ButterKnife.bind(this, v);

        shimmer.setVisibility(VISIBLE);
        walletContainer.setVisibility(GONE);


        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isActive){

                    Intent intent = new Intent(getContext(), AktivasiWalletActivity.class);

                    getContext().startActivity(intent);

                }

            }
        });

        reloadButtonSaldoLinkAja.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegisterLinkAja.class);
                getContext().startActivity(intent);
            }
        });

        reloadButtonSaldo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!isActive){
                    Intent intent = new Intent(getContext(), AktivasiWalletActivity.class);
                    getContext().startActivity(intent);
                }else{
                    Intent topIntent = new Intent(getContext(), TopUpActivity.class);
                    topIntent.putExtra("wallet", info);
                    getContext().startActivity(topIntent);


//                    Toast.makeText(getContext(), "Under Development", Toast.LENGTH_LONG).show();
                }



            }
        });
    }


    private void loadPicture(){

        User user = getUser();

        String url = Cfg.BASE_ASSET_URL + user.getKdAgen() + "_profile.png";


        Picasso
                .get()
                .load(url)
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.profil_default)
                .into(profileImage);



    }

    private User getUser(){
        String json = getSecPref().getString(Cfg.prefUserdata_USER, "");

        Gson gson = new Gson();

        User user = gson.fromJson(json, User.class);

        return user;
    }

    public void refresh(String token){

        loadPicture();

        shimmer.setVisibility(VISIBLE);
        walletContainer.setVisibility(GONE);

        WalletApi.create().walletInfo(token).subscribe(new Observer<Response<WalletInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(id.hike.apps.android_mpos_mumu.model.Response<WalletInfo> walletInfoResponse) {

                if(walletInfoResponse.getData() != null){
                    info = walletInfoResponse.getData();

                    if(info.getSts_aktif() != null){
                        switch(info.getSts_aktif()){
                            case "1":
                                isActive = true;

                                break;

                            case "2":
                            case "3":
                                isActive = true;

                                break;

                            case "0":
                                isActive = false;
                                break;
                        }


                        updateUi();
                    }else{
                        updateUi();
                    }


                }


            }

            @Override
            public void onError(Throwable e) {

                updateUi();

            }

            @Override
            public void onComplete() {

            }
        });

    }

    public SharedPreferences getSecPref(){
        return ((BaseActivity)getContext()).getSecPref();
    }


    private void updateUi(){

        shimmer.setVisibility(GONE);
        walletContainer.setVisibility(VISIBLE);
//        profileImage.setImageDrawable(getResources().getDrawable(R.drawable.profil_default));

        if(isActive){

            username.setText(info.getNm_agen());
            dompetContainer.setVisibility(VISIBLE);
            depositBalance.setText("Rp." + UnitConversion.format2Rupiah2(info.getSaldo()));
            if ( info.getSaldo() == -9999999 ) {
                depositBalance.setText("Gangguan Jaringan.");
            }

            getSecPref().edit().putString(Cfg.prefsWalletRekening, info.getNomor_rekening()).apply();
            getSecPref().edit().putInt(Cfg.prefsWalletSaldo, info.getSaldo()).apply();
            getSecPref().edit().putBoolean(Cfg.prefsWalletEnabled, true).apply();

        }else{

            username.setText("Aktifkan");
            depositBalance.setText(String.valueOf(0));
            dompetContainer.setVisibility(GONE);

            getSecPref().edit().putString(Cfg.prefsWalletRekening, "").apply();
            getSecPref().edit().putInt(Cfg.prefsWalletSaldo, 0).apply();
            getSecPref().edit().putBoolean(Cfg.prefsWalletEnabled, false).apply();
        }

    }



}
