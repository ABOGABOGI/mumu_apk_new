package id.hike.apps.android_mpos_mumu.features.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseFragment;
import id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.FragmentTelepon;
import id.hike.apps.android_mpos_mumu.util.DBTransaction;

public class MainPPOBFragment extends BaseFragment {

    private FrameLayout main_container;
    //public BottomBar bottomBar;
    private DBTransaction dbTransaction;
    //private TextView depositBalance;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.frag_ppob, container, false);
        setHasOptionsMenu(true);

        ((Home)getActivity()).tabSearch.setVisibility(View.GONE);

        main_container = (FrameLayout) v.findViewById(R.id.main_container);
        main_container.setBackgroundColor(Color.parseColor("#f3f3ff"));
        /*depositBalance = (TextView) v.findViewById(R.id.deposit_balance);

        tabPerformClick(v,getTag());

        bottomBar = (BottomBar) v.findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                switch (tabId) {
                    case R.id.tab_pulsa:
                        commitFragment(new PulsaFragment());
                        break;
                    case R.id.tab_listrik:
                        commitFragment(new F_Listrik());
                        break;
                    case R.id.tab_tv:
                        commitFragment(new F_Tv());
                        break;
                }
            }
        });*/

        dbTransaction = new DBTransaction(getActivity());

        switch(getTag()){
            case Cfg.TAG_PULSA:

                commitFragment(new PulsaFragment());


                break;


            case Cfg.TAG_LISTRIK:

                commitFragment(new ListrikFragment());

                break;


            case Cfg.TAG_TELKOM:

                commitFragment(new TelkomFragment());

                break;

            case Cfg.TAG_PDAM:

                commitFragment(new PDAMFragment());

                break;

            case Cfg.TAG_TELEPON:

                commitFragment(new FragmentTelepon());

                break;
        }


        return v;
    }


    @Override
    public void onStart() {
        super.onStart();
        //if(TokenUtils.isTokenExpired(getFragSecPrefs())){
        //    doActionWithRefreshToken();

        //}else{
        //    getDepositBalance();
        //    getAllPpob();
        //}
    }

    private void commitFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }

    public boolean checkProductIdExistence(int productId) {
        return dbTransaction.checkProductExistence(productId, getFragSecPrefs().getString(Cfg.prefsRecentTransIdStr, null));
    }

    int getJumlahBarangByProdukId(int produkId){
        return dbTransaction.getJumlahBarang(produkId);
    }


    public boolean isItemDeletedOnTheTrans(String transaQueue, int productId) {
        return dbTransaction.isItemDeletedOnTheTrans(productId, transaQueue);
    }

    public void updateTransaction(int jumlahBeli, int productId) {
        //menggunakan transaksi Id hasil dari insertBlankTransaksi
        dbTransaction.updateJumlah(jumlahBeli, getFragSecPrefs().getString(Cfg.prefsRecentTransIdStr, null), productId);
    }

    private void getDepositBalance(){
        /*HomeService homeService = ApiClient.serviceGenerator(Cfg.BASEURL_WALLET_APIS).create(HomeService.class);
        Call<ResDeposit> call = homeService.getDeposit(TokenUtils.getBearer(getFragSecPrefs()));
        call.enqueue(new Callback<ResDeposit>() {
            @Override
            public void onResponse(Call<ResDeposit> call, Response<ResDeposit> response) {
                ResDeposit deposit = response.body();
                if(response.code() == 200){
                    depositBalance.setText(UnitConversion.format2Rupiah(deposit.getBalance()));
                    getFragSecPrefs()
                            .edit()
                            .putLong(Cfg.depositKey, deposit.getBalance())
                            .apply();
                }else{
                    depositBalance.setText("Rp. 0");
                    getFragSecPrefs()
                            .edit()
                            .putLong(Cfg.depositKey, 0)
                            .apply();
                }

            }

            @Override
            public void onFailure(Call<ResDeposit> call, Throwable throwable) {
                depositBalance.setText("Terjadi Kesalahan");
            }
        });*/

    }

}
