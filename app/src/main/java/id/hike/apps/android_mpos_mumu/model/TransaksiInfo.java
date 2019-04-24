package id.hike.apps.android_mpos_mumu.model;

import java.io.Serializable;

public class TransaksiInfo implements Serializable {

    private String trx_id;
    private String trx_produk_nama;
    private String trx_pelanggan_id;
    private String trx_tgl_mulai;
    private String trx_tgl_selesai;
    private String trx_status;
    private String trx_rcode;
    private String trx_kode_referensi_sn;
    private String trx_nilai;

    public String getTrx_id() {
        return trx_id;
    }

    public void setTrx_id(String trx_id) {
        this.trx_id = trx_id;
    }

    public String getTrx_produk_nama() {
        return trx_produk_nama;
    }

    public void setTrx_produk_nama(String trx_produk_nama) {
        this.trx_produk_nama = trx_produk_nama;
    }

    public String getTrx_pelanggan_id() {
        return trx_pelanggan_id;
    }

    public void setTrx_pelanggan_id(String trx_pelanggan_id) {
        this.trx_pelanggan_id = trx_pelanggan_id;
    }

    public String getTrx_tgl_mulai() {
        return trx_tgl_mulai;
    }

    public void setTrx_tgl_mulai(String trx_tgl_mulai) {
        this.trx_tgl_mulai = trx_tgl_mulai;
    }

    public String getTrx_tgl_selesai() {
        return trx_tgl_selesai;
    }

    public void setTrx_tgl_selesai(String trx_tgl_selesai) {
        this.trx_tgl_selesai = trx_tgl_selesai;
    }

    public String getTrx_status() {
        return trx_status;
    }

    public void setTrx_status(String trx_status) {
        this.trx_status = trx_status;
    }

    public String getTrx_rcode() {
        return trx_rcode;
    }

    public void setTrx_rcode(String trx_rcode) {
        this.trx_rcode = trx_rcode;
    }

    public String getTrx_nilai() {
        return trx_nilai;
    }

    public void setTrx_nilai(String trx_nilai) {
        this.trx_nilai = trx_nilai;
    }

    public String getTrx_kode_referensi_sn() {
        return trx_kode_referensi_sn;
    }

    public void setTrx_kode_referensi_sn(String trx_kode_referensi_sn) {
        this.trx_kode_referensi_sn = trx_kode_referensi_sn;
    }
}
