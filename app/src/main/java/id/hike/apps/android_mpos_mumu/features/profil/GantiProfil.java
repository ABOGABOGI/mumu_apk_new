package id.hike.apps.android_mpos_mumu.features.profil;


import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iceteck.silicompressorr.SiliCompressor;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseFragment;
import id.hike.apps.android_mpos_mumu.util.ApiClient;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.util.FileUtils3;
import id.hike.apps.android_mpos_mumu.util.ImageCompressor;
import id.hike.apps.android_mpos_mumu.util.MyUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class GantiProfil extends BaseFragment implements EasyPermissions.PermissionCallbacks {
    final static String[] perms = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    static final String MULTIPART_FORM_DATA = "multipart/form-data";
    private static final int RC_STORAGE = 9;
    private final int CAMERA_REQCODE_FOTO1 = 1;
    private final int GALLERY_REQCODE_FOTO1 = 5;
    ImageView btnTakeFoto;
    EditText etAlamat, etNama, etEmail, etTelepon;
    private Uri uriFoto1;

    public GantiProfil() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ganti_profil, container, false);

        ((Profil) getActivity()).setTitle(getString(R.string.title_ganti_profil));

        btnTakeFoto = (ImageView) view.findViewById(R.id.btnTakeFoto);

        Picasso.get().load(Cfg.PROFIL_IMAGEURL + getFragSecPrefs().getString(Cfg.prefsUrlAvatarKasir_Str, "")).into(btnTakeFoto);
        btnTakeFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogMedia("foto1");
            }
        });

        etAlamat = (EditText) view.findViewById(R.id.etAlamat);
        etNama = (EditText) view.findViewById(R.id.etName);
        etEmail = (EditText) view.findViewById(R.id.editText_email_login);
        etTelepon = (EditText) view.findViewById(R.id.etTelepon);

        etAlamat.setText(getFragSecPrefs().getString(Cfg.prefsAlamatKasir_Str, ""));
        etNama.setText(getFragSecPrefs().getString(Cfg.prefsRealEmployeeName_Str, ""));
        etEmail.setText(getFragSecPrefs().getString(Cfg.prefsKasirEmail_Str, ""));
        etTelepon.setText(getFragSecPrefs().getString(Cfg.prefsTeleponKasir_Str, ""));

        Button btnSimpan = (Button) view.findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (
                        etAlamat.getText().toString().isEmpty() ||
                                etEmail.getText().toString().isEmpty() ||
                                etTelepon.getText().toString().isEmpty() ||
                                etNama.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), getString(R.string.mohon_lengkapi_form), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (uriFoto1 != null) {
                    uploadPhoto();
                } else {
                    updateProfilNoPhoto();
                }
            }
        });
        return view;
    }

    int RC_PHOTOCODE = 192;

    private void onDialogMedia(final String tipeSurat) {
        final CharSequence[] items = {"Kamera", "Galeri"};

        AlertDialog.Builder mDialog = new AlertDialog.Builder(getActivity())
                .setTitle("Ambil Gambar")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("Kamera")) {
//                            pickOnCamera(tipeSurat);

                            Intent intent = new Intent(getActivity(), PosCamera.class);
                            startActivityForResult(intent, CAMERA_REQCODE_FOTO1);
                        } else {
                            pickOnGallery(tipeSurat);
                        }
                    }
                });
        mDialog.show();
    }

    void pickOnCamera(String tipeSurat) {

        if (EasyPermissions.hasPermissions(getContext(), perms)) {

            try {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File photoFile = null;
                try {
                    photoFile = createImageFile(tipeSurat);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));

                switch (tipeSurat) {
                    case "foto1":
                        startActivityForResult(cameraIntent, CAMERA_REQCODE_FOTO1);
                        break;
                }

            } catch (ActivityNotFoundException e) {
                Log.d(GantiProfil.class.getSimpleName(), getString(R.string.error_pick_on_kamera), e);
            }
        } else {
            // Request one permission
            EasyPermissions.requestPermissions(this, getString(R.string.photo_permission_rationale),
                    RC_STORAGE, perms);
        }
    }

    private void pickOnGallery(String tipeSurat) {
        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_PICK);
            String sugest = "Pilih Foto";
            switch (tipeSurat) {
                case "foto1":
                    startActivityForResult(Intent.createChooser(intent, sugest), GALLERY_REQCODE_FOTO1);
                    break;
            }
        } catch (ActivityNotFoundException e) {
            Log.d(GantiProfil.class.getSimpleName(), getString(R.string.error_pick_on_gallery), e);
        }
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

    private File getDestinationFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
