package id.hike.apps.android_mpos_mumu.features.pelanggan;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.util.ApiClient;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.util.SimpleDividerItemDecoration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Pelanggan extends BaseActivity {

    RecyclerView rvPelanggan;
    int duration = 90;
    ViewAnimator viewAnimator;
    ModelPelanggan modelPelanggan;
    int indexDataPelanggan = 1;
    long totalPage, currentPage;
    SwipyRefreshLayout swipyRefreshLayout;
    private int indexSearchPelanggan = 1;
    private String mode;
    AutoCompleteTextView actvInputSearchWord;
    TextView tvTotalPelanggan;
    public int View_Layout;

    private Boolean isPelangganResultCalled;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_data_pelanggan);

        View_Layout = getIntent().getIntExtra("F_PULSA", 0);
        isPelangganResultCalled = false;

        /*if (View_Layout == RVAdapterPelanggan.PPOB_VIEW){
            Toast.makeText(getApplicationContext(), "Data diterima PPOB", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Data diterima Menu", Toast.LENGTH_SHORT).show();
        }*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvPelanggan = (RecyclerView) findViewById(R.id.rvPelanggan);
        rvPelanggan.setLayoutManager(layoutManager);
        rvPelanggan.setItemAnimator(new DefaultItemAnimator());
        dummyAdapter();
        tvTotalPelanggan= (TextView) findViewById(R.id.tvTotalPelanggan);
        getPelangganData();
        swipyRefreshLayout = (SwipyRefreshLayout) findViewById(R.id.swipyrefreshlayout);
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                if (currentPage <= totalPage) {
//                    addPelangganData();
                } else {
                    swipyRefreshLayout.setRefreshing(false);
                }
            }
        });
        rvPelanggan.addItemDecoration(new SimpleDividerItemDecoration(this));

        final FrameLayout btnSearch = (FrameLayout) findViewById(R.id.frameLayoutSearch);
        viewAnimator = (ViewAnimator) findViewById(R.id.VaSearch);

        actvInputSearchWord = (AutoCompleteTextView) findViewById(R.id.edInputSearchWord);

        ImageButton imageButton = (ImageButton) findViewById(R.id.btnBackSearch);
        ImageView ivDelSearch = (ImageView) findViewById(R.id.ivDeleteText);
        ImageView ivSearch = (ImageView) findViewById(R.id.ivSearch);

        ImageView btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ivSearch.setOnClickListener(searchWriting);
        btnSearch.setOnClickListener(searchWriting);


        actvInputSearchWord.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {}

                Intent intent=new Intent(Pelanggan.this, PelangganSearchResult.class);
                if(View_Layout == RVAdapterPelanggan.PPOB_VIEW){
                    intent.putExtra("F_PULSA", RVAdapterPelanggan.PPOB_VIEW);
                }
                intent.putExtra("namaPelanggan",actvInputSearchWord.getText().toString());
                if(!isPelangganResultCalled){
                    isPelangganResultCalled = true;
                    startActivity(intent);
                }
                return false;
            }
        });
        actvInputSearchWord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Pelanggan.this, "position : " + String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });

        actvInputSearchWord.setThreshold(1);
        ImageView btnTambahPelanggan = (ImageView) findViewById(R.id.btnTambahPelanggan);
        btnTambahPelanggan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DFTambahPelanggan dfTambahPelanggan = new DFTambahPelanggan();
                dfTambahPelanggan.show(getSupportFragmentManager(), "tambahPelanggan");
            }
        });
        ivDelSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actvInputSearchWord.setText("");
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation in = AnimationUtils.loadAnimation(Pelanggan.this, R.anim.slide_in_left_dp);
                Animation out = AnimationUtils.loadAnimation(Pelanggan.this, R.anim.slide_out_left_dp);
                viewAnimator.setInAnimation(in);
                viewAnimator.setOutAnimation(out);
                in.setDuration(duration);
                out.setDuration(duration);
                in.setInterpolator(new AccelerateDecelerateInterpolator());
                out.setInterpolator(new AccelerateDecelerateInterpolator());
                viewAnimator.showPrevious();
            }
        });
//        rvPelanggan.addOnItemTouchListener(pelangganClick);

        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            if (bundle.getString(Cfg.BUNDLE_PELANGGAN_INFO,"").equalsIgnoreCase("add")){
                Toast.makeText(this, getString(R.string.pelanggan_telah_ditambah), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        String backToPpob = getSecPref().getString(Cfg.ppobBackKey,null);
        if(backToPpob!=null && backToPpob.equals(Cfg.ppobBackVal)){
            getSecPref().edit().remove(Cfg.ppobBackKey).apply();
            finish();
        }
    }

    View.OnClickListener searchWriting = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Animation in = AnimationUtils.loadAnimation(Pelanggan.this, R.anim.slide_in_right_dp);
            Animation out = AnimationUtils.loadAnimation(Pelanggan.this, R.anim.slide_out_right_dp);
            in.setDuration(duration);
            out.setDuration(duration);
            in.setInterpolator(new AccelerateDecelerateInterpolator());
            out.setInterpolator(new AccelerateDecelerateInterpolator());
            viewAnimator.setInAnimation(in);
            viewAnimator.setOutAnimation(out);
            viewAnimator.showNext();
        }
    };

    void dummyAdapter() {
        rvPelanggan.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });
    }

    int perpage = 5;

    public void reloadActivityFromHalamanPelanggan() {
        Intent intent = getIntent();
        intent.putExtra(Cfg.BUNDLE_PELANGGAN_INFO, "add");
        finish();
        startActivity(intent);
    }

    void getPelangganData() {
        mode = "dataPelanggan";
        SvPelanggan menulist = ApiClient.serviceGenerator(Cfg.BASEURL_IDENTITY).create(SvPelanggan.class);
        Call<ModelPelanggan> call = menulist.getPelanggan(getSecPref().getString(Cfg.prefsOutletId_STR,null));
        call.enqueue(new Callback<ModelPelanggan>() {

            @Override
            public void onResponse(Call<ModelPelanggan> call, Response<ModelPelanggan> response) {
                if (response.isSuccessful()) {
                    indexDataPelanggan++;
                    modelPelanggan = response.body();
                    tvTotalPelanggan.setText(String.valueOf(modelPelanggan.getData().size()));
                    /*totalPage = (long) Math.round(modelPelanggan.getTotal() / perpage);
                    currentPage = 1;*/
                    rvPelanggan.setAdapter(new RVAdapterPelanggan(Pelanggan.this, modelPelanggan, View_Layout));
                } else {
                    /*indexDataPelanggan++;
                    modelPelanggan = response.body();
                    totalPage = (long) Math.round(modelPelanggan.getTotal() / perpage);
                    currentPage = 1;*/
                    tvTotalPelanggan.setText("0");
//                    rvPelanggan.setAdapter(new RVAdapterPelanggan(getBaseContext(), modelPelanggan));
                }
            }

            @Override
            public void onFailure(Call<ModelPelanggan> call, Throwable t) {
                Throwable t1 = t;
            }
        });
    }

    public final boolean containsDigit(String s) {
        boolean containsDigit = false;

        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (containsDigit = Character.isDigit(c)) {
                    break;
                }
            }
        }

        return containsDigit;
    }

}
