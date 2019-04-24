package id.hike.apps.android_mpos_mumu.features.landing_page.model;

/**
 * Created by root on 31/07/17.
 */

public class ModelTransaksiTertunda2 {
    public String totalHarga;
    public String nama;
    public String transaksiId;
    public String customerPhone;
    public String negoDisc;
    public String transDate;
    public String email;
    public String transType;

    public ModelTransaksiTertunda2(String totalHarga, String nama, String transaksiId, String customerPhone, String transDate, String negoDisc, String email, String transType) {
        this.totalHarga = totalHarga;
        this.nama = nama;
        this.transaksiId = transaksiId;
        this.customerPhone = customerPhone;
        this.transDate = transDate;
        this.negoDisc=negoDisc;
        this.email=email;
        this.transType=transType;
    }
}
