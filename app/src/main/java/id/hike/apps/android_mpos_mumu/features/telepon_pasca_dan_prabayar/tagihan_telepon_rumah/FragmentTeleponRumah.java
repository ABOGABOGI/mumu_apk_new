package id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.tagihan_telepon_rumah;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.googlecode.mp4parser.authoring.Edit;

import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.pascabayar_telkomsel.ActivityDetailPascabayarTelkomsel;
import id.hike.apps.android_mpos_mumu.features.telepon_pasca_dan_prabayar.pasca_bayar.pascabayar_telkomsel.ActivityPascabayarTelkomsel;

public class FragmentTeleponRumah extends Fragment {

    EditText nomorDepanTlpRumeh;
    Button button;
    EditText inputNoTeleponRumah;
    String nomor = "02112345678";

    public FragmentTeleponRumah() {
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
        View view = inflater.inflate(R.layout.fragment_telepon_rumah, container, false);

        nomorDepanTlpRumeh = view.findViewById(R.id.nomorDepanTlpRumah);
        nomorDepanTlpRumeh.setEnabled(false);

        inputNoTeleponRumah = view.findViewById(R.id.inputNoTeleponRumah);

        button = view.findViewById(R.id.btnLihatDetailTagihanTlpRumah);
        button.setOnClickListener(v -> {
            if (inputNoTeleponRumah.getText().toString().equals(nomor)) {
                Intent intent = new Intent(getContext(), DetailTagihanTeleponRumah.class);
                getContext().startActivity(intent);
            } else if (inputNoTeleponRumah.getText().toString().equals("")) {
                inputNoTeleponRumah.setError("Mohon isi nomor telepon anda");
            } else {
                //Toast.makeText(ActivityPascaBayarIndosat.this, "Data nomor tidak ditemukan", Toast.LENGTH_SHORT).show();
                inputNoTeleponRumah.setError("Data nomor tidak ditemukan");
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
