package id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.pascabayar_indosat.ActivityPascaBayarIndosat;
import id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.pascabayar_telkomsel.ActivityPascabayarTelkomsel;
import id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.pascabayar_three.ActivityDetailPascaBayarThree;
import id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.pascabayar_three.ActivityPascaBayarThree;
import id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.pascabayar_xl.ActivityPascaBayarXl;

public class TeleponPascaBayar extends Fragment {

    CardView pascaBayarTelkomsel, pascaBayarXl, pascaBayarIndosat, pascaBayarThree;

    public TeleponPascaBayar(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_telepon_pasca_bayar, container, false);

        pascaBayarTelkomsel = view.findViewById(R.id.cardView11);
        pascaBayarXl = view.findViewById(R.id.pascaBayarXl);
        pascaBayarIndosat = view.findViewById(R.id.pascaBayarIndosat);
        pascaBayarThree = view.findViewById(R.id.pascaBayarThree);

        pascaBayarTelkomsel.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ActivityPascabayarTelkomsel.class);
            getContext().startActivity(intent);
        });

        pascaBayarXl.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ActivityPascaBayarXl.class);
            getContext().startActivity(intent);
        });

        pascaBayarThree.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ActivityPascaBayarThree.class);
            getContext().startActivity(intent);
        });

        pascaBayarIndosat.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ActivityPascaBayarIndosat.class);
            getContext().startActivity(intent);
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
