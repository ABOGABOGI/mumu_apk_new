package id.hike.apps.android_mpos_mumu.features.login.df;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import id.hike.apps.android_mpos_mumu.R;

/**
 * Created by root on 20/06/17.
 */

public class DfLoading extends DialogFragment {

//    Context mContext;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_loading, null);
        /*ColorfulRingProgressView ringProgressView= (ColorfulRingProgressView) view.findViewById(R.id.ringProgress);
        ringProgressView.animateIndeterminate();*/
        builder.setView(view);
        builder.setCancelable(false);
        return builder.create();
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }*/
}
