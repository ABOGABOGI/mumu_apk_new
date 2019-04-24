package id.hike.apps.android_mpos_mumu.features.landing_page.api;

import java.io.File;

import id.hike.apps.android_mpos_mumu.base.RetroInstance;
import id.hike.apps.android_mpos_mumu.model.ImageUpload;
import id.hike.apps.android_mpos_mumu.model.Response;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class UploadImageApi extends RetroInstance {


    public static UploadImageApi create(){
        return new UploadImageApi();
    }

    interface UploadImageService{

        @POST("/resource-service/upload/")
        Call<Response<ImageUpload>> uploadImage(@Body MultipartBody body);

    }


    private UploadImageService getService(){
        return getInstance(UploadImageService.class);
    }

    public Observable<Response<ImageUpload>> uploadImage(final File file, final String id, final String type){

        RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);

        MultipartBody.Builder body = new MultipartBody.Builder();
        body.setType(MultipartBody.FORM);

        body.addFormDataPart("id", id);
        body.addFormDataPart("type", type);

        body.addFormDataPart("file", "file_image.png", fileBody);


        return Observable.create(new ObservableOnSubscribe<Response<ImageUpload>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<ImageUpload>> emitter) throws Exception {
                getService().uploadImage(body.build()).enqueue(new Callback<Response<ImageUpload>>() {
                    @Override
                    public void onResponse(Call<Response<ImageUpload>> call, retrofit2.Response<Response<ImageUpload>> response) {
                        if(response.isSuccessful()){
                            emitter.onNext(response.body());
                        }else{
                            emitter.onError(new Exception(response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<ImageUpload>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });
            }
        });


    }

}
