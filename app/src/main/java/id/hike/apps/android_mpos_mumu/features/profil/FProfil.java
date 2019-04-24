package id.hike.apps.android_mpos_mumu.features.profil;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseFragment;
import id.hike.apps.android_mpos_mumu.util.ApiClient;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.util.MyUtils;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FProfil extends BaseFragment {

    public FProfil() {
        // Required empty public constructor
    }

    TextView tvNama, tvLokasi, tvLoginTerakhir, tvBergabung, tvNotel, tvEmail;
    CircleImageView circleImageView;

    Button btnModal;

    @Inject
    ProfilTest profilTest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fprofil, container, false);
        ProfilComponent component=DaggerProfilComponent.create();
        component.inject(getActivity());

        ((Profil) getActivity()).setTitle(getString(R.string.title_profil));
        tvNama = (TextView) view.findViewById(R.id.tvNamaKasir);
        tvLokasi = (TextView) view.findViewById(R.id.tvLokasi);
        tvLoginTerakhir = (TextView) view.findViewById(R.id.tvLoginTerakhir);
        tvBergabung = (TextView) view.findViewById(R.id.tvBergabung);
        tvNotel = (TextView) view.findViewById(R.id.tvNotel);
        tvEmail = (TextView) view.findViewById(R.id.tvEmail);
        circleImageView = (CircleImageView) view.findViewById(R.id.btnTakeFoto);

        btnModal = (Button) view.findViewById(R.id.btnModal);
        btnModal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layChange, new FModal()).addToBackStack("").commit();
            }
        });

        Button btnSetor = (Button) view.findViewById(R.id.btnSetor);
        btnSetor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layChange, new FSetor()).addToBackStack("").commit();
            }
        });

        getProfil();
        return view;
    }

    void getProfil() {
        SvProfil svProfil = ApiClient.serviceGenerator(Cfg.BASEURL_IDENTITY).create(SvProfil.class);
        Call<ModelProfil> call = svProfil.getProfil(getFragSecPrefs().getInt(Cfg.prefsKasirId_INT, 0));
        call.enqueue(new Callback<ModelProfil>() {
            @Override
            public void onResponse(Call<ModelProfil> call, Response<ModelProfil> response) {
                ModelProfil modelProfil = response.body();
                tvNama.setText(modelProfil.getData().getEmployeeName());
                tvLokasi.setText(modelProfil.getData().getAddress());
                tvLoginTerakhir.setText(modelProfil.getData().getLastUpdate() != null ? ": " + UnitConversion.ConvertMilliSecondsToTime2(modelProfil.getData().getLastUpdate()) : ": -");
                tvBergabung.setText(modelProfil.getData().getCreatedDate() != null ? ": " + modelProfil.getData().getCreatedDate() : ": -");
                tvNotel.setText(": " + modelProfil.getData().getPhone());
                tvEmail.setText(": " + modelProfil.getData().getEmail());

                Picasso.get().load(Cfg.PROFIL_IMAGEURL + modelProfil.getData().getUrlAvatar()).into(circleImageView);

                getFragSecPrefs().edit().putString(Cfg.prefsUrlAvatarKasir_Str, modelProfil.getData().getUrlAvatar()).apply();
                getFragSecPrefs().edit().putString(Cfg.prefsAlamatKasir_Str, modelProfil.getData().getAddress()).apply();
                getFragSecPrefs().edit().putString(Cfg.prefsGenderKasir_Str, modelProfil.getData().getGender()).apply();
                getFragSecPrefs().edit().putString(Cfg.prefsPinKasir_Str, modelProfil.getData().getPin()).apply();
                getFragSecPrefs().edit().putString(Cfg.prefsTeleponKasir_Str, modelProfil.getData().getPhone()).apply();
                getFragSecPrefs().edit().putString(Cfg.prefsKasirEmail_Str, modelProfil.getData().getEmail()).apply();
                getFragSecPrefs().edit().putString(Cfg.prefsRealEmployeeName_Str, modelProfil.getData().getEmployeeName()).apply();
            }

            @Override
            public void onFailure(Call<ModelProfil> call, Throwable t) {
                Toast.makeText(getActivity(), MyUtils.getThisMethodNameForError(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ((Profil)getActivity()).showBtnEdit(true);
        getProfil();
    }
}