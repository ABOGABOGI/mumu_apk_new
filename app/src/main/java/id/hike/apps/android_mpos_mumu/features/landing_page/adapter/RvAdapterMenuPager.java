package id.hike.apps.android_mpos_mumu.features.landing_page.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.destinansi.DestinasiActivity;
import id.hike.apps.android_mpos_mumu.features.donasi.donasi;
import id.hike.apps.android_mpos_mumu.features.home.Home;
import id.hike.apps.android_mpos_mumu.features.landing_page.model.MenuItem;
import id.hike.apps.android_mpos_mumu.features.pdam.PdamActivity;
import id.hike.apps.android_mpos_mumu.features.proteksi.ProteksiActivity;
import id.hike.apps.android_mpos_mumu.features.tagihan_telepon.TagihanTeleponActivity;
import id.hike.apps.android_mpos_mumu.features.wakaf.ProgramWakafActivity;

public class RvAdapterMenuPager extends RecyclerView.Adapter<RvAdapterMenuPager.RvAdapterMenuPagerHolder> {


    List<MenuItem> items = new ArrayList<>();
    private Context context;

    public void setItem(MenuItem item){
        items.add(item);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RvAdapterMenuPagerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_menu_pager, parent, false);
        context = parent.getContext();
        return new RvAdapterMenuPagerHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapterMenuPagerHolder holder, int position) {

        MenuItem item = items.get(position);

        holder.imageView.setImageResource(item.getImage());
        holder.titleView.setText(item.getTitle());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(item);
            }
        });

        holder.titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(item);
            }
        });

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                open(item);

            }
        });
    }

    private void open(MenuItem item){
        switch(item.getImage()){
            case R.drawable.pulsa_umu_bordered:
                startHomeActivity(Cfg.TAG_PULSA);
                break;

            case R.drawable.ic_proteksi:
//                Toast.makeText(context, "PROTEKSI On Development", Toast.LENGTH_LONG).show();
                Intent goToProteksi = new Intent(context, ProteksiActivity.class);
                context.startActivity(goToProteksi);
                break;



            case R.drawable.ic_donasi:
                //Intent goToDonasiIntent = new Intent(context, /*donasi.class*/DonasiSecondActivity.class);
                Intent goToDonasiIntent = new Intent(context, donasi.class);
                context.startActivity(goToDonasiIntent);

                //Toast.makeText(context, "On Development", Toast.LENGTH_LONG).show();
                break;

            case R.drawable.ic_wakaf:
                Intent goToWakafIntent = new Intent(context, ProgramWakafActivity.class);
                context.startActivity(goToWakafIntent);
                break;

            case R.drawable.pln_umu:
                startHomeActivity(Cfg.TAG_LISTRIK);
                break;

            case R.drawable.pdam_umu:
                //startHomeActivity(Cfg.TAG_PDAM);
                Intent goToPdamIntent = new Intent(context, PdamActivity.class);
                context.startActivity(goToPdamIntent);
                break;

            case R.drawable.telkom_umu:
                //startHomeActivity(Cfg.TAG_TELKOM);
                Intent goToTagihanTeleponIntent = new Intent(context, TagihanTeleponActivity.class);
                context.startActivity(goToTagihanTeleponIntent);
                break;

            case R.drawable.airplane:
                Intent goToDestinasiIntent = new Intent(context, DestinasiActivity.class);
                context.startActivity(goToDestinasiIntent);
                break;

            case R.drawable.ticket_umu:
                Toast.makeText(context, "On Development", Toast.LENGTH_LONG).show();
                break;

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void startHomeActivity( String paymentTag) {
        Intent intent = new Intent(context, Home.class);
        intent.putExtra("payment", paymentTag);
        context.startActivity(intent);
    }

    public class RvAdapterMenuPagerHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imageView)
        ImageView imageView;

        @BindView(R.id.titleView)
        TextView titleView;

        @BindView(R.id.containerView)
        View view;

        public RvAdapterMenuPagerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
