package id.hike.apps.android_mpos_mumu.wallet;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.regstep.fragment.FragmentForm;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class WalletAktifasiSimple extends FragmentForm {

    @BindView(R.id.nomor_ktp_field)
    TextInputEditText nomorKtp;


    @BindView(R.id.nomor_ktp_layout)
    TextInputLayout nomorKtpLayout;

    public WalletAktifasiSimple() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        //public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_wallet_aktifasi_simple, container, false);
        View v = inflater.inflate(R.layout.fragment_wallet_aktifasi_simple, container, false);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this, v);
        //ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public Observable<Map<String, Object>> isFormComplete(){
        return Observable.create(new ObservableOnSubscribe<Map<String, Object>>() {
            @Override
            public void subscribe(ObservableEmitter<Map<String, Object>> emitter) throws Exception {


                int error = 0;
                //Map<String, Object> form = new HashMap<>();
                //emitter.onNext(form);
                //emitter.onNext(new HashMap<>());
                //emitter.onNext(new HashMap<>());
                String nomor = nomorKtp.getText().toString();
                int nomorLenght = nomorKtp.getText().toString().length();

                if(TextUtils.isEmpty(nomor)){
                    nomorKtpLayout.setError("Nomor ktp harus diisi!");
                    error += 1;
                }else{
                    if(nomorLenght == 16 ) {
                        nomorKtpLayout.setError(null);
                    } else {
                        nomorKtpLayout.setError("Panjang Nomor ktp harus 16 Digit.");
                        error += 1;
                    }
                }


                if(error == 0){

                    Map<String, Object> form = new HashMap<>();
                    form.put("nomor_identitas", nomor);
                    form.put("nama_nasabah", "NAMAnasabah");
                    form.put("nama_singkat", "NAMAsingkat");
                    form.put("jenis_kelamin", "X");
                    form.put("agama", "X");

                    // InputIdentitasLain
                    form.put("alamat_rumah_rt", "rt");
                    form.put("alamat_rumah_rw", "rw");
                    form.put("alamat_rumah_kode_pos", "kodeP");
                    form.put("alamat_rumah_kelurahan_id", "00000000000000");
                    form.put("tujuan_penggunaan_dana", "X");
                    form.put("sumber_dana", "Y");
                    form.put("penghasilan_utama_per_tahun", "0");

                    // InputLahir
                    form.put("tempat_lahir", "tmplahir");
                    form.put("tanggal_lahir", "01/01/1990");
                    form.put("nama_ibu_kandung", "IbuKandung");

                    // InputKTP
                    //form.put("nomor_identitas", nomor);
                    form.put("alamat_rumah_jalan", "alamat");
                    form.put("pekerjaan_id", "18");
                    form.put("status_perkawinan", "X");
                    form.put("ktp", "KTP.IMAGE");
                    form.put("tanggal_berakhir_identitas", "01/01/3000");

                    // InputSelfie
                    form.put("selfie", "selectedImage");
                    emitter.onNext(form);
                }else{
                    emitter.onError(new Exception("Error " + error));
                }
            }
        });
    }
}
