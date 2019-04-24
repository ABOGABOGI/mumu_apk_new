package id.hike.apps.android_mpos_mumu.model;

import java.io.Serializable;

public class Kelurahan implements Serializable {

    private String kdKelurahan = "";
    private String namaKelurahan = "";
    private String kodePos = "";

    public String getKd_kelurahan() {
        return kdKelurahan;
    }

    public void setKd_kelurahan(String kd_kelurahan) {
        this.kdKelurahan = kd_kelurahan;
    }

    public String getNm_kelurahan() {
        return namaKelurahan;
    }

    public void setNm_kelurahan(String nm_kelurahan) {
        this.namaKelurahan = nm_kelurahan;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }
}
