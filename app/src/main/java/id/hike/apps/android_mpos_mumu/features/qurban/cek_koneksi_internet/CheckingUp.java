package id.hike.apps.android_mpos_mumu.features.qurban.cek_koneksi_internet;

import android.app.Application;

public class CheckingUp extends Application {

    private static CheckingUp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized CheckingUp getInstance(){
        return mInstance;
    }

    public void setConnectivityListener (ConnectivityReciever.ConnectivityReceiverListener listener){
        ConnectivityReciever.connectivityReceiverListener = listener;
    }
}
