package id.hike.apps.android_mpos_mumu.features.pln;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.pdam.ActivityStrukBayarPdam;
import id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.pascabayar_telkomsel.ActivityDetailPascabayarTelkomsel;

public class FragmentDetailListrikPascaBayar extends Fragment {

    TextView periodePascaBayarTelkomsel, samaDenganPediode, samaDenganPemakaian, pemakaianPascaBayarTelkomsel, textLihatDetail, jumlahBayarPlnPb, noRefPlnPb;

    ConstraintLayout lihatDetail;
    ImageView gambarLihatDetail;
    Button btnBayar, btnBatal;

    private int a = 1;

    public FragmentDetailListrikPascaBayar() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_detail_listrik_pasca_bayar, container, false);

        periodePascaBayarTelkomsel = view.findViewById(R.id.textView75);
        samaDenganPediode = view.findViewById(R.id.samaDenganSatu);
        pemakaianPascaBayarTelkomsel = view.findViewById(R.id.textView76);
        samaDenganPemakaian = view.findViewById(R.id.samaDenganDua);
        jumlahBayarPlnPb = view.findViewById(R.id.jumlahBayarPlnPb);
        noRefPlnPb = view.findViewById(R.id.noRefPlnPb);

        lihatDetail = view.findViewById(R.id.lihatDetail);
        gambarLihatDetail = view.findViewById(R.id.gambarLihatDetail);
        textLihatDetail = view.findViewById(R.id.textView78);

        btnBayar = view.findViewById(R.id.btnBayarPdam);
        btnBayar.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ActivityStrukPlnPascaBayar.class);
            startActivity(intent);
        });

        btnBatal = view.findViewById(R.id.btnBatalPdam);
        btnBatal.setOnClickListener(v -> {

        });

        periodePascaBayarTelkomsel.setVisibility(View.GONE);
        samaDenganPediode.setVisibility(View.GONE);
        pemakaianPascaBayarTelkomsel.setVisibility(View.GONE);
        samaDenganPemakaian.setVisibility(View.GONE);
        jumlahBayarPlnPb.setVisibility(View.GONE);
        noRefPlnPb.setVisibility(View.GONE);

        lihatDetail.setOnClickListener(v -> {

            if (a == 1) {
                periodePascaBayarTelkomsel.setVisibility(View.VISIBLE);
                samaDenganPediode.setVisibility(View.VISIBLE);
                pemakaianPascaBayarTelkomsel.setVisibility(View.VISIBLE);
                samaDenganPemakaian.setVisibility(View.VISIBLE);
                jumlahBayarPlnPb.setVisibility(View.VISIBLE);
                noRefPlnPb.setVisibility(View.VISIBLE);

                textLihatDetail.setText("Tutup");
                gambarLihatDetail.setImageResource(R.drawable.ic_uparrow);

                a = 0;
            }else{
                periodePascaBayarTelkomsel.setVisibility(View.GONE);
                samaDenganPediode.setVisibility(View.GONE);
                pemakaianPascaBayarTelkomsel.setVisibility(View.GONE);
                samaDenganPemakaian.setVisibility(View.GONE);
                jumlahBayarPlnPb.setVisibility(View.GONE);
                noRefPlnPb.setVisibility(View.GONE);

                textLihatDetail.setText("Lihat Detail");
                gambarLihatDetail.setImageResource(R.drawable.ic_downarrow);
                a = 1;
            }
        });

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
