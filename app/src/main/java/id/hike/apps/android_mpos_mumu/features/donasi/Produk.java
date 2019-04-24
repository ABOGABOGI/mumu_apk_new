package id.hike.apps.android_mpos_mumu.features.donasi;

import java.io.Serializable;

public class Produk implements Serializable {

    private String kdDonasiPendidikanKesehatan;
    private String donasiPendidikanKesehatan = "";
    private String kdDonasiInfakSedekah = "";
    private String donasiInfakSedekah = "";
    private String kodePos = "";

    public Produk(String kdDonasiPendidikanKesehatan, String donasiPendidikanKesehatan, String kdDonasiInfakSedekah, String donasiInfakSedekah){

        this.donasiPendidikanKesehatan = donasiPendidikanKesehatan;
        this.kdDonasiPendidikanKesehatan = kdDonasiPendidikanKesehatan;

        this.kdDonasiInfakSedekah = kdDonasiInfakSedekah;
        this.donasiInfakSedekah = donasiInfakSedekah;

    }

    public String getKdDonasiPendidikanKesehatan(){
        return kdDonasiPendidikanKesehatan;
    }

    public String getDonasiPendidikanKesehatan(){
        return donasiPendidikanKesehatan;
    }

    public String getDonasiInfakSedekah(){
        return donasiInfakSedekah;
    }

    public String getKdDonasiInfakSedekah(){
        return kdDonasiInfakSedekah;
    }

    public String toString(){
        return this.donasiPendidikanKesehatan;
    }
}
