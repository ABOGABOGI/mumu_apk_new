package id.hike.apps.android_mpos_mumu.features.landing_page;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.tech.freak.wizardpager.ui.StepPagerStrip;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.landing_page.api.UploadImageApi;
import id.hike.apps.android_mpos_mumu.features.pin_verifikasi.InFrameWalletActivity;
import id.hike.apps.android_mpos_mumu.model.ImageUpload;
import id.hike.apps.android_mpos_mumu.model.Response;
import id.hike.apps.android_mpos_mumu.model.WalletCRP;
import id.hike.apps.android_mpos_mumu.model.WalletInfo;
import id.hike.apps.android_mpos_mumu.regstep.adapter.RegStepSimpleAdapter;
import id.hike.apps.android_mpos_mumu.regstep.api.RegistrasiApi;
import id.hike.apps.android_mpos_mumu.regstep.fragment.FragmentForm;
import id.hike.apps.android_mpos_mumu.wallet.WalletAktifasiSimple;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MultipartBody;

public class AktivasiWalletActivity extends BaseActivity {

    @BindView(R.id.pager)
    ViewPager pager;

    @BindView(R.id.prev_button)
    Button prevButton;

    @BindView(R.id.next_button)
    Button nextButton;

    @BindView(R.id.strip)
    StepPagerStrip strip;

    private RegStepSimpleAdapter adapter;
    private Map<String, Object> formData = new HashMap<>();
    private RegistrasiApi registrasiApi = new RegistrasiApi();

    private String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktivasi_wallet);

        ButterKnife.bind(this);

        token = getSecPref().getString(Cfg.oauthAccessToken, "");


        adapter = new RegStepSimpleAdapter(getSupportFragmentManager());

        adapter.addPage(new WalletAktifasiSimple());
        Log.i("DEBUGTAG","WalletAktifasiSimple-OK");
        //adapter.addPage(new InputIdentitasSimple());
        //adapter.addPage(new WelcomeFragment());
        //adapter.addPage(new InputIdentitasSimple());
        //adapter.addPage(new InputIdentitas());
        //adapter.addPage(new Aktifasi_simple());
        /*
        adapter.addPage(new InputIdentitas());
        adapter.addPage(new InputIdentitasLain());
        adapter.addPage(new InputLahir());
        adapter.addPage(new InputKTP());
        adapter.addPage(new InputSelfie());
         */

        pager.setAdapter(adapter);


        strip.setOnPageSelectedListener(new StepPagerStrip.OnPageSelectedListener() {
            @Override
            public void onPageStripSelected(int position) {
                position = Math.min(adapter.getCount() - 1, position);

                if(pager.getCurrentItem() != position){
                    pager.setCurrentItem(position);
                }
            }
        });

        strip.setPageCount(adapter.getCount());

        pager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){

            @Override
            public void onPageSelected(int position){
                strip.setCurrentPage(position);


            }

        });


        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int prev = pager.getCurrentItem() - 1;

                if(prev >= 0){
                    pager.setCurrentItem(prev);

                    updateButton();
                }


            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentForm form = adapter.getItem(pager.getCurrentItem());

                form.isFormComplete().subscribe(new Observer<Map<String, Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Map<String, Object> stringStringMap) {

                        formData.putAll(stringStringMap);
                        Log.i("DEBUGTAG","Activity onNext");

                        int next = pager.getCurrentItem() + 1;

                        if(next < adapter.getCount()){
                            pager.setCurrentItem(next);
                        }else{

                            ProgressDialog dialog = new ProgressDialog(AktivasiWalletActivity.this);
                            dialog.setMessage("Mengaktifkan wallet...");
                            dialog.setCancelable(false);
                            dialog.show();

                            MultipartBody.Builder body = new MultipartBody.Builder();
                            body.setType(MultipartBody.FORM);

                            Map<String, String> forms = new HashMap<>();
                            Map<String, Bitmap> images = new HashMap<>();

                            for(String key : formData.keySet()){

                                Object obj = formData.get(key);

                                if(obj instanceof String){
                                    String val = (String) obj;

                                    forms.put(key, val);
                                    body.addFormDataPart(key, val);
                                }else if(obj instanceof Bitmap){

                                    Bitmap bitmap = (Bitmap) obj;
                                    images.put(key, bitmap);

                                }

                            }



                            for(String key: forms.keySet()){

                                Log.i("BOWOTAG", key + " - " + forms.get(key));

                            }


                            final String ktpNo = forms.get("nomor_identitas");

                            body.addFormDataPart("access_token", token);
                            body.addFormDataPart("jenis_identitas", "1");

                            Log.i("DEBUGTAG", "registrasiApi");
                            Gson gson = new Gson();
                            String json = gson.toJson(body.build());
                            Log.i("DEBUGTAG", json);
                            registrasiApi.createWallet2(body.build()).subscribe(new Observer<Response<WalletInfo>>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Response<WalletInfo> userResponse) {
                                    dialog.dismiss();

                                    //uploadImage(images, ktpNo);

                                    new AlertDialog.Builder(AktivasiWalletActivity.this)
                                            .setMessage("Selamat, Wallet anda telah di aktifkan. Silahkan daftarkan pin untuk dapat bertransaksi")
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                    finish();
                                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                                                    Random r = new Random();
                                                    int i1 = r.nextInt(99999 - 100) + 100;
                                                    String ret = formatter.format(new Date());
                                                    Intent intent = new Intent(AktivasiWalletActivity.this, InFrameWalletActivity.class);
                                                    WalletCRP crp = new WalletCRP();
                                                    crp.setTrx_type("CRP");
                                                    //crp.setAccount_no(getSecPref().getString(Cfg.prefsWalletRekening,""));
                                                    crp.setAccount_no(userResponse.getData().getNomor_rekening());
                                                    crp.setTrx_date_time(ret);
                                                    crp.setSystem_trace_audit(String.valueOf(i1));
                                                    crp.setPos_terminal_type("6017");
                                                    crp.setEnc_pin("123123");
                                                    crp.setJenis_crp("N");
                                                    crp.setOtp("819607");
                                                    crp.setSign("OTR03000010542019021110200016017Mpassword2019-02-10keys");
                                                    intent.putExtra("infoCrp", crp);
                                                    intent.putExtra("url","/hh");
                                                    intent.putExtra("title","Buat PIN");
                                                    startActivity(intent);
                                                }
                                            }).show();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.i("DEBUGTAG", "registrasiApi error");
                                    Log.i("DEBUGTAG", e.getMessage());
                                    Log.i("DEBUGTAG", "registrasiApi error2");
                                    dialog.dismiss();
                                }
                                @Override
                                public void onComplete() {
                                }
                            });


                        }

                        updateButton();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



            }
        });

        token = getSecPref().getString(Cfg.oauthAccessToken, "");



        updateButton();
    }

