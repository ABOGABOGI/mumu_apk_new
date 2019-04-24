package id.hike.apps.android_mpos_mumu.base;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.Fragment;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.app.BaseApplication;
import id.hike.apps.android_mpos_mumu.features.login.df.DfLoading;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.util.DBTransaction;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

    Context mContext;

    public DBTransaction dbTransaction;
    public String recentTransactionId;

    public BaseFragment() {
        // Required empty public constructor
    }
    public DfLoading dfLoading;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dbTransaction=new DBTransaction(getActivity());
        recentTransactionId=getFragSecPrefs().getString(Cfg.prefsRecentTransIdStr,null);
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    public String LOG=getClass().getSimpleName();

    public SharedPreferences getFragSecPrefs(){
        return ((BaseApplication)getActivity().getApplicationContext()).secPrefs;
    }

    public DBTransaction getDbTransaction() {
        return dbTransaction;
    }

    public void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(
                Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
        dfLoading =new DfLoading();
    }
}
