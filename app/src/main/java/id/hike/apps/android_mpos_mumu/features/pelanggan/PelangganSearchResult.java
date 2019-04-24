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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.home.PulsaFragment;
import id.hike.apps.android_mpos_mumu.features.home.Home;
import id.hike.apps.android_mpos_mumu.features.summary.SummaryActivity;
import id.hike.apps.android_mpos_mumu.util.ApiClient;
import id.hike.apps.android_mpos_mumu.util.ListenerRecyclerItemClick;
import id.hike.apps.android_mpos_mumu.util.MyUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PelangganSearchResult extends BaseActivity {

    RecyclerView rvPelangganResult;
    private AutoCompleteTextView actvInputSearchWord;
    private ViewAnimator viewAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pelanggan_result);

        rvPelangganResult = (RecyclerView) findViewById(R.id.rvSearchList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvPelangganResult.setLayoutManager(layoutManager);
        rvPelangganResult.setItemAnimator(new DefaultItemAnimator());
        dummyAdapter();

        getPelanggan(getIntent().getStringExtra("namaPelanggan"));


        onComponent();

        ppobView = getIntent().getIntExtra("F_PULSA", 0);
    }

    ImageButton btnBack;
    Button btnLoadMore;
    TextView tvInfoNoFound;
    int duration = 90;
    int ppobView =0;

    private void onComponent() {
        //SEARCH PELANGGAN COMPONENT
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        viewAnimator = (ViewAnimator) findViewById(R.id.VaSearch);
        final FrameLayout btnSearch = (FrameLayout) findViewById(R.id.frameLayoutSearch);
        ImageView ivSearch = (ImageView) findViewById(R.id.ivSearch);
        ImageButton imageButton = (ImageButton) findViewById(R.id.btnBackSearch);
        actvInputSearchWord = (AutoCompleteTextView) findViewById(R.id.edInputSearchWord);
        ImageView ivDelSearch = (ImageView) findViewById(R.id.ivDeleteText);

        ivDelSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actvInputSearchWord.setText("");
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation in = AnimationUtils.loadAnimation(PelangganSearchResult.this, R.anim.slide_in_left_dp);
                Animation out = AnimationUtils.loadAnimation(PelangganSearchResult.this, R.anim.slide_out_left_dp);
                viewAnimator.setInAnimation(in);
                viewAnimator.setOutAnimation(out);
                in.setDuration(duration);
                out.setDuration(duration);
                in.setInterpolator(new AccelerateDecelerateInterpolator());
                out.setInterpolator(new AccelerateDecelerateInterpolator());
                viewAnimator.showPrevious();
            }
        });
        View.OnClickListener searchWriting = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation in = AnimationUtils.loadAnimation(PelangganSearchResult.this, R.anim.slide_in_right_dp);
                Animation out = AnimationUtils.loadAnimation(PelangganSearchResult.this, R.anim.slide_out_right_dp);
                in.setDuration(duration);
                out.setDuration(duration);
                in.setInterpolator(new AccelerateDecelerateInterpolator());
                out.setInterpolator(new AccelerateDecelerateInterpolator());
                viewAnimator.setInAnimation(in);
                viewAnimator.setOutAnimation(out);
                viewAnimator.showNext();
            }
        };


        rvPelangganResult.addOnItemTouchListener(pelangganClick);

        ivSearch.setOnClickListener(searchWriting);
        btnSearch.setOnClickListener(searchWriting);
        //END SEARCH PELANGGAN COMPONENT

        btnLoadMore = (Button) findViewById(R.id.btnLoadMoreSearch);
        tvInfoNoFound = (TextView) findViewById(R.id.tvInfo);
        actvInputSearchWord = (AutoCompleteTextView) findViewById(R.id.edInputSearchWord);

        actvInputSearchWord.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    getPelanggan(actvInputSearchWord.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    ListenerRecyclerItemClick pelangganClick = new ListenerRecyclerItemClick(getBaseContext(), new ListenerRecyclerItemClick.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            getSecPref().edit().putString(Cfg.prefsKostumerName_STR, mm.getData().get(position).getName()).apply();
            getSecPref().edit().putLong(Cfg.prefsKostumerId_Long, mm.getData().get(position).getCustomerId()).apply();
            getSecPref().edit().putString(Cfg.prefsTeleponKostumerStr, mm.getData().get(position).getPhone()).apply();
            getSecPref().edit().putString(Cfg.prefsEmailKostumer_Str, mm.getData().get(position).getEmail()).apply();

            if (getIntent().getStringExtra("origin") != null) {
                if (getIntent().getStringExtra("origin").equalsIgnoreCase("summary")) {
                    Intent intent = new Intent(getBaseContext(), SummaryActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                    dbTransaction.updateKostumer(mm.getData().get(position).getName(), mm.getData().get(position).getCustomerId(), getSecPref().getString(Cfg.prefsRecentTransIdStr, null));
                    return;
                }
            }
            else if(ppobView == RVAdapterPelanggan.PPOB_VIEW){
                PulsaFragment.edtNumber.setText(mm.getData().get(position).getPhone());
                getSecPref().edit().putString(Cfg.ppobBackKey, Cfg.ppobBackVal).apply();
                finish();

            }else{
                Intent intent = new Intent(getBaseContext(), Home.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }
    });

    ModelPelanggan mm;

    public void getPelanggan(String username) {
        dfLoading.show(getSupportFragmentManager(),"");
        SvPelanggan svPelanggan = ApiClient.serviceGenerator(Cfg.BASEURL_IDENTITY).create(SvPelanggan.class);
        Call<ModelPelanggan> call = svPelanggan.searchPelanggan(username,
                getSecPref().getString(Cfg.prefsOutletId_STR, null));
        call.enqueue(new Callback<ModelPelanggan>() {
            @Override
            public void onResponse(Call<ModelPelanggan> call, Response<ModelPelanggan> response) {
                dfLoading.dismiss();
                mm = response.body();
                if (response.isSuccessful()) {
                    if (mm.getStatus() != false) {
                        if (mm.getData().size() > 0) {
                            rvPelangganResult.setAdapter(new RVAdapterPelanggan(PelangganSearchResult.this, mm, 0));
                            rvPelangganResult.setVisibility(View.VISIBLE);
                            tvInfoNoFound.setVisibility(View.GONE);
//                        btnLoadMore.setVisibility(View.VISIBLE);
                        }

                    } else {
                        rvPelangganResult.setVisibility(View.GONE);
                        tvInfoNoFound.setVisibility(View.VISIBLE);
                    }

                } else {
                    Toast.makeText(PelangganSearchResult.this, Cfg.ERRNOT200, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ModelPelanggan> call, Throwable t) {
                dfLoading.dismiss();
                Toast.makeText(PelangganSearchResult.this, MyUtils.getThisMethodNameForError(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void dummyAdapter() {
        rvPelangganResult.setAdapter(new RecyclerView.Adapter() {
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
}