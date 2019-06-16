package id.hike.apps.android_mpos_mumu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;

public class KonfirmasiPembayaranByQrCode extends BaseActivity {

    TextView jumlahBayar, sisaSaldo, tanggalTransaksi, waktuTransaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_pembayaran_by_qr_code);

        jumlahBayar = findViewById(R.id.jumlahBayar);
        sisaSaldo = findViewById(R.id.sisaSaldo);
        tanggalTransaksi = findViewById(R.id.tanggalTransaksi);
        waktuTransaksi = findViewById(R.id.waktuTransaksi);

        tanggalTransaksi.setText(getTanggal());
        waktuTransaksi.setText(getWaktu());

        jumlahBayar.setText("Rp" + " " + UnitConversion.format2Rupiah2(getSecPref().getString(Cfg.JUMLAH_BAYAR_BY_QR_CODE, Cfg.JUMLAH_BAYAR_BY_QR_CODE)));
        sisaSaldo.setText("Rp" + " " + UnitConversion.format2Rupiah2(getSecPref().getString(Cfg.SISA_SALDO, Cfg.SISA_SALDO)));
    }

    private String getTanggal() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private String getWaktu() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
