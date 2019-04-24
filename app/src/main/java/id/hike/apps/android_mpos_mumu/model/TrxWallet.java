package id.hike.apps.android_mpos_mumu.model;

public class TrxWallet extends BaseIsoResponse {

    private String kode_agen;
    private String app_header;
    private String app_build;
    private String kode_transaksi;
    private String kode_produk;
    private String id_pelanggan;
    private String nilai;
    private String system_date_time;
    private String system_trace_audit;
    private String institution_code;
    private String institution_account;
    private String agen_account;
    private String agen_account_no;
    private String trx_code;

    public String getAgen_account() {
        return agen_account;
    }
    public void setAgen_account(String agen_account) {
        this.agen_account = agen_account;
    }
    public String getAgen_account_no() {
        return agen_account_no;
    }
    public void setagen_account_no(String agen_account_no) { this.agen_account_no = agen_account_no; }
    public String getTrx_code() {
        return trx_code;
    }
    public void setTrx_code(String trx_code) {
        this.trx_code = trx_code;
    }

    public String getKode_agen() {
        return kode_agen;
    }

    public void setKode_agen(String kode_agen) {
        this.kode_agen = kode_agen;
    }

    public String getApp_header() {
        return app_header;
    }

    public void setApp_header(String app_header) {
        this.app_header = app_header;
    }

    public String getApp_build() {
        return app_build;
    }

    public void setApp_build(String app_build) {
        this.app_build = app_build;
    }

    public String getKode_transaksi() {
        return kode_transaksi;
    }

    public void setKode_transaksi(String kode_transaksi) {
        this.kode_transaksi = kode_transaksi;
    }

    public String getKode_produk() {
        return kode_produk;
    }

    public void setKode_produk(String kode_produk) {
        this.kode_produk = kode_produk;
    }

    public String getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(String id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public String getSystem_date_time() {
        return system_date_time;
    }

    public void setSystem_date_time(String system_date_time) {
        this.system_date_time = system_date_time;
    }

    public String getSystem_trace_audit() {
        return system_trace_audit;
    }

    public void setSystem_trace_audit(String system_trace_audit) {
        this.system_trace_audit = system_trace_audit;
    }

    public String getInstitution_code() {
        return institution_code;
    }

    public void setInstitution_code(String institution_code) {
        this.institution_code = institution_code;
    }

    public String getInstitution_account() {
        return institution_account;
    }

    public void setInstitution_account(String institution_account) {
        this.institution_account = institution_account;
    }
}
