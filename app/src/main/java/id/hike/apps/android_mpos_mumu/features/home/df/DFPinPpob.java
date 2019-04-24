package id.hike.apps.android_mpos_mumu.features.home.df;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import id.hike.apps.android_mpos_mumu.base.BaseDialogFragment;

/**
 * Created by M Alvin Syahrin on 4/5/2018.
 */

public class DFPinPpob extends BaseDialogFragment {
    EditText edPin;
    TextView tvDeposit;
    Context mCtx;
    Context savedContext;
    SharedPreferences preferences;
    String transactionId;

    String msidn;
    String kdProduk;

    final static String depositKey = "deposit";
    final static String msidnKey = "msidn";
    final static String kdProdukKey = "kdProduk";

    public static DFPinPpob newDfPinPpob(String deposit){
        DFPinPpob df = new DFPinPpob();
        Bundle args = new Bundle();
        args.putString(depositKey, deposit);
        df.setArguments(args);
        return df;
    }

    public static DFPinPpob newDfPinPpob(String deposit, String msidn, String kdProduk) {
        DFPinPpob df = new DFPinPpob();
        Bundle args = new Bundle();
        args.putString(depositKey, deposit);
        args.putString(msidnKey, msidn);
        args.putString(kdProdukKey, kdProduk);
        df.setArguments(args);
        return df;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCtx=context;
    }
}
