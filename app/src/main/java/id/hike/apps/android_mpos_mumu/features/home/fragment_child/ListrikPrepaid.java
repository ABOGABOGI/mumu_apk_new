package id.hike.apps.android_mpos_mumu.features.home.fragment_child;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.api.ProdukApi;
import id.hike.apps.android_mpos_mumu.base.BaseFragment;
import id.hike.apps.android_mpos_mumu.features.home.adapter.RVAdapterPlnToken;
import id.hike.apps.android_mpos_mumu.model.Produk;
import id.hike.apps.android_mpos_mumu.model.Response;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ListrikPrepaid extends BaseFragment {

    @BindView(R.id.etIdPelangganToken)
    EditText idPelanggangToken;

    @BindView(R.id.rvListrikToken)
    RecyclerView rvListrikToken;

    private RVAdapterPlnToken adapterPlnToken;

    public ListrikPrepaid(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){

        View v = inflater.inflate(R.layout.fragment_listrik_prepaid, container, false);

        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);


        getListrikToken();

    }

    private void getListrikToken(){

        ProdukApi api = new ProdukApi();

        String token = getFragSecPrefs().getString(Cfg.oauthAccessToken, "");

        api.getByKategori(token, "PLN", "TOKEN").subscribe(new Observer<Response<List<Produk>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<List<Produk>> listResponse) {

                setListToken(listResponse.getData());

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private void setListToken(List<Produk> produks){
        adapterPlnToken = new RVAdapterPlnToken(produks, getContext(), idPelanggangToken);

        rvListrikToken.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvListrikToken.setAdapter(adapterPlnToken);
    }
}
