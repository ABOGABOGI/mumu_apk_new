package id.hike.apps.android_mpos_mumu.features.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.summary.SummaryActivity;
import id.hike.apps.android_mpos_mumu.util.DBTransaction;

public class Home extends BaseActivity {
    static final int BARCODE_REQUEST_CODE = 678;
    static final String productID="pid";
    static final String barFormat="3";

    public FrameLayout snackBarBeli;
    public FrameLayout homeContainer;

    TextView tvUsername;
    DBTransaction dbTransaction;
    public TextView tvTotalPriceOnSnackbar;

    public LinearLayout tabSearch;

    FrameLayout llSnackbar;

    String productIdBanner;

    boolean isBannerClicked=false;
    boolean isFromSummary=false;

    Button btnBackSnackbar;

    public HomePresenter homePresenter;
//    public F_HomePresenter f_homePresenter;

    LinearLayout abMain;
    LinearLayout transLayout;
    EditText edSearch;

    String barcode="0";

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_home);

        onComponent();

        // nanti ini dioper ke frag_home
        if (getIntent().getExtras()!=null){
            Bundle data=getIntent().getExtras();
            if (data.getBoolean(Cfg.BUNDLE_ISCLICK_BANNER_BOOLEAN,false)){
                isBannerClicked=true;
                productIdBanner=String.valueOf(getIntent().getExtras().getLong(Cfg.BUNDLE_DETAIL_ID_LONG));
            }

            if (data.getBoolean(Cfg.BUNDLE_ISFROM_SUMMARY_BOOLEAN,false)){
                isFromSummary=true;

                // Jika dari summary ditekan tombol tambah
                // Maka saat tiba di halaman ini, langsung memunculkan snackBar
                // Agar kasir bisa melihat tombol lanjut
                snackBarBeli.post(new Runnable() {
                    @Override
                    public void run() {
                        homePresenter.showSnackbar();
                    }
                });
            }
        }
    }

    @Override
    protected void onDestroy() {
        homePresenter=null;
        super.onDestroy();
    }

    private void onComponent() {
        homePresenter=new HomePresenter(this,getSecPref());

        fragmentManager = getSupportFragmentManager();

        /* Original
        fragmentManager.beginTransaction().replace(R.id.transLayout, new F_Home(),Cfg.TAG_HOME_STR).commit();
        */

        // Baroka & Microstore
        String payment = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            payment = extras.getString("payment");
        }
        fragmentManager.beginTransaction().replace(R.id.transLayout, new MainPPOBFragment(), payment).commit();
        // END Baroka & Microstore

        tabSearch= (LinearLayout) findViewById(R.id.searchTab);
        llSnackbar= (FrameLayout) findViewById(R.id.snackBarBeli);
        llSnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //nothing
            }
        });

        transLayout= (LinearLayout) findViewById(R.id.transLayout);
        homeContainer = (FrameLayout) findViewById(R.id.homeContainer);
        snackBarBeli= (FrameLayout) findViewById(R.id.snackBarBeli);
        snackBarBeli.post(new Runnable() {
            @Override
            public void run() {
                snackBarBeli.animate().setDuration(0L).y(homeContainer.getHeight());
            }
        });

        abMain= (LinearLayout) findViewById(R.id.abMain);

        tvTotalPriceOnSnackbar =(TextView)findViewById(R.id.tvTotalPrice);
        dbTransaction=new DBTransaction(this);

        btnBackSnackbar= (Button) findViewById(R.id.btnBackSnackbar);
        btnBackSnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tvUsername= (TextView) findViewById(R.id.tvUsername);
        ImageButton btnBarcode= (ImageButton) findViewById(R.id.btnBarcode);
        btnBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(Home.this,A_Barcode.class);
//                startActivityForResult(intent,BARCODE_REQUEST_CODE);
            }
        });

        ((ImageView)findViewById(R.id.btnBack)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button btnGoSummary= (Button) findViewById(R.id.btnGoSummary);
        btnGoSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, SummaryActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        edSearch= (EditText) findViewById(R.id.etSearchProduct);
        edSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                boolean returnStatus = false;
                if (keyCode == 66/*Search button code*/) {
                    hideKeyboard();
                    try{
//                        fragmentManager.beginTransaction().replace(R.id.transLayout, new F_SearchResult(), Cfg.TAG_SEARCH_RESULT_STR).commit();
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }

                    returnStatus = true;
                }
                return returnStatus;

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode) {
                case BARCODE_REQUEST_CODE:

                    try {
                        data.getStringExtra(productID);
                    } catch (java.lang.NullPointerException e){
                        return;
                    }

                    barcode=data.getStringExtra(productID);
//                    fragmentManager.beginTransaction().replace(R.id.transLayout, new F_SearchResult(), Cfg.TAG_BARCODE_RESULT_STR).commit();

                    break;
            }
    }



    @Override
    public void onBackPressed(){
//        if (Home.this.fragmentManager.findFragmentByTag(Cfg.TAG_HOME_STR) instanceof F_SearchResult){
//            fragmentManager.beginTransaction().replace(R.id.transLayout,new F_Home()).commit();
//        } else {
            super.onBackPressed();
//        }
    }

    public void hideKeyInput(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }
}
