package id.hike.apps.android_mpos_mumu.features.landing_page.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.landing_page.model.ResProdukPromo;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;

/**
 * Created by M Alvin Syahrin on 19/06/2018.
 */

public class RvAdapterPromoProduct extends RecyclerView.Adapter<RvAdapterPromoProduct.MyViewHolder>{
    private Context context;
    private List<ResProdukPromo> list;
    private int layoutWidth = 0;


    public RvAdapterPromoProduct(Context context, List<ResProdukPromo> list, int layoutWidth) {
        int paddingDp = 5;
        int paddingPx = (int) (paddingDp * Resources.getSystem().getDisplayMetrics().density);
        this.context = context;
        this.list = list;
        this.layoutWidth = layoutWidth - paddingPx;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_promo_product, parent, false);
        Log.d(Cfg.TAG_COMMON, "lebar item rv dikirim = "+ layoutWidth);
        Log.d(Cfg.TAG_COMMON, "lebar item rv actual = "+ view.getMeasuredWidth());
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ResProdukPromo prod = list.get(position);
        final String pictUrl = prod.getPictUrl();
        Picasso.get()
                .load(Cfg.IMAGE_URL + pictUrl).error(R.drawable.ic_local_offer)
                .into(holder.imvPromoProduct);
        String disc = "Disc "+list.get(position).getDiscVal()+"%";
        String prevPrice = UnitConversion.format2Rupiah(prod.getPriceNormal());
        String currPrice = UnitConversion.format2Rupiah(prod.getPriceSale());

        holder.tvDisc.setText(disc);
        holder.tvPrevPrice.setText(prevPrice);
        holder.tvCurrPrice.setText(currPrice);

//        holder.imvPromoProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, A_ZoomImage.class);
//                intent.putExtra("pictUrl", Cfg.IMAGE_URL + pictUrl);
//                context.startActivity(intent);
//            }
//        });

        holder.rlPromoProduct.getLayoutParams().width = layoutWidth;


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imvPromoProduct;
        TextView tvDisc;
        TextView tvPrevPrice;
        TextView tvCurrPrice;
        RelativeLayout rlPromoProduct;

        public MyViewHolder(View itemView) {
            super(itemView);
            rlPromoProduct = (RelativeLayout)itemView.findViewById(R.id.rlPromoProduct);
            tvDisc = (TextView)itemView.findViewById(R.id.tvDisc);
            tvPrevPrice = (TextView)itemView.findViewById(R.id.tvPrevPrice);
            tvCurrPrice = (TextView)itemView.findViewById(R.id.tvCurrPrice);
            imvPromoProduct = (ImageView)itemView.findViewById(R.id.imvPromoProduct);
        }


    }
}
