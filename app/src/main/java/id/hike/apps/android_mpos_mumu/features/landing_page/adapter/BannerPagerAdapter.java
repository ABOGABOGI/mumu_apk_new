package id.hike.apps.android_mpos_mumu.features.landing_page.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import id.hike.apps.android_mpos_mumu.features.landing_page.FragmentBanner;

public class BannerPagerAdapter extends FragmentStatePagerAdapter {
    int[] imagesResource;
    String[] imagesUrl;

    public BannerPagerAdapter(FragmentManager fm) {
        super(fm);
        imagesResource = new int[0];
        imagesUrl = new String[0];
    }

    public BannerPagerAdapter setImagesResource(int[] items) {
        imagesResource = items == null ? new int[0] : items;
        return this;
    }

    public BannerPagerAdapter setImagesUrl(String[] urls) {
        imagesUrl = urls == null ? new String[0] : urls;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public Fragment getItem(int i) {
        /**
         *  images from url is priority
         */
        FragmentBanner fragmentBanner = new FragmentBanner();

        if (imagesUrl.length > 0) {
            fragmentBanner.setImageUrl(imagesUrl[i]);
        } else if (imagesResource.length > 0) {
            fragmentBanner.setImageResource(imagesResource[i]);
        }

        return fragmentBanner;
    }

    @Override
    public int getCount() {
        /**
         *  images from url is priority
         */
        int count = 0;

        if (imagesUrl.length > 0) {
            count = imagesUrl.length;
        } else if (imagesResource.length > 0) {
            count = imagesResource.length;
        }

        return count;
    }
}
