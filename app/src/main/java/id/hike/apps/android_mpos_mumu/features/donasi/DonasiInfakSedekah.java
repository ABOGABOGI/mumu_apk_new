package id.hike.apps.android_mpos_mumu.features.donasi;

import java.io.Serializable;

public class DonasiInfakSedekah implements Serializable {

    private String kdDonasiInfakSedekah;
    private String kategoriDonasi = "";
    private String kodePos = "";


    public String getKdDonasiInfakSedekah(){
        return kdDonasiInfakSedekah;
    }

    public String getKategoriDonasi(){
        return kategoriDonasi;
    }

    public String toString(){
        return this.kategoriDonasi;
    }
}
