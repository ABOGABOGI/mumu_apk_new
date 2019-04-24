package id.hike.apps.android_mpos_mumu.regstep.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.securepreferences.SecurePreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.model.Kecamatan;
import id.hike.apps.android_mpos_mumu.model.Kelurahan;
import id.hike.apps.android_mpos_mumu.model.Kodya;
import id.hike.apps.android_mpos_mumu.model.Propinsi;
import id.hike.apps.android_mpos_mumu.model.Response;
import id.hike.apps.android_mpos_mumu.regstep.api.MasterApi;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class InputIdentitasLain extends FragmentForm {

    @BindView(R.id.kelurahan_field)
    Spinner kelurahanField;

    @BindView(R.id.kecamatan_field)
    Spinner kecamatanField;

    @BindView(R.id.kodya_field)
    Spinner kodyaField;

    @BindView(R.id.propinsi_field)
    Spinner propinsiField;

    @BindView(R.id.tujuan_dana_field)
    Spinner tujuanField;

    @BindView(R.id.sumber_dana_field)
    Spinner sumberDanaField;

    @BindView(R.id.penghasilan_field)
    Spinner penghasilanField;

    @BindView(R.id.rt_field)
    TextInputEditText rtField;

    @BindView(R.id.rw_field)
    TextInputEditText rwField;

    @BindView(R.id.kode_pos_field)
    TextInputEditText kodePosField;

    @BindView(R.id.rt_field_layout)
    TextInputLayout rtFieldLayout;

    @BindView(R.id.rw_field_layout)
    TextInputLayout rwFieldLayout;

    @BindView(R.id.kode_pos_field_layout)
    TextInputLayout kodePosFieldLayout;

    private List<Propinsi> propinsiList = new ArrayList<>();
    private List<Kodya> kodyaList = new ArrayList<>();
    private List<Kecamatan> kecamatanList = new ArrayList<>();
    private List<Kelurahan> kelurahanList = new ArrayList<>();

    private String token = "";
    private Kelurahan selectedKelurahan = null;


    @Override
    public Observable<Map<String, Object>> isFormComplete(){

        return Observable.create(new ObservableOnSubscribe<Map<String, Object>>() {
            @Override
            public void subscribe(ObservableEmitter<Map<String, Object>> emitter) throws Exception {

                int error = 0;

                String rt = rtField.getText().toString();
                String rw = rwField.getText().toString();
                String kodePos = kodePosField.getText().toString();

                if(selectedKelurahan == null){
                    Toast.makeText(getContext(), "Pilih provinsi sampai kelurahan dahulu", Toast.LENGTH_LONG).show();
                    error += 1;
                }


                if(TextUtils.isEmpty(rt)){

                    rtFieldLayout.setError("Kolom RT tidak bisa kosong");

                    error += 1;
                }else{

                    rtFieldLayout.setError(null);
                }


                if(TextUtils.isEmpty(rw)){

                    rwFieldLayout.setError("Kolom RW tidak bisa kosong");

                    error += 1;
                }else{

                    rwFieldLayout.setError(null);
                }

                if(TextUtils.isEmpty(kodePos)){

                    kodePosFieldLayout.setError("Kode Pos tidak bisa kosong");

                    error += 1;
                }else{

                    kodePosFieldLayout.setError(null);
                }



                if(error == 0){
                    Map<String, Object> forms = new HashMap<>();
                    forms.put("alamat_rumah_rt", rt);
                    forms.put("alamat_rumah_rw", rw);
                    forms.put("alamat_rumah_kode_pos", kodePos);
                    forms.put("alamat_rumah_kelurahan_id", selectedKelurahan.getKd_kelurahan());
                    forms.put("tujuan_penggunaan_dana", String.valueOf(tujuanField.getSelectedItemPosition() + 1));
                    forms.put("sumber_dana", String.valueOf(sumberDanaField.getSelectedItemPosition() + 1));
                    forms.put("penghasilan_utama_per_tahun", String.valueOf(penghasilanField.getSelectedItemPosition() + 1));

                    emitter.onNext(forms);


                }else{
                    emitter.onError(new Exception("Total Error " + error));
                }




            }
        });

    }

    private void initArrayString(int resource, Spinner spinner){

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), resource,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    private void initKelurahan(String kecamatan){
        kelurahanList = new ArrayList<>();
        selectedKelurahan = null;

        MasterApi.create().getKelurahan(kecamatan, token).subscribe(new Observer<Response<List<Kelurahan>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<List<Kelurahan>> listResponse) {

                kelurahanList = listResponse.getData();

                initKelurahanField();

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void initKecamatan(String kodya){
        kecamatanList = new ArrayList<>();

        MasterApi.create().getKecamatan(kodya, token).subscribe(new Observer<Response<List<Kecamatan>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<List<Kecamatan>> listResponse) {

                kecamatanList = listResponse.getData();

                initKecamatanField();

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void initKodya(String propinsi){

        kodyaList = new ArrayList<>();

        MasterApi.create().getKodya(propinsi, token).subscribe(new Observer<Response<List<Kodya>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<List<Kodya>> listResponse) {

                kodyaList = listResponse.getData();

                initKodyaField();

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private void initPropinsi(){

        propinsiList = new ArrayList<>();

        MasterApi.create().getPropinsi(token).subscribe(new Observer<Response<List<Propinsi>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<List<Propinsi>> listResponse) {

                propinsiList = listResponse.getData();

                initProvinsiField();

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){

        View v = inflater.inflate(R.layout.fragment_input_identitas_lain, container, false);

        ButterKnife.bind(this, v);

        return v;

    }

    private void initProvinsiField(){

        List<String> listData = new ArrayList<>();

        for(Propinsi propinsi : propinsiList){
            listData.add(propinsi.getPropinsi());

        }


        String[] data = listData.toArray(new String[listData.size()]);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, data);

        propinsiField.setAdapter(adapter);



    }


    private void initKodyaField(){

        List<String> listData = new ArrayList<>();

        for(Kodya propinsi : kodyaList){
            listData.add(propinsi.getNm_kodya());
        }


        String[] data = listData.toArray(new String[listData.size()]);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, data);

        kodyaField.setAdapter(adapter);

    }


    private void initKecamatanField(){

        List<String> listData = new ArrayList<>();

        for(Kecamatan propinsi : kecamatanList){
            listData.add(propinsi.getNm_kecamatan());
            Log.i("BOWOTAG", propinsi.getNm_kecamatan());
        }


        String[] data = listData.toArray(new String[listData.size()]);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, data);

        kecamatanField.setAdapter(adapter);

    }

    private void initKelurahanField(){

        List<String> listData = new ArrayList<>();

        for(Kelurahan propinsi : kelurahanList){
            listData.add(propinsi.getNm_kelurahan());
        }

        String[] data = listData.toArray(new String[listData.size()]);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, data);

        kelurahanField.setAdapter(adapter);

    }


    private SharedPreferences getSecPref(){

        SecurePreferences secPrefs = new SecurePreferences(getContext(), "mposSmartFren2017", "mpos_smartfren.xml");
        secPrefs.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

            }
        });

        return secPrefs;
    }


    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);

        token = getSecPref().getString(Cfg.oauthAccessToken, "");

        initArrayString(R.array.tujuan_dana_list, tujuanField);
        initArrayString(R.array.sumber_dana_list, sumberDanaField);
        initArrayString(R.array.penghasilan_pertahun_list, penghasilanField);

        initPropinsi();

        propinsiField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Propinsi p = propinsiList.get(position);

                initKodya(p.getKd_propinsi());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        kodyaField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Kodya k = kodyaList.get(position);

                initKecamatan(k.getKd_kodya());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        kecamatanField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Kecamatan k = kecamatanList.get(position);

                initKelurahan(k.getKd_kecamatan());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        kelurahanField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedKelurahan = kelurahanList.get(position);


                kodePosField.setText(selectedKelurahan.getKodePos());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
