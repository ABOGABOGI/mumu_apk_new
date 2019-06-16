package id.hike.apps.android_mpos_mumu;

public class ListKotaAsal {

    private String namaKota;

    public ListKotaAsal(){

    }

    public ListKotaAsal(String namaKota) {
        this.namaKota = namaKota;
    }

    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }

    public String getNamaKota() {
        return namaKota;
    }
}
