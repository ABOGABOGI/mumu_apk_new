package id.hike.apps.android_mpos_mumu.features.landing_page;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import id.hike.apps.android_mpos_mumu.R;

public class FragmentBanner extends Fragment {
    int imageResource;
    String imageUrl;

    public FragmentBanner setImageResource(int resourceId) {
        imageResource = resourceId;
        return this;
    }

    public FragmentBanner setImageUrl(String url) {
        imageUrl = url;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_banner, container, false);
        final ImageView ivBanner = view.findViewById(R.id.ivBanner);
        ivBanner.setScaleType(ImageView.ScaleType.FIT_XY);




        try {
            if (imageUrl != null) {
                Picasso.get()
                        .load(imageUrl)
                        .error(R.drawable.ic_close)
                        .placeholder(R.drawable.progress_animation)
                        .into(ivBanner);
            } else if (imageResource > 0) {
                container.getResources().getResourceName(imageResource);
                ivBanner.setImageResource(imageResource);
            }

            ivBanner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        } catch (IllegalArgumentException | Resources.NotFoundException ex) {
            ivBanner.setImageResource(R.drawable.ic_close);
            ivBanner.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Log.d("ERROR", "image not found");
        }

        return view;
    }
}
