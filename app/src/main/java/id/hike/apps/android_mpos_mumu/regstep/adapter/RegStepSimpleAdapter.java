package id.hike.apps.android_mpos_mumu.regstep.adapter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import id.hike.apps.android_mpos_mumu.regstep.fragment.FragmentForm;

public class RegStepSimpleAdapter extends FragmentStatePagerAdapter {


    private List<FragmentForm> fragmentItem = new ArrayList<>();

    public void addPage(FragmentForm fragment){
        this.fragmentItem.add(fragment);
    }

    public RegStepSimpleAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public FragmentForm getItem(int position) {

        return fragmentItem.get(position);

    }

    @Override
    public int getCount() {
        return fragmentItem.size();
    }
}
