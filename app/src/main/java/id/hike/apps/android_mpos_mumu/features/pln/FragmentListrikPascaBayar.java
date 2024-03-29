package id.hike.apps.android_mpos_mumu.features.pln;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.pascabayar_telkomsel.ActivityDetailPascabayarTelkomsel;

public class FragmentListrikPascaBayar extends Fragment {

    Button button;
    EditText inputKodeTagihanPln;
    String nomor = "0123456789";

    public FragmentListrikPascaBayar() {
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
        View view = inflater.inflate(R.layout.fragment_fragment_listrik_pasca_bayar, container, false);

        inputKodeTagihanPln = view.findViewById(R.id.inputKodeTagihanPln);

        button = view.findViewById(R.id.btnNextPascaBayarListrik);
        button.setOnClickListener(v -> {

            if (inputKodeTagihanPln.getText().toString().equals(nomor)) {
                FragmentManager fm = getFragmentManager();
                fm.popBackStack();
                fm.beginTransaction().replace(R.id.plnPascaBayar, new FragmentDetailListrikPascaBayar()).addToBackStack(null).commit();
            } else if (inputKodeTagihanPln.getText().toString().equals("")) {
                inputKodeTagihanPln.setError("Mohon isi nomor telepon anda");
            } else {
                //Toast.makeText(ActivityPascaBayarIndosat.this, "Data nomor tidak ditemukan", Toast.LENGTH_SHORT).show();
                inputKodeTagihanPln.setError("Data nomor tidak ditemukan");
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
