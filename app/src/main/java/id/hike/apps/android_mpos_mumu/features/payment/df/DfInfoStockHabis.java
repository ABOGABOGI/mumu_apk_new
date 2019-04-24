package id.hike.apps.android_mpos_mumu.features.payment.df;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.payment.F_Payment;
import id.hike.apps.android_mpos_mumu.features.payment.Payment;

/**
 * Created by getwiz on 18/05/17.
 */

public class DfInfoStockHabis extends DialogFragment {

    TextView tvListItemStock;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogStyle);
        View view=getActivity().getLayoutInflater().inflate(R.layout.df_list_stok_kosong,null);

        tvListItemStock= (TextView) view.findViewById(R.id.tvListItemStock);

        String itemStokKosong="";
        for (int i=0;i<f_payment.listStockItems.size();i++){
            itemStokKosong+=String.valueOf(i+1)+". "+f_payment.listStockItems.get(i).getProductName()+" (Stok: "+f_payment.listStockItems.get(i).getStock()+") \n";
        }

        tvListItemStock.setText(itemStokKosong);

        Button btnCloseDf= (Button) view.findViewById(R.id.btnCloseDF);
        btnCloseDf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f_payment.btnCashUbah.performClick();
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

    Payment payment;
    F_Payment f_payment;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        payment= (Payment) activity;
        f_payment= (F_Payment) payment.getSupportFragmentManager().findFragmentById(R.id.activity_payment);
    }
}