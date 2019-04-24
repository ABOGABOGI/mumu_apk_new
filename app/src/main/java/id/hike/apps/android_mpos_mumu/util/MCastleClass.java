package id.hike.apps.android_mpos_mumu.util;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.features.landing_page.LandingPage;
import tw.com.castech.ctpaylibrary.CastlesClass;

/**
 * Created by root on 11/09/17.
 */

public class MCastleClass {
    static final String LOG = "MCastleClass";
    private static CastlesClass mCastleClass = new CastlesClass();
    private static int ret;
    private static MCheckStatusCr mCheckStatusCr;

    public static CastlesClass getInstance() {
        return mCastleClass;
    }

    private MCastleClass() {
    }

    public static int getConnectionStatus() {
        return ret;
    }

    public static void checkStatusCr(String from, Handler handler, Activity activity) {
        mCheckStatusCr = new MCheckStatusCr(handler, from, activity);
        mCheckStatusCr.start();
    }

    public static class MCheckStatusCr extends Thread {

        Handler handler;
        String from;
        Activity activity;
        public static boolean setKill=false;

        public MCheckStatusCr(Handler handler, String from, Activity activity) {
            this.handler = handler;
            this.from = from;
            this.activity = activity;
        }

        @Override
        public void run() {
            if (setKill == false) {
                BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                // If the adapter is null, then Bluetooth is not active or not supported
                if (mBluetoothAdapter == null) {
                    Log.d(LOG, "bluetooth not supported");
                    handler.sendEmptyMessage(Cfg.BT_NOT_SUPPORTED);
                }

                if (!mBluetoothAdapter.isEnabled()) {
                    Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

                    switch (from) {
                        case "fcheckconnection":
                            // Resultnya muncul di fragment
//                            F_CheckConnection fCheckConnection = (F_CheckConnection) ((LoginActivity) activity).getSupportFragmentManager().findFragmentById(R.id.activity_main);
//                            fCheckConnection.startActivityForResult(enableIntent, 3);
                            handler.sendEmptyMessage(Cfg.BT_CONNECT_BT);

                            break;
                        case "landing_page":
                            ((LandingPage) activity).startActivityForResult(enableIntent, 3);
                            handler.sendEmptyMessage(Cfg.BT_CONNECT_BT);
                            break;
                    }
                }

                ret = mCastleClass.ctpConnectBluetoothDevice("MP200-0566");
                if (ret == Cfg.BT_RETURN_SUCCESS) {
                    Log.d(LOG, "connect card reader success");
                    handler.sendEmptyMessage(Cfg.BT_RETURN_SUCCESS);
                } else if (ret == Cfg.BT_ALREADY_CONNECT) {
                    Log.d(LOG, "card reader already connect");
                    handler.sendEmptyMessage(Cfg.BT_ALREADY_CONNECT);
                } else {
                    Log.d(LOG, "connect card reader failed");
                    handler.sendEmptyMessage(Cfg.BT_CONNECT_CR_FAILED);
                }

                setKill=true;
                mCheckStatusCr = null;
            }
        }

        static public void setSetKill(boolean setKill) {
            setKill = setKill;
        }
    }
}