package id.hike.apps.android_mpos_mumu.features.aktifitas.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

import androidx.fragment.app.FragmentManager;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.aktifitas.AktifitasHistoryActivity;
import id.hike.apps.android_mpos_mumu.features.aktifitas.df.DfDetailTransaksi;
import id.hike.apps.android_mpos_mumu.features.aktifitas.model.ResAktifitasNew;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;

/**
 * Created by root on 11/08/17.
 */

public class RvAdapterAktivitas extends ExpandableRecyclerViewAdapter<RvAdapterAktivitas.HeaderViewHolder, RvAdapterAktivitas.ItemViewHolder> {

    Context mContext;
    FragmentManager fragmentManager;

    public RvAdapterAktivitas(List<? extends ExpandableGroup> groups, FragmentManager fragmentManager, Context mContext) {
        super(groups);
        this.fragmentManager = fragmentManager;
        this.mContext = mContext;
    }

    @Override
    public HeaderViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.i_aktifitas_waktu2, parent, false);
        return new HeaderViewHolder(view);
    }

    @Override
    public ItemViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.i_aktifitas, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(ItemViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final ResAktifitasNew.DataAktifitas rvModelItem = (ResAktifitasNew.DataAktifitas) group.getItems().get(childIndex);
        String hargaText = "";

        if(rvModelItem.getIs_digital() == 1){ // trans ppob
            hargaText = rvModelItem.getProduct_name();
            holder.jam.setText(UnitConversion.ConvertMilliSecondsToJamPpob(rvModelItem.getUpdated_date()));
            switch (rvModelItem.getStatus()) {
                case 3: //in progress
                    holder.gambarTipe.setImageResource(R.drawable.u3590);
                    holder.title.setText("Transaksi Sedang Diproses");
                    break;
                case 4: //PAID
                    holder.gambarTipe.setImageResource(R.mipmap.ic_pulsa);
                    holder.title.setText("Transaksi Berhasil");
                    break;
                case 5: //Fail
                    holder.gambarTipe.setImageResource(R.drawable.u3590);
                    holder.title.setText("Transaksi Gagal");
                    break;
                case 6: //CANCEL
                    holder.gambarTipe.setImageResource(R.drawable.u3590);
                    holder.title.setText("Transaksi Batal");
                    break;
            }

        }else {
            hargaText = UnitConversion.format2Rupiah(rvModelItem.getTotal_payment() != null ? rvModelItem.getTotal_payment().longValue() : 0L);
            holder.jam.setText(UnitConversion.ConvertMilliSecondsToJam(rvModelItem.getUpdated_date()));
            switch (rvModelItem.getStatus()) {
                case 3: //PAID
                    holder.gambarTipe.setImageResource(R.drawable.u3582);
                    holder.title.setText("Transaksi Berhasil");
                    break;
                case 4: //CANCEL
                    holder.gambarTipe.setImageResource(R.drawable.u3590);
                    holder.title.setText("Transaksi Batal");
                    break;
            }

        }
        holder.harga.setText(hargaText);


        final Bundle bundle = new Bundle();
        bundle.putLong(Cfg.BUNDLE_HARGA_KURANG_DISKON_LONG, rvModelItem.getTotal_payment() != null ? rvModelItem.getTotal_payment().longValue() : 0L);
        holder.jam.setOnClickListener(new KlikAktifitas(rvModelItem,bundle));
        holder.gambarTipe.setOnClickListener(new KlikAktifitas(rvModelItem,bundle));
        holder.title.setOnClickListener(new KlikAktifitas(rvModelItem,bundle));
        holder.layItemAktifitas.setOnClickListener(new KlikAktifitas(rvModelItem,bundle));
    }

    @Override
    public void onBindGroupViewHolder(HeaderViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.tvTanggal.setText(group.getTitle());
    }

    public class HeaderViewHolder extends GroupViewHolder {
        TextView tvTanggal;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            tvTanggal = (TextView) itemView.findViewById(R.id.tvTanggalTransaksi);
        }
    }

    public class ItemViewHolder extends ChildViewHolder {
        TextView title, jam, harga;
        ImageView gambarTipe;
        LinearLayout layItemAktifitas;

        public ItemViewHolder(View itemView) {
            super(itemView);
            gambarTipe = (ImageView) itemView.findViewById(R.id.ivTipe);
            harga = (TextView) itemView.findViewById(R.id.tvHarga);
            jam = (TextView) itemView.findViewById(R.id.tvWaktu);
            title = (TextView) itemView.findViewById(R.id.tvTitle);
            layItemAktifitas= (LinearLayout) itemView.findViewById(R.id.layItemAktifitas);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    private class KlikAktifitas implements View.OnClickListener {
        ResAktifitasNew.DataAktifitas rvModelItem;
        Bundle bundle;

        KlikAktifitas(ResAktifitasNew.DataAktifitas rvModelItem, Bundle bundle) {
            this.rvModelItem = rvModelItem;
            this.bundle = bundle;
        }

        @Override
        public void onClick(View v) {

            Boolean displayDetail = false;
            Boolean showPrintStruk = false;
            if(rvModelItem.getIs_digital()==1){// ppob
                if(rvModelItem.getStatus() == 4 || // sukses
                        rvModelItem.getStatus() == 5 || // gagal
                        rvModelItem.getStatus() == 6) // cancel
                {
                    displayDetail = true;
                }
            }else if(rvModelItem.getStatus() == 3 || rvModelItem.getStatus() == 4){ /// non ppob sukses / gagal
                displayDetail = true;
            }

            if(rvModelItem.getIs_digital()==1 && rvModelItem.getStatus() == 4){ /// ppob yg sukses
                showPrintStruk = true;

            } else if(rvModelItem.getStatus() == 3){ // non ppob yg sukses
                showPrintStruk = true;
            }
            bundle.putBoolean(Cfg.IS_STRUK_PRINTED_KEY, showPrintStruk);

            if(displayDetail){
                String tag = rvModelItem.getId_tr_sales() +","+rvModelItem.getIs_digital();
                ((AktifitasHistoryActivity) mContext).getSecPref().edit().putString(Cfg.prefsTransStatus_STR, String.valueOf(rvModelItem.getStatus())).apply();
                DfDetailTransaksi dfDetailTransaksi = new DfDetailTransaksi();
                dfDetailTransaksi.setArguments(bundle);
                dfDetailTransaksi.show(fragmentManager, tag);

            }else{
                Toast.makeText(mContext, "Under Development", Toast.LENGTH_SHORT).show();

            }
        }
    }
}


