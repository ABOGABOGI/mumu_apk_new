package id.hike.apps.android_mpos_mumu.features.qurban;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.area_kurban.kota.kambing_standar.FragmentKotaDistribusiKurban;
import id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.area_kurban.kota.sapi.FragmentKotaDistribusiKurbanSapi;
import id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.area_kurban.provinsi.sapi.FragmentProvinsiDistribusiKurban;

public class ActivityNextKurbanSapi extends BaseActivity {

    EditText jenisKurban, jumlahKurban, hargaSatuan, jumlahBayar, namaPekurban, namaPekurbanDua, namaPekurbanTiga, namaPekurbanEmpat, namaPekurbanLima, namaPekurbanEnam, namaPekurbanTujuh;
    Button button;
    ImageView kurangJumlahKurban, tambahJumlahKurban, btnBack;
    Context context;

    Toolbar toolbar;
    TextView addSatu, addDua, addTiga, addEmpat, addLima, addEnam, addTujuh;
    View view;
    ConstraintLayout constraintLayout, pilihAreaDistribusiKurban, pilihProvinsiDistribusiKurban, constraintLayoutArea, constraintLayoutFormKurban;
    CheckBox checkBox;
    String pilihanJenisKurban, harga;
    int hargaSapiStandar = 1975000;
    int a = 1;
    int b = 1;
    int c = 1;
    int d = 1;
    int e = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_kurban_sapi);

        addSatu = findViewById(R.id.textView97);
        addDua = findViewById(R.id.textView98);
        addDua.setVisibility(View.GONE);
        addTiga = findViewById(R.id.textView99);
        addTiga.setVisibility(View.GONE);
        addEmpat = findViewById(R.id.textView100);
        addEmpat.setVisibility(View.GONE);
        addLima = findViewById(R.id.textView101);
        addLima.setVisibility(View.GONE);
        addEnam = findViewById(R.id.textView102);
        addEnam.setVisibility(View.GONE);
        addTujuh = findViewById(R.id.textView103);
        addTujuh.setVisibility(View.GONE);

        namaPekurban = findViewById(R.id.editText5);
        namaPekurbanDua = findViewById(R.id.editText9);
        namaPekurbanDua.setVisibility(View.GONE);
        namaPekurbanTiga = findViewById(R.id.editText10);
        namaPekurbanTiga.setVisibility(View.GONE);
        namaPekurbanEmpat = findViewById(R.id.editText11);
        namaPekurbanEmpat.setVisibility(View.GONE);
        namaPekurbanLima = findViewById(R.id.editText12);
        namaPekurbanLima.setVisibility(View.GONE);
        namaPekurbanEnam = findViewById(R.id.editText13);
        namaPekurbanEnam.setVisibility(View.GONE);
        namaPekurbanTujuh = findViewById(R.id.editText14);
        namaPekurbanTujuh.setVisibility(View.GONE);

        addSatu.setOnClickListener(v -> {
            if(addSatu.isClickable()){
                namaPekurbanDua.setVisibility(View.VISIBLE);
                addDua.setVisibility(View.VISIBLE);
            }
        });

        addDua.setOnClickListener(v -> {
            if(a == 1){
                namaPekurbanTiga.setVisibility(View.VISIBLE);
                addTiga.setVisibility(View.VISIBLE);
                addDua.setText("Hps");
                a = 0 ;
            }else{
                namaPekurbanDua.setVisibility(View.GONE);
                addDua.setVisibility(View.GONE);
                addDua.setText("Add");
                a = 1;
            }
        });

        addTiga.setOnClickListener(v -> {
            if(b == 1){
                namaPekurbanEmpat.setVisibility(View.VISIBLE);
                addEmpat.setVisibility(View.VISIBLE);
                addTiga.setText("Hps");
                b = 0;
            }else {
                namaPekurbanTiga.setVisibility(View.GONE);
                addTiga.setVisibility(View.GONE);
                addTiga.setText("Add");
                b = 1;
            }
        });

        addEmpat.setOnClickListener(v -> {
            if(c == 1){
                namaPekurbanLima.setVisibility(View.VISIBLE);
                addLima.setVisibility(View.VISIBLE);
                addEmpat.setText("Hps");
                c = 0;
            }else{
                namaPekurbanEmpat.setVisibility(View.GONE);
                addEmpat.setVisibility(View.GONE);
                addEmpat.setText("Add");
               c = 1;
            }
        });

        addLima.setOnClickListener(v -> {
            if(d == 1){
                namaPekurbanEnam.setVisibility(View.VISIBLE);
                addEnam.setVisibility(View.VISIBLE);
                addLima.setText("Hps");
                d = 0;
            }else{
                namaPekurbanLima.setVisibility(View.GONE);
                addLima.setVisibility(View.GONE);
                addLima.setText("Add");
                d = 1;
            }
        });

        addEnam.setOnClickListener(v -> {
            if(e == 1){
                namaPekurbanTujuh.setVisibility(View.VISIBLE);
                addTujuh.setVisibility(View.VISIBLE);
                addTujuh.setText("Hps");
                addEnam.setText("Hps");
                e = 0;
            }else{
                namaPekurbanEnam.setVisibility(View.GONE);
                addEnam.setVisibility(View.GONE);
                addEnam.setText("Add");
                e = 1;
            }
        });

        addTujuh.setOnClickListener(v -> {
            if(addTujuh.isClickable()){
                namaPekurbanTujuh.setVisibility(View.GONE);
                addTujuh.setVisibility(View.GONE);
            }
        });

        constraintLayout = findViewById(R.id.isiForm);
        constraintLayoutFormKurban = findViewById(R.id.formKurban);

        constraintLayoutArea = findViewById(R.id.constraintLayoutArea);
        constraintLayoutArea.setVisibility(View.GONE);

        checkBox = findViewById(R.id.checkBoxMemilihAreaDistribusi);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (checkBox.isChecked()){
                constraintLayoutArea.setVisibility(View.VISIBLE);
            }else {
                constraintLayoutArea.setVisibility(View.GONE);
            }
        });

        String pilihanJenisKurban = getSecPref().getString(Cfg.JENIS_KURBAN, "");
        String harga = String.valueOf(getSecPref().getString(Cfg.HARGA_SAPI, ""));

        jenisKurban = findViewById(R.id.editText2);
        jenisKurban.setEnabled(false);
        jenisKurban.setText(pilihanJenisKurban);

        jumlahKurban = findViewById(R.id.editText);
        kurangJumlahKurban = findViewById(R.id.imageView18);
        tambahJumlahKurban = findViewById(R.id.imageView19);

        tambahJumlahKurban.setOnClickListener(v -> {
            try {

                String b = jumlahKurban.getText().toString();

                if (b == null || b.isEmpty()) {
                    int a = 0;
                    a = a + 1;

                    int total = a * hargaSapiStandar;
                    jumlahKurban.setText(String.valueOf(a));
                    jumlahBayar.setText(String.valueOf(total));
                } else {
                    int a = Integer.parseInt((jumlahKurban.getText().toString()));
                    a = a + 1;
                    //int c = a < 10 ? a : 10;
                    //yang pertama menentukan kondisi, dan yang kedua jika nilai sebelum titik menentukan true & setelah titik nilai false

                    int total = a * hargaSapiStandar;
                    jumlahKurban.setText(String.valueOf(a));
                    jumlahBayar.setText(String.valueOf(total));
                }
            } catch (NumberFormatException ex) {
                // handle your exception
                ex.fillInStackTrace();
            }
        });

        kurangJumlahKurban.setOnClickListener(v -> {
            try {

                String b = jumlahKurban.getText().toString();

                if (b == null || b.isEmpty()) {
                    int a = 0;
                    a = a - 0;
                    int total = a * hargaSapiStandar;
                    jumlahKurban.setText(String.valueOf(a));
                    jumlahBayar.setText(String.valueOf(total));
                } else {
                    int a = Integer.valueOf(jumlahKurban.getText().toString());
                    a = a - 1;
                    int u = a > 0 ? a : 0;
                    //yang pertama menentukan kondisi, dan yang kedua jika nilai sebelum titik menentukan true & setelah titik nilai false

                    int total = a * hargaSapiStandar;
                    jumlahKurban.setText(String.valueOf(u));
                    jumlahBayar.setText(String.valueOf(total));
                }
            } catch (NumberFormatException ex) {
                // handle your exception
                ex.fillInStackTrace();
            }
        });

        hargaSatuan = findViewById(R.id.editText3);
        hargaSatuan.setEnabled(false);
        hargaSatuan.setText(harga);

        jumlahBayar = findViewById(R.id.editText4);
        jumlahBayar.setEnabled(false);

