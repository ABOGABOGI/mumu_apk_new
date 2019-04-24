package id.hike.apps.android_mpos_mumu.features.profil;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.OnClick;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.util.MyUtils;
import io.fotoapparat.Fotoapparat;
import io.fotoapparat.parameter.ScaleType;
import io.fotoapparat.photo.BitmapPhoto;
import io.fotoapparat.result.PendingResult;
import io.fotoapparat.result.PhotoResult;
import io.fotoapparat.view.CameraView;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static io.fotoapparat.log.Loggers.fileLogger;
import static io.fotoapparat.log.Loggers.logcat;
import static io.fotoapparat.log.Loggers.loggers;
import static io.fotoapparat.parameter.selector.FocusModeSelectors.autoFocus;
import static io.fotoapparat.parameter.selector.FocusModeSelectors.continuousFocus;
import static io.fotoapparat.parameter.selector.FocusModeSelectors.fixed;
import static io.fotoapparat.parameter.selector.LensPositionSelectors.back;
import static io.fotoapparat.parameter.selector.Selectors.firstAvailable;
import static io.fotoapparat.parameter.selector.SizeSelectors.biggestSize;

public class PosCamera extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    Fotoapparat fotoapparat;
    final static String[] perms = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int RC_STORAGE = 9;
    private Uri uriFoto1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pos_camera);

        ButterKnife.bind(this);

        CameraView cameraView = (CameraView) findViewById(R.id.camera_view);

        fotoapparat = Fotoapparat
                .with(this)
                .into(cameraView)
                .build();

        Fotoapparat
                .with(this)
                .into(cameraView)           // view which will draw the camera preview
                .previewScaleType(ScaleType.CENTER_CROP)  // we want the preview to fill the view
                .photoSize(biggestSize())   // we want to have the biggest photo possible
                .lensPosition(back())       // we want back camera
                .focusMode(firstAvailable(  // (optional) use the first focus mode which is supported by device
                        continuousFocus(),
                        autoFocus(),        // in case if continuous focus is not available on device, auto focus will be used
                        fixed()             // if even auto focus is not available - fixed focus mode will be used
                ))
                /*.flash(firstAvailable(      // (optional) similar to how it is done for focus mode, this time for flash
                        autoRedEye(),
                        autoFlash(),
                        torch()
                ))*/
//                .frameProcessor(myFrameProcessor)   // (optional) receives each frame from preview stream
                .logger(loggers(            // (optional) we want to log camera events in 2 places at once
                        logcat(),           // ... in logcat
                        fileLogger(this)    // ... and to file
                ))
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (EasyPermissions.hasPermissions(this, perms)) {
            fotoapparat.start();
        } else {
            // Request one permission
            EasyPermissions.requestPermissions(this, getString(R.string.photo_permission_rationale),
                    RC_STORAGE, perms);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        fotoapparat.stop();
    }

    @OnClick(R.id.btnBatal)
    void tutup() {
        onBackPressed();
    }

    @OnClick(R.id.btnTakeFoto)
    void takeFoto() {
        if (EasyPermissions.hasPermissions(this, perms)) {
            PhotoResult photoResult = fotoapparat.takePicture();

            // Asynchronously saves photo to file
            try {
                photoResult.saveToFile(createImageFile("foto1"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Asynchronously converts photo to bitmap and returns result on main thread
            photoResult
                    .toBitmap()
                    .whenAvailable(new PendingResult.Callback<BitmapPhoto>() {
                        @Override
                        public void onResult(BitmapPhoto result) {

                            MyUtils.scanFileToGallery(uriFoto1, getBaseContext());

                            int RC_CAMERA = 1;
                            Intent returnIntent = new Intent();
                            getSecPref().edit().putString(Cfg.prefsPhotoUri_STR, uriFoto1.getPath()).apply();
                            setResult(RC_CAMERA, returnIntent);
                            finish();

                        }
                    });
        } else {
            // Request one permission
            EasyPermissions.requestPermissions(this, getString(R.string.photo_permission_rationale),
                    RC_STORAGE, perms);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    private File createImageFile(String tipeSurat) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "photo_" + timeStamp;

        //absolute path hanya dipakai untuk membuat direktory di gallery
        File storageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/temp_mpos");
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        if (tipeSurat.equals("foto1")) {
            uriFoto1 = Uri.fromFile(image);
        }
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = null;

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            if (EasyPermissions.hasPermissions(PosCamera.this, perms)) {
                Toast.makeText(PosCamera.this, R.string.returned_from_app_settings_to_activity, Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(PosCamera.this, R.string.returned_from_app_settings_to_activity_notactive, Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}
