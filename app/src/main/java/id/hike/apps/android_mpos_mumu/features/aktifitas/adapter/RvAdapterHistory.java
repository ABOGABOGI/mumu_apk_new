package id.hike.apps.android_mpos_mumu.features.aktifitas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.model.TransaksiInfo;

public class RvAdapterHistory extends RecyclerView.Adapter<RvAdapterHistory.HistoryHolder> {


    List<TransaksiInfo> transaksiInfoList = new ArrayList<>();

    public void setTransaksiInfoList(List<TransaksiInfo> infoList){
        this.transaksiInfoList = infoList;
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_history, parent, false);

        return new HistoryHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {

        TransaksiInfo info = transaksiInfoList.get(position);

        holder.numberView.setText(info.getTrx_pelanggan_id());
        holder.dateView.setText(info.getTrx_tgl_selesai());
        holder.titleView.setText(info.getTrx_produk_nama());
        holder.nilaiView.setText(info.getTrx_nilai());
        //Log.i("DEBUGTAG",info.getTrx_produk_nama());
        //Log.i("DEBUGTAG",info.getTrx_nilai());
        switch (info.getTrx_rcode()){
            case "00":

                holder.iconView.setImageResource(R.drawable.coin_hijau);
                holder.statusView.setText("Berhasil");

                break;


            case "68":
                holder.iconView.setImageResource(R.drawable.coin_kuning);
                holder.statusView.setText("Diproses");

                break;


            default:

                holder.iconView.setImageResource(R.drawable.coin_merah);
                holder.statusView.setText("Gagal");

                break;
        }
        holder.statusView.setText(info.getTrx_status());


    }

    @Override
    public int getItemCount() {
        return transaksiInfoList.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.iconView)
        ImageView iconView;

        @BindView(R.id.titleView)
        TextView titleView;

        @BindView(R.id.nilaiView)
        TextView nilaiView;

        @BindView(R.id.dateView)
        TextView dateView;

        @BindView(R.id.statusView)
        TextView statusView;

        @BindView(R.id.numberView)
        TextView numberView;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

}
