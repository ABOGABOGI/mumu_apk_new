package id.hike.apps.android_mpos_mumu.model;

import java.io.Serializable;

public class UserDetail implements Serializable {

    private int id_agen;
    private String noCif;
    private String namaNasabah;
    private String namaSingkat;
    private String kodeCabang;
    private String requestDate;
    private String tempatLahir;
    private String tanggalLahir;
    private String tanggalBuka;
    private String jenisKelamin;
    private String agama;
    private int pekerjaanId;
    private String statusPerkawinan;
    private String jenisIdentitas = "1";
    private String nomorIdentitas;
    private String tanggalBerakhirIdentitas;
    private String alamatRumahJalan;
    private String alamatRumahRT;
    private String alamatRumahRW;
    private String alamatRumahKelurahanId;
    private String alamatRumahKodePos;
    private String tujuanPenggunaanDana;
    private String sumberDana;
    private String penghasilanUtamaPerTahun;
    private String namaIbuKandung;
    private String posIdType;
    private String posId;
    private String telpFix;
    private String telpMobile;
    private String email;
    private int pictureProfile;
    private int pictureKtp;
    private int pictureSelfie = 0;
    private Integer userIdIn = 0;
    private Integer userIdLast = 0;
    private String sysdateIn;
    private String sysDateLast;
    private String h39;
    private String h62;

    public int getId_agen() {
        return id_agen;
    }

    public void setId_agen(int id_agen) {
        this.id_agen = id_agen;
    }

    public String getNoCif() {
        return noCif;
    }

    public void setNoCif(String noCif) {
        this.noCif = noCif;
    }

    public String getNamaNasabah() {
        return namaNasabah;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }

    public String getNamaSingkat() {
        return namaSingkat;
    }

    public void setNamaSingkat(String namaSingkat) {
        this.namaSingkat = namaSingkat;
    }

    public String getKodeCabang() {
        return kodeCabang;
    }

    public void setKodeCabang(String kodeCabang) {
        this.kodeCabang = kodeCabang;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTanggalBuka() {
        return tanggalBuka;
    }

    public void setTanggalBuka(String tanggalBuka) {
        this.tanggalBuka = tanggalBuka;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public int getPekerjaanId() {
        return pekerjaanId;
    }

    public void setPekerjaanId(int pekerjaanId) {
        this.pekerjaanId = pekerjaanId;
    }

    public String getStatusPerkawinan() {
        return statusPerkawinan;
    }

    public void setStatusPerkawinan(String statusPerkawinan) {
        this.statusPerkawinan = statusPerkawinan;
    }

    public String getJenisIdentitas() {
        return jenisIdentitas;
    }

    public void setJenisIdentitas(String jenisIdentitas) {
        this.jenisIdentitas = jenisIdentitas;
    }

    public String getNomorIdentitas() {
        return nomorIdentitas;
    }

    public void setNomorIdentitas(String nomorIdentitas) {
        this.nomorIdentitas = nomorIdentitas;
    }

    public String getTanggalBerakhirIdentitas() {
        return tanggalBerakhirIdentitas;
    }

    public void setTanggalBerakhirIdentitas(String tanggalBerakhirIdentitas) {
        this.tanggalBerakhirIdentitas = tanggalBerakhirIdentitas;
    }

    public String getAlamatRumahJalan() {
        return alamatRumahJalan;
    }

    public void setAlamatRumahJalan(String alamatRumahJalan) {
        this.alamatRumahJalan = alamatRumahJalan;
    }

    public String getAlamatRumahRT() {
        return alamatRumahRT;
    }

    public void setAlamatRumahRT(String alamatRumahRT) {
        this.alamatRumahRT = alamatRumahRT;
    }

    public String getAlamatRumahRW() {
        return alamatRumahRW;
    }

    public void setAlamatRumahRW(String alamatRumahRW) {
        this.alamatRumahRW = alamatRumahRW;
    }

    public String getAlamatRumahKelurahanId() {
        return alamatRumahKelurahanId;
    }

    public void setAlamatRumahKelurahanId(String alamatRumahKelurahanId) {
        this.alamatRumahKelurahanId = alamatRumahKelurahanId;
    }

    public String getAlamatRumahKodePos() {
        return alamatRumahKodePos;
    }

    public void setAlamatRumahKodePos(String alamatRumahKodePos) {
        this.alamatRumahKodePos = alamatRumahKodePos;
    }

    public String getTujuanPenggunaanDana() {
        return tujuanPenggunaanDana;
    }

    public void setTujuanPenggunaanDana(String tujuanPenggunaanDana) {
        this.tujuanPenggunaanDana = tujuanPenggunaanDana;
    }

    public String getSumberDana() {
        return sumberDana;
    }

    public void setSumberDana(String sumberDana) {
        this.sumberDana = sumberDana;
    }

    public String getPenghasilanUtamaPerTahun() {
        return penghasilanUtamaPerTahun;
    }

    public void setPenghasilanUtamaPerTahun(String penghasilanUtamaPerTahun) {
        this.penghasilanUtamaPerTahun = penghasilanUtamaPerTahun;
    }

    public String getNamaIbuKandung() {
        return namaIbuKandung;
    }

    public void setNamaIbuKandung(String namaIbuKandung) {
        this.namaIbuKandung = namaIbuKandung;
    }

    public String getPosIdType() {
        return posIdType;
    }

    public void setPosIdType(String posIdType) {
        this.posIdType = posIdType;
    }

    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }

    public String getTelpFix() {
        return telpFix;
    }

    public void setTelpFix(String telpFix) {
        this.telpFix = telpFix;
    }

    public String getTelpMobile() {
        return telpMobile;
    }

    public void setTelpMobile(String telpMobile) {
        this.telpMobile = telpMobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPictureProfile() {
        return pictureProfile;
    }

    public void setPictureProfile(int pictureProfile) {
        this.pictureProfile = pictureProfile;
    }

    public int getPictureKtp() {
        return pictureKtp;
    }

    public void setPictureKtp(int pictureKtp) {
        this.pictureKtp = pictureKtp;
    }

    public int getPictureSelfie() {
        return pictureSelfie;
    }

    public void setPictureSelfie(int pictureSelfie) {
        this.pictureSelfie = pictureSelfie;
    }

    public int getUserIdIn() {
        return userIdIn;
    }

    public void setUserIdIn(int userIdIn) {
        this.userIdIn = userIdIn;
    }

    public int getUserIdLast() {
        return userIdLast;
    }

    public void setUserIdLast(int userIdLast) {
        this.userIdLast = userIdLast;
    }

    public String getSysdateIn() {
        return sysdateIn;
    }

    public void setSysdateIn(String sysdateIn) {
        this.sysdateIn = sysdateIn;
    }

    public String getSysDateLast() {
        return sysDateLast;
    }

    public void setSysDateLast(String sysDateLast) {
        this.sysDateLast = sysDateLast;
    }

    public String getH39() {
        return h39;
    }

    public void setH39(String h39) {
        this.h39 = h39;
    }

    public String getH62() {
        return h62;
    }

    public void setH62(String h62) {
        this.h62 = h62;
    }
}