//    private File bitmapToFile(Bitmap bitmap, String filename){
//        //create a file to write bitmap data
//        File f = new File(this.getCacheDir(), filename);
//        try {
//            f.createNewFile();
//
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
//            byte[] bitmapdata = bos.toByteArray();
//
//            FileOutputStream fos = new FileOutputStream(f);
//            fos.write(bitmapdata);
//            fos.flush();
//            fos.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    private File createTempFile(Bitmap bitmap) {
        File file = new File(getCacheDir()
                , System.currentTimeMillis() +"");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG,0, bos);
        byte[] bitmapdata = bos.toByteArray();
        //write the bytes in file

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private void uploadImage(Map<String, Bitmap> images, String id){

        List<Observable<Response<ImageUpload>>> list=  new ArrayList<>();

        for(String key: images.keySet()){

            Bitmap bitmap = images.get(key);

            if(bitmap != null){
                File file  = createTempFile(bitmap);


                if(file != null){
                    list.add(UploadImageApi.create().uploadImage(file, id, key));
                }
            }



        }


        Observable.merge(list).subscribe(new Observer<Response<ImageUpload>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<ImageUpload> imageUploadResponse) {
                Log.i("BOWOTAG", imageUploadResponse.getData().getImage_file());
            }

            @Override
            public void onError(Throwable e) {
                Log.i("BOWOTAG", e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        });


    }

    private void updateButton(){
        int current = pager.getCurrentItem();



        if((current + 1) == adapter.getCount()){
            nextButton.setText(R.string.done_button);
        }else{
            nextButton.setText(R.string.next_button);
        }


        if(current == 0){
            prevButton.setText("");
            prevButton.setEnabled(false);
        }else{
            prevButton.setText(R.string.prev_button);
            prevButton.setEnabled(true);
        }

    }
}
