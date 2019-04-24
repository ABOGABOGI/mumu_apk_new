package id.hike.apps.android_mpos_mumu.features.payment.df;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseDialogFragment;
import id.hike.apps.android_mpos_mumu.features.payment.F_Payment;
import id.hike.apps.android_mpos_mumu.features.payment.Payment;

/**
 * Created by root on 07/09/17.
 */

public class DfEMoney extends BaseDialogFragment{

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.TransactionListDialog);
        View view=getActivity().getLayoutInflater().inflate(R.layout.df_emoney,null);

        Button btnBatal = (Button) view.findViewById(R.id.btnBatal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Button btnDebitBayar = (Button) view.findViewById(R.id.btnDebitBayar);
        btnDebitBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(payment, "On Development", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnDebitKonfirmasi = (Button) view.findViewById(R.id.btnDebitKonfirmasi);
        btnDebitKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(payment, "On Development", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setView(view);
        return builder.create();
    }

    Payment payment;
    F_Payment fPayment;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        payment = ((Payment) activity);
        fPayment = (F_Payment) payment.getSupportFragmentManager().findFragmentById(R.id.activity_payment);
    }
}
