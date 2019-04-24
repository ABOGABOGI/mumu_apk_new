package id.hike.apps.android_mpos_mumu.model;

import java.io.Serializable;

public class Produk implements Serializable {

    private int id_produk;

    private String kategori;

    private String sub_kategori;

    private String biller;

    private String name;

    private int nominal;

    private String sts_live;

    private String tipe_bayar;

    private int pattern_no;

    private int pattern_cetak;

    private String pattern_label_id;

    private String keterangan;

    private String nickname;

    private Integer harga;

    private String kode_produk;

    private Integer biaya_admin;

    private String kdDonasiPendidikanKesehatan;
    private String donasiPendidikanKesehatan = "";
    private String kdDonasiInfakSedekah = "";
    private String donasiInfakSedekah = "";
    private String kodePos = "";

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

    public int getId_produk() {
        return id_produk;
    }

    public void setId_produk(int id_produk) {
        this.id_produk = id_produk;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getSub_kategori() {
        return sub_kategori;
    }

    public void setSub_kategori(String sub_kategori) {
        this.sub_kategori = sub_kategori;
    }

    public String getBiller() {
        return biller;
    }

    public void setBiller(String biller) {
        this.biller = biller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getSts_live() {
        return sts_live;
    }

    public void setSts_live(String sts_live) {
        this.sts_live = sts_live;
    }

    public String getTipe_bayar() {
        return tipe_bayar;
    }

    public void setTipe_bayar(String tipe_bayar) {
        this.tipe_bayar = tipe_bayar;
    }

    public int getPattern_no() {
        return pattern_no;
    }

    public void setPattern_no(int pattern_no) {
        this.pattern_no = pattern_no;
    }

    public int getPattern_cetak() {
        return pattern_cetak;
    }

    public void setPattern_cetak(int pattern_cetak) {
        this.pattern_cetak = pattern_cetak;
    }

    public String getPattern_label_id() {
        return pattern_label_id;
    }

    public void setPattern_label_id(String pattern_label_id) {
        this.pattern_label_id = pattern_label_id;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public String getKode_produk() {
        return kode_produk;
    }

    public void setKode_produk(String kode_produk) {
        this.kode_produk = kode_produk;
    }

    public Integer getBiaya_admin() {
        return biaya_admin;
    }

    public void setBiaya_admin(Integer biaya_admin) {
        this.biaya_admin = biaya_admin;
    }

    @Override
    public String toString() {
        return "Produk{" +
                "id_produk=" + id_produk +
                ", kategori='" + kategori + '\'' +
                ", sub_kategori='" + sub_kategori + '\'' +
                ", biller='" + biller + '\'' +
                ", name='" + name + '\'' +
                ", nominal=" + nominal +
                ", sts_live='" + sts_live + '\'' +
                ", tipe_bayar='" + tipe_bayar + '\'' +
                ", pattern_no=" + pattern_no +
                ", pattern_cetak=" + pattern_cetak +
                ", pattern_label_id='" + pattern_label_id + '\'' +
                ", keterangan='" + keterangan + '\'' +
                ", nickname='" + nickname + '\'' +
                ", harga=" + harga +
                ", kode_produk='" + kode_produk + '\'' +
                ", biaya_admin=" + biaya_admin +
                ", kdDonasiPendidikanKesehatan='" + kdDonasiPendidikanKesehatan + '\'' +
                ", donasiPendidikanKesehatan='" + donasiPendidikanKesehatan + '\'' +
                ", kdDonasiInfakSedekah='" + kdDonasiInfakSedekah + '\'' +
                ", donasiInfakSedekah='" + donasiInfakSedekah + '\'' +
                ", kodePos='" + kodePos + '\'' +
                '}';
    }
}
