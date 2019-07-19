package id.hike.apps.android_mpos_mumu.features.qurban.area_distribusi_adapter;

public class AreaAdapter {

    public String getNamaKotaDistribusiKurban() {
        return namaKotaDistribusiKurban;
    }

    public void setNamaKotaDistribusiKurban(String namaKotaDistribusiKurban) {
        this.namaKotaDistribusiKurban = namaKotaDistribusiKurban;
    }

    private String namaKotaDistribusiKurban;

    public AreaAdapter(String namaKotaDistribusiKurban) {
        this.namaKotaDistribusiKurban = namaKotaDistribusiKurban;
    }

    public AreaAdapter(){

    }
}
