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

import java.text.DecimalFormat;
import java.text.NumberFormat;

import androidx.appcompat.app.AlertDialog;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseDialogFragment;
import id.hike.apps.android_mpos_mumu.features.summary.SummaryActivity;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.util.FormatCurrencyEditInCursor;
import id.hike.apps.android_mpos_mumu.util.MyUtils;

/**
 * Created by root on 29/08/17.
 */

public class DfEditHargaProduk extends BaseDialogFragment {
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

        TextView title= (TextView) view.findViewById(R.id.idTitle);
        title.setText("Edit Harga Produk");
        Button btnBatal= (Button) view.findViewById(R.id.btnBatal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        bundle = getArguments();

        // Bundle lemparan dari adapter
        jumlah = bundle.getInt(Cfg.BUNDLE_JUMLAH_ITEM_INT);
        transaId = bundle.getInt(Cfg.BUNDLE_TRANSID_STR);
        maxPrice = bundle.getLong(Cfg.BUNDLE_MAXPRICE_LONG);
        salesPrice = bundle.getLong(Cfg.BUNDLE_SALESPRICE_LONG);
        minPrice = bundle.getLong(Cfg.BUNDLE_MINPRICE_LONG);
        position = bundle.getInt(Cfg.BUNDLE_ITEM_SUMMARY_POSITION_INT);

        // First Separator Init
        etInputHarga.setText(MyUtils.formatCurrency(salesPrice * jumlah));

        // Typing Separator
        etInputHarga.addTextChangedListener(new FormatCurrencyEditInCursor(etInputHarga));
        etInputHarga.setSelection(etInputHarga.getText().length());
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Untuk menghilangkan titik separator
                String originalString = etInputHarga.getText().toString();

                if (originalString.isEmpty()) {
                    originalString = "0";
                }

                if (originalString.contains(".")) {
                    originalString = originalString.replaceAll("\\.", "");
                }
                Long uangInput = Long.parseLong(originalString);
                //=============================================================================

                //jika input harganya kosong
                if (etInputHarga.getText().toString().isEmpty()) {
                    smr.hideKeyInput(etInputHarga);
                    return;
                }

                //untuk menghitung harga kotor
                long totalStashPrice = uangInput;

                //untuk menghitung max price
                Long totalMaxPrice = maxPrice * jumlah;

                //untuk menghitung baseprice * jumlah
                long minPriceTotal = minPrice * jumlah;

                if (totalStashPrice < minPriceTotal) {
                    Toast.makeText(smr, "Pengurangan harga terlalu besar", Toast.LENGTH_SHORT).show();

                    // Format curency
                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(
                            smr.summaryPresenter.getRecentSalesPrice(transaId) * jumlah
                    );
                    formattedString = formattedString.replaceAll(",", ".");
                    etInputHarga.setText(String.valueOf(formattedString));

                } else if (totalStashPrice > totalMaxPrice) {
                    Toast.makeText(smr, "Kenaikan harga terlalu besar", Toast.LENGTH_SHORT).show();
                    // Format curency
                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(
                            smr.summaryPresenter.getRecentSalesPrice(transaId) * jumlah
                    );
                    formattedString = formattedString.replaceAll(",", ".");
                    etInputHarga.setText(String.valueOf(formattedString));
                } else {
                    smr.summaryPresenter.updateSalesPrice(
                            transaId,
                            uangInput / jumlah
                    );

                    smr.rvAdapterListBelanja.setTotalHarga(position, uangInput/jumlah, jumlah);

                    // Format curency
//                    etInputHarga.setText(MyUtils.formatCurrency(uangInput));
                }

                smr.hideKeyInput(etInputHarga);
                dismiss();
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