package id.hike.apps.android_mpos_mumu.model;

import java.io.Serializable;

public class Kodya implements Serializable {

    private String kdKodya = "";
    private String namaKodya = "";

    public String getKd_kodya() {
        return kdKodya;
    }

    public void setKd_kodya(String kd_kodya) {
        this.kdKodya = kd_kodya;
    }

    public String getNm_kodya() {
        return namaKodya;
    }

    public void setNm_kodya(String nm_kodya) {
        this.namaKodya = nm_kodya;
    }
}