//        Bundle extras = getIntent().getExtras();
        String a = getSecPref().getString(Cfg.KOTA_DISTRIBUSI_KURBAN, "");
        String b = getSecPref().getString(Cfg.PROVINSI_DISTRIBUSI_KURBAN, "");

        TextView kotaDistribusi = findViewById(R.id.editText6);
        kotaDistribusi.setText(a);

        TextView provinsiDistribusi = findViewById(R.id.editText7);
        provinsiDistribusi.setText(b);

        pilihAreaDistribusiKurban = findViewById(R.id.pilihAreaDistribusiKurban);
        pilihAreaDistribusiKurban.setOnClickListener(v -> {
            constraintLayout.setVisibility(View.GONE);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.formKurban, new FragmentKotaDistribusiKurbanSapi()).commit();
        });

        pilihProvinsiDistribusiKurban = findViewById(R.id.pilihProvinsiDistribusiKurban);
        pilihProvinsiDistribusiKurban.setOnClickListener(v -> {
            constraintLayout.setVisibility(View.GONE);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.formKurban, new FragmentProvinsiDistribusiKurban()).commit();
        });

        btnBack = findViewById(R.id.backButton);
        btnBack.setOnClickListener(v -> finish());

        button = findViewById(R.id.metoePembayaranKurban);
        button.setOnClickListener(v -> {

            if(jumlahKurban.getText().toString().equals("")) {
                jumlahKurban.setError("Isi data terlebih dahulu");

            }else if(namaPekurban.getText().toString().equals("")) {
                namaPekurban.setError("Isi data terlebih dahulu");
            }else if(namaPekurbanDua.getText().toString().equals("")) {
                namaPekurbanDua.setError("Isi data terlebih dahulu");
            }else if(namaPekurbanTiga.getText().toString().equals("")) {
                namaPekurbanTiga.setError("Isi data terlebih dahulu");
            }else if (namaPekurbanEmpat.getText().toString().equals("")) {
                namaPekurbanEmpat.setError("Isi data terlebih dahulu");
            }else if(namaPekurbanLima.getText().toString().equals("")) {
                namaPekurbanLima.setError("Isi data terlebih dahulu");
            }else if(namaPekurbanEnam.getText().toString().equals("")) {
                namaPekurbanEnam.setError("Isi data terlebih dahulu");
            }else if(namaPekurbanTujuh.getText().toString().equals("")){
                namaPekurbanTujuh.setError("Isi data terlebih dahulu");
            }else if(!checkBox.isChecked()){
                checkBox.setError("Centang area pemotongan qurban");
            }else {

                Dialog dialog = new Dialog(ActivityNextKurbanSapi.this);
                dialog.setContentView(R.layout.popup_lanjut_kurban);

                Button rejectedKurban = dialog.findViewById(R.id.menolakRegis);
                Button acceptedKurban = dialog.findViewById(R.id.setujuRegis);

                rejectedKurban.setOnClickListener(v1 -> {
                    dialog.dismiss();
                });

                acceptedKurban.setOnClickListener(v12 -> {
                    Intent intent = new Intent(ActivityNextKurbanSapi.this, TransaksiKurbanSapi.class);
                    startActivity(intent);
                });

                dialog.show();
            }
        });
    }
}
