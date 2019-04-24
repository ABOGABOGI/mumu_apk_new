package id.hike.apps.android_mpos_mumu.features.landing_page.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.regstep.fragment.FragmentForm;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class WelcomeFragment extends FragmentForm {

    @BindView(R.id.nomor_ktp_field)
    TextInputEditText nomorKtp;

    @BindView(R.id.nomor_ktp_layout)
    TextInputEditText nomorKtpLayout;

    //int error = 0;

    @Override
    public Observable<Map<String, Object>> isFormComplete(){

        return Observable.create(new ObservableOnSubscribe<Map<String, Object>>() {
            @Override
            public void subscribe(ObservableEmitter<Map<String, Object>> emitter) throws Exception {


                //int error = 0;
                //Map<String, Object> form = new HashMap<>();
                //emitter.onNext(form);

                //emitter.onNext(new HashMap<>());

                //emitter.onNext(new HashMap<>());
                String nomor = nomorKtp.getText().toString();

                Map<String, Object> form = new HashMap<>();
                form.put("nomor_identitas", nomor);
                emitter.onNext(form);
                form.put("nama_nasabah", "nasabah");
                form.put("nama_singkat", "singkat");
                form.put("jenis_kelamin", "gender");
                form.put("agama", "agama");
                form.put("alamat_rumah_rt", "rt");
                form.put("alamat_rumah_rw", "rw");
                form.put("alamat_rumah_kode_pos", "kodeP");
                form.put("alamat_rumah_kelurahan_id", "1");
                form.put("tujuan_penggunaan_dana", "1");
                form.put("sumber_dana", "1");
                form.put("penghasilan_utama_per_tahun", "1");
                form.put("tempat_lahir", "tmplahir");
                form.put("tanggal_lahir", "2000-01-01");
                //form.put("nama_ibu_kandung", "IbuKandung");
                //form.put("nomor_identitas", nomor);
                form.put("alamat_rumah_jalan", "alamat");
                form.put("pekerjaan_id", "A");
                form.put("status_perkawinan", "1");
                form.put("ktp", "KTP.IMAGE");
                form.put("tanggal_berakhir_identitas", "5000-01-01");
                //adapter.addPage(new InputSelfie());
                form.put("selfie", "selectedImage");
                emitter.onNext(form);

                /*
                if(TextUtils.isEmpty(nomor)){
                    nomorKtpLayout.setError("Nomor ktp harus diisi!");
                    error += 1;
                }else{
                    nomorKtpLayout.setError(null);
                }

                if(error == 0){

                    Map<String, Object> form = new HashMap<>();
                    form.put("nomor_identitas", nomor);
                    form.put("nama_nasabah", "nasabah");
                    form.put("nama_singkat", "singkat");
                    form.put("jenis_kelamin", "gender");
                    form.put("agama", "agama");
                    form.put("alamat_rumah_rt", "rt");
                    form.put("alamat_rumah_rw", "rw");
                    form.put("alamat_rumah_kode_pos", "kodeP");
                    form.put("alamat_rumah_kelurahan_id", "1");
                    form.put("tujuan_penggunaan_dana", "1");
                    form.put("sumber_dana", "1");
                    form.put("penghasilan_utama_per_tahun", "1");
                    form.put("tempat_lahir", "tmplahir");
                    form.put("tanggal_lahir", "2000-01-01");
                    //form.put("nama_ibu_kandung", "IbuKandung");
                    //form.put("nomor_identitas", nomor);
                    form.put("alamat_rumah_jalan", "alamat");
                    form.put("pekerjaan_id", "A");
                    form.put("status_perkawinan", "1");
                    form.put("ktp", "KTP.IMAGE");
                    form.put("tanggal_berakhir_identitas", "5000-01-01");
                    //adapter.addPage(new InputSelfie());
                    form.put("selfie", "selectedImage");


                    emitter.onNext(form);


                }else{
                    emitter.onError(new Exception("Error " + error));
                }*/
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){
        //View v = inflater.inflate(R.layout.fragment_aktivasi_wallet, container, false);
        //ButterKnife.bind(this, v);
        //return v;
        //View v = inflater.inflate(R.layout.fragment_aktivasi_wallet, container, false);
        //ButterKnife.bind(this, v);
        //return v;
        return inflater.inflate(R.layout.fragment_aktivasi_wallet, container, false);
    }



}
