package id.hike.apps.android_mpos_mumu.regstep.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class InputIdentitas extends FragmentForm {

    @BindView(R.id.nomor_ktp_field)
    TextInputEditText nomorKtp;

    @BindView(R.id.nomor_ktp_layout)
    TextInputEditText nomorKtpLayout;

/*
    @BindView(R.id.nama_nasabah_field)
    TextInputEditText namaNasabah;

    @BindView(R.id.nama_nasabah_layout)
    TextInputLayout namaNasabahLayout;

    @BindView(R.id.nama_singkat_field)
    TextInputEditText namaSingkat;

    @BindView(R.id.nama_singkat_field_layout)
    TextInputLayout namaSingkatLayout;


    @BindView(R.id.gender_field)
    RadioGroup genderGroup;

    @BindView(R.id.agama_field)
    Spinner agamaField;
*/


    @Override
    public Observable<Map<String, Object>> isFormComplete(){

        return Observable.create(new ObservableOnSubscribe<Map<String, Object>>() {
            @Override
            public void subscribe(ObservableEmitter<Map<String, Object>> emitter) throws Exception {

                int error = 0;
                /*
                String nasabah = namaNasabah.getText().toString();
                String singkat = namaSingkat.getText().toString();
                int agama = agamaField.getSelectedItemPosition() + 1 ;
                String gender = "";

                switch(genderGroup.getCheckedRadioButtonId()){
                    case R.id.radioM:
                        gender = "P";
                        break;

                    case R.id.radioF:
                        gender = "W";
                        break;
                }

                if(TextUtils.isEmpty(nasabah)){

                    namaNasabahLayout.setError("Nama lengkap gak bisa kosong");

                    error += 1;
                }else{
                    namaNasabahLayout.setError(null);
                }

                if(TextUtils.isEmpty(singkat)){

                    namaSingkatLayout.setError("Panggilan gak bisa kosong");

                    error += 1;
                }else{
                    namaSingkatLayout.setError(null);
                }
                */



                if(error == 0){

                    Map<String, Object> form = new HashMap<>();

                    form.put("nama_nasabah", "nasabah");
                    form.put("nama_singkat", "singkat");
                    form.put("jenis_kelamin", "gender");
                    form.put("nama_ibu_kandung", "IbuKandung");
                    //form.put("agama", String.valueOf(agama));


                    emitter.onNext(form);
                }else{
                    emitter.onError(new Exception("Total Error " + error));
                }





            }
        });
    }

    /*
    private void initAgama(){

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.agama_list, android.R.layout.simple_spinner_dropdown_item);
        agamaField.setAdapter(adapter);

    }
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){

        View v = inflater.inflate(R.layout.fragment_input_identitas, container, false);

        ButterKnife.bind(this, v);

        return v;
    }

    /*
    @Override
    public void onActivityCreated(Bundle context){
        super.onActivityCreated(context);

        initAgama();
    }
    */

}
