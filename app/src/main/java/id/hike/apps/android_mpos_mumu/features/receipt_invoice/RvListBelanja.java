package id.hike.apps.android_mpos_mumu.features.receipt_invoice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.util.MyUtils;

/**
 * Created by root on 06/07/17.
 */

public class RvListBelanja extends RecyclerView.Adapter<RvListBelanja.MyViewHolder> {
    private Context mContext;
    private List<ModelListBelanja> mm = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvJumlah,tvHarga;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvJumlah = (TextView) itemView.findViewById(R.id.tvJumlah);
            tvHarga = (TextView) itemView.findViewById(R.id.tvHarga);
        }
    }

    public RvListBelanja(Context mContext, List<ModelListBelanja> msg) {
        this.mContext = mContext;
        this.mm = msg;
    }

    @Override
    public RvListBelanja.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.i_struk_listbelanja, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RvListBelanja.MyViewHolder holder, int position) {
        holder.tvName.setText(mm.get(position).getNamaProduk());
        holder.tvHarga.setText(MyUtils.formatCurrency((Long.parseLong(mm.get(position).getHarga())*mm.get(position).getJumlah())));
        holder.tvJumlah.setText(
                String.valueOf(mm.get(position).getJumlah())
        );
    }

    @Override
    public int getItemCount() {
        return mm.size();
    }
}
