package id.hike.apps.android_mpos_mumu.features.home.df;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseDialogFragment;
import id.hike.apps.android_mpos_mumu.features.home.MainPPOBFragment;
import id.hike.apps.android_mpos_mumu.features.home.Home;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;

/**
 * Created by getwiz on 18/05/17.
 */

public class DFDetailProdukPPOB extends BaseDialogFragment {

    Context mContext;
    Bundle dataProduk;
    Home activityHome;
    MainPPOBFragment fragPPOB;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.TransactionListDialog);
        View view = null;
        dataProduk=getArguments();
        TextView tvIdPelanggan, tvNama, tvTarif, tvKwh, tvAlamat, tvTagihan, tvBiayaAdmin, tvTotal;
        TextView tvTokenPln;
        TextView tvPeriode, tvDenda;
        Button btnBayar;

        switch (getTag()) {
            case Cfg.TAG_FRAG_PPOB_LISTRIK_POSTPAID:
                view = getActivity().getLayoutInflater().inflate(R.layout.f_dp_pln_postpaid, null);
                tvIdPelanggan = (TextView) view.findViewById(R.id.tvIdPelanggan);
                tvNama = (TextView) view.findViewById(R.id.tvNama);
                tvTarif = (TextView) view.findViewById(R.id.tvTarif);
                tvKwh = (TextView) view.findViewById(R.id.tvKwh);
                tvAlamat = (TextView) view.findViewById(R.id.tvAlamat);
                tvTagihan = (TextView) view.findViewById(R.id.tvTagihan);
                tvBiayaAdmin = (TextView) view.findViewById(R.id.tvBiayaAdmin);
                tvTotal= (TextView) view.findViewById(R.id.tvTotal);
                btnBayar = (Button) view.findViewById(R.id.btnBayar);

                tvNama.setText(getDFSecPref().getString(Cfg.prefsKostumerName_STR,Cfg.defaultKostumerName));
                tvIdPelanggan.setText(String.valueOf(getDFSecPref().getLong(Cfg.prefsKostumerId_Long,Cfg.defaultKostumerId)));
                tvBiayaAdmin.setText(UnitConversion.format2Rupiah(dataProduk.getLong(Cfg.BUNDLE_BIAYA_ADMIN_LONG,0L)));
                tvTagihan.setText(UnitConversion.format2Rupiah(dataProduk.getLong(Cfg.BUNDLE_BIAYA_TAGIHAN_LONG,0L)));
                tvTotal.setText(UnitConversion.format2Rupiah(
                        dataProduk.getLong(Cfg.BUNDLE_BIAYA_ADMIN_LONG,0L)+
                                dataProduk.getLong(Cfg.BUNDLE_BIAYA_TAGIHAN_LONG,0L)
                ));
                tvAlamat.setText(getDFSecPref().getString(Cfg.prefsAlamatKasir_Str,Cfg.defaultAlamatKasir));

                btnBayar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
            case Cfg.TAG_FRAG_PPOB_LISTRIK_TOKEN:
                view = getActivity().getLayoutInflater().inflate(R.layout.f_dp_pln_token, null);
                tvIdPelanggan = (TextView) view.findViewById(R.id.tvIdPelanggan);
                tvNama = (TextView) view.findViewById(R.id.tvNama);
                tvTarif = (TextView) view.findViewById(R.id.tvTarif);
                tvKwh = (TextView) view.findViewById(R.id.tvKwh);
                tvTokenPln = (TextView) view.findViewById(R.id.tvTokenPln);
                tvAlamat = (TextView) view.findViewById(R.id.tvAlamat);
                btnBayar = (Button) view.findViewById(R.id.btnBayar);
                tvTagihan = (TextView) view.findViewById(R.id.tvTagihan);
                tvTotal= (TextView) view.findViewById(R.id.tvTotal);

                tvTokenPln.setText(UnitConversion.format2Rupiah(dataProduk.getLong(Cfg.BUNDLE_BIAYA_ADMIN_LONG)+dataProduk.getLong(Cfg.BUNDLE_BIAYA_TAGIHAN_LONG)));
                tvNama.setText(getDFSecPref().getString(Cfg.prefsKostumerName_STR,Cfg.defaultKostumerName));
                tvIdPelanggan.setText(String.valueOf(getDFSecPref().getLong(Cfg.prefsKostumerId_Long,Cfg.defaultKostumerId)));
                tvTotal.setText(UnitConversion.format2Rupiah(
                        dataProduk.getLong(Cfg.BUNDLE_BIAYA_ADMIN_LONG,0L)+
                                dataProduk.getLong(Cfg.BUNDLE_BIAYA_TAGIHAN_LONG,0L)
                ));
                tvAlamat.setText(getDFSecPref().getString(Cfg.prefsAlamatKasir_Str,Cfg.defaultAlamatKasir));

                btnBayar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
            case Cfg.TAG_FRAG_PPOB_TV:
                view = getActivity().getLayoutInflater().inflate(R.layout.f_dp_tv, null);
                tvIdPelanggan = (TextView) view.findViewById(R.id.tvIdPelanggan);
                tvNama = (TextView) view.findViewById(R.id.tvNama);
                tvTagihan = (TextView) view.findViewById(R.id.tvTagihan);
                tvDenda = (TextView) view.findViewById(R.id.tvDenda);
                tvBiayaAdmin = (TextView) view.findViewById(R.id.tvBiayaAdmin);
                tvPeriode = (TextView) view.findViewById(R.id.tvPeriode);
                tvTotal = (TextView) view.findViewById(R.id.tvTotalHarga);

                btnBayar = (Button) view.findViewById(R.id.btnBayar);
                btnBayar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
        }

        builder.setView(view);
        return builder.create();
    }




    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activityHome = ((Home)activity);
            fragPPOB = (MainPPOBFragment) activityHome.getSupportFragmentManager().findFragmentById(R.id.transLayout);
    }
}
