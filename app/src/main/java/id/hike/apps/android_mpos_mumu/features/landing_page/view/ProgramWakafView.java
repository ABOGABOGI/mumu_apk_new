package id.hike.apps.android_mpos_mumu.features.landing_page.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.landing_page.adapter.ProgramWakafAdapter;
import id.hike.apps.android_mpos_mumu.features.landing_page.api.WakafApi;
import id.hike.apps.android_mpos_mumu.model.MetaInfo;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ProgramWakafView extends LinearLayout {
    public ProgramWakafView(Context context) {
        super(context);
        init();
    }

    public ProgramWakafView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgramWakafView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ProgramWakafView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @BindView(R.id.shimmerLayout)
    ShimmerFrameLayout shimmerFrameLayout;

    @BindView(R.id.programWakaf)
    RecyclerView programWakaf;


    private ProgramWakafAdapter adapter;
    private LinearLayoutManager manager;

    private void init(){
        View v = inflate(getContext(), R.layout.item_program_wakaf_layout,this);

        ButterKnife.bind(this, v);

        programWakaf.setVisibility(GONE);
        manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false );
        adapter = new ProgramWakafAdapter();

        programWakaf.setAdapter(adapter);
        programWakaf.setLayoutManager(manager);



        fetchData();

    }

    public void stopShimmer(){
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.clearAnimation();
        shimmerFrameLayout.setVisibility(GONE);
        programWakaf.setVisibility(VISIBLE);
    }

    public void fetchData(){
        shimmerFrameLayout.startShimmer();
        shimmerFrameLayout.setVisibility(VISIBLE);
        programWakaf.setVisibility(GONE);

        WakafApi api = new WakafApi();

        api.filtered().subscribe(new Observer<List<MetaInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<MetaInfo> metaInfos) {

                adapter.set(metaInfos);
                stopShimmer();

            }

            @Override
            public void onError(Throwable e) {

                stopShimmer();

            }

            @Override
            public void onComplete() {

            }
        });

    }

}
