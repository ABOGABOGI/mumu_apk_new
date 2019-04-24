package id.hike.apps.android_mpos_mumu.features.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.home.MainPPOBFragment;
import id.hike.apps.android_mpos_mumu.features.home.PulsaFragment;
import id.hike.apps.android_mpos_mumu.features.home.Home;
import id.hike.apps.android_mpos_mumu.features.home.df.DFPinPpob;
import id.hike.apps.android_mpos_mumu.features.login.df.DfLoading;
import id.hike.apps.android_mpos_mumu.features.summary.SummaryActivity;
import id.hike.apps.android_mpos_mumu.model.Produk;
import id.hike.apps.android_mpos_mumu.util.MyUtils;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;

/**
 * Created by M Alvin Syahrin on 4/4/2018.
 */

public class RVAdapterPpob extends RecyclerView.Adapter<RVAdapterPpob.PpobHolder>{
    private List<Produk> list = new ArrayList<>();
    MainPPOBFragment fragPPOB;
    Home activityHome;
    PulsaFragment fPulsa;
    DFPinPpob dfPinPpob;

    public void clear(){
        list.clear();
        notifyDataSetChanged();
    }

    public void setProduks(List<Produk> produks){
        this.list = produks;
        notifyDataSetChanged();
    }

    public RVAdapterPpob(List<Produk> list, MainPPOBFragment fragPPOB, Home activityHome, PulsaFragment fPulsa) {
        this.list = list;
        this.fragPPOB = fragPPOB;
        this.activityHome = activityHome;
        this.fPulsa = fPulsa;
    }

    @Override
    public RVAdapterPpob.PpobHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_pulsa_content,parent,false);
        PpobHolder holder = new PpobHolder(view, fragPPOB.getFragSecPrefs());
        return holder;
    }

    @Override
    public void onBindViewHolder(RVAdapterPpob.PpobHolder holder, int position) {
        String desc = list.get(position).getKeterangan();
        String ls_sts_live = String.valueOf(list.get(position).getSts_live());
        //String price = String.valueOf(list.get(position).getNominal());
        String price = String.valueOf(list.get(position).getHarga());
        holder.tvSalesPrice.setText("Rp. " + UnitConversion.format2Rupiah2(price));
        holder.btnDescription.setText(desc);
        if (ls_sts_live.equals("0")) {
            holder.btnDescription.setBackgroundColor(Color.parseColor("#B00020"));
            //holder.btnDescription.setBackgroundTintList(activityHome.getResources().getColorStateList(Color.parseColor("#B00020")));
            holder.tvSalesPrice.setBackgroundColor(Color.parseColor("#B00020"));
        } else {
            holder.btnDescription.setBackgroundColor(Color.parseColor("#018786"));
            //holder.btnDescription.setBackgroundTintList(activityHome.getResources().getColorStateList(Color.parseColor("#018786")));
            holder.tvSalesPrice.setBackgroundColor(Color.parseColor("#018786"));
        };
        //holder.btnDescription.setBackgroundColor(15037299);
        holder.ppob = list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PpobHolder extends RecyclerView.ViewHolder{
        Button btnDescription;
        TextView tvSalesPrice;
        Context context;
        Produk ppob;
        SharedPreferences preferences;
        EditText edtNumber;

        String transactionId;

        public PpobHolder(View itemView, SharedPreferences pref) {
            super(itemView);
            context = itemView.getContext();
            btnDescription = (Button)itemView.findViewById(R.id.btnDescription);
            tvSalesPrice = (TextView) itemView.findViewById(R.id.tvSalesPrice);
            preferences = pref;

            edtNumber = (EditText) fPulsa.getView().findViewById(R.id.edtNumber);
            btnDescription.setOnClickListener(new btnDescOnClick());

        }

        private class btnDescOnClick implements View.OnClickListener {
            DfLoading dfLoading = new DfLoading();

            @Override
            public void onClick(View v) {
                dfLoading.show(((FragmentActivity) context).getSupportFragmentManager(), "rvAdapterPpob");
                dfLoading.setCancelable(false);

                Long depositBalance = preferences.getLong(Cfg.depositKey, 0);
                int salesPrice = ppob.getNominal();
                String deposit = UnitConversion.format2Rupiah(depositBalance - salesPrice);


                //dfPinPpob = DFPinPpob.newDfPinPpob(deposit);
//                dfPinPpob = DFPinPpob.newDfPinPpob(deposit, ppob.getName(), ppob.getKode_produk());


                Log.e(Cfg.TAG_COMMON, "deposit saat ini = " + depositBalance);
                /*if(depositBalance < salesPrice){
                    dfLoading.dismiss();
                    Toast.makeText(activityHome, R.string.deposit_tidak_cukup, Toast.LENGTH_SHORT).show();
                    return;
                }else*/
                if (edtNumber.getText().toString().length() < 10) {
                    dfLoading.dismiss();
                    Toast.makeText(activityHome, R.string.nomor_terlalu_pendek, Toast.LENGTH_SHORT).show();
                    return;
                } else if (!MyUtils.isValidPhoneNumber(edtNumber.getText().toString())) {
                    dfLoading.dismiss();
                    Toast.makeText(activityHome, R.string.nomor_tidak_valid, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (ppob.getSts_live().equals("0")) {
                    dfLoading.dismiss();
                    Toast.makeText(activityHome, "Produk Gangguan/Close", Toast.LENGTH_SHORT).show();
                    return;
                }



                String msidn = ppob.getName();
                String kdProduk = ppob.getKode_produk();
                String kdBank = "UM0001";
                Log.d("SIAPKIRIMPULSA", msidn + ":" + kdProduk + ":" + kdBank);

//                    dfPinPpob.show(fPulsa.getFragmentManager(),"pin_ppob");
                dfLoading.dismiss();


                Intent intent = new Intent(context, SummaryActivity.class);
                intent.putExtra(Cfg.transactionTypeKey, Cfg.pulsaTypeVal);
                intent.putExtra("msidn", msidn);
                intent.putExtra("idPelanggan", edtNumber.getText().toString());
                intent.putExtra("produk", ppob);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                context.startActivity(intent);

            }


        }




    }
}


