package id.hike.apps.android_mpos_mumu.features.aktifitas;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.api.TransaksiApi;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.aktifitas.adapter.RvAdapterHistory;
import id.hike.apps.android_mpos_mumu.features.aktifitas.adapter.RvAdapterTitle;
import id.hike.apps.android_mpos_mumu.features.aktifitas.model.ResAktifitas;
import id.hike.apps.android_mpos_mumu.features.aktifitas.model.ResAktifitasNew;
import id.hike.apps.android_mpos_mumu.model.Response;
import id.hike.apps.android_mpos_mumu.model.TransaksiInfo;
import id.hike.apps.android_mpos_mumu.util.MyUtils;
import id.hike.apps.android_mpos_mumu.util.SimpleDividerItemDecoration;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AktifitasHistoryActivity extends BaseActivity {

    RecyclerView rvAktifitas;
    List<ResAktifitas> mt;
    String tempTransDateParamRvAdapter = "";
    LinearLayout.LayoutParams zeroHeightParam;
    ListView lvAktifitas2;

    private TransaksiApi transaksiApi = new TransaksiApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_aktifitas);

        ((TextView)findViewById(R.id.abTvTitle)).setText(getString(R.string.title_aktifitas));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvAktifitas = (RecyclerView) findViewById(R.id.rvAktifitas);
        rvAktifitas.setLayoutManager(layoutManager);
        rvAktifitas.setItemAnimator(new DefaultItemAnimator());

        zeroHeightParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);

        mt = new ArrayList<>();

        rvAktifitas.addItemDecoration(new SimpleDividerItemDecoration(this));

        ImageView imageView = (ImageView) findViewById(R.id.btnBack);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        lvAktifitas2 = (ListView) findViewById(R.id.lvAktifitas2);

        getAktifitas();
    }

    void dummyAdapter() {
        rvAktifitas.setAdapter(new RecyclerView.Adapter() {
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

    void getAktifitas() {
        dfLoading.show(getSupportFragmentManager(),"");
        dfLoading.setCancelable(false);

        transaksiApi.getHistory().subscribe(new Observer<Response<List<TransaksiInfo>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<List<TransaksiInfo>> listResponse) {
                dfLoading.dismiss();

                if(listResponse.getData() != null){
                    RvAdapterHistory adapterHistory = new RvAdapterHistory();
                    adapterHistory.setTransaksiInfoList(listResponse.getData());

                    rvAktifitas.setAdapter(adapterHistory);
                }


            }

            @Override
            public void onError(Throwable e) {
                dfLoading.dismiss();
                Toast.makeText(AktifitasHistoryActivity.this, MyUtils.getThisMethodNameForError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }
        });

//        AktifitasService aktifitasService = ApiClient.serviceGenerator(Cfg.BASEURL_TRANSAKSI).create(AktifitasService.class);
//        Call<ResAktifitasNew> call = aktifitasService.getAktifitas(getSecPref().getString(Cfg.prefsOutletId_STR,""));
//        call.enqueue(new Callback<ResAktifitasNew>() {
//            @Override
//            public void onResponse(Call<ResAktifitasNew> call, Response<ResAktifitasNew> response) {
//                dfLoading.dismiss();
//                ResAktifitasNew resAktifitas = response.body();
//                final RvAdapterAktivitas rvAdapterAktivitas = new RvAdapterAktivitas(generateGroupDate(resAktifitas),getSupportFragmentManager(),AktifitasHistoryActivity.this);
//                rvAktifitas.setAdapter(rvAdapterAktivitas);
//                rvAdapterAktivitas.toggleGroup(0);
//            }
//
//            @Override
//            public void onFailure(Call<ResAktifitasNew> call, Throwable t) {
//                dfLoading.dismiss();
//                Toast.makeText(AktifitasHistoryActivity.this, MyUtils.getThisMethodNameForError(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public List<RvAdapterTitle> generateGroupDate(ResAktifitasNew mm) {
        List<RvAdapterTitle> rvAdapterTitles = new ArrayList<>();
        List<ModelTemp> modelTemp=new ArrayList<>();

        String tempTanggal="";
        for (int i = 0; i < mm.getData().size(); i++) {
            Long tanggal = mm.getData().get(i).getUpdated_date();
            if(mm.getData().get(i).getIs_digital() == 1){
                tanggal = getPpobDateTimeMilis(tanggal);
            }
            String tanggalS = UnitConversion.ConvertMilliSecondsToAktivityTime(
                    tanggal
            );
            if (!tanggalS.equalsIgnoreCase(tempTanggal)){
                Long tanggal2 = mm.getData().get(i).getUpdated_date();

                if(mm.getData().get(i).getIs_digital() == 1){
                    tanggal2 = getPpobDateTimeMilis(tanggal2);
                }

                modelTemp.add(new ModelTemp(tanggal2,tanggal));
                tempTanggal=tanggalS;
            }
        }


        Map<String, List<ResAktifitasNew.DataAktifitas>> listGroupDate = new HashMap<>();
        for (ResAktifitasNew.DataAktifitas resAktifitasData : mm.getData()) {
            //key adl tanggal
            Long updatedDate = resAktifitasData.getUpdated_date();
            if(resAktifitasData.getIs_digital() == 1){// ppob
                updatedDate = getPpobDateTimeMilis(updatedDate);
            }
            String key = UnitConversion.ConvertMilliSecondsToAktivityTime(
                    updatedDate);
            if (listGroupDate.get(key) == null) {
                listGroupDate.put(key, new ArrayList<ResAktifitasNew.DataAktifitas>());
            }
            listGroupDate.get(key).add(resAktifitasData);
        }

        Collections.sort(modelTemp,new CustomComparator());
        Collections.reverse(modelTemp);

        for (int i = 0; i < modelTemp.size(); i++) {
            Long tanggal = modelTemp.get(i).getTanggal();
            String activityDate = UnitConversion.ConvertMilliSecondsToAktivityTime(tanggal);
            rvAdapterTitles.add(new RvAdapterTitle(activityDate, listGroupDate.get(activityDate)));
        }

        return rvAdapterTitles;
    }

    private Long getPpobDateTimeMilis(Long currMilis){
        Integer minusHourPpob = 7;
        DateTime ppobTime3 = new DateTime(currMilis);
        ppobTime3 = ppobTime3.minusHours(minusHourPpob);
        return ppobTime3.getMillis();
    }

    class CustomComparator implements Comparator<ModelTemp>{
        @Override
        public int compare(ModelTemp o1, ModelTemp o2) {
            return o1.getTransDate().compareTo(o2.getTransDate());
        }
    }

    class ModelTemp{
        Long transDate;
        Long tanggal;

        public ModelTemp(Long transDate, Long tanggal) {
            this.transDate = transDate;
            this.tanggal = tanggal;
        }

        public Long getTransDate() {
            return transDate;
        }

        public Long getTanggal() {
            return tanggal;
        }
    }
}

