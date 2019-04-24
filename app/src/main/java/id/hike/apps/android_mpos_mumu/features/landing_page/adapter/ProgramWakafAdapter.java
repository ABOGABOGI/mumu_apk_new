package id.hike.apps.android_mpos_mumu.features.landing_page.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.wakaf.WakafDetailActivity;
import id.hike.apps.android_mpos_mumu.model.MetaInfo;

public class ProgramWakafAdapter  extends RecyclerView.Adapter<ProgramWakafAdapter.ProgramWakafHolder> {

    private List<MetaInfo> wakafList = new ArrayList<>();
    private Context context;

    public void add(MetaInfo info){
        wakafList.add(info);
    }

    public void set(List<MetaInfo> info){
        wakafList = info;
        notifyDataSetChanged();
    }


    public List<MetaInfo> getWakafList(){
        return wakafList;
    }

    @NonNull
    @Override
    public ProgramWakafHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.frag_program_wakaf, parent, false);
        context = parent.getContext();
        return new ProgramWakafHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramWakafHolder holder, int position) {

        MetaInfo info = wakafList.get(position);


        try {


            JSONObject object = new JSONObject(info.getMetavalue());

            String urlImg = Cfg.BASE_ASSET_URL + object.getString("img");

            Log.i("BOWOTAG", urlImg);

            Picasso.get()
                    .load(urlImg)
                    .placeholder(R.drawable.progress_animation)
                    .into(holder.imageView);

            holder.titleView.setText(object.getString("title"));
            holder.descriptionView.setText(object.getString("keterangan"));


            holder.mainView.setOnClickListener(new View.OnClickListener() {
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
        return wakafList.size();
    }

    public class ProgramWakafHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imagePlaceholder)
        ImageView imageView;

        @BindView(R.id.title)
        TextView titleView;

        @BindView(R.id.description)
        TextView descriptionView;

        @BindView(R.id.linearLayout)
        LinearLayout mainView;

        public ProgramWakafHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
