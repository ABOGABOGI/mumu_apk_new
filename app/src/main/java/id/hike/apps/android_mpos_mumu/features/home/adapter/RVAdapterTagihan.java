package id.hike.apps.android_mpos_mumu.features.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.model.TagihanItem;

public class RVAdapterTagihan extends RecyclerView.Adapter<RVAdapterTagihan.RVTagihanHolder> {


    private List<TagihanItem> transaksi = new ArrayList<>();


    public void addTagihan(TagihanItem item){
        transaksi.add(item);
    }

    @NonNull
    @Override
    public RVTagihanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tagihan_layout, parent, false);

        RVTagihanHolder holder = new RVTagihanHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVTagihanHolder holder, int position) {

        TagihanItem key = transaksi.get(position);

        holder.titleLine.setText(key.getTitleLine());
        holder.descriptionLine.setText(key.getDescriptionLine());
    }

    @Override
    public int getItemCount() {
        return transaksi.size();
    }

    public class RVTagihanHolder extends RecyclerView.ViewHolder{

        Context context;
        TextView titleLine;
        TextView descriptionLine;

        public RVTagihanHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            titleLine = itemView.findViewById(R.id.titleLine);
            descriptionLine = itemView.findViewById(R.id.descriptionLine);
        }
    }

}
