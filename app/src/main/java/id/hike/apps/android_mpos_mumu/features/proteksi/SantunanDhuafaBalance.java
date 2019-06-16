package id.hike.apps.android_mpos_mumu.features.proteksi;

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SantunanDhuafaBalance extends BaseActivity {

    private final Context context = this;

    Calendar myCalendar;
    TextView totalSaldo;
    ImageView plus, minus;
    EditText tambahAnggota, namaSantunan, tanggalSantunan;
    Button btn;
    RadioButton radioButton;
    RadioGroup jenisKelaminMustahik;
    CheckBox checkBox;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    int jumlahBayar = 50000;
    String c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_santunan_dhuafa_balance);

        totalSaldo = findViewById(R.id.totalSaldo);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        btn = findViewById(R.id.button);
        tambahAnggota = findViewById(R.id.tambahAnggota);
        checkBox = findViewById(R.id.checkBox2);
        namaSantunan = findViewById(R.id.namaSantunan);
        tanggalSantunan = findViewById(R.id.tanggalSantunan);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        myCalendar = Calendar.getInstance();

        //lakiLaki = findViewById(R.id.lakiLaki);
        //perempuan = findViewById(R.id.perempuan);
        jenisKelaminMustahik = findViewById(R.id.radioJenisKelaminMustahik);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    String b = String.valueOf((tambahAnggota.getText().toString()));

                    if(b == null || b.isEmpty()){
                        int a = 0;
                        a = a + 1;
                        tambahAnggota.setText(String.valueOf(a));
                    }else{
                        int a = Integer.parseInt((tambahAnggota.getText().toString()));
                        a = a + 1;
                        int c = a < 10 ? a : 10;
                        //yang pertama menentukan kondisi, dan yang kedua jika nilai sebelum titik menentukan true & setelah titik nilai false

                        tambahAnggota.setText(String.valueOf(c));
                    }
                }catch(NumberFormatException ex){
                    // handle your exception
                    ex.fillInStackTrace();
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int b = Integer.valueOf(tambahAnggota.getText().toString());

                //int[] nilai = new int[b];
                //jika incompatible type harus menggunakan kondisi if

                int i = b - 1;
                int u = i > 0 ? i : 1;

                //int min = u;
                //min = e > c;
                tambahAnggota.setText(String.valueOf(u));
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    namaSantunan.setVisibility(View.VISIBLE);
                    tanggalSantunan.setVisibility(View.VISIBLE);
                    jenisKelaminMustahik.setVisibility(View.VISIBLE);
                }else if (!checkBox.isChecked()){
                    namaSantunan.setVisibility(View.GONE);
                    tanggalSantunan.setVisibility(View.GONE);
                    jenisKelaminMustahik.setVisibility(View.GONE);
                }
            }
        });

        tanggalSantunan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDateDialog();

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SantunanDhuafaBalance.this, AkadSantunan.class);

                int a = Integer.parseInt(tambahAnggota.getText().toString());
                int w = 10;

                if (tambahAnggota == null && !checkBox.isChecked()) {
                    Toast.makeText(context, "Silahkan isi data dengan benar", Toast.LENGTH_SHORT).show();
                }else if(a > w){
                    tambahAnggota.setError("Batas maksimum adalah 10 mustahik");
                    //Toast.makeText(context, "Maaf jumlah mustahik melebihi batas maksimum", Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences.Editor secPref = getSecPref().edit();
                    //muzaki = etNamaOrang.getText().toString();
                    secPref.putString(Cfg.NAMA_MUSTAHIQ_SANTUNAN, String.valueOf(namaSantunan.getText().toString()));
                    secPref.putString(Cfg.TANGGAL_MUSTAHIQ_SANTUNAN, String.valueOf(tanggalSantunan.getText().toString()));

                    int selectedId = jenisKelaminMustahik.getCheckedRadioButtonId();
                    radioButton = findViewById(selectedId);
                    String jenisKelamin = radioButton.getText().toString();

                    secPref.putString(Cfg.JENIS_KELAMIN_MUSTAHIQ, jenisKelamin);
                    Toast.makeText(SantunanDhuafaBalance.this, getSecPref().getString(Cfg.JENIS_KELAMIN_MUSTAHIQ, Cfg.JENIS_KELAMIN_MUSTAHIQ), Toast.LENGTH_SHORT).show();
                    //secPref.putString(Cfg.JENIS_KELAMIN_MUSTAHIQ, String.valueOf(jenisKelamin.getSelectedItem().toString()));
                    //secPref.apply();

                        int jumlahAnggota = Integer.parseInt(tambahAnggota.getText().toString());
                        //total.setText(Integer.toString(jumlahAnggota*jumlahBayar));
                        //total.setText("Rp" + " " +UnitConversion.format2Rupiah2(Integer.toString(jumlahAnggota*jumlahBayar)));
                        final int totalBayar = jumlahAnggota * jumlahBayar;

                        secPref.putInt(Cfg.JUMLAH_MUSTAHIQ, jumlahAnggota);
                        secPref.putInt(Cfg.TOTAL_BAYAR_MUSTAHIQ, totalBayar);
                        secPref.apply();

                    startActivity(intent);
                }
            }
        });
    }

    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                tanggalSantunan.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}
