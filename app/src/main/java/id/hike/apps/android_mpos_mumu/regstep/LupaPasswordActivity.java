package id.hike.apps.android_mpos_mumu.regstep;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.regstep.adapter.RegStepSimpleAdapter;
import id.hike.apps.android_mpos_mumu.regstep.api.RegistrasiApi;
import id.hike.apps.android_mpos_mumu.regstep.fragment.FragmentForm;
import id.hike.apps.android_mpos_mumu.regstep.fragment.InputLupaPassword;
import id.hike.apps.android_mpos_mumu.regstep.fragment.InputOtpPassword;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.tech.freak.wizardpager.ui.StepPagerStrip;

import java.util.HashMap;
import java.util.Map;

public class LupaPasswordActivity extends BaseActivity {

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
        setContentView(R.layout.activity_lupa_password);

        ButterKnife.bind(this);


        adapter = new RegStepSimpleAdapter(getSupportFragmentManager());

        adapter.addPage(new InputLupaPassword());
        adapter.addPage(new InputOtpPassword());

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


                            new AlertDialog.Builder(LupaPasswordActivity.this)
                                    .setMessage("Ganti password berhasil, silahkan login kembali menggunakan password baru")
                                    .setPositiveButton("ya", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            finish();

                                        }
                                    }).show();

                        }

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
