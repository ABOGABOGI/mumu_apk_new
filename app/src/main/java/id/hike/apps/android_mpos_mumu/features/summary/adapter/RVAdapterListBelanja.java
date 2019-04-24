package id.hike.apps.android_mpos_mumu.features.summary.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.summary.SummaryActivity;
import id.hike.apps.android_mpos_mumu.global.global_model.ModelTransaksi;
import id.hike.apps.android_mpos_mumu.features.summary.df.DfEditHargaProduk;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.util.MyUtils;

/**
 * Created by getwiz on 22/05/17.
 */

public class RVAdapterListBelanja extends RecyclerView.Adapter<RVAdapterListBelanja.MyViewHolder> {

    Context mContext;
    List<ModelTransaksi> mm = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView namaProduk, tvSubtotal, etHargaProduk;
        public ImageView btnDeleteItem, btnIncrease, btnDecrease;
        public EditText etInputJumlah;
        public LinearLayout layListBelanja;
        public View lineSeparatorKiri, lineSeparatorKanan;

        public MyViewHolder(View itemView) {
            super(itemView);
            namaProduk = (TextView) itemView.findViewById(R.id.tvNamaProduk);
            etHargaProduk = (TextView) itemView.findViewById(R.id.etHargaProduk);
            btnDeleteItem = (ImageView) itemView.findViewById(R.id.btnDeleteItem);
            etInputJumlah = (EditText) itemView.findViewById(R.id.edInputJumlah);
            btnIncrease = (ImageView) itemView.findViewById(R.id.btnIncr);
            btnDecrease = (ImageView) itemView.findViewById(R.id.btnDecr);
            tvSubtotal = (TextView) itemView.findViewById(R.id.tvSubtotal);
            layListBelanja = (LinearLayout) itemView.findViewById(R.id.idLayBelanja);
            lineSeparatorKiri = itemView.findViewById(R.id.lineSeparatorKiri);
            lineSeparatorKanan = itemView.findViewById(R.id.lineSeparatorKanan);
        }
    }

    public RVAdapterListBelanja(Context mContext, List<ModelTransaksi> msg) {
        this.mContext = mContext;
        this.mm = msg;
    }

    public void setTotalHarga(int position, Long totalHarga, int jumlah) {

        SummaryActivity smr=(SummaryActivity)mContext;
        mm.set(position,
                new ModelTransaksi(
                        mm.get(position).transaId,
                        mm.get(position).productName,
                        smr.getSecPref().getString(Cfg.prefsKostumerName_STR,null),
                        mm.get(position).productId,
                        mm.get(position).basePrice,
                        totalHarga,
                        mm.get(position).stock,
                        Cfg.TS_OPEN,
                        Cfg.TIS_UNLOCK,
                        mm.get(position).baselineStock,
                        jumlah,
                        mm.get(position).pictUrlPath,
                        mm.get(position).category,
                        mm.get(position).brand,
                        mm.get(position).satuan,
                        mm.get(position).upTime,
                        mm.get(position).maxPrice,
                        mm.get(position).minPrice
                )
        );

        notifyDataSetChanged();
    }

    @Override
    public RVAdapterListBelanja.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.i_daftar_belanja, parent, false);
        return new MyViewHolder(itemView);
    }

    private int jumlah = 0;
    long hargaSatuan = 0;

    @Override
    public void onBindViewHolder(final RVAdapterListBelanja.MyViewHolder holder, final int position) {
        //Menghindari bug scrolling
        holder.setIsRecyclable(false);

        if (mm.get(position).category.equalsIgnoreCase("ppob")) {
            holder.btnIncrease.setVisibility(View.GONE);
            holder.btnDecrease.setVisibility(View.GONE);
        }

        if (MyUtils.isEvenOrOdd(position) == Cfg.EVEN_NUM) {
            holder.layListBelanja.setBackground(mContext.getResources().getDrawable(R.drawable.bg_round_dark_summary));
            holder.etInputJumlah.setBackground(mContext.getResources().getDrawable(R.drawable.bg_round_light_summary));

            holder.lineSeparatorKiri.setBackground(mContext.getResources().getDrawable(R.drawable.bg_round_light_summary));
            holder.lineSeparatorKanan.setBackground(mContext.getResources().getDrawable(R.drawable.bg_round_light_summary));
        } else {
            //ODD_NUM
            holder.layListBelanja.setBackground(mContext.getResources().getDrawable(R.drawable.bg_round_light_summary));
            holder.etInputJumlah.setBackground(mContext.getResources().getDrawable(R.drawable.bg_round_dark_summary));

            holder.lineSeparatorKiri.setBackground(mContext.getResources().getDrawable(R.drawable.bg_round_dark_summary));
            holder.lineSeparatorKanan.setBackground(mContext.getResources().getDrawable(R.drawable.bg_round_dark_summary));
        }

        // Urutan 1. ALG 1
        if (mm.get(position).transItemStatus_flag == Cfg.TIS_DELETE) {
            holder.layListBelanja.setVisibility(View.GONE);
            holder.layListBelanja.getLayoutParams().height = 0;
            holder.layListBelanja.requestLayout();
        }
        holder.namaProduk.setText(mm.get(position).productName);

        // Urutan 2. ALG 1
        // Memberi warning saat stok habis
        if (mm.get(position).stock==0){
            holder.layListBelanja.setBackground(mContext.getResources().getDrawable(R.drawable.bg_round_warning_summary));
            holder.etInputJumlah.setBackground(mContext.getResources().getDrawable(R.drawable.bg_round_light_summary));
//            holder.etInputJumlah.setEnabled(false);
//            holder.etInputJumlah.setText("0");

            holder.lineSeparatorKiri.setBackground(mContext.getResources().getDrawable(R.drawable.bg_round_light_summary));
            holder.lineSeparatorKanan.setBackground(mContext.getResources().getDrawable(R.drawable.bg_round_light_summary));

            holder.btnIncrease.setColorFilter(ContextCompat.getColor(mContext, R.color.md_white_1000), PorterDuff.Mode.MULTIPLY);
            holder.btnDecrease.setColorFilter(ContextCompat.getColor(mContext, R.color.md_white_1000), PorterDuff.Mode.MULTIPLY);
            holder.btnDeleteItem.setColorFilter(ContextCompat.getColor(mContext, R.color.md_white_1000), PorterDuff.Mode.MULTIPLY);

//            ((SummaryActivity) mContext).dbTransaction.changeTransactionFlag(Cfg.TIS_DELETE, Cfg.TS_CANCEL, mm.get(position).transaId);
        }

        // Format curency
        holder.etHargaProduk.setText(String.valueOf(MyUtils.formatCurrency(
                mm.get(position).salesPrice * mm.get(position).jumlahBeli
        )));

        ((SummaryActivity) mContext).summaryPresenter.updateSubtotal();

        holder.etInputJumlah.setText(String.valueOf(mm.get(position).jumlahBeli));
        holder.btnDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mm.size() == 1) {
                    Toast.makeText(mContext, "Tidak dapat dihapus", Toast.LENGTH_SHORT).show();
                    return;
                }

                ((SummaryActivity) mContext).summaryPresenter.resetInputDiskon();

                ((SummaryActivity) mContext).dbTransaction.changeTransactionFlag(Cfg.TIS_DELETE, Cfg.TS_CANCEL, mm.get(position).transaId);
                mm.remove(position);
                ((SummaryActivity) mContext).summaryPresenter.updateSubtotal();
                ((SummaryActivity) mContext).summaryPresenter.updateTotal();
                notifyDataSetChanged();
            }
        });

        holder.etHargaProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DfEditHargaProduk dfEditHargaProduk = new DfEditHargaProduk();
                Bundle bundle = new Bundle();

                jumlah = Integer.parseInt(holder.etInputJumlah.getText().toString());

                bundle.putInt(Cfg.BUNDLE_JUMLAH_ITEM_INT, jumlah);
                bundle.putLong(Cfg.BUNDLE_MAXPRICE_LONG, mm.get(position).maxPrice);
                bundle.putLong(Cfg.BUNDLE_SALESPRICE_LONG, mm.get(position).salesPrice);

                //TODO:nanti diubah ke min price
                bundle.putLong(Cfg.BUNDLE_MINPRICE_LONG, mm.get(position).minPrice);

                bundle.putInt(Cfg.BUNDLE_TRANSID_STR, mm.get(position).transaId);
