package id.hike.apps.android_mpos_mumu.features.home;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.api.ProdukApi;
import id.hike.apps.android_mpos_mumu.base.BaseFragment;
import id.hike.apps.android_mpos_mumu.features.home.adapter.RVAdapterPpob;
import id.hike.apps.android_mpos_mumu.features.home.decoration.GridSpacingItemDecoration;
import id.hike.apps.android_mpos_mumu.features.home.enums.PPOB_PREFIX;
import id.hike.apps.android_mpos_mumu.features.pelanggan.Pelanggan;
import id.hike.apps.android_mpos_mumu.features.pelanggan.RVAdapterPelanggan;
import id.hike.apps.android_mpos_mumu.model.Produk;
import id.hike.apps.android_mpos_mumu.model.Response;
import id.hike.apps.android_mpos_mumu.util.DBTransaction;
import id.hike.apps.android_mpos_mumu.util.ProviderFinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class PulsaFragment extends BaseFragment {
    private int productId;
    private int basePrice;
    private int salesPrice;
    private DBTransaction dbTransaction;
    Home activityHome;
    public static final int RequestPermissionCode = 1;

    private RecyclerView rvPulsa;
    private GridLayoutManager gridLayoutManagerPulsa;
    private RecyclerView.Adapter adapterPulsa;


    public PulsaFragment() {
        // Required empty public constructor
    }

    MainPPOBFragment fragPPOB;
    public static EditText edtNumber;
    ImageView ivOperator;
    ImageView ivAdduser;

    private PPOB_PREFIX lastPrefix;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.frag_pulsa, container, false);

        activityHome.homePresenter.setToolbarTitle(getString(R.string.title_pembelian_pulsa_telepon));

        ivAdduser = (ImageView) view.findViewById(R.id.ivAdduser);

        rvPulsa = (RecyclerView) view.findViewById(R.id.rvPulsaParent);
        gridLayoutManagerPulsa = new GridLayoutManager(view.getContext(),2);



        edtNumber = (EditText) view.findViewById(R.id.edtNumber);

        edtNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(edtNumber.getText().toString().length() >= 4){
                    getProductListByPhoneNumb();
                }else{
                    if(adapterPulsa != null){
                        ((RVAdapterPpob) adapterPulsa).clear();
                    }
                    lastPrefix = null;
                    ivOperator.setVisibility(View.GONE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        ivOperator = (ImageView) view.findViewById(R.id.ivOperator);
        ivOperator.setVisibility(View.GONE);
        dbTransaction = new DBTransaction(getActivity());

        ivAdduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Pelanggan.class);
                intent.putExtra("F_PULSA", RVAdapterPelanggan.PPOB_VIEW);
                startActivity(intent);
            }
        });



        return view;
    }

    private void getProductListByPhoneNumb(){
        String number = edtNumber.getText().toString();

        PPOB_PREFIX prefix = ProviderFinder.getInstance().getProvider(number);

        if(lastPrefix != prefix){
            lastPrefix = prefix;
            if(prefix != null){
                getPulsaByIdPrefix(prefix);


                switch(prefix){
                    case TELKOMSEL:
                        ivOperator.setImageResource(R.drawable.opr_telkomsel);
                        break;

                    case XL:
                        ivOperator.setImageResource(R.drawable.opr_xl);
                        break;

                    case INDOSAT:
                        ivOperator.setImageResource(R.drawable.opr_im3);
                        break;

                    case AXIS:
                        ivOperator.setImageResource(R.drawable.opr_axis);
                        break;

                    case SMARTFREN:
                        ivOperator.setImageResource(R.drawable.opr_smartfren);
                        break;

                    case THREE:
                        ivOperator.setImageResource(R.drawable.opr_tri);
                        break;
                }

                ivOperator.setVisibility(View.VISIBLE);
                getPulsaByIdPrefix(prefix);
            }
        }


    }



