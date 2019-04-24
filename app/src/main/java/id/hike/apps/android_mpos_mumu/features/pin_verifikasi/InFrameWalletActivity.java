package id.hike.apps.android_mpos_mumu.features.pin_verifikasi;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.landing_page.LandingPage;
import id.hike.apps.android_mpos_mumu.model.WalletCRP;
import id.hike.apps.android_mpos_mumu.model.WalletReqRes;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

public class InFrameWalletActivity extends AppCompatActivity {


    interface WebViewService{
        @POST()
        Call<ResponseBody> getResponse(@Url String url, @Body WalletReqRes object);

        @POST()
        Call<ResponseBody> getResponse(@Url String url, @Body WalletCRP object);
    }

    @BindView(R.id.webview)
    WebView webView;

    @BindView(R.id.toolbar)
    View toolbar;

    private WalletReqRes reqRes;
    private WalletCRP walletCRP;

    private String baseUrl = null;
    private String url = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_frame_wallet);
        ButterKnife.bind(this);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.setPadding(0,0,0,0);
        webView.setInitialScale(getScale());
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);


        TextView title = toolbar.findViewById(R.id.abTvTitle);
        ImageView backBtn = toolbar.findViewById(R.id.btnBack);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InFrameWalletActivity.this, LandingPage.class);
                startActivity(intent);

                finishAffinity();
            }
        });

        Bundle bundle = getIntent().getExtras();

        if(bundle != null && bundle.containsKey("title")){
            title.setText(bundle.getString("title"));
        }

        if(bundle != null && bundle.containsKey("base_url")){
            baseUrl = bundle.getString("base_url");
        }

        if(bundle != null && bundle.containsKey("url")){
            url = bundle.getString("url");
        }


        if(bundle != null && bundle.containsKey("info")){
            reqRes = (WalletReqRes) bundle.getSerializable("info");
            Gson gson = new Gson();
            String json = gson.toJson(reqRes);
            Log.i("DEBUGTAG", json);

            loadPost(reqRes);
        }


        if(bundle != null && bundle.containsKey("infoCrp")){
            walletCRP = (WalletCRP) bundle.getSerializable("infoCrp");

            Log.d("infoCrp", "waw");
            loadPost(walletCRP);
        }



    }


    private void loadPost(WalletCRP reqRes){


        if(baseUrl == null){
            baseUrl = Cfg.INFRAME_URL;
        }


        if(url == null){
            url = "/h2h";
        }

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        GsonConverterFactory factory = GsonConverterFactory.create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(factory)
                .client(builder.build())
                .build();

        WebViewService service = retrofit.create(WebViewService.class);


        service.getResponse(url, reqRes).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {

                    String body = response.body().string();
                    Log.i("DEBUGTAG", body);
                    webView.loadDataWithBaseURL(null, body, "text/html", "utf-8", null);
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("BOWOTAG", t.getLocalizedMessage());
            }
        });


    }

    private void loadPost(WalletReqRes reqRes){


        if(baseUrl == null){
            baseUrl = Cfg.INFRAME_URL;
        }


        if(url == null){
            url = "/h2h";
        }

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        GsonConverterFactory factory = GsonConverterFactory.create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(factory)
                .client(builder.build())
                .build();

        WebViewService service = retrofit.create(WebViewService.class);


        service.getResponse(url, reqRes).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {

                    String body = response.body().string();
                    Log.i("DEBUGTAG", body);
                    webView.loadDataWithBaseURL(null, body, "text/html", "utf-8", null);
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("BOWOTAG", t.getLocalizedMessage());
            }
        });


    }

    private int getScale(){
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        Double val = new Double(width)/new Double(360);
        val = val * 100d;
        return val.intValue();
    }
}
