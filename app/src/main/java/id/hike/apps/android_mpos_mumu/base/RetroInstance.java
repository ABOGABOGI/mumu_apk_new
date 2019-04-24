package id.hike.apps.android_mpos_mumu.base;

import java.io.IOException;

import id.hike.apps.android_mpos_mumu.BuildConfig;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.app.BaseApplication;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetroInstance  {

    private OkHttpClient.Builder builder;
    private static final String url = Cfg.BASE_URL_DEV;

    public static String getUrl(){
        return url;
    }

    protected <Any>Any getInstance(Class<Any> typeClass) {

        if(builder == null){
            builder = new OkHttpClient.Builder();
        }

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder.interceptors().clear();

        builder.addInterceptor(interceptor);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                Headers.Builder headers = new Headers.Builder();
                headers.add("Accept", "application/json");
                headers.add("AppOrigin", BuildConfig.appHeader);
                headers.add("AppVersion", String.valueOf(BuildConfig.VERSION_CODE));

                if(BaseApplication.secPrefs != null){
                    String token = BaseApplication.secPrefs.getString(Cfg.oauthAccessToken, "");
                    String username = BaseApplication.secPrefs.getString(Cfg.prefsUserEmail, "");
                    headers.add("Authorization", "Bearer " + token);
                    headers.add("UserName", username);
                    headers.add("AccessToken", token);
                }

                Request.Builder newRequest = request.newBuilder().headers(headers.build());
                return chain.proceed(newRequest.build());
            }
        });

        GsonConverterFactory factory = GsonConverterFactory.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(factory)
                .client(builder.build())
                .build();

        return retrofit.create(typeClass);

    }

}

