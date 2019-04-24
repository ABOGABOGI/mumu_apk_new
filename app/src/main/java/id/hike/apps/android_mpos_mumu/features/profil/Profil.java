package id.hike.apps.android_mpos_mumu.features.profil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.landing_page.LandingPage;

public class Profil extends BaseActivity {

    public Uri uriFoto1;
    boolean toggleBtnEdit=false;
    ImageView btnEdit;
    FragmentManager fragmentManager;

    TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_profil);

        fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layChange,new FProfil()).commit();

        ImageView btnBack= (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnEdit=(ImageView)findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBtnEdit(false);
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_left,R.anim.slide_in_right,R.anim.slide_out_right).replace(R.id.layChange,new GantiProfil()).addToBackStack("").commit();
            }
        });

        tvTitle= (TextView) findViewById(R.id.tvTitle);
        setTitle("Profil");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent killAny = new Intent(this, LandingPage.class);
        killAny.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(killAny);
    }

    void setTitle(String title){
        tvTitle.setText(title);
    }
    public void showBtnEdit(boolean isTrue){
        if (isTrue) {
            btnEdit.setVisibility(View.VISIBLE);
        } else {
            btnEdit.setVisibility(View.GONE);
        }
    }

    long balanceSaatIni,beginningBalance;
}
