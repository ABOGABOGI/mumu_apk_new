package id.hike.apps.android_mpos_mumu.features.landing_page.df;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseDialogFragment;
import id.hike.apps.android_mpos_mumu.features.landing_page.LandingPage;
import id.hike.apps.android_mpos_mumu.features.landing_page.model.ResEditTrans;
import id.hike.apps.android_mpos_mumu.features.payment.PaymentService;
import id.hike.apps.android_mpos_mumu.features.summary.SummaryActivity;
import id.hike.apps.android_mpos_mumu.util.ApiClient;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.util.GreenDividerItemDecoration;
import id.hike.apps.android_mpos_mumu.util.MyUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by getwiz on 18/05/17.
 */

public class DFPendingTransaction extends BaseDialogFragment {

    RecyclerView recyclerView;
    String choosenTransId, choosenTransDate;
    String choosenName, choosenPhone, choosenEmail;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.TransactionListDialog);
        View view=getActivity().getLayoutInflater().inflate(R.layout.df_transaction_list,null);
        recyclerView= (RecyclerView) view.findViewById(R.id.rvTransactionList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new GreenDividerItemDecoration(getActivity()));
        dummyAdapter();

//        recyclerView.setAdapter(new RvAdapterPendingTrans(getActivity(),landingPage.modelTransaksiTertunda2));

        ImageButton btnCloseDf= (ImageButton) view.findViewById(R.id.btnCloseDF);
        btnCloseDf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

//        ListenerRecyclerItemClick listTransaksiClick=new ListenerRecyclerItemClick(getActivity(), new ListenerRecyclerItemClick.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                choosenTransId=String.valueOf(landingPage.modelTransaksiTertunda2.get(position).transaksiId);
//                choosenName=String.valueOf(landingPage.modelTransaksiTertunda2.get(position).nama);
//                choosenTransDate=String.valueOf(landingPage.modelTransaksiTertunda2.get(position).transDate);
//                choosenPhone=String.valueOf(landingPage.modelTransaksiTertunda2.get(position).customerPhone);
//                choosenEmail=String.valueOf(landingPage.modelTransaksiTertunda2.get(position).email);
//                String type = landingPage.modelTransaksiTertunda2.get(position).transType;
//                if(type.equals(Cfg.transTypeValNonPpob)){
//                    editTransaksi(String.valueOf(landingPage.modelTransaksiTertunda2.get(position).transaksiId));
//                }
//                else if(type.equals(Cfg.transTypeValPulsa) ||
//                        type.equals(Cfg.transTypeValPulsa) ||
//                        type.equals(Cfg.transTypeValListrik) ) {
//
//                    Intent intent=new Intent(getActivity(),SummaryActivity.class);
//                    getDFSecPref().edit().putString(Cfg.pulsaTransIdKey, landingPage.modelTransaksiTertunda2.get(position).transaksiId).apply();
//                    intent.putExtra(Cfg.transactionTypeKey, Cfg.pulsaTypeVal);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
//
//                }
//            }
//        });

//        recyclerView.addOnItemTouchListener(listTransaksiClick);

        builder.setView(view);

        return builder.create();
    }

    void dummyAdapter() {
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });
    }

    LandingPage landingPage;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        landingPage= (LandingPage) activity;
    }

    void editTransaksi(final String transId) {
        dfLoading.show(getFragmentManager(),"");
        PaymentService menulist = ApiClient.serviceGenerator(Cfg.BASEURL_TRANSAKSI).create(PaymentService.class);
        Call<ResEditTrans> call = menulist.editTrans(transId);
        call.enqueue(new Callback<ResEditTrans>() {
            @Override
            public void onResponse(Call<ResEditTrans> call, Response<ResEditTrans> response) {
                dfLoading.dismiss();
                if (response.isSuccessful()) {
                    ResEditTrans mm = response.body();

                    getDFSecPref().edit().putString(Cfg.prefsRecentTransIdStr,choosenTransId).apply();
                    getDFSecPref().edit().putString(Cfg.prefsKostumerName_STR,choosenName).apply();
                    getDFSecPref().edit().putString(Cfg.prefsTeleponKostumerStr,choosenPhone).apply();
                    getDFSecPref().edit().putString(Cfg.prefsEmailKostumer_Str,choosenEmail).apply();
                    getDFSecPref().edit().putString(Cfg.prefsNegoDisc_Str,mm.getModelEditTransTrsales().getNegoDisc()==null?"0":mm.getModelEditTransTrsales().getNegoDisc().toString()).apply();
                    getDFSecPref().edit().putLong(Cfg.prefsKostumerId_Long,mm.getModelEditTransTrsales().getCustomerId()).apply();

                    /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date resultdate = new Date(mm.getModelEditTransTrsales().getTransDate());*/
                    getDFSecPref().edit().putString(Cfg.prefsTransDate_STR, mm.getModelEditTransTrsales().getTransDate()).apply();

                    for (int i=0;i<mm.getData().size();i++){
                        landingPage.dbTransaction.insertTransactionWithDetailId(
                                mm.getData().get(i).getProductName(),
                                mm.getData().get(i).getProductId(),
                                String.valueOf(mm.getData().get(i).getItemTransId()),
                                getDFSecPref().getString(Cfg.prefsNamaKasir_STR,Cfg.defaultKasirName),
                                getDFSecPref().getInt(Cfg.prefsKasirId_INT,Cfg.defaultKasirId),
                                getDFSecPref().getString(Cfg.prefsKostumerName_STR,Cfg.defaultKostumerName),
                                getDFSecPref().getInt(Cfg.prefsKostumerId_Long,Cfg.defaultKostumerId),
                                mm.getData().get(i).getBasePrice(),
                                0, //pcnt disc nanti
                                0,// price disc nanti
                                // yang negoDisc ditaruh disharedprefs aja
                                Cfg.TIS_UNLOCK,Cfg.TS_POSTPONE,
                                mm.getData().get(i).getSalesPrice(),
                                mm.getData().get(i).getStock(), //ini biarin aja 0.
                                mm.getData().get(i).getBaselineStock(),
                                mm.getData().get(i).getQty(),
                                "","","","", MyUtils.getUpTime()
                                , mm.getData().get(i).getDetailId(),
                                mm.getData().get(i).getMaxPrice(),
                                mm.getData().get(i).getMinPrice());
                    }

                    Intent intent=new Intent(getActivity(), SummaryActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "404", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResEditTrans> call, Throwable t) {
                dfLoading.dismiss();
                Toast.makeText(getActivity().getApplicationContext(), "500", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
