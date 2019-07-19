package id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.FragmentSekaliJalan;
import id.hike.apps.android_mpos_mumu.ListKotaAsal;
import id.hike.apps.android_mpos_mumu.ListKotaAsalAdapter;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.profil.ProfilModule_ProvidesFProfilFactory;
import id.hike.apps.android_mpos_mumu.features.qurban.area_distribusi_adapter.AreaAdapter;

public class FragmentListKotaDistribusiKurban extends RecyclerView.Adapter<FragmentListKotaDistribusiKurban.MyViewHolder> {

    private Context mContext;
    public List<AreaAdapter> dataList;

    public FragmentListKotaDistribusiKurban(Context mContext, List<AreaAdapter> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public FragmentListKotaDistribusiKurban.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_fragment_list_kota_distribusi_kurban, parent, false);

        return new FragmentListKotaDistribusiKurban.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FragmentListKotaDistribusiKurban.MyViewHolder holder, int position) {
        holder.txtNamaKota.setText(dataList.get(position).getNamaKotaDistribusiKurban());

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
