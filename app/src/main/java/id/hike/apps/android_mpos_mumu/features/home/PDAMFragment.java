package id.hike.apps.android_mpos_mumu.features.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.api.ProdukApi;
import id.hike.apps.android_mpos_mumu.api.TransaksiApi;
import id.hike.apps.android_mpos_mumu.base.BaseFragment;
import id.hike.apps.android_mpos_mumu.features.home.adapter.RVAdapterTagihan;
import id.hike.apps.android_mpos_mumu.features.summary.SummaryActivity;
import id.hike.apps.android_mpos_mumu.model.Produk;
import id.hike.apps.android_mpos_mumu.model.Response;
import id.hike.apps.android_mpos_mumu.model.TagihanItem;
import id.hike.apps.android_mpos_mumu.model.TransaksiTagihan;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PDAMFragment extends BaseFragment {

    @BindView(R.id.etIdPelangganPostPaid)
    EditText idPelangganPostId;

    @BindView(R.id.btnCekTagihan)
    Button cekTagihan;

    @BindView(R.id.spWilayahPdam)
    Spinner spWilayahPdam;

    @BindView(R.id.shimmerLayout)
    ShimmerFrameLayout shimmerFrameLayout;

    @BindView(R.id.rvPdamTagihan)
    RecyclerView rvPdamTagihan;

    @BindView(R.id.bayarBtn)
    Button bayarBtn;

    private List<Produk> produkList;
    private TransaksiTagihan tagihan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){

        View v = inflater.inflate(R.layout.fragment_pdam, container, false);

        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);

        ((Home) getActivity()).homePresenter.setToolbarTitle(getString(R.string.title_pembelian_pdam));

        shimmerFrameLayout.setVisibility(View.GONE);
        bayarBtn.setVisibility(View.GONE);

        cekTagihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                checkTagihan();

            }
        });

        bayarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Produk produk = new Produk();
                produk.setName("pln_postpaid");
                produk.setHarga(Integer.parseInt(tagihan.getNilai_tagihan()));
                produk.setNominal(Integer.parseInt(tagihan.getNilai_tagihan()));

                Intent intent=new Intent(getContext(), SummaryActivity.class);
                intent.putExtra(Cfg.transactionTypeKey, Cfg.transTypeValListrik);
                intent.putExtra("msidn", "pln_postpaid");
                intent.putExtra("idPelanggan", idPelangganPostId.getText().toString());
                intent.putExtra("produk", produk);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);

            }
        });


        getWilayahPdam();

    }

    private void stopAnimation(){
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.clearAnimation();
        shimmerFrameLayout.setVisibility(View.GONE);
    }
    private void updateTagihan(RVAdapterTagihan adapterTagihan){
        stopAnimation();
        bayarBtn.setVisibility(View.VISIBLE);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        rvPdamTagihan.setLayoutManager(linearLayoutManager);
        rvPdamTagihan.setAdapter(adapterTagihan);

    }

    private void checkTagihan(){

        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmer();


        Produk produk = produkList.get(spWilayahPdam.getSelectedItemPosition());

        TransaksiApi api = new TransaksiApi();

        String token = getFragSecPrefs().getString(Cfg.oauthAccessToken, "");

        api.inquiry(token, produk.getKode_produk(), idPelangganPostId.getText().toString()).subscribe(new Observer<Response<TransaksiTagihan>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<TransaksiTagihan> transaksiResponse) {


                TransaksiTagihan t = transaksiResponse.getData();
                tagihan = t;

                if(t != null){
                    RVAdapterTagihan tagihan = new RVAdapterTagihan();
                    tagihan.addTagihan(new TagihanItem("Produk ", t.getProduk() ));
                    tagihan.addTagihan(new TagihanItem("ID Transaksi ", t.getId_trx() ));
                    tagihan.addTagihan(new TagihanItem("Nomor Pelanggan ", t.getNomor_pelanggan() ));
                    tagihan.addTagihan(new TagihanItem("Periode ", t.getPeriode() ));
                    tagihan.addTagihan(new TagihanItem("Tagihan ", "Rp. " + UnitConversion.format2Rupiah2(t.getNilai_tagihan())));

                    updateTagihan(tagihan);
                }else{
                    stopAnimation();
                    Toast.makeText(getContext(), "Tagihan tidak ditemukan", Toast.LENGTH_LONG).show();
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


    private void getWilayahPdam(){

        ProdukApi api = new ProdukApi();

        String token = getFragSecPrefs().getString(Cfg.oauthAccessToken, "");

        api.getByKategori(token, "PDAM", "-").subscribe(new Observer<Response<List<Produk>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<List<Produk>> listResponse) {

                produkList = listResponse.getData();


                setWilayahPdam();

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

    private void setWilayahPdam(){
        List<String> strings = new ArrayList<>();

        for(Produk produk : produkList){
            strings.add(produk.getName());
        }

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, strings);
        spWilayahPdam.setAdapter(stringArrayAdapter);
    }
}
