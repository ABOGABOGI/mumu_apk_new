package id.hike.apps.android_mpos_mumu.regstep.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import id.hike.apps.android_mpos_mumu.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class InputSelfie extends FragmentForm {

    @BindView(R.id.selfie_button)
    Button selfieButton;

    @BindView(R.id.selfie_field)
    CircleImageView selfieField;

    private final static int REQUEST_PERMISSION_CAMERA = 1;
    private final static int CAMERA_REQUEST_IMAGE = 2;
    private Bitmap selectedImage = null;

    @Override
    public Observable<Map<String, Object>> isFormComplete(){
        return Observable.create(new ObservableOnSubscribe<Map<String, Object>>() {
            @Override
            public void subscribe(ObservableEmitter<Map<String, Object>> emitter) throws Exception {

                if(selectedImage != null){

                    Map<String, Object> form = new HashMap<>();
                    form.put("selfie", selectedImage);

                    emitter.onNext(form);

                }else{
                    Toast.makeText(getContext(),"Selfie belum diambil", Toast.LENGTH_LONG).show();
                    emitter.onError(new Exception("Image not taken!"));
                }

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){

        View v = inflater.inflate(R.layout.fragment_input_selfie, container, false);

        ButterKnife.bind(this, v);

        return v;

    }


    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);


        selfieButton.setOnClickListener(new View.OnClickListener() {
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
//        cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_IMAGE);
    }

    private void updateImage(Bitmap bitmap){
        if(bitmap == null){
            selfieField.setVisibility(View.GONE);
        }else{
            selfieField.setVisibility(View.VISIBLE);
            selfieField.setImageBitmap(bitmap);
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
                    updateImage(selectedImage);
                }

                break;
        }
    }
}
