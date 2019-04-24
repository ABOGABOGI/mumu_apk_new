package id.hike.apps.android_mpos_mumu.features.pelanggan;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.app.BaseApplication;
import id.hike.apps.android_mpos_mumu.features.home.PulsaFragment;

/**
 * Created by getwiz on 16/05/17.
 */

public class RVAdapterPelanggan extends RecyclerView.Adapter<RVAdapterPelanggan.MyViewHolder> {

    private Context mContext;
    private List<ModelPelanggan> mm = new ArrayList<>();
    private BaseApplication baseApplication;
    private int LASTVIEW = 0;
    public static int MENU_VIEW = 1;
    public static int PPOB_VIEW = 2;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView nama, telepon, email, initName;
        public ImageView ivInitialName;
        public ImageView ivSlide;
        public LinearLayout layFirst;
        public LinearLayout ivTes2;
        public LinearLayout ivTes3;
        public LinearLayout llItemPelanggan;

        public ImageButton btnAddEmail;

        public MyViewHolder(View itemView) {
            super(itemView);
            nama = (TextView) itemView.findViewById(R.id.tvName);
            telepon = (TextView) itemView.findViewById(R.id.tvTelepon);
            initName = (TextView) itemView.findViewById(R.id.tvInitName);
            ivSlide = (ImageView) itemView.findViewById(R.id.ivSlide);

            email = (TextView) itemView.findViewById(R.id.tvEmail);

            layFirst = (LinearLayout) itemView.findViewById(R.id.layFirst);
            ivTes2 = (LinearLayout) itemView.findViewById(R.id.layTwo);
            ivTes3 = (LinearLayout) itemView.findViewById(R.id.layThree);
            llItemPelanggan = (LinearLayout) itemView.findViewById(R.id.llItemPelanggan);

            btnAddEmail = (ImageButton) itemView.findViewById(R.id.btnAddEmail);
            baseApplication = (BaseApplication) mContext.getApplicationContext();
        }
    }

    public RVAdapterPelanggan(Context mContext, ModelPelanggan msg, int viewLayout ) {
        this.mContext = mContext;
        this.mm.add(msg);
        this.LASTVIEW = viewLayout;
    }

    /*void addData(ModelCustomers mm){
        this.mm.get(0).getResponLogin3Data().addAll(mm.getResponLogin3Data());
    }*/

    @Override
    public RVAdapterPelanggan.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.i_pelanggan, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RVAdapterPelanggan.MyViewHolder holder, int position) {
        final boolean[] tez = {false};
        boolean theresEmail = false;
        holder.nama.setText(mm.get(0).getData().get(position).getName());
        holder.telepon.setText(mm.get(0).getData().get(position).getPhone());
        holder.initName.setText(mm.get(0).getData().get(position).getName().substring(0, 1));
        holder.email.setText(mm.get(0).getData().get(position).getEmail());
        if (mm.get(0).getData().get(position).getEmail().isEmpty()) {
            holder.ivSlide.setImageResource(R.drawable.u3414);
            theresEmail = false;
        } else {
            holder.ivSlide.setImageResource(R.drawable.u3361);
            theresEmail = true;
        }
        final boolean finalTheresEmail = theresEmail;
        holder.ivSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalTheresEmail == true) {
                    if (!tez[0]) {
                        holder.layFirst.setVisibility(View.GONE);
                        holder.ivTes2.setVisibility(View.VISIBLE);
                        tez[0] = !tez[0];
                    } else {
                        holder.layFirst.setVisibility(View.VISIBLE);
                        holder.ivTes2.setVisibility(View.GONE);
                        tez[0] = !tez[0];
                    }
                } else {
                    if (!tez[0]) {
                        holder.layFirst.setVisibility(View.GONE);
                        holder.ivTes3.setVisibility(View.VISIBLE);
                        tez[0] = !tez[0];
                    } else {
                        holder.layFirst.setVisibility(View.VISIBLE);
                        holder.ivTes3.setVisibility(View.GONE);
                        tez[0] = !tez[0];
                    }
                }
            }
        });

        holder.btnAddEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Email berhasil ditambahkan.", Toast.LENGTH_SHORT).show();
            }
        });

        /*holder.layFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                baseApplication.secPrefs.edit().putString(Cfg.prefsKostumerName_STR, modelPelanggan.getResLogin2Data().get(position).getName()).apply();

                baseApplication.secPrefs.edit().putLong(Cfg.prefsKostumerId_Long, modelPelanggan.getResLogin2Data().get(position).getCustomerId()).apply();

                String transactionId = String.valueOf(Cfg.getTransactionId());

                baseApplication.secPrefs.edit().putString(Cfg.prefsRecentTransIdStr, transactionId).apply();

                Intent intent = new Intent(Pelanggan.this, Home.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });*/
        holder.llItemPelanggan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LASTVIEW == PPOB_VIEW){
                    PulsaFragment.edtNumber.setText(holder.telepon.getText());
                    Toast.makeText(mContext, holder.nama.getText() + " " + "telah dipilih", Toast.LENGTH_SHORT).show();
                    ((Activity)mContext).finish();
                } else {
//                    Toast.makeText(mContext, "Menu Floating", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return (long) mm.get(0).getData().get(position).getCustomerId();
    }

    @Override
    public int getItemCount() {
        return mm.get(0).getData().size();
    }


}