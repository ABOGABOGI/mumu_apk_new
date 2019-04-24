package id.hike.apps.android_mpos_mumu.features.profil;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.File;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.landing_page.LandingPage;
import id.hike.apps.android_mpos_mumu.features.landing_page.api.UploadImageApi;
import id.hike.apps.android_mpos_mumu.features.landing_page.df.WalletApi;
import id.hike.apps.android_mpos_mumu.features.login.LoginActivity;
import id.hike.apps.android_mpos_mumu.features.profil.activity.ChangePasswordActivity;
import id.hike.apps.android_mpos_mumu.model.ImageUpload;
import id.hike.apps.android_mpos_mumu.model.Response;
import id.hike.apps.android_mpos_mumu.model.User;
import id.hike.apps.android_mpos_mumu.model.WalletInfo;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

import static androidx.constraintlayout.widget.ConstraintSet.GONE;
import static androidx.constraintlayout.widget.ConstraintSet.VISIBLE;

public class profilConto extends BaseActivity {


    private ImageView btnBack;

    @BindView(R.id.cv7)
    View logoutButton;

    @BindView(R.id.cv2)
    View gantiPasswordButton;

    @BindView(R.id.cv3)
    View gantiPinButton;

    @BindView(R.id.pvUser)
    CircleImageView profilePicture;

    @BindView(R.id.profilNama)
    TextView profilNama;

    @BindView(R.id.profilKodePelanggan)
    TextView profilKodePelanggan;

    @BindView(R.id.profilNoHP)
    TextView profilNoHP;

    @BindView(R.id.profilEmail)
    TextView profilEmail;

    @BindView(R.id.profilNomorRekening)
    TextView profilNomorRekening;

    @BindView(R.id.profilAll)
    ConstraintLayout profilAll;

    private boolean isActive = false;
    private WalletInfo info;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_conto);

        ButterKnife.bind(this);

        loadPicture();
        String token = getSecPref().getString(Cfg.oauthAccessToken, "");
        refresh(token);

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent killAny = new Intent(profilConto.this, LandingPage.class);
                killAny.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(profilConto.this)
                        .setMessage("Anda yakin akan keluar?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                Intent intent = new Intent(profilConto.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                                getSecPref().edit().clear().apply();

                                startActivity(intent);
                                finishAffinity();

                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();

            }
        });


        gantiPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(profilConto.this, ChangePasswordActivity.class);
                startActivity(intent);

            }
        });

        gantiPinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(profilConto.this, LoginGantiPin.class);
                startActivity(intent);

            }
        });


        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EasyImage.openChooserWithGallery(profilConto.this, "Pilih ambil gambar", 0);

            }
        });
    }

    public void refresh(String token) {
        WalletApi.create().walletInfo(token).subscribe(new Observer<Response<WalletInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(id.hike.apps.android_mpos_mumu.model.Response<WalletInfo> walletInfoResponse) {
                if (walletInfoResponse.getData() != null) {
                    info = walletInfoResponse.getData();
                    if (info.getSts_aktif() != null) {
                        switch (info.getSts_aktif()) {
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
                    } else {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {

            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {

                if(type == 0){

                    changeImageProfile(imageFile);

                }

            }
        });
    }

    private void updateUi(){
        //shimmer.setVisibility(GONE);
        //walletContainer.setVisibility(VISIBLE);
        if(isActive){
            profilAll.setVisibility(VISIBLE);
            profilNama.setText(info.getNm_agen());
            profilKodePelanggan.setText(info.getKode_agen());
            profilNomorRekening.setText(info.getNomor_rekening());
            profilEmail.setText(info.getEmail());
            profilNoHP.setText(info.getNo_hp());
            //dompetContainer.setVisibility(VISIBLE);
            //depositBalance.setText("Rp." + UnitConversion.format2Rupiah2(info.getSaldo()));
            //if ( info.getSaldo() == -9999999 ) {
                //depositBalance.setText("Gangguan Jaringan.");
            //}
            //getSecPref().edit().putString(Cfg.prefsWalletRekening, info.getNomor_rekening()).apply();
            //getSecPref().edit().putInt(Cfg.prefsWalletSaldo, info.getSaldo()).apply();
            //getSecPref().edit().putBoolean(Cfg.prefsWalletEnabled, true).apply();
        }else{
            profilAll.setVisibility(GONE);
            //username.setText("Aktifkan");
            //depositBalance.setText(String.valueOf(0));
            //dompetContainer.setVisibility(GONE);
            //getSecPref().edit().putString(Cfg.prefsWalletRekening, "").apply();
            //getSecPref().edit().putInt(Cfg.prefsWalletSaldo, 0).apply();
            //getSecPref().edit().putBoolean(Cfg.prefsWalletEnabled, false).apply();
        }
    }

    private void loadPicture(){

        User user = getUser();

        String url = Cfg.BASE_ASSET_URL + user.getKdAgen() + "_profile.png";


        Picasso
                .get()
                .load(url)
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.profil_default)
                .into(profilePicture);



    }

    private User getUser(){
        String json = getSecPref().getString(Cfg.prefUserdata_USER, "");

        Gson gson = new Gson();

        User user = gson.fromJson(json, User.class);

        return user;
    }

    private void changeImageProfile(File imageFile){

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Ganti Profile");
        dialog.setCancelable(false);
//        dialog.show();

        User user = getUser();

        if(user.getKdAgen() != null){
            dialog.show();
            UploadImageApi api = new UploadImageApi();

            api.uploadImage(imageFile, user.getKdAgen(), "profile")
                    .subscribe(new Observer<Response<ImageUpload>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Response<ImageUpload> imageUploadResponse) {

                            ImageUpload res = imageUploadResponse.getData();

                            loadPicture();

                            dialog.dismiss();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(profilConto.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }

                        @Override
                        public void onComplete() {
                            dialog.dismiss();
                        }
                    });

        }


    }

    @Override
    public void onResume(){
        super.onResume();

        loadPicture();
    }
}
