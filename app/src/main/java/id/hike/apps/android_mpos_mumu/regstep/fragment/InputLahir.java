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
import id.hike.apps.android_mpos_mumu.regstep.customview.EditTextCalendar;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class InputLahir extends FragmentForm {


    @BindView(R.id.tanggal_lahir_field)
    TextInputEditText tanggalLahir;

    @BindView(R.id.tanggal_lahir_field_layout)
    TextInputLayout tanggalLahirLayout;

    @BindView(R.id.tempat_lahir_field)
    TextInputEditText tempatLahir;

    @BindView(R.id.tempat_lahir_field_layout)
    TextInputLayout tempatLahirLayout;

    @BindView(R.id.nama_ibu_field)
    TextInputEditText namaIbu;

    @BindView(R.id.nama_ibu_layout)
    TextInputLayout namaIbuLayout;

    @Override
    public Observable<Map<String, Object>> isFormComplete(){
        return Observable.create(new ObservableOnSubscribe<Map<String, Object>>() {
            @Override
            public void subscribe(ObservableEmitter<Map<String, Object>> emitter) throws Exception {


                int error = 0;
                String tempat = tempatLahir.getText().toString();
                String tanggal = tanggalLahir.getText().toString();
                String ibu = namaIbu.getText().toString();

                if(TextUtils.isEmpty(tempat)){
                    tempatLahirLayout.setError("Tempat lahir tidak bisa kosong!");

                    error += 1;
                }else{
                    tempatLahirLayout.setError(null);
                }

                if(TextUtils.isEmpty(ibu)){
                    namaIbuLayout.setError("Nama ibu tidak bisa kosong!");

                    error += 1;
                }else{
                    namaIbuLayout.setError(null);
                }


                if(TextUtils.isEmpty(tanggal)){
                    tanggalLahirLayout.setError("Pilih tanggal lahir dahulu!");

                    error += 1;
                }else{
                    tanggalLahirLayout.setError(null);
                }


                if(error == 0){

                    Map<String, Object> form = new HashMap<>();

                    form.put("tempat_lahir", tempat);
                    form.put("tanggal_lahir", tanggal);
                    form.put("nama_ibu_kandung", ibu);

                    emitter.onNext(form);

                }else{
                    emitter.onError(new Exception("Total error " + error ));
                }



            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){

        View v = inflater.inflate(R.layout.fragment_input_lahir, container, false);

        ButterKnife.bind(this, v);

        return v;
    }


    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);

        new EditTextCalendar(getContext(), tanggalLahir);

        tanggalLahir.setFocusable(false);

    }

}
