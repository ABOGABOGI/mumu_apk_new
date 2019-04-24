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

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.login.df.DfLoading;
import id.hike.apps.android_mpos_mumu.features.profil.ProdukApiDua;
import id.hike.apps.android_mpos_mumu.model.Response;
import id.hike.apps.android_mpos_mumu.model.WalletInfo;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ZakatSecondActivityBaru extends BaseActivity {

    Context ctx;
    private final Context context = this;
    private Spinner spinnerZakat, spinnerZakatOptions;
    private String[] zakatList = {"Zakat Maal", "Zakat Penghasilan", "Fidyah"};
    private EditText etNamaMuzakiZakat, etNamaOrangZakat, etNoZakat;
    private CheckBox checkZakat;
    private Button btnProsesZakat;
    private ImageButton btnSepuluh, btnDuapuluh, btnDualima, btnLimapuluh, btnSeratus, btnLimaratus, btnSatujuta;
    private EditText etNominal;
    private TextView txtSaldo;
    private ImageView back_btn_zakat;
    public DfLoading dfLoading;
    private String token = "";
    private String kategori = "";
    private WalletInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakat_second);

        ctx = this;
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

        running();
        runningDua();

        int saldo = getSecPref().getInt(Cfg.prefsWalletSaldo, 0);
        txtSaldo.setText("Rp. " + UnitConversion.format2Rupiah2(saldo));

        btnProsesZakat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(ZakatSecondActivityBaru.this, AkadActivity.class);
                Intent intent = new Intent(ZakatSecondActivityBaru.this, AkadZakatActivity.class);
                try {
                    SharedPreferences.Editor secPref = getSecPref().edit();
                    secPref.putString(Cfg.prefDonasiType, "WAKAF");
                    //running();
                    //runningDua();
                    String muzaki = etNamaMuzakiZakat.getText().toString();
                    //Log.i("DEBUGTAG","muzaki1");
                    //Log.i("DEBUGTAG",muzaki);
                    //Log.i("DEBUGTAG",Boolean.toString(checkBoxBerwakaf.isChecked()));
                    if (checkZakat.isChecked()) {
                        muzaki = etNamaOrangZakat.getText().toString();
                        //Log.i("DEBUGTAG",muzaki);
                        secPref.putString(Cfg.prefDonasiPhone, etNoZakat.getText().toString());
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

        if(bundle != null && bundle.containsKey("wallet")){
            info = (WalletInfo) bundle.getSerializable("wallet");

            txtSaldo.setText("Rp. " + UnitConversion.format2Rupiah2(info.getSaldo()));
        }

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

        back_btn_zakat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    class MyArrayAdapterZakat extends ArrayAdapter<String> {
        public MyArrayAdapterZakat(Context context, int resource, List<String> objects) {
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
        ArrayAdapter<String> adapter = new ZakatSecondActivityBaru.MyArrayAdapterZakat(ctx, android.R.layout.simple_spinner_dropdown_item, data);
        spinnerZakat.setAdapter(adapter);
        try {
            ProdukApiDua.create().getProdukZakat(token).subscribe(new Observer<Response<List<List<String>>>>() {
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

        spinnerZakat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSub = ((ZakatSecondActivityBaru.MyArrayAdapterZakat) parent.getAdapter()).getItemSub(position);
                String item = ((ZakatSecondActivityBaru.MyArrayAdapterZakat) parent.getAdapter()).getItem(position);
                String itemIdentity = ((ZakatSecondActivityBaru.MyArrayAdapterZakat) parent.getAdapter()).getItemIdentity(position);


                if (itemSub.equals("SUB")){
                    kategori = item;
                    runningDua();
                    spinnerZakatOptions.setVisibility(view.VISIBLE);
                } else {
                    Log.i("DEBUGTAG","gs_WakafInfo_KodeProduk:" + itemIdentity);
                    Cfg.gs_WakafInfo_KodeProduk = itemIdentity;
                    spinnerZakatOptions.setVisibility(view.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    class MyArrayAdapterDuaZakat extends ArrayAdapter<String> {
        public MyArrayAdapterDuaZakat(Context context, int resource, List<String> objects) {
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
        ArrayAdapter<String> adapter = new ZakatSecondActivityBaru.MyArrayAdapterDuaZakat(ctx, android.R.layout.simple_spinner_dropdown_item, data);
        spinnerZakatOptions.setAdapter(adapter);
        try {
            ProdukApiDua.create().getProdukZakatSub(token, kategori).subscribe(new Observer<Response<List<List<String>>>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Response<List<List<String>>> listResponse) {
                    Log.d("BAKKA", listResponse.toString());
                    for (List<String> produk : listResponse.getData()) {
                        adapter.add(produk.get(0) + "|" + produk.get(1) + "|" + produk.get(2));
                        Log.i("DEBUGTAG","RUNNING2:" + produk.get(0) + "|" + produk.get(1) + "|" + produk.get(2));
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

        spinnerZakatOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSub = ((ZakatSecondActivityBaru.MyArrayAdapterDuaZakat) parent.getAdapter()).getItemSub(position);
                String itemIdentity = ((ZakatSecondActivityBaru.MyArrayAdapterDuaZakat) parent.getAdapter()).getItemIdentity(position);
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