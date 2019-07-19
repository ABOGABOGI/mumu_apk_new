package id.hike.apps.android_mpos_mumu.features.qurban;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.area_kurban.kota.kambing_premium.FragmentKotaDistribusiKurbanKambingPremium;
import id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.area_kurban.kota.kambing_standar.FragmentKotaDistribusiKurban;
import id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban.area_kurban.provinsi.kambing_premium.FragmentProvinsiDistribusiKurbanKambingPremium;

public class ActivityNextKambingPremium extends BaseActivity {

    EditText jenisKurban, jumlahKurban, hargaSatuan, jumlahBayar, namaPekurban;
    Button button;
    ImageView kurangJumlahKurban, tambahJumlahKurban, btnBack;
    ConstraintLayout constraintLayout, pilihAreaDistribusiKurban, pilihProvinsiDistribusiKurban, constraintLayoutArea;
    CheckBox checkBox;
    String pilihanJenisKurban, harga;
    int hargaSapiStandar = 1975000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_kambing_premium);

        constraintLayout = findViewById(R.id.isiForm);

        pilihanJenisKurban = getSecPref().getString(Cfg.JENIS_KURBAN, "");
        harga = getSecPref().getString(Cfg.HARGA_KAMBING, "");
        //String pilihanJenisKurban = getIntent().getStringExtra(Cfg.JENIS_KURBAN);
        //String harga = String.valueOf(getIntent().getStringExtra(Cfg.HARGA_KAMBING));

        jenisKurban = findViewById(R.id.editText2);
        jenisKurban.setEnabled(false);
        jenisKurban.setText(pilihanJenisKurban);

        jumlahKurban = findViewById(R.id.editText);
        kurangJumlahKurban = findViewById(R.id.imageView18);
        tambahJumlahKurban = findViewById(R.id.imageView19);

        checkBox = findViewById(R.id.checkBoxMemilihAreaDistribusi);
        constraintLayoutArea = findViewById(R.id.constraintLayoutArea);
        constraintLayoutArea.setVisibility(View.GONE);

        btnBack = findViewById(R.id.backButton);
        btnBack.setOnClickListener(v -> finish());

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (checkBox.isChecked()){
                constraintLayoutArea.setVisibility(View.VISIBLE);
            }else {
                constraintLayoutArea.setVisibility(View.GONE);
            }
        });

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
                    int total = a * hargaSapiStandar;
                    //int c = a < 10 ? a : 10;
                    //yang pertama menentukan kondisi, dan yang kedua jika nilai sebelum titik menentukan true & setelah titik nilai false

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

        //Bundle extras = getIntent().getExtras();
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
            fragmentManager.beginTransaction().replace(R.id.formKurban, new FragmentKotaDistribusiKurbanKambingPremium()).commit();
        });

        pilihProvinsiDistribusiKurban = findViewById(R.id.pilihProvinsiDistribusiKurban);
        pilihProvinsiDistribusiKurban.setOnClickListener(v -> {
            constraintLayout.setVisibility(View.GONE);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.formKurban, new FragmentProvinsiDistribusiKurbanKambingPremium()).commit();
        });

        namaPekurban = findViewById(R.id.editText5);

        button = findViewById(R.id.metoePembayaranKurban);
        button.setOnClickListener(v -> {

            if(jumlahKurban.getText().toString().equals("")) {
                jumlahKurban.setError("Isi data terlebih dahulu");

            }else if(namaPekurban.getText().toString().equals("")) {
                namaPekurban.setError("Isi data terlebih dahulu");

            }else if(!checkBox.isChecked()){
                checkBox.setError("Centang area pemotongan qurban");
            }else {

                Dialog dialog = new Dialog(ActivityNextKambingPremium.this);
                dialog.setContentView(R.layout.popup_lanjut_kurban);

                Button rejectedKurban = dialog.findViewById(R.id.menolakRegis);
                Button acceptedKurban = dialog.findViewById(R.id.setujuRegis);

                rejectedKurban.setOnClickListener(v1 -> {
                    dialog.dismiss();
                });

                acceptedKurban.setOnClickListener(v12 -> {
                    Intent intent = new Intent(ActivityNextKambingPremium.this, TransaksiKurbanKambingPremium.class);
                    startActivity(intent);
                });

                dialog.show();
            }
        });
    }
}