//                bundle.putInt(Cfg.BUNDLE_ITEM_SUMMARY_POSITION_INT, mm.indexOf(mm.get(position).productId));
                bundle.putInt(Cfg.BUNDLE_ITEM_SUMMARY_POSITION_INT, position);
                dfEditHargaProduk.setArguments(bundle);
                dfEditHargaProduk.show(((SummaryActivity) mContext).getSupportFragmentManager(), "");
            }
        });

        final TextWatcher etInputJumlahTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
                if (s.length() != 0 && Integer.parseInt(s.toString()) != 0) {
                    holder.etInputJumlah.removeTextChangedListener(this);

                    // Untuk menghilangkan titik separator
                    String originalString = holder.etHargaProduk.getText().toString();
                    if (originalString.contains(".")) {
                        originalString = originalString.replaceAll("\\.", "");
                    }
                    long uangInput = Long.parseLong(originalString);

                    /*// Mencegah uang 0
                    if (String.valueOf(uangInput).equalsIgnoreCase("0")){
                        uangInput=0L;
                    }*/

                    //TODO: Baselinestock / Stok? atau Stok - Baselinestock
                    if (Integer.parseInt(holder.etInputJumlah.getText().toString()) >
                            ((SummaryActivity) mContext).summaryPresenter.getStock(mm.get(position).transaId)) {
                        jumlah = ((SummaryActivity) mContext).summaryPresenter.getStock(mm.get(position).transaId);
                        Toast.makeText(mContext, "Pembelian melebihi batas stok", Toast.LENGTH_SHORT).show();
                    } else {
                        jumlah = Integer.parseInt(holder.etInputJumlah.getText().toString());
                    }

                    try {
                        hargaSatuan = uangInput / jumlah;
                    } catch (ArithmeticException e){
                        hargaSatuan=0;
                    }

                    holder.etInputJumlah.setText(String.valueOf(jumlah));
                    holder.etInputJumlah.setSelection(holder.etInputJumlah.getText().length());

                    // Format curency
                    holder.etHargaProduk.setText(String.valueOf(MyUtils.formatCurrency(((SummaryActivity) mContext).summaryPresenter.getRecentSalesPrice(mm.get(position).transaId) * jumlah)));

                    ((SummaryActivity) mContext).summaryPresenter.updateModel((int) mm.get(position).productId, jumlah);
                    ((SummaryActivity) mContext).summaryPresenter.updateTotal();
                    holder.etInputJumlah.addTextChangedListener(this);

                } else {//kalo nilai edittextnya 0
                    holder.etInputJumlah.removeTextChangedListener(this);

                    jumlah = 0;
                    hargaSatuan = 0;
                    holder.etInputJumlah.setText("");
                    holder.etInputJumlah.setSelection(holder.etInputJumlah.getText().length());
                    holder.etHargaProduk.setText("0");
                    ((SummaryActivity) mContext).summaryPresenter.updateModel((int) mm.get(position).productId, jumlah);
                    ((SummaryActivity) mContext).summaryPresenter.updateTotal();
                    holder.etInputJumlah.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(final Editable s) {

            }
        };
        holder.etInputJumlah.addTextChangedListener(etInputJumlahTextWatcher);

        holder.etInputJumlah.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    holder.etInputJumlah.removeTextChangedListener(etInputJumlahTextWatcher);
                    if (holder.etInputJumlah.getText().toString().isEmpty()) {
                        holder.etInputJumlah.setText("0");
                        jumlah = 0;
                    } else {
                        jumlah = Integer.parseInt(holder.etInputJumlah.getText().toString());
                    }

                    String hargaProduk=holder.etHargaProduk.getText().toString();
                    String formattedString = hargaProduk.replaceAll("\\.", "");

                    hargaSatuan = Long.parseLong(formattedString) / jumlah;
                    holder.etInputJumlah.setText(String.valueOf(jumlah));
                    holder.etInputJumlah.setSelection(holder.etInputJumlah.getText().length());
                    holder.etHargaProduk.setText(
                            MyUtils.formatCurrency(((SummaryActivity) mContext).summaryPresenter.getRecentSalesPrice(mm.get(position).transaId) * jumlah));
                    ((SummaryActivity) mContext).summaryPresenter.updateModel((int) mm.get(position).productId, jumlah);
                    ((SummaryActivity) mContext).summaryPresenter.updateTotal();
                    ((SummaryActivity) mContext).hideKeyInput(holder.etInputJumlah);
                    holder.etInputJumlah.addTextChangedListener(etInputJumlahTextWatcher);
                    return true;
                }
                return false;
            }
        });

        holder.btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.etInputJumlah.getText().toString().isEmpty() ||
                        holder.etInputJumlah.getText().toString().equalsIgnoreCase("0")) {
                    // setiap setText input jumlah, mempengaruhi listener
                    holder.etInputJumlah.setText("1");
                    jumlah = Integer.parseInt(holder.etInputJumlah.getText().toString());
                } else {
                    jumlah = Integer.parseInt(holder.etInputJumlah.getText().toString()) + 1;
                    holder.etInputJumlah.setText(String.valueOf(jumlah));
                }

                // Format curency
                holder.etHargaProduk.setText(MyUtils.formatCurrency(
                        ((SummaryActivity) mContext).summaryPresenter.getRecentSalesPrice(mm.get(position).transaId) * jumlah)
                );
                ((SummaryActivity) mContext).summaryPresenter.updateModel((int) mm.get(position).productId, jumlah);

                ((SummaryActivity) mContext).summaryPresenter.updateTotal();
            }
        });

        holder.btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.etInputJumlah.getText().toString().isEmpty() ||
                        holder.etInputJumlah.getText().toString().equalsIgnoreCase("0") ||
                        holder.etInputJumlah.getText().toString().equalsIgnoreCase("1")) {
                    // setiap setText input jumlah, mempengaruhi listener
                    holder.etInputJumlah.setText("1");
                    jumlah = Integer.parseInt(holder.etInputJumlah.getText().toString());
                } else {
                    jumlah = Integer.parseInt(holder.etInputJumlah.getText().toString()) - 1;
                    holder.etInputJumlah.setText(String.valueOf(jumlah));
                }

                ((SummaryActivity) mContext).summaryPresenter.resetInputDiskon();

                // Format curency
                DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();
                formatter.applyPattern("#,###,###,###");
                String formattedString = formatter.format(
                        ((SummaryActivity) mContext).summaryPresenter.getRecentSalesPrice(mm.get(position).transaId) * jumlah
                );
                formattedString = formattedString.replaceAll(",", ".");
                holder.etHargaProduk.setText(String.valueOf(formattedString));

                ((SummaryActivity) mContext).summaryPresenter.updateModel((int) mm.get(position).productId, jumlah);
                ((SummaryActivity) mContext).summaryPresenter.updateTotal();
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return mm.get(position).productId;
    }

    @Override
    public int getItemCount() {
        return mm.size();
    }

    public static void underlineText(TextView view) {
        view.setPaintFlags(view.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}