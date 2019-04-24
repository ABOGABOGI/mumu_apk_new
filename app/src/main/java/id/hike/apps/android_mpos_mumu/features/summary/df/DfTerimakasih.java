package id.hike.apps.android_mpos_mumu.features.summary.df;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.receipt_invoice.ReceiptInvoice;

/**
 * Created by getwiz on 18/05/17.
 */

public class DfTerimakasih extends DialogFragment {

    RecyclerView recyclerView;
    String tag;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogStyle);
        View view=getActivity().getLayoutInflater().inflate(R.layout.df_terimakasih,null);

        Button btnCloseDf= (Button) view.findViewById(R.id.btnCloseDF);
        tag = "";

        btnCloseDf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ReceiptInvoice.class);
                if(getTag().equals(Cfg.ppobCategory)){
                    tag= Cfg.pulsaTypeVal;
                }
                intent.putExtra(Cfg.transactionTypeKey,tag);
                startActivity(intent);
            }
        });

        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

}
