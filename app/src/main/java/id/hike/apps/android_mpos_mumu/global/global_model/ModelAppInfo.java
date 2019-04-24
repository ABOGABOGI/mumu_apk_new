package id.hike.apps.android_mpos_mumu.global.global_model;

/**
 * Created by root on 10/10/17.
 */

public class ModelAppInfo {
    String appsId;
    String deviceId;
    String imei;

    public ModelAppInfo(String appsId, String deviceId, String imei) {
        this.appsId = appsId;
        this.deviceId = deviceId;
        this.imei = imei;
    }

    public String getAppsId() {
        return appsId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getImei() {
        return imei;
    }
}