//        String imageFileName = "photo_" + timeStamp;

        //absolute path hanya dipakai untuk membuat direktory di gallery
        File storageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/temp_mpos");
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
        /*if (tipeSurat.equals("foto1")) {
            uriFoto1 = Uri.fromFile(image);
        }*/
        return storageDir;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap = null;
        final Profil pa = (Profil) getActivity();
        switch (requestCode) {
            case CAMERA_REQCODE_FOTO1:
                    try {
                        uriFoto1 = Uri.fromFile(new File(getFragSecPrefs().getString(Cfg.prefsPhotoUri_STR, null)));
                        bitmap = getThumbnail(uriFoto1);
                        String compressedImage = SiliCompressor.with(getContext()).compress(uriFoto1.toString(), getDestinationFile());

                        // lebih dari 5 MB file yang dikompres
                        if (MyUtils.getImageSize(compressedImage) >= 5242880L) {
                            Toast.makeText(pa, getString(R.string.image_terlalu_besar), Toast.LENGTH_LONG).show();
                            uriFoto1 = null;
                            return;
                        }
                        uriFoto1 = Uri.fromFile(new File(compressedImage));
                        MyUtils.scanFileToGallery(uriFoto1, getContext());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    btnTakeFoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    btnTakeFoto.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 102, 102, true));
                    btnTakeFoto.setRotation(90F);
                break;
            case GALLERY_REQCODE_FOTO1:
                    try {
                        bitmap = getThumbnail(data.getData());
                        String compressedImage = ImageCompressor.compressImage(data.getData().toString(), getActivity());

                        // lebih dari 5 MB file yang dikompres
                        if (MyUtils.getImageSize(compressedImage) >= 5242880L) {
                            Toast.makeText(pa, getString(R.string.image_terlalu_besar), Toast.LENGTH_LONG).show();
                            uriFoto1 = null;
                            return;
                        }

                        uriFoto1 = Uri.fromFile(new File(compressedImage));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    btnTakeFoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
                break;
        }

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            if (EasyPermissions.hasPermissions(getActivity(), perms)) {
                Toast.makeText(getActivity(), R.string.returned_from_app_settings_to_activity, Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(getActivity(), R.string.returned_from_app_settings_to_activity_notactive, Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    /**
     * A safe way to get an instance of the Camera object.
     */
    public Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        } catch (Exception e) {
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    /**
     * script agar suatu activity tidak terlalu besar memorinya
     * jadi gambar tersebut dijadikan ukuran thumbnail hanya diactivity saja
     * tapi kalo dikirimkan, adalah file aslinya
     */
    public Bitmap getThumbnail(Uri uri) throws IOException {
        final int THUMBNAIL_SIZE = 100; //in pixel
        InputStream input = getActivity().getContentResolver().openInputStream(uri);

        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true; //optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888; //optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1))
            return null;

        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;

        double ratio = (originalSize > THUMBNAIL_SIZE) ? (originalSize / THUMBNAIL_SIZE) : 1.0;

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither = true; //optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888; //optional
        input = getActivity().getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();
        return bitmap;
    }

    private int getPowerOfTwoForSampleRatio(double ratio) {
        int k = Integer.highestOneBit((int) Math.floor(ratio));
        if (k == 0) return 1;
        else return k;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    void uploadPhoto() {
        dfLoading.show(getFragmentManager(), "");
        Profil pa = (Profil) getActivity();
        Gson gson = new Gson();
        String json = gson.toJson(new ReqUpdateProfilWithFoto(
                etNama.getText().toString(),
                etAlamat.getText().toString(),
                getFragSecPrefs().getString(Cfg.prefsGenderKasir_Str, "L"),
                getFragSecPrefs().getString(Cfg.prefsPinKasir_Str, ""),
                etTelepon.getText().toString(),
                "kasir",
                String.valueOf(getFragSecPrefs().getInt(Cfg.prefsKasirId_INT, 0)),
                etEmail.getText().toString(),
                getFragSecPrefs().getString(Cfg.prefsNamaKasir_STR, Cfg.defaultKasirName), 1,
                getFragSecPrefs().getInt(Cfg.prefsStoreId_INT, Cfg.defaultStoreId),
                Integer.parseInt(getFragSecPrefs().getString(Cfg.prefsOutletId_STR, ""))
        ));

        SvProfil svProfil = ApiClient.serviceGenerator(Cfg.BASEURL_IDENTITY).create(SvProfil.class);
        Call<Void> call = svProfil.updateProfilWithPhoto(
                createPartFromString(json), prepareFilePart("file", uriFoto1)
        );

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                dfLoading.dismiss();
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), getString(R.string.update_profil_sukses), Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    Toast.makeText(getActivity(), Cfg.ERRNOT200, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                dfLoading.dismiss();
                Toast.makeText(getActivity(), Cfg.ERR500, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateProfilNoPhoto() {
        dfLoading.show(getFragmentManager(), "");
        Gson gson = new Gson();
        String json = gson.toJson(new ReqUpdateProfilNoFoto(
                etNama.getText().toString(),
                etAlamat.getText().toString(),
                getFragSecPrefs().getString(Cfg.prefsGenderKasir_Str, "L"),
                getFragSecPrefs().getString(Cfg.prefsPinKasir_Str, ""),
                etTelepon.getText().toString(),
                "kasir",
                String.valueOf(getFragSecPrefs().getInt(Cfg.prefsKasirId_INT, 0)),
                etEmail.getText().toString(),
                getFragSecPrefs().getString(Cfg.prefsNamaKasir_STR, Cfg.defaultKasirName),
                getFragSecPrefs().getString(Cfg.prefsUrlAvatarKasir_Str, ""), 1,
                getFragSecPrefs().getInt(Cfg.prefsStoreId_INT, Cfg.defaultStoreId),
                Integer.parseInt(getFragSecPrefs().getString(Cfg.prefsOutletId_STR, ""))
        ));

        SvProfil svProfil = ApiClient.serviceGenerator(Cfg.BASEURL_IDENTITY).create(SvProfil.class);
        Call<Void> call = svProfil.updateProfilNoPhoto(
                createPartFromString(json)
        );

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                dfLoading.dismiss();
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), getString(R.string.update_profil_sukses), Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    Toast.makeText(getActivity(), Cfg.ERRNOT200, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                dfLoading.dismiss();
                Toast.makeText(getActivity(), Cfg.ERR500, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        File file = FileUtils3.getFile(getActivity(), fileUri);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);

        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }
}
