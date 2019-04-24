package id.hike.apps.android_mpos_mumu.features.wakaf.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.wakaf.WakafDetailActivity;
import id.hike.apps.android_mpos_mumu.model.MetaInfo;

public class RvAdapterWakaf extends RecyclerView.Adapter<RvAdapterWakaf.RvAdapterWakafHolder> {


    List<MetaInfo> wakafs = new ArrayList<>();

    private Context context;

    public void addWakaf(MetaInfo info){

        wakafs.add(info);

    }

    public void setWakafs(List<MetaInfo> wakafs){
        this.wakafs = wakafs;
    }

    @NonNull
    @Override
    public RvAdapterWakafHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_wakaf, parent, false);
        context = parent.getContext();
        return new RvAdapterWakafHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapterWakafHolder holder, int position) {

        MetaInfo info = wakafs.get(position);

        try {
            JSONObject obj = new JSONObject(info.getMetavalue());

            Picasso.get().load(Cfg.BASE_ASSET_URL + obj.getString("img")).placeholder(R.drawable.progress_animation).into(holder.iconView);

            holder.titleView.setText(obj.getString("title"));
            holder.descView.setText(obj.getString("keterangan"));

            holder.selengkapnyaButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, WakafDetailActivity.class);
                    intent.putExtra("info", info);
                    Cfg.gs_WakafInfo_MetaKey = info.getMetakey();
                    Cfg.gs_WakafInfo_KodeProduk = info.getMetakodeproduk();
                    context.startActivity(intent);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return wakafs.size();
    }

    public class RvAdapterWakafHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iconWakaf)
        CircleImageView iconView;

        @BindView(R.id.titleWakaf)
        TextView titleView;

        @BindView(R.id.descWakaf)
        TextView descView;

        @BindView(R.id.selengkapnyaButton)
        TextView selengkapnyaButton;

        public RvAdapterWakafHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

}
