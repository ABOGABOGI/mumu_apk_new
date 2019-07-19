package id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.area_kurban.provinsi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.qurban.area_distribusi_adapter.AreaAdapter;
import id.hike.apps.android_mpos_mumu.features.qurban.area_distribusi_adapter.ProvinsiDistribusiKurbanAdapter;
import id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.area_kurban.kota.FragmentListKotaDistribusiKurban;

public class FragmentListProvinsiDistribusiKurban extends RecyclerView.Adapter<FragmentListProvinsiDistribusiKurban.MyViewHolder> {

    private Context mContext;
    public List<ProvinsiDistribusiKurbanAdapter> dataList;

    public FragmentListProvinsiDistribusiKurban(Context mContext, List<ProvinsiDistribusiKurbanAdapter> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public FragmentListProvinsiDistribusiKurban.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_list_provinsi_distribusi_kurban, parent, false);

        return new FragmentListProvinsiDistribusiKurban.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FragmentListProvinsiDistribusiKurban.MyViewHolder holder, int position) {
        holder.txtNamaKota.setText(dataList.get(position).getNamaProvinsiDistribusiKurban());

        //holder.constraintLayout.setVisibility(dataList.get(position).getNamaKota() != dataList.get(position).getNamaKota() ? View.VISIBLE : View.GONE);

        /*holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //holder.constraintLayout.setVisibility(dataList.get(position).getNamaKota() != dataList.get(position).getNamaKota() ? View.VISIBLE : View.GONE);

                try {

                    Bundle bundle = new Bundle();
                    bundle.putString(Cfg.KOTA_TUJUAN, dataList.get(position).getNamaKotaDistribusiKurban());

                    FragmentSekaliJalan fragmentSekaliJalan = new FragmentSekaliJalan();
                    fragmentSekaliJalan.setArguments(bundle);

                    FragmentTransaction transaction = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.layoutFullKotaAsal, fragmentSekaliJalan);
                    transaction.addToBackStack(null).commit();

                    //transaction.commit();

                    Toast.makeText(mContext, String.valueOf(dataList.get(position).getNamaKotaDistribusiKurban()), Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNamaKota;
        private LinearLayout linearLayout, linearLayoutDua;
        private ConstraintLayout constraintLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtNamaKota = (TextView) itemView.findViewById(R.id.kotaSatu);
            constraintLayout = itemView.findViewById(R.id.kotaAsalSatu);
            linearLayout = itemView.findViewById(R.id.linearLayoutKotaAsal);
        }
    }
}