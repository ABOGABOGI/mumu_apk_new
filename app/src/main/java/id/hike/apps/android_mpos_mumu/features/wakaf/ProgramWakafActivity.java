package id.hike.apps.android_mpos_mumu.features.wakaf;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.landing_page.api.WakafApi;
import id.hike.apps.android_mpos_mumu.features.wakaf.adapter.RvAdapterWakaf;
import id.hike.apps.android_mpos_mumu.model.MetaInfo;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ProgramWakafActivity extends AppCompatActivity {

    @BindView(R.id.shimmerLayout)
    ShimmerFrameLayout shimmerFrameLayout;

    @BindView(R.id.rvWakaf)
    RecyclerView rvWakaf;

    @BindView(R.id.toolbar)
    View toolbar;

    private RvAdapterWakaf adapterWakaf;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_program_wakaf);
        ButterKnife.bind(this);

        adapterWakaf = new RvAdapterWakaf();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvWakaf.setLayoutManager(manager);
        rvWakaf.setAdapter(adapterWakaf);

        refresh();

        TextView titleView = toolbar.findViewById(R.id.abTvTitle);
        titleView.setText("Wakafku");

        ImageView btnBack = toolbar.findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void hideShimmer(){
        shimmerFrameLayout.setVisibility(View.GONE);
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.clearAnimation();

        rvWakaf.setVisibility(View.VISIBLE);
    }

    private void refresh(){
        WakafApi api = new WakafApi();

        api.index().subscribe(new Observer<List<MetaInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<MetaInfo> metaInfos) {

                hideShimmer();
                adapterWakaf.setWakafs(metaInfos);

            }

            @Override
            public void onError(Throwable e) {
                hideShimmer();

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}


