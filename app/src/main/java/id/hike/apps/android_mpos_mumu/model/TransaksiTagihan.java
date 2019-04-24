package id.hike.apps.android_mpos_mumu.model;

public class TransaksiTagihan {

    private String produk;
    private String id_trx;
    private String nomor_pelanggan;
    private String periode;
    private String nilai_tagihan;
    private String status;

    public String getProduk() {
        return produk;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

    public String getId_trx() {
        return id_trx;
    }

    public void setId_trx(String id_trx) {
        this.id_trx = id_trx;
    }

    public String getNomor_pelanggan() {
        return nomor_pelanggan;
    }

    public void setNomor_pelanggan(String nomor_pelanggan) {
        this.nomor_pelanggan = nomor_pelanggan;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getNilai_tagihan() {
        return nilai_tagihan;
    }

    public void setNilai_tagihan(String nilai_tagihan) {
        this.nilai_tagihan = nilai_tagihan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
