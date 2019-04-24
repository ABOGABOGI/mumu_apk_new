package id.hike.apps.android_mpos_mumu.features.wakaf;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;


public class PembayaranWakaf extends BaseActivity {
    private final String LOG = "BAKKA";
    private final Context context = this;
    Button button_proses_wakaf;
    EditText editTextWakaf, txtWakafSecondNominal,etNamaMuzaki,etNamaOrang,etNohp,spinnerDonasiOption;
    private TextView txtWakafSaldo;
    private TextView saldoDompet;
    private CheckBox checkBoxBerwakaf;

    private ImageButton btnSepuluh,btnDuapuluh,btnDualima,btnLimapuluh,btnSeratus,btnLimaratus,btnSatujuta;
    private EditText etNominal;
    private ImageView back_btn_wakaf_bayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_wakaf);

        btnSepuluh = findViewById(R.id.btnSepuluh);
        btnDuapuluh = findViewById(R.id.btnDuapuluh);
        btnDualima = findViewById(R.id.btnDualima);
        btnLimapuluh = findViewById(R.id.btnLimapuluh);
        btnSeratus = findViewById(R.id.btnSeratus);
        btnLimaratus = findViewById(R.id.btnLimaratus);
        btnSatujuta = findViewById(R.id.btnSatujuta);
        etNominal = findViewById(R.id.txtWakafSecondNominal);
        back_btn_wakaf_bayar = findViewById(R.id.back_btn_wakaf_bayar);

        button_proses_wakaf = findViewById(R.id.button_proses_wakaf);
        editTextWakaf = findViewById(R.id.editTextWakaf);
        txtWakafSaldo = findViewById(R.id.textView35);
        txtWakafSecondNominal = findViewById(R.id.txtWakafSecondNominal);
        checkBoxBerwakaf = findViewById(R.id.check_berwakaf);
        etNamaMuzaki = findViewById(R.id.editText_nama);
        etNamaOrang = findViewById(R.id.editText_namaOranglain);
        etNohp = findViewById(R.id.editText_noHp_wakaf);
        spinnerDonasiOption = findViewById(R.id.editTextWakaf);

        saldoDompet = findViewById(R.id.textView34);

        saldoDompet.setText(getResources().getString(R.string.dompet_name, getString(R.string.app_name)));


        editTextWakaf.setEnabled(false);

        etNamaOrang.setVisibility(View.GONE);
        etNohp.setVisibility(View.GONE);


        int saldo = getSecPref().getInt(Cfg.prefsWalletSaldo, 0);
        txtWakafSaldo.setText("Rp. " + UnitConversion.format2Rupiah2(saldo));

        checkBoxBerwakaf.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBoxBerwakaf.isChecked()){
                    etNamaMuzaki.setEnabled(false);
                    etNamaOrang.setVisibility(View.VISIBLE);
                    etNohp.setVisibility(View.VISIBLE);
                }else {
                    etNamaMuzaki.setEnabled(true);
                    etNamaOrang.setVisibility(View.GONE);
                    etNohp.setVisibility(View.GONE);
                }
            }
        });

        back_btn_wakaf_bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // button Menu untuk memilih nominal
        btnSepuluh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etNominal.setText("10000");
            }
        });

        btnDuapuluh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etNominal.setText("20000");
            }
        });

        btnDualima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etNominal.setText("25000");
            }
        });

        btnLimapuluh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etNominal.setText("50000");
            }
        });

        btnSeratus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etNominal.setText("100000");
            }
        });

        btnLimaratus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etNominal.setText("500000");
            }
        });

        btnSatujuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etNominal.setText("10000000");
            }
        });

//        editTextWakaf.setText(getIntent().getStringExtra("name"));
        Intent intent = getIntent();
        String str = intent.getStringExtra("name");
        Log.d("BAKKA", "getting extra: " + intent.getStringExtra("name"));
        editTextWakaf.setText(str);

        button_proses_wakaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PembayaranWakaf.this, AkadActivity.class);
                try {
                    SharedPreferences.Editor secPref = getSecPref().edit();
                    secPref.putString(Cfg.prefDonasiType, "WAKAF");
                    secPref.putString(Cfg.prefDonasiProgram, spinnerDonasiOption.getText().toString()/*spinnerDonasiOption.getSelectedItem().toString()*/);
                    String muzaki = etNamaMuzaki.getText().toString();
                    //Log.i("DEBUGTAG","muzaki1");
                    //Log.i("DEBUGTAG",muzaki);
                    //Log.i("DEBUGTAG",Boolean.toString(checkBoxBerwakaf.isChecked()));
                    if (checkBoxBerwakaf.isChecked()) {
                        muzaki = etNamaOrang.getText().toString();
                        //Log.i("DEBUGTAG",muzaki);
                        secPref.putString(Cfg.prefDonasiPhone, etNohp.getText().toString());
                    }
                    secPref.putString(Cfg.prefDonasiMuzakki, muzaki);
                    Cfg.gs_WakafInfo_Muzakki = muzaki;
                    secPref.putInt(Cfg.prefDonasiAmount, Integer.parseInt(txtWakafSecondNominal.getText().toString()));
                    secPref.apply();

                } catch (NullPointerException | NumberFormatException ex) {
                    Log.e(LOG, ex.getMessage(), ex);
                    Toast.makeText(context, getString(R.string.input_error), Toast.LENGTH_LONG).show();
                }
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
