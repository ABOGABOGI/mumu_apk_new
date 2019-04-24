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
import id.hike.apps.android_mpos_mumu.util.UnitConversion;

/**
 * Created by root on 29/08/17.
 */

public class DfEditTotalDiskon extends BaseDialogFragment {
    SummaryActivity smr;
    EditText etInputHarga;
    Button btnSubmit;
    Bundle bundle;
    Integer jumlah, transaId, position;
    Long maxPrice, minPrice, salesPrice;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.df_edit_harga_summary, null);
        etInputHarga = (EditText) view.findViewById(R.id.etInputTotalHarga);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
        TextView title = (TextView) view.findViewById(R.id.idTitle);
        title.setText("Edit Diskon");
        Button btnBatal= (Button) view.findViewById(R.id.btnBatal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        bundle = getArguments();

        // First Separator Init
        etInputHarga.setText(smr.edDiskon.getText().toString());

        // Typing Separator
        etInputHarga.addTextChangedListener(new FormatCurrencyEditInCursor(etInputHarga));
        etInputHarga.setSelection(etInputHarga.getText().length());
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//TODO: nanti min price diubah ke countTotalBaseMinPrice, karena skrg pakai baseprice karena yg minprice belum dideploy
                Long totalMinPrice;
                Long totalSalesPrice;
                if(getTag().equals(Cfg.ppobCategory)){
                    String transId = getDFSecPref().getString(Cfg.pulsaTransIdKey,"");
                    ModelTransaksiPpob ppob = smr.summaryPresenter.getPpobTransactionById(transId);
                    totalMinPrice = ppob.getMinPrice();
                    totalSalesPrice = ppob.getSalesPrice();
                }else{
                    totalMinPrice = Long.parseLong(smr.summaryPresenter.getTotalMinPrice());
                    totalSalesPrice = Long.parseLong(smr.summaryPresenter.getCountHarga());

                }

                Long totalDiskon = totalSalesPrice - totalMinPrice;

                //filter input text untuk menghilangkan leading zero
                String inputDiskon = etInputHarga.getText().toString().isEmpty() ||
                        etInputHarga.getText().toString().equalsIgnoreCase("null") ? "0" : etInputHarga.getText().toString();
                inputDiskon = inputDiskon.replaceFirst("^0+(?!$)", "");

                if (inputDiskon.contains(".")) {
                    inputDiskon = inputDiskon.replaceAll("\\.", "");
                }

                if (Long.parseLong(inputDiskon) > totalDiskon) {
                    smr.etTotal.setText(UnitConversion.format2Rupiah(totalSalesPrice));
                    smr.edDiskon.setText(String.valueOf(0L));
//                    smr.edDiskon.setSelection(1);
                    Toast.makeText(smr, "Input diskon kurang dari total min price", Toast.LENGTH_LONG).show();
                } else {
                    //harga yang diinput valid
                    smr.edDiskon.setText(UnitConversion.format2Rupiah2(inputDiskon));
//                    smr.edDiskon.setSelection(inputDiskon.length());
                    Long diskon = Long.parseLong(inputDiskon);
                    smr.summaryPresenter.totalPaymentAfterDisc = totalSalesPrice - diskon;
                    smr.etTotal.setText(UnitConversion.format2Rupiah(smr.summaryPresenter.totalPaymentAfterDisc));
                    Toast.makeText(smr, "Diskon diinput", Toast.LENGTH_SHORT).show();
                    dismiss();
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
        smr = (SummaryActivity) context;
    }
}