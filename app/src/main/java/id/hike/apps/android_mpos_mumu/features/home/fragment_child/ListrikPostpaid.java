package id.hike.apps.android_mpos_mumu.features.home.fragment_child;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
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

public class ListrikPostpaid extends BaseFragment {


    @BindView(R.id.etIdPelangganPostPaid)
    EditText idPelangganPostId;

    @BindView(R.id.btnCekTagihan)
    Button cekTagihan;

    @BindView(R.id.rvListrikTagihan)
    RecyclerView rvListrikTagihan;

    @BindView(R.id.shimmerLayout)
    ShimmerFrameLayout shimmerFrameLayout;

    @BindView(R.id.bayarBtn)
    Button bayarBtn;

    private TransaksiTagihan tagihan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){

        View v = inflater.inflate(R.layout.fragment_listrik_postpaid, container, false);

        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);

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

    }

    private void checkTagihan(){

        shimmerFrameLayout.setVisibility(View.VISIBLE);
        rvListrikTagihan.setVisibility(View.GONE);
        shimmerFrameLayout.startShimmer();

        TransaksiApi api = new TransaksiApi();

        String token = getFragSecPrefs().getString(Cfg.oauthAccessToken, "");

        api.inquiry(token, "1234567", idPelangganPostId.getText().toString())
                .subscribe(new Observer<Response<TransaksiTagihan>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<TransaksiTagihan> transaksiTagihanResponse) {

                        TransaksiTagihan t = transaksiTagihanResponse.getData();
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
                            Toast.makeText(getContext(), "Nomor tagihan tidak ditemukan", Toast.LENGTH_LONG).show();
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

    private void stopAnimation(){
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.clearAnimation();
        shimmerFrameLayout.setVisibility(View.GONE);
    }

    private void updateTagihan(RVAdapterTagihan adapterTagihan){
        stopAnimation();
        rvListrikTagihan.setVisibility(View.VISIBLE);

        bayarBtn.setVisibility(View.VISIBLE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        rvListrikTagihan.setLayoutManager(linearLayoutManager);
        rvListrikTagihan.setAdapter(adapterTagihan);
    }

}
