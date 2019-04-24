package id.hike.apps.android_mpos_mumu.features.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.summary.SummaryActivity;
import id.hike.apps.android_mpos_mumu.model.Produk;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;

/**
 * Created by M Alvin Syahrin on 30/04/2018.
 */

public class RVAdapterPlnToken extends RecyclerView.Adapter<RVAdapterPlnToken.PpobHolder>{
    private List<Produk> list = new ArrayList<>();
    private Context context;
    private EditText etIdPelangganToken;

    public RVAdapterPlnToken(List<Produk> list, Context context, EditText etIdPelangganToken) {
        this.list = list;
        this.context = context;
        this.etIdPelangganToken = etIdPelangganToken;
    }

    @Override
    public PpobHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_listrik_list,parent,false);
        RVAdapterPlnToken.PpobHolder holder = new RVAdapterPlnToken.PpobHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PpobHolder holder, int position) {
        //String desc = list.get(position).getNickname();
        String desc = list.get(position).getKeterangan();
        String ls_sts_live = String.valueOf(list.get(position).getSts_live());
        String price = String.valueOf(list.get(position).getHarga());

        SpannableString spanString = new SpannableString(desc);
        spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
        holder.btnPlnTokenNominal.setText(desc);

        holder.tvPlnTokenPrice.setText("Rp. " + UnitConversion.format2Rupiah2(price));
        holder.ppob = list.get(position);
        if (ls_sts_live.equals("0")) {
            holder.btnPlnTokenNominal.setBackgroundColor(Color.parseColor("#B00020"));
            //holder.tvPlnTokenPrice.setBackgroundColor(Color.parseColor("#B00020"));
        } else {
            holder.btnPlnTokenNominal.setBackgroundColor(Color.parseColor("#018786"));
            //holder.tvPlnTokenPrice.setBackgroundColor(Color.parseColor("#018786"));
        };

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PpobHolder extends RecyclerView.ViewHolder{
        Button btnPlnTokenNominal;
        TextView tvPlnTokenPrice;
        Produk ppob;

        public PpobHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            btnPlnTokenNominal = (Button)itemView.findViewById(R.id.btnPlnTokenNominal);
            tvPlnTokenPrice = (TextView)itemView.findViewById(R.id.tvPlnTokenPrice);
            btnPlnTokenNominal.setOnClickListener(new btnPlnTokenNominalClickListener());
        }

        private class btnPlnTokenNominalClickListener implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                if (etIdPelangganToken.getText().toString().length() < 11) {
                    Toast.makeText(context, context.getString(R.string.id_pelanggan_kurang_dari_11_karakter), Toast.LENGTH_SHORT).show();
                    etIdPelangganToken.requestFocus();
                    return;
                }  if (ppob.getSts_live().equals("0")) {
                    Toast.makeText(context, "Produk Gangguan/Close", Toast.LENGTH_SHORT).show();
                    return;
                } else {
//                    Long tokenPrice = Long.parseLong(ppob.getDescriptions());
//                    Long adminFee = ppob.getSalesPrice() - tokenPrice;
//                    Toast.makeText(context, "On Development", Toast.LENGTH_SHORT).show();

                    String msidn = ppob.getName();
                    String kdProduk = ppob.getKode_produk();
                    String kdBank = "UM0001";

                    Intent intent=new Intent(context, SummaryActivity.class);
                    intent.putExtra(Cfg.transactionTypeKey, Cfg.pulsaTypeVal);
                    intent.putExtra("msidn", msidn);
                    intent.putExtra("idPelanggan", etIdPelangganToken.getText().toString());
                    intent.putExtra("produk", ppob);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    context.startActivity(intent);

                }
            }


        }
    }
}
