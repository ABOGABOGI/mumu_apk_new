package id.hike.apps.android_mpos_mumu.model;

import java.io.Serializable;

public class Kecamatan implements Serializable {

    private String kdKecamatan = "";
    private String namaKecamatan = "";

    public String getKd_kecamatan() {
        return kdKecamatan;
    }

    public void setKd_kecamatan(String kd_kecamatan) {
        this.kdKecamatan = kd_kecamatan;
    }

    public String getNm_kecamatan() {
        return namaKecamatan;
    }

    public void setNm_kecamatan(String nm_kecamatan) {
        this.namaKecamatan = nm_kecamatan;
    }
}
