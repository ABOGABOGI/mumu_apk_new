package id.hike.apps.android_mpos_mumu.features.pelanggan;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseDialogFragment;
import id.hike.apps.android_mpos_mumu.features.summary.SummaryActivity;
import id.hike.apps.android_mpos_mumu.util.ApiClient;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.util.MyUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by getwiz on 18/05/17.
 */

public class DFTambahPelanggan extends BaseDialogFragment {

    RecyclerView recyclerView;
    EditText edUsername, edEmail, edPhone;

    String origin = "";

    SharedPreferences secPrefs;

    Context savedContext;
    Context mCtx;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogStyle2);
        View view = getActivity().getLayoutInflater().inflate(R.layout.df_tambah_pelanggan, null);
        savedContext = getContext();

        edUsername = (EditText) view.findViewById(R.id.edUsername);
        edEmail = (EditText) view.findViewById(R.id.edEmail);
        edPhone = (EditText) view.findViewById(R.id.edPhone);

        origin = getTag();

        Button btnSimpan = (Button) view.findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edUsername.getText().toString().isEmpty() || edEmail.getText().toString().isEmpty() || edPhone.getText().toString().isEmpty()) {
                    Toast.makeText(savedContext, getString(R.string.mohon_lengkapi_form), Toast.LENGTH_SHORT).show();
                    return;
                }

                String phone = edPhone.getText().toString();
                final String PHONE_REGEX = "^\\w{10,25}$";
                final Pattern patternPhone = Pattern.compile(PHONE_REGEX);
                final Matcher matcherPhone = patternPhone.matcher(phone);
                String email = edEmail.getText().toString();

                if (!matcherPhone.matches()) {
                    Toast.makeText(savedContext, getString(R.string.nomor_tidak_valid), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!MyUtils.isValidEmail(email)) {
                    Toast.makeText(savedContext, getString(R.string.email_tidak_valid), Toast.LENGTH_SHORT).show();
                    return;
                }
                /*if (!matcherEmail.matches()){
                    Toast.makeText(savedContext, "Email tidak valid", Toast.LENGTH_SHORT).show();
                    return;
                }*/


                saveCostumer();
            }
        });

        FrameLayout btnBatal = (FrameLayout) view.findViewById(R.id.btnBatal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    void saveCostumer() {
        dfLoading.show(getFragmentManager(),"");
        SvPelanggan menulist = ApiClient.serviceGenerator(Cfg.BASEURL_IDENTITY).create(SvPelanggan.class);
        Call<ModelAddPelanggan> call = menulist.tambahKostumer(
                getDFSecPref().getString(Cfg.prefsOutletId_STR, null),
                new RqPelanggan.RqTambahPelanggan(
                        edPhone.getText().toString(),
                        edUsername.getText().toString(),
                        edEmail.getText().toString(),
                        getDFSecPref().getString(Cfg.prefsNamaKasir_STR, Cfg.defaultKasirName)));

        call.enqueue(new Callback<ModelAddPelanggan>() {
            @Override
            public void onResponse(Call<ModelAddPelanggan> call, Response<ModelAddPelanggan> response) {
                dfLoading.dismiss();
                if (response.isSuccessful()) {
                    ModelAddPelanggan mm = response.body();

                    if (mm.getStatus()==false){
                        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.pelanggan_dan_email_sudah_ada), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //asal dari landing page
                    if (origin.equalsIgnoreCase("landingPage")) {
                        getDFSecPref().edit().putLong(Cfg.prefsKostumerId_Long, mm.getmCustomerId()).apply();
                        getDFSecPref().edit().putString(Cfg.prefsKostumerName_STR, edUsername.getText().toString()).apply();
                        getDFSecPref().edit().putString(Cfg.prefsTeleponKostumerStr, edPhone.getText().toString()).apply();
                        getDFSecPref().edit().putString(Cfg.prefsEmailKostumer_Str, edEmail.getText().toString()).apply();
                        getNewTransactionId();
                    } //asal dari summary
                    else if (origin.equalsIgnoreCase("summary")) {
                        getDFSecPref().edit().putLong(Cfg.prefsKostumerId_Long, mm.getmCustomerId()).apply();

                        getDFSecPref().edit().putString(Cfg.prefsTeleponKostumerStr, edPhone.getText().toString()).apply();
                        getDFSecPref().edit().putString(Cfg.prefsKostumerName_STR, edUsername.getText().toString()).apply();
                        getDFSecPref().edit().putString(Cfg.prefsEmailKostumer_Str, edEmail.getText().toString()).apply();
                        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.pelanggan_telah_ditambah), Toast.LENGTH_SHORT).show();
                        SummaryActivity summaryActivity = (SummaryActivity) getActivity();
                        summaryActivity.dbTransaction.updateKostumer(edUsername.getText().toString(), mm.getData(), getDFSecPref().getString(Cfg.prefsRecentTransIdStr, null));

                        summaryActivity.actvCariPelanggan.setText(edUsername.getText().toString() + " (" + edPhone.getText().toString() + ")");
                        summaryActivity.actvCariPelanggan.setEnabled(false);
                        dismiss();
                    } else if (origin.equalsIgnoreCase("tambahPelanggan")){
                        // ini dari halaman pelanggan
                        Pelanggan pelanggan= (Pelanggan) mCtx;
                        pelanggan.reloadActivityFromHalamanPelanggan();
                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }

            @Override
            public void onFailure(Call<ModelAddPelanggan> call, Throwable t) {
                dfLoading.dismiss();
                dismiss();
                Toast.makeText(getActivity().getApplicationContext(), t.getCause().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getNewTransactionId() {
        dfLoading.show(getFragmentManager(), "");
//        LandingPageService landingPageService = ApiClient.serviceGenerator(Cfg.BASEURL_TRANSAKSI).create(LandingPageService.class);
//        List<TrSalesItemBukaTransaksi> trSalesItemBukaTransaksi = new ArrayList<>();
//        trSalesItemBukaTransaksi.add(new TrSalesItemBukaTransaksi(
//                getDFSecPref().getString(Cfg.prefsOutletId_STR, "0"),
//                getDFSecPref().getLong(Cfg.prefsKostumerId_Long, Cfg.defaultKostumerId),
//                String.valueOf(getDFSecPref().getLong(Cfg.prefsKasirId_INT, Cfg.defaultKasirId))
//        ));
//        Call<ModelBukaTransaksi> call = landingPageService.bukaTransaksi(new ReqBukaTransaksi(trSalesItemBukaTransaksi));
//        call.enqueue(new Callback<ModelBukaTransaksi>() {
//            @Override
//            public void onResponse(Call<ModelBukaTransaksi> call, Response<ModelBukaTransaksi> response) {
//                dfLoading.dismiss();
//                if (response.isSuccessful()) {
//                    ModelBukaTransaksi mm = response.body();
//                    if (origin.equalsIgnoreCase("landingPage")) {
//
//                        ((LandingPage) savedContext).getSecPref().edit().putString(Cfg.prefsRecentTransIdStr, String.valueOf(mm.getTransid())).apply();
//
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                        Date resultdate = new Date(mm.getTransid());
//
//                        ((LandingPage) savedContext).getSecPref().edit().putString(Cfg.prefsTransDate_STR, sdf.format(resultdate)).apply();
//
//                        Intent intent = new Intent(savedContext, Home.class);
//                        savedContext.startActivity(intent);
////                        Toast.makeText(savedContext.getApplicationContext(), String.valueOf(mm.getTransid()), Toast.LENGTH_SHORT).show();
//                    } else if (origin.equalsIgnoreCase("summary")) {
//                        // nothing
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ModelBukaTransaksi> call, Throwable t) {
//                dfLoading.dismiss();
//                Log.d(this.getClass().getSimpleName(), t.getCause().toString());
//            }
//        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCtx=context;
    }
}
