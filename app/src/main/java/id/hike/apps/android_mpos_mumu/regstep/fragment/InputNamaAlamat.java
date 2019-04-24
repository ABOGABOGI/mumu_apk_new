package id.hike.apps.android_mpos_mumu.regstep.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class InputNamaAlamat extends FragmentForm {

    @BindView(R.id.nama_field)
    TextInputEditText namaField;

    @BindView(R.id.nama_field_layout)
    TextInputLayout namaFieldLayout;

    @BindView(R.id.alamat_field)
    TextInputEditText alamatField;

    @BindView(R.id.alamat_field_layout)
    TextInputLayout alamatFieldLayout;

    @Override
    public Observable<Map<String, Object>> isFormComplete(){

        return Observable.create(new ObservableOnSubscribe<Map<String, Object>>() {
            @Override
            public void subscribe(ObservableEmitter<Map<String, Object>> emitter) throws Exception {

                int error = 0;
                String nama = namaField.getText().toString();
                String alamat = alamatField.getText().toString();

                if(TextUtils.isEmpty(nama)){

                    namaFieldLayout.setError("Isi dahulu namanya");
                    error += 1;

                }else{
                    namaFieldLayout.setError(null);
                }

                if(TextUtils.isEmpty(alamat)){

                    alamatFieldLayout.setError("Isi dahulu alamat");
                    error += 1;

                }else{
                    alamatFieldLayout.setError(null);
                }

                if(error == 0){

                    Map<String, Object> forms = new HashMap<>();
                    forms.put("nm_agen", nama);
                    forms.put("alamat", alamat);

                    emitter.onNext(forms);

                }else{

                    emitter.onError(new Exception("Total error " + error));
                }

            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){


        View v = inflater.inflate(R.layout.fragment_input_nama, container, false);

        ButterKnife.bind(this, v);

        return v;

    }


    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);


    }



}
