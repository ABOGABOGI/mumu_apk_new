package id.hike.apps.android_mpos_mumu.features.kamera;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.io.IOException;

import static android.content.Context.WINDOW_SERVICE;

public class CameraSurface extends SurfaceView implements SurfaceHolder.Callback {
    private Camera mCamera;
    private SurfaceHolder mHolder;

    private boolean isCameraPreview = false;

    public CameraSurface(Context context) {
        super(context);
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        mCamera = Camera.open();
    }

    public void startPreview(){
        isCameraPreview = true;
        Camera.CameraInfo info = new Camera.CameraInfo();
        mCamera.setDisplayOrientation(getCorrectCameraOrientation(info, mCamera));
        mCamera.startPreview();
    }
//
//    public Bitmap getBitmap(){
//        setDrawingCacheEnabled(true);
//        buildDrawingCache(true);
//        final Bitmap bitmap = Bitmap.createBitmap(getDrawingCache());
//
//        return bitmap;
//    }



    @Override
    public void surfaceCreated(SurfaceHolder surface) {
        if (mHolder == null) {
            mHolder = surface;
        }
        if (mCamera != null) {
            try {
                mCamera.setPreviewDisplay(surface);
            } catch (IOException e) {
                // Something bad happened
                e.printStackTrace();
            }
        }else{
            Log.i("BOWOTAG", "camera is null!");
        }
    }

    public boolean isRunning(){
        return  isCameraPreview;
    }

    public void pause(){
        isCameraPreview = false;
        mCamera.stopPreview();
    }

    public void destroy(){
        mCamera.release();
    }

    public int getCorrectCameraOrientation(Camera.CameraInfo info, Camera camera) {

        int rotation = ((WindowManager) getContext().getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getRotation();
        int degrees = 0;

        switch(rotation){
            case Surface.ROTATION_0:
                degrees = 0;
                break;

            case Surface.ROTATION_90:
                degrees = 90;
                break;

            case Surface.ROTATION_180:
                degrees = 180;
                break;

            case Surface.ROTATION_270:
                degrees = 270;
                break;

        }

        int result;
        if(info.facing==Camera.CameraInfo.CAMERA_FACING_FRONT){
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;
        }else{
            result = (info.orientation - degrees + 360) % 360;
        }

        return result;
    }



    @Override
    public void surfaceChanged(SurfaceHolder surface, int format, int width, int height) {
//            if (isCameraPreview) {
//                mCamera.stopPreview();
//                isCameraPreview = false;
//            }
//
//            Camera.Parameters parameters = mCamera.getParameters();
//            Display display = ((WindowManager) getContext().getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
//
//            if(display.getRotation() == Surface.ROTATION_0) {
//                parameters.setPreviewSize(height, width);
//                mCamera.setDisplayOrientation(90);
//            }
//
//            if(display.getRotation() == Surface.ROTATION_90) {
//                parameters.setPreviewSize(width, height);
//            }
//
//            if(display.getRotation() == Surface.ROTATION_180) {
//                parameters.setPreviewSize(height, width);
//            }
//
//            if(display.getRotation() == Surface.ROTATION_270) {
//                parameters.setPreviewSize(width, height);
//                mCamera.setDisplayOrientation(180);
//            }
//
//            mCamera.setParameters(parameters);
//            startPreview();
//            isCameraPreview = true;

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surface) {

    }
}

