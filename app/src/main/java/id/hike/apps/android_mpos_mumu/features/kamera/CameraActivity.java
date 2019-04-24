package id.hike.apps.android_mpos_mumu.features.kamera;

import androidx.appcompat.app.AppCompatActivity;
import id.hike.apps.android_mpos_mumu.R;
import io.fotoapparat.Fotoapparat;
import io.fotoapparat.view.CameraView;
import io.fotoapparat.parameter.ScaleType;
import pub.devrel.easypermissions.EasyPermissions;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class CameraActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    private CameraView cameraView;
    private KTPViewPort ktpViewPort;
    private SelfieViewPort selfieViewPort;
    private Button ambilFoto;
    private Fotoapparat fotoapparat;

    private final int PERMISSION_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera2);


        if(EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)){
            run();
        }else{
            EasyPermissions.requestPermissions(this, "ID", PERMISSION_CAMERA,new String[]{
                    Manifest.permission.CAMERA
            });
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    public void setKTPVisible(int visible){
        ktpViewPort.setVisibility(visible);
    }

    public void setSelfieVisible(int visible){
        selfieViewPort.setVisibility(visible);
    }

    private void run(){
        cameraView = (CameraView) findViewById(R.id.camera_view);
        ktpViewPort = (KTPViewPort) findViewById(R.id.ktpViewPort);
        selfieViewPort = (SelfieViewPort) findViewById(R.id.selfieViewPort);
        ambilFoto = findViewById(R.id.ambilFoto);

        fotoapparat = Fotoapparat
                .with(this)
                .into(cameraView)
                .previewScaleType(ScaleType.CENTER_CROP)
                .build();

        fotoapparat.start();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            if(bundle.containsKey("ktp")){
                boolean ktp = bundle.getBoolean("ktp");

                if(ktp){
                    setKTPVisible(View.VISIBLE);
                }else{
                    setKTPVisible(View.GONE);
                }
            }
        }


        ambilFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        run();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }


    @Override
    public void onPause(){
        super.onPause();

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        fotoapparat.stop();
    }

    @Override
    public  void onResume(){
        super.onResume();

    }
}
