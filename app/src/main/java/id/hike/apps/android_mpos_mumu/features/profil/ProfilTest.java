package id.hike.apps.android_mpos_mumu.features.profil;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.util.ApiClient;
import id.hike.apps.android_mpos_mumu.util.MyUtils;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 12/10/17.
 */

public class ProfilTest {

    SharedPreferences secPrefs;
    FProfil fProfil;
    Context context;

    public ProfilTest() {
    }

    void getProfil() {
        SvProfil svProfil = ApiClient.serviceGenerator(Cfg.BASEURL_IDENTITY).create(SvProfil.class);
        Call<ModelProfil> call = svProfil.getProfil(secPrefs.getInt(Cfg.prefsKasirId_INT, 0));
        call.enqueue(new Callback<ModelProfil>() {
            @Override
            public void onResponse(Call<ModelProfil> call, Response<ModelProfil> response) {
                ModelProfil modelProfil = response.body();
                fProfil.tvNama.setText(modelProfil.getData().getEmployeeName());
                fProfil.tvLokasi.setText(modelProfil.getData().getAddress());
                fProfil.tvLoginTerakhir.setText(modelProfil.getData().getLastUpdate() != null ? ": " + UnitConversion.ConvertMilliSecondsToTime2(modelProfil.getData().getLastUpdate()) : ": -");
                fProfil.tvBergabung.setText(modelProfil.getData().getCreatedDate() != null ? ": " + modelProfil.getData().getCreatedDate() : ": -");
                fProfil.tvNotel.setText(": " + modelProfil.getData().getPhone());
                fProfil.tvEmail.setText(": " + modelProfil.getData().getEmail());

                Picasso.get().load(Cfg.PROFIL_IMAGEURL + modelProfil.getData().getUrlAvatar()).into(fProfil.circleImageView);

                secPrefs.edit().putString(Cfg.prefsUrlAvatarKasir_Str, modelProfil.getData().getUrlAvatar()).apply();
                secPrefs.edit().putString(Cfg.prefsAlamatKasir_Str, modelProfil.getData().getAddress()).apply();
                secPrefs.edit().putString(Cfg.prefsGenderKasir_Str, modelProfil.getData().getGender()).apply();
                secPrefs.edit().putString(Cfg.prefsPinKasir_Str, modelProfil.getData().getPin()).apply();
                secPrefs.edit().putString(Cfg.prefsTeleponKasir_Str, modelProfil.getData().getPhone()).apply();
                secPrefs.edit().putString(Cfg.prefsKasirEmail_Str, modelProfil.getData().getEmail()).apply();
                secPrefs.edit().putString(Cfg.prefsRealEmployeeName_Str, modelProfil.getData().getEmployeeName()).apply();

            }

            @Override
            public void onFailure(Call<ModelProfil> call, Throwable t) {
                Toast.makeText(context, MyUtils.getThisMethodNameForError(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
