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
public class FSetor extends BaseFragment {

    public FSetor() {
        // Required empty public constructor
    }

    TextView tvBalanceSaatIni, tvbeginningBalance;
    EditText etJumlahLain;
    Button btnSetor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fsetor, container, false);

        ((Profil) getActivity()).setTitle(getString(R.string.title_setor));
        tvBalanceSaatIni = (TextView) view.findViewById(R.id.tvBalanceSaatIni);
        tvbeginningBalance = (TextView) view.findViewById(R.id.tvBeginningBalance);
        etJumlahLain = (EditText) view.findViewById(R.id.etJumlahLain);

        etJumlahLain.addTextChangedListener(new FormatCurrencyEditInCursor(etJumlahLain));

        btnSetor = (Button) view.findViewById(R.id.btnSetor);
        dfLoading.show(getFragmentManager(), "");
        modalSummaryTotal();
        modalTerima();
        ((Profil) getActivity()).showBtnEdit(false);
        btnSetor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etJumlahLain.getText().toString().isEmpty() ||
                        getRawValue(etJumlahLain) < 100) {
                    /*etJumlahLain.setText("100");
                    etJumlahLain.setSelection(3);*/
                    Toast.makeText(getActivity(), getString(R.string.input_kurang_dari_100), Toast.LENGTH_SHORT).show();
                } else {
                    modalSetor();
                }
            }
        });
        return view;
    }

    long balanceSaatIni = 0;

    public void modalSummaryTotal() {
        SvProfil svProfil = ApiClient.serviceGenerator(Cfg.BASEURL_MODAL).create(SvProfil.class);
        Call<ResponseSummaryTotal> call = svProfil.summaryTotal(getFragSecPrefs().getString(Cfg.prefsOutletId_STR, ""));
        call.enqueue(new Callback<ResponseSummaryTotal>() {
            @Override
            public void onResponse(Call<ResponseSummaryTotal> call, Response<ResponseSummaryTotal> response) {
                ResponseSummaryTotal rpsSummaryTotal = response.body();
                balanceSaatIni = rpsSummaryTotal.getData();
                tvBalanceSaatIni.setText(UnitConversion.format2Rupiah(
                        rpsSummaryTotal.getData())
                );

                if (rpsSummaryTotal.getModalAwalHarian() != null) {
                    tvbeginningBalance.setText(UnitConversion.format2Rupiah(
                            Math.round(Double.parseDouble(rpsSummaryTotal.getModalAwalHarian()))));
                } else {
                    tvbeginningBalance.setText(
                            UnitConversion.format2Rupiah(0L)
                    );
                }
            }

            @Override
            public void onFailure(Call<ResponseSummaryTotal> call, Throwable t) {

            }
        });
    }

    ResponseModalTerima rmt;

    public void modalTerima() {
        SvProfil svProfil = ApiClient.serviceGenerator(Cfg.BASEURL_MODAL).create(SvProfil.class);
        Call<ResponseModalTerima> call = svProfil.modalTerima(getFragSecPrefs().getString(Cfg.prefsOutletId_STR, ""));
        call.enqueue(new Callback<ResponseModalTerima>() {
            @Override
            public void onResponse(Call<ResponseModalTerima> call, Response<ResponseModalTerima> response) {
                dfLoading.dismiss();
                ResponseModalTerima responseModalTerima = response.body();
                if (!responseModalTerima.getData().isEmpty()) {
                    rmt = responseModalTerima;
                    btnSetor.setEnabled(false);
                    btnSetor.setBackground(getResources().getDrawable(R.drawable.gray_button));
                } else {
                    btnSetor.setEnabled(true);
                    btnSetor.setBackground(getResources().getDrawable(R.drawable.btn_style));
                }
            }

            @Override
            public void onFailure(Call<ResponseModalTerima> call, Throwable t) {
                Toast.makeText(getActivity(), Cfg.ERR500, Toast.LENGTH_SHORT).show();
                dfLoading.dismiss();
            }
        });
    }

    //jika rmt kosong, maka tidak bisa modal setor
    public void modalSetor() {
        dfLoading.show(getFragmentManager(), "");
        List<ImmodalItem> immodalItems = new ArrayList<>();
        immodalItems.add(new ImmodalItem(
                getFragSecPrefs().getLong(Cfg.prefsModalId_Long, 0L),
                getRawValue(etJumlahLain),
                getFragSecPrefs().getString(Cfg.prefsNamaKasir_STR, "")
        ));
        SvProfil svProfil = ApiClient.serviceGenerator(Cfg.BASEURL_MODAL).create(SvProfil.class);
        Call<ResponseModalSetor> call = svProfil.modalSetor(new ReqModalSetor(immodalItems));
        call.enqueue(new Callback<ResponseModalSetor>() {
            @Override
            public void onResponse(Call<ResponseModalSetor> call, Response<ResponseModalSetor> response) {
                dfLoading.dismiss();
                btnSetor.setEnabled(false);
                btnSetor.setBackground(getResources().getDrawable(R.drawable.gray_button));
                Toast.makeText(getActivity(), getString(R.string.setor_modal_berhasil), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseModalSetor> call, Throwable t) {
                dfLoading.dismiss();
                btnSetor.setEnabled(true);
                btnSetor.setBackground(getResources().getDrawable(R.drawable.btn_style));
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
