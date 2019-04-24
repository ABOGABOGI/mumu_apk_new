package id.hike.apps.android_mpos_mumu.features.donasi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;

public class DonasiSecondActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    private final Context context = this;
    private Spinner spinnerDonasi,spinnerDonasiOption;
    private String[] donasiList = {"Infak/Sedekah","Kemanusiaan"};
    private EditText etNamaMuzaki,etNamaOrang,etNohp,txtDonasiSecondNominal;
    private CheckBox checkBoxBerdonasi;
    private Button btnProsesDonasi;
    private ImageButton btnSepuluh,btnDuapuluh,btnDualima,btnLimapuluh,btnSeratus,btnLimaratus,btnSatujuta;
    private EditText etNominal;
    private TextView txtSaldo;
    private ImageView back_btn_donasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi_second);

        btnSepuluh = findViewById(R.id.btnSepuluh);
        btnDuapuluh = findViewById(R.id.btnDuapuluh);
        btnDualima = findViewById(R.id.btnDualima);
        btnLimapuluh = findViewById(R.id.btnLimapuluh);
        btnSeratus = findViewById(R.id.btnSeratus);
        btnLimaratus = findViewById(R.id.btnLimaratus);
        btnSatujuta = findViewById(R.id.btnSatujuta);
        etNominal = findViewById(R.id.txtDonasiSecondNominal);
        back_btn_donasi = findViewById(R.id.back_btn_donasi);

        etNamaMuzaki = findViewById(R.id.editText_nama);
        etNamaOrang = findViewById(R.id.editText_namaOranglain);
        etNohp = findViewById(R.id.editText_noHp_donasi);
        checkBoxBerdonasi = findViewById(R.id.check_berdonasi);
        spinnerDonasi = findViewById(R.id.spinner_donasi);
        spinnerDonasiOption = findViewById(R.id.spinner_donasi_option);
        btnProsesDonasi = findViewById(R.id.button_proses_donasi);
        txtDonasiSecondNominal = findViewById(R.id.txtDonasiSecondNominal);
        txtSaldo = findViewById(R.id.textView35);

        back_btn_donasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnProsesDonasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAkadDonasi = new Intent(DonasiSecondActivity.this,AkadDonasiActivity.class);

                try {
                    SharedPreferences.Editor secPref = getSecPref().edit();
                    secPref.putString(Cfg.prefDonasiType, spinnerDonasi.getSelectedItem().toString());
                    secPref.putString(Cfg.prefDonasiProgram, spinnerDonasiOption.getSelectedItem().toString());
                    String muzaki = etNamaMuzaki.getText().toString();
                    if (checkBoxBerdonasi.isActivated()) {
                        muzaki = etNamaOrang.getText().toString();
                        secPref.putString(Cfg.prefDonasiPhone, etNohp.getText().toString());
                    }
                    secPref.putString(Cfg.prefDonasiMuzakki, muzaki);
                    secPref.putInt(Cfg.prefDonasiAmount, Integer.parseInt(txtDonasiSecondNominal.getText().toString()));
                    secPref.apply();

                    validateSaldoAndGo(goToAkadDonasi);
                } catch (NullPointerException | NumberFormatException ex) {
                    Toast.makeText(context, getString(R.string.input_error), Toast.LENGTH_LONG).show();
                }
            }
        });

        ArrayAdapter<String> donasiAdapter = new ArrayAdapter<>(DonasiSecondActivity.this, android.R.layout.simple_list_item_1, donasiList);
        donasiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDonasi.setAdapter(donasiAdapter);

        spinnerDonasi.setOnItemSelectedListener(this);

        etNamaOrang.setVisibility(View.GONE);
        etNohp.setVisibility(View.GONE);

        checkBoxBerdonasi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBoxBerdonasi.isChecked()){
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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String spDonasi = String.valueOf(spinnerDonasi.getSelectedItem());
        if(spDonasi.contentEquals("Infak/Sedekah")){
            List<String> list = new ArrayList<String>();
            list.add("Pendidikan");
            list.add("Kesehatan");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            spinnerDonasiOption.setAdapter(dataAdapter);
        }
        if(spDonasi.contentEquals("Kemanusiaan")){
            List<String> list = new ArrayList<String>();
            list.add("Sekolah untuk Sulteng dan Lombok");
            list.add("Air bersih untuk Sulteng dan Lombok");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            spinnerDonasiOption.setAdapter(dataAdapter);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void validateSaldoAndGo(final Intent intent) {
        dfLoading.show(getSupportFragmentManager(), "");
        dfLoading.setCancelable(false);

        int donationAmount = Integer.parseInt(txtDonasiSecondNominal.getText().toString());
        final long totalDonationAmount = 2500 + donationAmount;
//
    }
}
