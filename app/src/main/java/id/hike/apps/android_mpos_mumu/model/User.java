package id.hike.apps.android_mpos_mumu.model;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String kd_agen;
    private String username;
    private String password;
    private String nm_agen;
    private String sts_admin;
    private String alamat;
    private String no_hp;
    private String email;
    private String sts_aktif;
    private String device_token;
    private int id_whitelabel;
    private String h39;
    private String h62;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKdAgen() {
        return kd_agen;
    }

    public void setKdAgen(String kdAgen) {
        this.kd_agen = kdAgen;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNamaAgen() {
        return nm_agen;
    }

    public void setNamaAgen(String namaAgen) {
        this.nm_agen = namaAgen;
    }

    public String getStatusAdmin() {
        return sts_admin;
    }

    public void setStatusAdmin(String statusAdmin) {
        this.sts_admin = statusAdmin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNomorHandphone() {
        return no_hp;
    }

    public void setNomorHandphone(String nomorHandphone) {
        this.no_hp = nomorHandphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatusAktif() {
        return sts_aktif;
    }

    public void setStatusAktif(String statusAktif) {
        this.sts_aktif = statusAktif;
    }

    public String getDeviceToken() {
        return device_token;
    }

    public void setDeviceToken(String deviceToken) {
        this.device_token = deviceToken;
    }

    public int getIdWhitelabel() {
        return id_whitelabel;
    }

    public void setIdWhitelabel(int idWhitelabel) {
        this.id_whitelabel = idWhitelabel;
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
