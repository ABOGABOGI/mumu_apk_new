package id.hike.apps.android_mpos_mumu.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import id.hike.apps.android_mpos_mumu.app.BaseApplication;
import id.hike.apps.android_mpos_mumu.features.login.df.DfLoading;
import id.hike.apps.android_mpos_mumu.util.DBTransaction;

/**
 * Created by root on 19/07/17.
 */

public class BaseDialogFragment extends DialogFragment {

    public DBTransaction dbTransaction;
    Context mContext;
    public DfLoading dfLoading;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dbTransaction=new DBTransaction(getActivity());
        return super.onCreateDialog(savedInstanceState);
    }

    public SharedPreferences getDFSecPref(){
        return ((BaseApplication)getActivity().getApplication()).secPrefs;
    }

    public void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager)mContext.getSystemService(
                Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public DBTransaction getDbTransaction() {
        return dbTransaction;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
        dfLoading =new DfLoading();
    }
}
