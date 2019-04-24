package id.hike.apps.android_mpos_mumu.model;

import java.io.Serializable;

public class Propinsi implements Serializable {

    private String kdPropinsi = "";
    private String namaPropinsi = "";

    public String getPropinsi() {
        return namaPropinsi;
    }

    public void setPropinsi(String propinsi) {
        this.namaPropinsi = propinsi;
    }

    public String getKd_propinsi() {
        return kdPropinsi;
    }

    public void setKd_propinsi(String kd_propinsi) {
        this.kdPropinsi = kd_propinsi;
    }
}
