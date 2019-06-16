package id.hike.apps.android_mpos_mumu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class KotaAsal extends Fragment {

    ConstraintLayout constraintLayout, constraintLayoutDua, constraintLayoutTiga, constraintLayoutEmpat;
    TextView kotaSatu, kotaDua;
    SearchView searchView;
    View viewKu;

    RecyclerView recyclerView;
    List<ListKotaAsal> daftarKotaAsal;

    public KotaAsal() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        daftarKotaAsal = new ArrayList<>();
        daftarKotaAsal.add(new ListKotaAsal("Jakarta, Indonesia"));
        daftarKotaAsal.add(new ListKotaAsal("Surabaya, Indonesia"));
        daftarKotaAsal.add(new ListKotaAsal("Palembang, Indonesia"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_recyclerview_kota_asal, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        ListKotaAsalAdapter listKotaAsalAdapter = new ListKotaAsalAdapter(getContext(), daftarKotaAsal);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(listKotaAsalAdapter);

        /*constraintLayout = view.findViewById(R.id.constraintLayout7);
        constraintLayoutDua = view.findViewById(R.id.constraintLayout8);
        constraintLayoutEmpat = view.findViewById(R.id.constraintLayout9);
        viewKu = view.findViewById(R.id.view);

        searchView = view.findViewById(R.id.search);
        kotaSatu = view.findViewById(R.id.kotaSatu);
        kotaDua = view.findViewById(R.id.kotaDua);*/

        /*constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewKu.setVisibility(View.GONE);
                constraintLayout.setVisibility(View.GONE);
                constraintLayoutDua.setVisibility(View.GONE);
                constraintLayoutEmpat.setVisibility(View.GONE);
                searchView.setVisibility(View.GONE);

                Bundle bundle = new Bundle();
                bundle.putString(Cfg.KOTA_TUJUAN, String.valueOf(kotaSatu.getText().toString()));
                //bundle.putString("dataDua", String.valueOf(kotaDua.getText().toString().length()));
                FragmentSekaliJalan fs = new FragmentSekaliJalan();
                fs.setArguments(bundle);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.kotaAsalFragment, fs);
                transaction.commit();
                *//*getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.show, fs)
                        .commit();*//*
            }
        });

        constraintLayoutEmpat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewKu.setVisibility(View.GONE);
                constraintLayout.setVisibility(View.GONE);
                constraintLayoutDua.setVisibility(View.GONE);
                constraintLayoutEmpat.setVisibility(View.GONE);
                searchView.setVisibility(View.GONE);

                Bundle bundle = new Bundle();
                bundle.putString(Cfg.KOTA_TUJUAN, String.valueOf(kotaDua.getText().toString()));
                //bundle.putString("dataDua", String.valueOf(kotaDua.getText().toString().length()));
                FragmentSekaliJalan fs = new FragmentSekaliJalan();
                fs.setArguments(bundle);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.kotaAsalFragment, fs);
                transaction.commit();
                *//*getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.show, fs)
                        .commit();*//*
            }
        });*/

        return view;
    }
}
