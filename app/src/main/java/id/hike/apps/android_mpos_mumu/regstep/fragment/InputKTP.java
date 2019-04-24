package id.hike.apps.android_mpos_mumu.regstep.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.regstep.customview.EditTextCalendar;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class InputKTP extends FragmentForm {


    @BindView(R.id.ktp_button)
    Button ktpButton;

    @BindView(R.id.ktp_field)
    ImageView ktpField;

    @BindView(R.id.nomor_ktp_field)
    TextInputEditText nomorKtp;

    @BindView(R.id.alamat_field)
    TextInputEditText alamatKtp;

    @BindView(R.id.nomor_ktp_layout)
    TextInputLayout nomorKtpLayout;

    @BindView(R.id.alamat_field_layout)
    TextInputLayout alamatFieldLayout;

    @BindView(R.id.pekerjaan_field)
    Spinner pekerjaanField;

    @BindView(R.id.status_kawin_field)
    Spinner statusKawinField;

    @BindView(R.id.tanggal_akhir_field)
    TextInputEditText tanggalAkhir;

    private Bitmap selectedImage = null;


    private final static int REQUEST_PERMISSION_CAMERA = 1;
    private final static int CAMERA_REQUEST_IMAGE = 2;

    @Override
    public Observable<Map<String, Object>> isFormComplete(){

        return Observable.create(new ObservableOnSubscribe<Map<String, Object>>() {
            @Override
            public void subscribe(ObservableEmitter<Map<String, Object>> emitter) throws Exception {


                int error = 0;
                String nomor = nomorKtp.getText().toString();
                String alamat = alamatKtp.getText().toString();
                int kawin = statusKawinField.getSelectedItemPosition() + 1;
                int pekerjaan = pekerjaanField.getSelectedItemPosition() + 1;


                if(TextUtils.isEmpty(nomor)){
                    nomorKtpLayout.setError("Nomor ktp harus diisi!");
                    error += 1;
                }else{
                    nomorKtpLayout.setError(null);
                }

                if(TextUtils.isEmpty(alamat)){
                    alamatFieldLayout.setError("Alamat ktp harus diisi!");
                    error += 1;
                }else{
                    alamatFieldLayout.setError(null);
                }

                if(selectedImage == null){
                    Toast.makeText(getContext(), "KTP Harus diambil dahulu", Toast.LENGTH_LONG).show();
                    error +=1;
                }


                if(error == 0){

                    Map<String, Object> form = new HashMap<>();
                    form.put("nomor_identitas", nomor);
                    form.put("alamat_rumah_jalan", alamat);
                    form.put("pekerjaan_id", String.valueOf(pekerjaan));
                    form.put("status_perkawinan", String.valueOf(kawin));
                    form.put("ktp", selectedImage);
                    form.put("tanggal_berakhir_identitas", tanggalAkhir.getText().toString());

                    emitter.onNext(form);


                }else{
                    emitter.onError(new Exception("Error " + error));
                }


            }
        });

    }

    private void initPekerjaan(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.pekerjaan_list, android.R.layout.simple_spinner_dropdown_item);

        pekerjaanField.setAdapter(adapter);

    }

    private void initKawin(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.kawin_list, android.R.layout.simple_spinner_dropdown_item);

        statusKawinField.setAdapter(adapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){

        View v = inflater.inflate(R.layout.fragment_input_ktp, container, false);

        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);

        initKawin();
        initPekerjaan();

        new EditTextCalendar(getContext(), tanggalAkhir);
        tanggalAkhir.setFocusable(false);

        ktpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkPermission()){
                    triggerCamera();
                }

            }
        });
    }

    private void triggerCamera(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        cameraIntent.putExtra(android.provider.MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        startActivityForResult(cameraIntent, CAMERA_REQUEST_IMAGE);

//        Intent cameraIntent = new Intent(getContext(), CameraActivity.class);
//        cameraIntent.putExtra("ktp", true);
//
//        startActivity(cameraIntent);

    }

    private void updateKtp(Bitmap bitmap){
        if(bitmap == null){
            ktpField.setVisibility(View.GONE);
        }else{
            ktpField.setVisibility(View.VISIBLE);
            ktpField.setImageBitmap(bitmap);
        }
    }

    private boolean checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getContext().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION_CAMERA);

                return false;
            } else {
                return true;
            }
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
            case REQUEST_PERMISSION_CAMERA:

                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    triggerCamera();

                }

                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent data){
        super.onActivityResult(requestCode, responseCode, data);

        switch (requestCode){
            case CAMERA_REQUEST_IMAGE:

                if(responseCode == Activity.RESULT_OK){

                    Log.i("BOWOTAG", "CAMERA DONE!");

                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    selectedImage = photo;
                    updateKtp(selectedImage);
                }

                break;
        }
    }

}
