package id.hike.apps.android_mpos_mumu.features.profil;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseFragment;
import id.hike.apps.android_mpos_mumu.util.ApiClient;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.util.FormatCurrencyEditInCursor;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FModal extends BaseFragment {

    public FModal() {
        // Required empty public constructor
    }

    DataItem reqModelTerima;
    TextView tvBeginningBalance, tvRemain;
    EditText etJumlahLain;
    Profil A_Profil;
    Button btnTerima;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f_modal, container, false);
        A_Profil = (Profil) getActivity();
        btnTerima = (Button) view.findViewById(R.id.btnTerima);
        tvRemain = (TextView) view.findViewById(R.id.tvRemain);
        A_Profil.setTitle(getString(R.string.title_modal));
        etJumlahLain = (EditText) view.findViewById(R.id.etJumlahLain);

        etJumlahLain.addTextChangedListener(new FormatCurrencyEditInCursor(etJumlahLain));

        tvBeginningBalance = (TextView) view.findViewById(R.id.tvBeginningBalance);
        modalTerima();
        btnTerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etJumlahLain.getText().toString().isEmpty() ||
                        getRawValue(etJumlahLain) < 100) {
                    Toast.makeText(A_Profil, getString(R.string.input_kurang_dari_100), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    konfirmasiModalTerima();
                }
            }
        });
        A_Profil.showBtnEdit(false);

        return view;
    }

    public void modalTerima() {
        dfLoading.show(getFragmentManager(), "");
        SvProfil svProfil = ApiClient.serviceGenerator(Cfg.BASEURL_MODAL).create(SvProfil.class);
        Call<ResponseModalTerima> call = svProfil.modalTerima(getFragSecPrefs().getString(Cfg.prefsOutletId_STR, ""));
        call.enqueue(new Callback<ResponseModalTerima>() {
            @Override
            public void onResponse(Call<ResponseModalTerima> call, Response<ResponseModalTerima> response) {
                dfLoading.dismiss();
                ResponseModalTerima responseModalTerima = response.body();
                if (!responseModalTerima.getData().isEmpty()) {
                    btnTerima.setEnabled(true);
                    if (responseModalTerima.getData().get(0).getNominalTerima() != null) {
                        tvRemain.setText(
                                UnitConversion.format2Rupiah(responseModalTerima.getData().get(0).getNominalTerima())
                        );
                    } else {
                        tvRemain.setText(UnitConversion.format2Rupiah(0L));
                    }

                    tvBeginningBalance.setText(
                            UnitConversion.format2Rupiah(responseModalTerima.getData().get(0).getNominalKirim())
                    );

                    reqModelTerima = responseModalTerima.getData().get(0);
                    btnTerima.setBackground(getResources().getDrawable(R.drawable.btn_style));

                    getFragSecPrefs().edit().putLong(Cfg.prefsModalId_Long, reqModelTerima.getIdModal()).apply();

                } else {
                    tvRemain.setText(
                            UnitConversion.format2Rupiah(0L)
                    );
                    tvBeginningBalance.setText(UnitConversion.format2Rupiah(0L));
                }
            }

            @Override
            public void onFailure(Call<ResponseModalTerima> call, Throwable t) {
                Toast.makeText(getActivity(), Cfg.ERR500, Toast.LENGTH_SHORT).show();
                btnTerima.setClickable(false);
                dfLoading.dismiss();
            }
        });
    }

    public void konfirmasiModalTerima() {
        List<ImmodalKonfirmTerima> immodal = new ArrayList<>();
        immodal.add(new ImmodalKonfirmTerima(
                getFragSecPrefs().getString(Cfg.prefsNamaKasir_STR, Cfg.defaultKasirName),
                reqModelTerima.getIdModal(),
                getRawValue(etJumlahLain),
                reqModelTerima.getCreatedBy(),
                Integer.parseInt(getFragSecPrefs().getString(Cfg.prefsOutletId_STR, ""))
        ));

        SvProfil svProfil = ApiClient.serviceGenerator(Cfg.BASEURL_MODAL).create(SvProfil.class);
        Call<ResponseModalKonfirmTerima> call = svProfil.modalKonfirmTerima(new ReqModalKonfirmTerima(
                immodal
        ));

        call.enqueue(new Callback<ResponseModalKonfirmTerima>() {
            @Override
            public void onResponse(Call<ResponseModalKonfirmTerima> call, Response<ResponseModalKonfirmTerima> response) {
                ResponseModalKonfirmTerima responseModalKonfirmTerima = response.body();
                tvRemain.setText(UnitConversion.format2Rupiah(Long.parseLong(
                        etJumlahLain.getText().toString())));
                Toast.makeText(getActivity(), getString(R.string.konfirmasi_terima_modal_sukses), Toast.LENGTH_SHORT).show();

                btnTerima.setEnabled(false);
                btnTerima.setBackground(getResources().getDrawable(R.drawable.gray_button));
            }

            @Override
            public void onFailure(Call<ResponseModalKonfirmTerima> call, Throwable t) {
                Toast.makeText(getActivity(), Cfg.ERR500, Toast.LENGTH_SHORT).show();
            }
        });
    }

    Long getRawValue(EditText et){
        String inputDiskon = et.getText().toString().isEmpty() ||
                et.getText().toString().equalsIgnoreCase("null") ? "0" : et.getText().toString();
        inputDiskon = inputDiskon.replaceFirst("^0+(?!$)", "");

        if (inputDiskon.contains(".")) {
            inputDiskon = inputDiskon.replaceAll("\\.", "");
        }

        return Long.parseLong(inputDiskon);
    }
}
