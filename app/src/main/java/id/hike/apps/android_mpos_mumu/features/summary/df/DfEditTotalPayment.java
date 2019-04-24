package id.hike.apps.android_mpos_mumu.features.summary.df;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseDialogFragment;
import id.hike.apps.android_mpos_mumu.features.summary.SummaryActivity;
import id.hike.apps.android_mpos_mumu.global.global_model.ModelTransaksiPpob;
import id.hike.apps.android_mpos_mumu.util.FormatCurrencyEditInCursor;
import id.hike.apps.android_mpos_mumu.util.MyUtils;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;

/**
 * Created by root on 29/08/17.
 */

public class DfEditTotalPayment extends BaseDialogFragment {
    SummaryActivity summaryActivity;
    EditText etInputTotalHarga;
    Button btnSubmit;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.df_edit_harga_summary, null);
        etInputTotalHarga = (EditText) view.findViewById(R.id.etInputTotalHarga);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
        TextView title = (TextView) view.findViewById(R.id.idTitle);
        title.setText("Edit Total Belanja");
        Button btnBatal = (Button) view.findViewById(R.id.btnBatal);

        String inputDiskon = summaryActivity.edDiskon.getText().toString();

        if (inputDiskon.contains(".")) {
            inputDiskon = inputDiskon.replaceAll("\\.", "");
        }

        Long discount = Long.parseLong(inputDiskon);
        Long totalSalesPrice;

        if(getTag().equals(Cfg.ppobCategory)){
            ModelTransaksiPpob ppob = summaryActivity.summaryPresenter.getPpobTransactionById(getPpobTransId());
            totalSalesPrice = Long.parseLong(ppob.getTotalBelanja());
        }else{
            totalSalesPrice = Long.parseLong(summaryActivity.summaryPresenter.getCountHarga());
        }

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // Format curency
        etInputTotalHarga.setText(MyUtils.formatCurrency(totalSalesPrice - discount));

        etInputTotalHarga.addTextChangedListener(new FormatCurrencyEditInCursor(etInputTotalHarga));

        etInputTotalHarga.setSelection(etInputTotalHarga.getText().length());
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Untuk menghilangkan titik separator
                String originalString = etInputTotalHarga.getText().toString();
                if (originalString.contains(".")) {
                    originalString = originalString.replaceAll("\\.", "");
                }
                Long uangInput = Long.parseLong(originalString);
                if(getTag().equals(Cfg.ppobCategory)){
                    setNewTotalPpobTrans(uangInput);
                }else{
                    setNewTotalNonPpobTrans(uangInput);
                }
            }
        });

        builder.setView(view);
        builder.setCancelable(false);
        return builder.create();
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        summaryActivity = (SummaryActivity) context;
    }

    private String getPpobTransId(){
        return getDFSecPref().getString(Cfg.pulsaTransIdKey,"");
    }

    private void setNewTotalNonPpobTrans(Long uangInput){
        Long totalMinPrice = Long.parseLong(summaryActivity.summaryPresenter.getTotalMinPrice());
        Long totalSalesPrice = Long.parseLong(summaryActivity.summaryPresenter.getCountHarga());
        Long inputTotalHarga = uangInput == null ? 0L : uangInput;
        if (inputTotalHarga < totalMinPrice || inputTotalHarga > totalSalesPrice) {
            doInvalidInputHarga(totalSalesPrice, totalMinPrice, inputTotalHarga);

        }else{
            Long totalDiskon = totalSalesPrice - inputTotalHarga;
            Long totalBayar = totalSalesPrice - totalDiskon;
            summaryActivity.edDiskon.setText(String.valueOf(totalDiskon));
            summaryActivity.etTotal.setText(MyUtils.formatCurrency(totalBayar));
            doSuccessEditTotalBelanja();
        }

    }

    private void setNewTotalPpobTrans(Long uangInput){
        ModelTransaksiPpob ppob = summaryActivity.summaryPresenter.getPpobTransactionById(getPpobTransId());
        Long totalMinPrice = ppob.getMinPrice();
        Long maxPrice = ppob.getMaxPrice();
        Long inputTotalHarga = uangInput == null ? 0L : uangInput;
        if (inputTotalHarga < totalMinPrice || inputTotalHarga > maxPrice) {
            doInvalidInputHarga(ppob.getSalesPrice(), totalMinPrice, inputTotalHarga);

        }else{
            Long totalBayar = inputTotalHarga;
            getDFSecPref()
                    .edit()
                    .putString(Cfg.prefsTotalHarga_Str, String.valueOf(totalBayar)).apply();
            totalBayar = Long.parseLong(getDFSecPref().getString(Cfg.prefsTotalHarga_Str, "0"));


            summaryActivity.edDiskon.setText("0");
            summaryActivity.etTotal.setText(MyUtils.formatCurrency(totalBayar));
            doSuccessEditTotalBelanja();

        }

    }

    private void doInvalidInputHarga(Long totalSalesPrice, Long totalMinPrice, Long inputTotalHarga){
        summaryActivity.etTotal.setText(UnitConversion.format2Rupiah(totalSalesPrice));
        summaryActivity.edDiskon.setText(String.valueOf(0L));

        // Format curency
        etInputTotalHarga.setText(MyUtils.formatCurrency(Long.parseLong(
                summaryActivity.summaryPresenter.getCountHarga()
        )));

        if (inputTotalHarga < totalMinPrice) {
            Toast.makeText(summaryActivity, "Input harga kurang dari total min price", Toast.LENGTH_LONG).show();
        } else if (inputTotalHarga > totalSalesPrice) {
            Toast.makeText(summaryActivity, "Input harga lebih dari total sales price", Toast.LENGTH_LONG).show();
        }

    }

    private void doSuccessEditTotalBelanja(){
        Toast.makeText(summaryActivity, "Total belanja diubah", Toast.LENGTH_SHORT).show();
        hideKeyboard();
        dismiss();

    }
}