//    void sendToDetailFragment(int productId, Long tagihan, Long biayaAdmin){
//        Double tagihanD = tagihan.doubleValue();
//        Double biayaAdminD = biayaAdmin.doubleValue();
//        Long adminPlusTagihanL = tagihan+biayaAdmin;
//        Double adminPlusTagihanD = tagihanD+biayaAdminD;
//        ResMobileProdukContent modelProdukParent=new ResMobileProdukContent(
//                "Y",null,productId,null,5,adminPlusTagihanL,"1",null,"1","description","PLS "+ edtNumber.getText().toString(),null,null,
//                "423512743Harga Rp 0.png",null,"pcs",adminPlusTagihanD,adminPlusTagihanD,"PPOB",100,"4534562533",
//                null,adminPlusTagihanD,"Y"
//        );
//
//        fragPPOB.modelProdukParent=modelProdukParent;
//
//        Bundle bundle = new Bundle();
//        bundle.putString(Cfg.BUNDLE_NAMAPRODUK_STRING, modelProdukParent.getProductName());
//        bundle.putLong(Cfg.BUNDLE_HARGA_PRODUK_LONG, modelProdukParent.getSalesPrice());
//        bundle.putLong(Cfg.BUNDLE_STOK_PRODUK_LONG, modelProdukParent.getStock());
//        bundle.putInt(Cfg.BUNDLE_PRODUK_ID_INT, modelProdukParent.getProductId());
//
//        bundle.putString(Cfg.BUNDLE_URLPRODUK, "");
//
//        bundle.putLong(Cfg.BUNDLE_BIAYA_ADMIN_LONG, tagihan);
//        bundle.putLong(Cfg.BUNDLE_BIAYA_TAGIHAN_LONG, biayaAdmin);
////        bundle.putLong(Cfg.BUNDLE_NOMOR_TELEPON_PULSA,);
//
//        //1. jika produk belum ada di transa tabel
//        if (!fragPPOB.checkProductIdExistence(modelProdukParent.getProductId())) {
//            fragPPOB.insertTransaction(1);
//            activityHome.homePresenter.showSnackbar();
//            return;
//        }
//
//        //2. jika produk ada && status delete
//        if (fragPPOB.checkProductIdExistence(modelProdukParent.getProductId()) &&
//                fragPPOB.isItemDeletedOnTheTrans(getFragSecPrefs().getString(Cfg.prefsRecentTransIdStr, null),  modelProdukParent.getProductId())) {
//            fragPPOB.insertTransaction(1);
//            activityHome.homePresenter.showSnackbar();
//        } else { //3. jika produk ada && status tidak delete
//            fragPPOB.updateTransaction(1,  modelProdukParent.getProductId());
//            activityHome.homePresenter.showSnackbar();
//        }
//    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);

        GridSpacingItemDecoration spacingItemDecoration = new GridSpacingItemDecoration(2, 50, false);

        rvPulsa.setLayoutManager(gridLayoutManagerPulsa);
        rvPulsa.addItemDecoration(spacingItemDecoration);
    }

    private void getPulsaByIdPrefix(PPOB_PREFIX prefix){
        List<Produk> listPerPrefix = new ArrayList<>();
        adapterPulsa = new RVAdapterPpob(listPerPrefix, fragPPOB, activityHome, this);
        rvPulsa.setAdapter(adapterPulsa);
        getFromApis(prefix.getText());
    }

    private void getFromApis(String biller) {
        ProdukApi api = new ProdukApi();

        String token = getFragSecPrefs().getString(Cfg.oauthAccessToken, "");

        Log.i("BOWOTAG", biller);

        api.getByPrefix(token, biller, "PULSA").subscribe(new Observer<Response<List<Produk>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<List<Produk>> listResponse) {
                ((RVAdapterPpob) adapterPulsa).setProduks(listResponse.getData());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activityHome = ((Home)activity);
        fragPPOB = (MainPPOBFragment) ((Home)activity).getSupportFragmentManager().findFragmentById(R.id.transLayout);
    }
}
