package id.hike.apps.android_mpos_mumu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.easing.linear.Linear;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import id.hike.apps.android_mpos_mumu.features.pelanggan.RVAdapterPelanggan;

public class ListKotaAsalAdapter extends RecyclerView.Adapter<ListKotaAsalAdapter.MyViewHolder> {

    private Context mContext;
    private List<ListKotaAsal> dataList;

    public ListKotaAsalAdapter(Context mContext, List<ListKotaAsal> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_kota_asal, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtNamaKota.setText(dataList.get(position).getNamaKota());

        //holder.constraintLayout.setVisibility(dataList.get(position).getNamaKota() != dataList.get(position).getNamaKota() ? View.VISIBLE : View.GONE);

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        AlertDialog optionDialog = builder.create();
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //holder.constraintLayout.setVisibility(dataList.get(position).getNamaKota() != dataList.get(position).getNamaKota() ? View.VISIBLE : View.GONE);

                try {

                    Bundle bundle = new Bundle();
                    bundle.putString(Cfg.KOTA_TUJUAN, dataList.get(position).getNamaKota());

                    FragmentSekaliJalan fragmentSekaliJalan = new FragmentSekaliJalan();
                    fragmentSekaliJalan.setArguments(bundle);

                    FragmentTransaction transaction = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.layoutFullKotaAsal, fragmentSekaliJalan);
                    transaction.addToBackStack(null).commit();

                    //transaction.commit();

                    Toast.makeText(mContext, String.valueOf(dataList.get(position).getNamaKota()), Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
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

        public void removeListKotaAsal(int position){
            dataList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeRemoved(position, dataList.size());
            //constraintLayout.setVisibility(View.GONE);
        }
    }
}
