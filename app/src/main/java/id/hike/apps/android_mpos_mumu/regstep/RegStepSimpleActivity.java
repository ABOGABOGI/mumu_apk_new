package id.hike.apps.android_mpos_mumu.regstep;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.model.Response;
import id.hike.apps.android_mpos_mumu.model.User;
import id.hike.apps.android_mpos_mumu.regstep.adapter.RegStepSimpleAdapter;
import id.hike.apps.android_mpos_mumu.regstep.api.RegistrasiApi;
import id.hike.apps.android_mpos_mumu.regstep.fragment.FragmentForm;
import id.hike.apps.android_mpos_mumu.regstep.fragment.InputEmailHP;
import id.hike.apps.android_mpos_mumu.regstep.fragment.InputNamaAlamat;
import id.hike.apps.android_mpos_mumu.regstep.fragment.InputOtpEmail;
import id.hike.apps.android_mpos_mumu.regstep.fragment.InputUsernamePassword;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MultipartBody;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tech.freak.wizardpager.ui.StepPagerStrip;

import java.util.HashMap;
import java.util.Map;

public class RegStepSimpleActivity extends BaseActivity {


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_step_simple);

        ButterKnife.bind(this);


        adapter = new RegStepSimpleAdapter(getSupportFragmentManager());

        adapter.addPage(new InputEmailHP());
        adapter.addPage(new InputOtpEmail());
        adapter.addPage(new InputNamaAlamat());
//        adapter.addPage(new InputIdentitas());
//        adapter.addPage(new InputLahir());
//        adapter.addPage(new InputKTP());
//        adapter.addPage(new InputSelfie());
        adapter.addPage(new InputUsernamePassword());

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

                        int next = pager.getCurrentItem() + 1;

                        if(next < adapter.getCount()){
                            pager.setCurrentItem(next);
                        }else{

                            ProgressDialog dialog = new ProgressDialog(RegStepSimpleActivity.this);
                            dialog.setMessage("Membuat akun...");
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


                            registrasiApi.createUser(body.build()).subscribe(new Observer<Response<User>>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Response<User> userResponse) {
                                    dialog.dismiss();

                                    User user = userResponse.getData();

                                    if(!TextUtils.isEmpty(user.getH39()) && user.getH39().equals("00")){
                                        Intent intent = new Intent(RegStepSimpleActivity.this, RegistrasiBerhasilActivity.class);

                                        startActivity(intent);

                                        finish();
                                    }else{
                                        Toast.makeText(RegStepSimpleActivity.this, user.getH62(), Toast.LENGTH_LONG).show();
                                    }


                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.i("BOWOTAG", e.getMessage());
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

        updateButton();
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
