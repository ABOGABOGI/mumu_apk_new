package id.hike.apps.android_mpos_mumu.features.payment.df;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseDialogFragment;
import id.hike.apps.android_mpos_mumu.features.landing_page.LandingPage;
import id.hike.apps.android_mpos_mumu.features.payment.model.ResDataSuccess;
import id.hike.apps.android_mpos_mumu.features.summary.SummaryService;
import id.hike.apps.android_mpos_mumu.features.summary.model.ReqBatchDeleteItem;
import id.hike.apps.android_mpos_mumu.features.summary.model.ReqSalesItemBatal;
import id.hike.apps.android_mpos_mumu.features.summary.model.ReqsalesItemListBatal;
import id.hike.apps.android_mpos_mumu.global.global_model.ModelTransaksiPpob;
import id.hike.apps.android_mpos_mumu.util.ApiClient;
import id.hike.apps.android_mpos_mumu.util.MyUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by getwiz on 18/05/17.
 */

public class DfBatalPayment extends BaseDialogFragment {

    EditText etRemarks;
    String ppobTransId;

    Integer cekStatusCounter;

    private void removePendingPpob(String transId){

        Gson gson = new Gson();
        Type ppobTransListType = new TypeToken<List<ModelTransaksiPpob>>(){}.getType();
        String ppobListTransString = getDFSecPref().getString(Cfg.ppobTransList,null);
        List<ModelTransaksiPpob> listPpobTrans = null;
        if(ppobListTransString!=null){
            listPpobTrans = gson.fromJson(ppobListTransString, ppobTransListType);
            for(int counter=0;counter<listPpobTrans.size();counter++){
                if(listPpobTrans.get(counter).getTransaId().equals(transId)){
                    listPpobTrans.remove(listPpobTrans.get(counter));
                    ppobListTransString = gson.toJson(listPpobTrans);
                    getDFSecPref().edit().putString(Cfg.ppobTransList, ppobListTransString).apply();
                    break;

                }

            }

        }


    }

    private void goToHome(){
        Intent intent = new Intent(mContext, LandingPage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    void batchDeleteTransaksi(List<ReqSalesItemBatal> trsalesItemSimpen, List<ReqsalesItemListBatal> trsalesitemListBatal) {
        dfLoading.show(getFragmentManager(), "");
        dfLoading.setCancelable(false);
        SummaryService menulist = ApiClient.serviceGenerator(Cfg.BASEURL_TRANSAKSI).create(SummaryService.class);
        Call<ResDataSuccess> call = menulist.batalTransaksi(new ReqBatchDeleteItem(
                trsalesItemSimpen, trsalesitemListBatal
        ));
        call.enqueue(new Callback<ResDataSuccess>() {
            @Override
            public void onResponse(Call<ResDataSuccess> call, Response<ResDataSuccess> response) {
                dfLoading.dismiss();
                hideKeyboard();
                Intent intent = new Intent(mContext, LandingPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                Toast.makeText(mContext, getString(R.string.transaksi_dihapus), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResDataSuccess> call, Throwable t) {
                dfLoading.dismiss();
                Toast.makeText(getActivity().getApplicationContext(), MyUtils.getThisMethodNameForError(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
