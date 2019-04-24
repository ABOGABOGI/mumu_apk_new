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

public class ZakatSecondActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    private final Context context = this;
    private Spinner spinnerZakat,spinnerZakatOptions;
    private String[] zakatList = {"Zakat Maal","Zakat Penghasilan","Fidyah"};
    private EditText etNamaMuzakiZakat,etNamaOrangZakat,etNoZakat;
    private CheckBox checkZakat;
    private Button btnProsesZakat;
    private ImageButton btnSepuluh,btnDuapuluh,btnDualima,btnLimapuluh,btnSeratus,btnLimaratus,btnSatujuta;
    private EditText etNominal;
    private TextView txtSaldo;
    private ImageView back_btn_zakat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakat_second);

        btnSepuluh = findViewById(R.id.btnSepuluh);
        btnDuapuluh = findViewById(R.id.btnDuapuluh);
        btnDualima = findViewById(R.id.btnDualima);
        btnLimapuluh = findViewById(R.id.btnLimapuluh);
        btnSeratus = findViewById(R.id.btnSeratus);
        btnLimaratus = findViewById(R.id.btnLimaratus);
        btnSatujuta = findViewById(R.id.btnSatujuta);
        etNominal = findViewById(R.id.edtNominalZakat);
        back_btn_zakat = findViewById(R.id.back_btn_zakat);

        spinnerZakat = findViewById(R.id.spinner_zakat);
        spinnerZakatOptions = findViewById(R.id.spinner_zakat_options);
        etNamaMuzakiZakat = findViewById(R.id.editText_namaMuzaki_zakat);
        etNamaOrangZakat = findViewById(R.id.editText_namaOrang_zakat);
        etNoZakat = findViewById(R.id.editText_noHp_zakat);
        checkZakat = findViewById(R.id.checkBox_berzakat);
        btnProsesZakat = findViewById(R.id.button_proses_zakat);
        txtSaldo = findViewById(R.id.textView35);


        back_btn_zakat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnProsesZakat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAkadZakat = new Intent(ZakatSecondActivity.this,AkadZakatActivity.class);

                try {
                    SharedPreferences.Editor secPref = getSecPref().edit();
                    secPref.putString(Cfg.prefDonasiType, spinnerZakat.getSelectedItem().toString());
                    secPref.putString(Cfg.prefDonasiProgram, spinnerZakatOptions.getSelectedItem().toString());
                    String muzaki = etNamaMuzakiZakat.getText().toString();
                    if (checkZakat.isActivated()) {
                        muzaki = etNamaOrangZakat.getText().toString();
                        secPref.putString(Cfg.prefDonasiPhone, etNoZakat.getText().toString());
                    }
                    secPref.putString(Cfg.prefDonasiMuzakki, muzaki);
                    secPref.putInt(Cfg.prefDonasiAmount, Integer.parseInt(etNominal.getText().toString()));
                    secPref.apply();

                    validateSaldoAndGo(goToAkadZakat);
                } catch (NullPointerException | NumberFormatException ex) {
                    Toast.makeText(context, getString(R.string.input_error), Toast.LENGTH_LONG).show();
                }
            }
        });

        ArrayAdapter<String> zakatAdapter = new ArrayAdapter<>(ZakatSecondActivity.this, android.R.layout.simple_list_item_1, zakatList);
        zakatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerZakat.setAdapter(zakatAdapter);

        spinnerZakat.setOnItemSelectedListener(this);

        etNamaOrangZakat.setVisibility(View.GONE);
        etNoZakat.setVisibility(View.GONE);

        checkZakat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkZakat.isChecked()){
                    etNamaMuzakiZakat.setEnabled(false);
                    etNamaOrangZakat.setVisibility(View.VISIBLE);
                    etNoZakat.setVisibility(View.VISIBLE);
                }else {
                    etNamaMuzakiZakat.setEnabled(true);
                    etNamaOrangZakat.setVisibility(View.GONE);
                    etNoZakat.setVisibility(View.GONE);
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

        String spZakat = String.valueOf(spinnerZakat.getSelectedItem());
        if(spZakat.contentEquals("Zakat Maal")){
            List<String> list = new ArrayList<String>();
            list.add("Penghasilan");
            list.add("Simpanan");
            list.add("Perdagangan");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            spinnerZakatOptions.setAdapter(dataAdapter);
            spinnerZakatOptions.setVisibility(View.VISIBLE);
        }else{
            spinnerZakatOptions.setVisibility(View.GONE);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void validateSaldoAndGo(final Intent intent) {
        dfLoading.show(getSupportFragmentManager(), "");
        dfLoading.setCancelable(false);

        int donationAmount = Integer.parseInt(etNominal.getText().toString());
        final long totalDonationAmount = 2500 + donationAmount;

    }
}
