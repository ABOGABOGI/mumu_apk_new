package id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.area_kurban.provinsi.sapi;

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
import id.hike.apps.android_mpos_mumu.features.qurban.area_distribusi_adapter.AreaAdapter;
import id.hike.apps.android_mpos_mumu.features.qurban.area_distribusi_adapter.ProvinsiDistribusiKurbanAdapter;
import id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.area_kurban.kota.FragmentListKotaDistribusiKurban;
import id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.area_kurban.provinsi.FragmentListProvinsiDistribusiKurban;
import id.hike.apps.android_mpos_mumu.util.ListenerRecyclerItemClick;

public class FragmentProvinsiDistribusiKurban extends BaseFragment {

    RecyclerView recyclerView;
    List<ProvinsiDistribusiKurbanAdapter> daftarProvinsiDistribusiKurban;

    public FragmentProvinsiDistribusiKurban() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        daftarProvinsiDistribusiKurban = new ArrayList<>();
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Aceh"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Sumatera Utara"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Sumatera Barat"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Riau"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Kepulauan Riau"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Jambi Bengkulu"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Sumatera Selatan"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Kepulauan Bangka Belitung"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Lampung"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Banten"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("DKI Jakarta"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Jawa Barat"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Jawa Tengah"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("DI Yogyakarta"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Bali"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Nusa Tenggara Barat"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Nusa Tenggara Timur"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Kalimantan Utara"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Kalimantan Barat"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Kalimantan Tengah"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Kalimantan Selatan"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Kalimantan Timur"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Gorontalo"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Sulawesi Utara"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Sulawesi Barat"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Sulawesi Tengah"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Sulawesi Selatan"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Sulawesi Tenggara"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Maluku Utara"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Maluku"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Papua Barat"));
        daftarProvinsiDistribusiKurban.add(new ProvinsiDistribusiKurbanAdapter("Papua"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_provinsi_distribusi_kurban, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        FragmentListProvinsiDistribusiKurban fragmentListProvinsiDistribusiKurban = new FragmentListProvinsiDistribusiKurban(getContext(), daftarProvinsiDistribusiKurban);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(fragmentListProvinsiDistribusiKurban);

        recyclerView.addOnItemTouchListener(new ListenerRecyclerItemClick(getContext(), (view1, position) -> {

            FragmentListProvinsiDistribusiKurban fragmentListKotaDistribusiKurban1 = ((FragmentListProvinsiDistribusiKurban) recyclerView.getAdapter());
            fragmentListProvinsiDistribusiKurban.dataList.get(position).getNamaProvinsiDistribusiKurban();

            //Bundle bundle = new Bundle();
            SharedPreferences.Editor sf = getFragSecPrefs().edit();
            sf.putString(Cfg.PROVINSI_DISTRIBUSI_KURBAN, String.valueOf(fragmentListProvinsiDistribusiKurban.dataList.get(position).getNamaProvinsiDistribusiKurban()));
            sf.apply();

            Intent intent = new Intent(getContext(), ActivityNextKurbanSapi.class);
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

