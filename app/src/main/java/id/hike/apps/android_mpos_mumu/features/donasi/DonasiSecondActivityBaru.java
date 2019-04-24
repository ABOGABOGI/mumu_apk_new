package id.hike.apps.android_mpos_mumu.features.donasi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

import butterknife.BindView;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.app.BaseApplication;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.profil.ProdukApiDua;
import id.hike.apps.android_mpos_mumu.model.Response;
import id.hike.apps.android_mpos_mumu.model.WalletInfo;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DonasiSecondActivityBaru extends BaseActivity {

    @BindView(R.id.spinner_donasi)
    Spinner spinnerDonasi;

    @BindView(R.id.spinner_donasi_option)
    Spinner spinnerDonasiOption;

    @BindView(R.id.textView35)
    TextView saldoCurrent;

    EditText etNominal, etNamaMuzaki, etNamaOrang, etNohp;
    TextView textViewNama;
    TextView textViewNamaOrang;
    Context ctx;
    Button btnProsesDonasi;
    ImageView btnBack;
    CheckBox checkBox;
    ImageButton btnSepuluh,btnDuapuluh,btnDualima,btnLimapuluh,btnSeratus,btnLimaratus,btnSatujuta;

    private final Context context = this;
    private WalletInfo info;

    private String token = "";
    private String kategori = "";
    private DonasiInfakSedekah selectedDonasiInfakSedekah;
    private DonasiPendidikanKesehatan selectedDonasiPendidikanKesehatan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        setContentView(R.layout.activity_donasi_second);

        saldoCurrent = findViewById(R.id.textView35);
        btnProsesDonasi = findViewById(R.id.button_proses_donasi);
        btnSepuluh = findViewById(R.id.btnSepuluh);
        btnDuapuluh = findViewById(R.id.btnDuapuluh);
        btnDualima = findViewById(R.id.btnDualima);
        btnLimapuluh = findViewById(R.id.btnLimapuluh);
        btnSeratus = findViewById(R.id.btnSeratus);
        btnLimaratus = findViewById(R.id.btnLimaratus);
        btnSatujuta = findViewById(R.id.btnSatujuta);
        etNominal = findViewById(R.id.txtDonasiSecondNominal);
        etNamaOrang = findViewById(R.id.editText_namaOranglain);
        etNamaMuzaki = findViewById(R.id.editText_nama);
        etNohp = findViewById(R.id.editText_noHp_donasi);
        checkBox = findViewById(R.id.check_berdonasi);
        btnBack = findViewById(R.id.back_btn_donasi);
        spinnerDonasi = findViewById(R.id.spinner_donasi);
        spinnerDonasiOption = findViewById(R.id.spinner_donasi_option);
        textViewNama = findViewById(R.id.editText_nama);
        textViewNamaOrang = findViewById(R.id.editText_namaOranglain);
        String token = BaseApplication.secPrefs.getString(Cfg.oauthAccessToken, "");
        String username = BaseApplication.secPrefs.getString(Cfg.prefsUserEmail, "");
        /*textViewNama.setText(token);
        textViewNamaOrang.setText(username);
        Toast.makeText(this, token, Toast.LENGTH_LONG).show();
        Toast.makeText(this, username, Toast.LENGTH_LONG).show();
        Log.d("BAKKA - token", token);
        Log.d("BAKKA - username", username);
        Log.v("BAKKA - token", token);
        Log.v("BAKKA - username", username);
        System.out.println("BAKKA - token " + token);
        System.out.println("BAKKA - username " + username);*/
        //Log.d("BAKKA", "SPINNER IS " + spinnerDonasi);
        //spinnerDonasi.setOnItemSelectedListener();
        running();
        runningDua();

        btnProsesDonasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(DonasiSecondActivityBaru.this, AkadActivity.class);
                Intent intent = new Intent(DonasiSecondActivityBaru.this, AkadDonasiActivity.class);
                try {
                    SharedPreferences.Editor secPref = getSecPref().edit();
                    secPref.putString(Cfg.prefDonasiType, "WAKAF");
                    //running();
                    //runningDua();
                    String muzaki = etNamaMuzaki.getText().toString();
                    //Log.i("DEBUGTAG","muzaki1");
                    //Log.i("DEBUGTAG",muzaki);
                    //Log.i("DEBUGTAG",Boolean.toString(checkBoxBerwakaf.isChecked()));
                    if (checkBox.isChecked()) {
                        muzaki = etNamaOrang.getText().toString();
                        //Log.i("DEBUGTAG",muzaki);
                        secPref.putString(Cfg.prefDonasiPhone, etNohp.getText().toString());
                    }
                    secPref.putString(Cfg.prefDonasiMuzakki, muzaki);
                    Cfg.gs_WakafInfo_Muzakki = muzaki;
                    secPref.putInt(Cfg.prefDonasiAmount, Integer.parseInt(etNominal.getText().toString()));
                    secPref.apply();

                } catch (NullPointerException | NumberFormatException ex) {
                    Log.e(LOG, ex.getMessage(), ex);
                    Toast.makeText(context, getString(R.string.input_error), Toast.LENGTH_LONG).show();
                }
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();

        //if(bundle != null && bundle.containsKey("wallet")){
        //    info = (WalletInfo) bundle.getSerializable("wallet");
        //    saldoCurrent.setText("Rp. " + UnitConversion.format2Rupiah2(info.getSaldo()));
        //}

        int saldo = getSecPref().getInt(Cfg.prefsWalletSaldo, 0);
        saldoCurrent.setText("Rp. " + UnitConversion.format2Rupiah2(saldo));

        etNamaOrang.setVisibility(View.GONE);
        etNohp.setVisibility(View.GONE);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox.isChecked()){
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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    class MyArrayAdapter extends ArrayAdapter<String> {
        public MyArrayAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }

        @Override
        public String getItem (int position) {
            String item = super.getItem(position);
            return item.split("\\|")[0];
        };

        public String getItemSub(int position) {
            String item = super.getItem(position);
            return item.split("\\|")[1];
        };

        public String getItemIdentity(int position) {
            String item = super.getItem(position);
            return item.split("\\|")[2];
        };
    }

    public void running(){
        List<String> data = new ArrayList<>();
        ArrayAdapter<String> adapter = new MyArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, data);
        spinnerDonasi.setAdapter(adapter);
        //kategori = "Infak/Sedekah";
        try {
            ProdukApiDua.create().getProdukDonasi(token).subscribe(new Observer<Response<List<List<String>>>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Response<List<List<String>>> listResponse) {
                    Log.d("BAKKA", listResponse.toString());
                    for (List<String> produk : listResponse.getData()) {
                        adapter.add(produk.get(0) + "|" + produk.get(1) + "|" + produk.get(2));
                        Log.i("DEBUGTAG","RUNNING1:" + produk.get(0) + "|" + produk.get(1) + "|" + produk.get(2));
                    }
                }

                @Override
                public void onError(Throwable e) {
                    Log.e("BAKKA", e.getMessage(), e);
                }

                @Override
                public void onComplete() {

                }
            });
        } catch (Exception e) {
            Log.e("BAKKA", e.getMessage(), e);
        }
    /*@Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);*/



        //initPropinsi();

    spinnerDonasi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String item = ((MyArrayAdapter) parent.getAdapter()).getItem(position);
            String itemSub = ((MyArrayAdapter) parent.getAdapter()).getItemSub(position);
            String itemIdentity = ((MyArrayAdapter) parent.getAdapter()).getItemIdentity(position);
            Log.i("DEBUGTAG","onItemSelected:" + item + "|" + itemSub);

            if (itemSub.equals("SUB")) {
                // show sub donasi
                // load data to spinner donasi sub
                kategori = item;
                runningDua();
            } else {
                Log.i("DEBUGTAG","gs_WakafInfo_KodeProduk:" + itemIdentity);
                Cfg.gs_WakafInfo_KodeProduk = itemIdentity;
                // hide sub donasi
                spinnerDonasiOption.setVisibility(View.GONE);
            }

            //textViewNama.setText(itemSub);
            //textViewNamaOrang.setText(itemIdentity);
            /*try {
                //Produk p = donasiInfakSedekahList.get(position);

                //initDonasiInfakSedekah(p.getKdDonasiInfakSedekah());
            } catch (Exception e) {
                Log.e("BAKKA", e.getMessage(), e);
            }*/
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    class MyArrayAdapterDua extends ArrayAdapter<String> {
        public MyArrayAdapterDua(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }

        @Override
        public String getItem (int position) {
            String item = super.getItem(position);
            return item.split("\\|")[0];
        };

        public String getItemSub(int position) {
            String item = super.getItem(position);
            return item.split("\\|")[1];
        };

        public String getItemIdentity(int position) {
            String item = super.getItem(position);
            return item.split("\\|")[2];
        };
    }

    public void runningDua(){
        List<String> data = new ArrayList<>();
        ArrayAdapter<String> adapter = new MyArrayAdapterDua(ctx, android.R.layout.simple_spinner_dropdown_item, data);
        spinnerDonasiOption.setAdapter(adapter);

        try {
            ProdukApiDua.create().getProdukDonasiSub(token, kategori).subscribe(new Observer<Response<List<List<String>>>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Response<List<List<String>>> listResponse) {
                    Log.d("BAKKA", listResponse.toString());
                    //Toast.makeText(ctx, "Data Size: " + listResponse.getData().size(), Toast.LENGTH_LONG).show();

                    for (List<String> produk : listResponse.getData()) {
                        adapter.add(produk.get(0) + "|" + produk.get(1) + "|" + produk.get(2));
                        Log.i("DEBUGTAG","RUNNING2:" + produk.get(0) + "|" + produk.get(1) + "|" + produk.get(2));
                        //Toast.makeText(ctx, produk.get(0) + "|" + produk.get(1) + "|" + produk.get(2), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    Log.e("BAKKA", e.getMessage(), e);
                }

                @Override
                public void onComplete() {

                }
            });
        } catch (Exception e) {
            Log.e("BAKKA", e.getMessage(), e);
        }

        spinnerDonasiOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSub = ((MyArrayAdapterDua) parent.getAdapter()).getItemSub(position);
                String itemIdentity = ((MyArrayAdapterDua) parent.getAdapter()).getItemIdentity(position);
                Log.i("DEBUGTAG","gs_WakafInfo_KodeProduk:" + itemIdentity);
                Cfg.gs_WakafInfo_KodeProduk = itemIdentity;
                //textViewNama.setText(itemSub);
                //textViewNamaOrang.setText(itemIdentity);
            /*try {
                //Produk p = donasiInfakSedekahList.get(position);

                //initDonasiInfakSedekah(p.getKdDonasiInfakSedekah());
            } catch (Exception e) {
                Log.e("BAKKA", e.getMessage(), e);
            }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void validateSaldoAndGo(final Intent intent) {
        dfLoading.show(getSupportFragmentManager(), "");
        dfLoading.setCancelable(false);

        int donationAmount = Integer.parseInt(etNominal.getText().toString());
        final long totalDonationAmount = 2500 + donationAmount;
//
    }
}
