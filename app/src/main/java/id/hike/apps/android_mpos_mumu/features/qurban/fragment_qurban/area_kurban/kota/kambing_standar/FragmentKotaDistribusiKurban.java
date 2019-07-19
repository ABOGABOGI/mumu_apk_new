package id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.area_kurban.kota.kambing_standar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseFragment;
import id.hike.apps.android_mpos_mumu.features.qurban.ActivityNextKurbanSapi;
import id.hike.apps.android_mpos_mumu.features.qurban.ActivityNextQurban;
import id.hike.apps.android_mpos_mumu.features.qurban.area_distribusi_adapter.AreaAdapter;
import id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.area_kurban.kota.FragmentListKotaDistribusiKurban;
import id.hike.apps.android_mpos_mumu.util.ListenerRecyclerItemClick;

public class FragmentKotaDistribusiKurban extends BaseFragment {

    RecyclerView recyclerView;
    List<AreaAdapter> daftarKotaAsal;

    public FragmentKotaDistribusiKurban() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        daftarKotaAsal = new ArrayList<>();
        daftarKotaAsal.add(new AreaAdapter("Jakarta, Indonesia"));
        daftarKotaAsal.add(new AreaAdapter("Surabaya, Indonesia"));
        daftarKotaAsal.add(new AreaAdapter("Palembang, Indonesia"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_kota_distribusi_kurban, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        FragmentListKotaDistribusiKurban fragmentListKotaDistribusiKurban = new FragmentListKotaDistribusiKurban(getContext(), daftarKotaAsal);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(fragmentListKotaDistribusiKurban);

        recyclerView.addOnItemTouchListener(new ListenerRecyclerItemClick(getContext(), (view1, position) -> {

            FragmentListKotaDistribusiKurban fragmentListKotaDistribusiKurban1 = ((FragmentListKotaDistribusiKurban) recyclerView.getAdapter());
            fragmentListKotaDistribusiKurban.dataList.get(position).getNamaKotaDistribusiKurban();

            SharedPreferences.Editor sf = getFragSecPrefs().edit();
            sf.putString(Cfg.KOTA_DISTRIBUSI_KURBAN, String.valueOf(fragmentListKotaDistribusiKurban.dataList.get(position).getNamaKotaDistribusiKurban()));
            sf.apply();

            Intent intent = new Intent(getContext(), ActivityNextQurban.class);
            getContext().startActivity(intent);

        }));

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
