package id.hike.apps.android_mpos_mumu.features.landing_page.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.landing_page.model.ModelTransaksiTertunda2;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;

/**
 * Created by getwiz on 18/05/17.
 */

public class RvAdapterPendingTrans extends RecyclerView.Adapter<RvAdapterPendingTrans.MyViewHolder> {
    private Context mContext;
    private List<ModelTransaksiTertunda2> mm = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView nama, totalBiaya, firstLetter;
        public LinearLayout layoutPendingTransItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            nama = (TextView) itemView.findViewById(R.id.tvName);
            totalBiaya = (TextView) itemView.findViewById(R.id.tvTotalBiaya);
            firstLetter = (TextView) itemView.findViewById(R.id.tvInitName);
            layoutPendingTransItem = (LinearLayout)itemView.findViewById(R.id.layoutPendingTransItem);
        }
    }

    public RvAdapterPendingTrans(Context mContext, List<ModelTransaksiTertunda2> msg) {
        this.mContext = mContext;
        this.mm = msg;
    }

    @Override
    public RvAdapterPendingTrans.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.i_traksaksi_tertunda, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RvAdapterPendingTrans.MyViewHolder holder, int position) {
        holder.nama.setText(mm.get(position).nama);
        holder.firstLetter.setText(mm.get(position).nama.substring(0, 1));
        if(!mm.get(position).transType.equals(Cfg.transTypeValNonPpob)){
            Drawable d = mContext.getResources().getDrawable(R.drawable.yellow_bg_style);
            holder.layoutPendingTransItem.setBackground(d);
        }

        //TODO: bug - nanti diunkomen
        holder.totalBiaya.setText(
                UnitConversion.format2Rupiah(
                        Math.round(Double.parseDouble(mm.get(position).totalHarga)) -
                                Math.round(Double.parseDouble(mm.get(position).negoDisc))) //harga masih tidak dikurangi negodisc
        );
    }

    @Override
    public int getItemCount() {
        return mm.size();
    }
}
