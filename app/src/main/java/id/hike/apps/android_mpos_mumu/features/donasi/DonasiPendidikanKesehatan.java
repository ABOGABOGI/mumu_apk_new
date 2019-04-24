package id.hike.apps.android_mpos_mumu.features.donasi;

import java.io.Serializable;

public class DonasiPendidikanKesehatan implements Serializable {

    private String kdDonasiPendidikanKesehatan;
    private String donasiPendidikanKesehatan = "";
    private String kodePos = "";

    public DonasiPendidikanKesehatan(String kdDonasiPendidikanKesehatan, String donasiPendidikanKesehatan){

        this.donasiPendidikanKesehatan = donasiPendidikanKesehatan;
        this.kdDonasiPendidikanKesehatan = kdDonasiPendidikanKesehatan;

    }

    public String getKdDonasiPendidikanKesehatan(){
        return kdDonasiPendidikanKesehatan;
    }

    public String getDonasiPendidikanKesehatan(){
        return donasiPendidikanKesehatan;
    }

    public String toString(){
        return this.donasiPendidikanKesehatan;
    }
}
