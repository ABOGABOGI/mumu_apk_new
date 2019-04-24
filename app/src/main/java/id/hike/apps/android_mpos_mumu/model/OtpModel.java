package id.hike.apps.android_mpos_mumu.model;

public class OtpModel extends BaseIsoResponse {

    private String email;
    private String kode_otp;
    private String app_header;
    private String trx_type;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKode_otp() {
        return kode_otp;
    }

    public void setKode_otp(String kode_otp) {
        this.kode_otp = kode_otp;
    }

    public String getApp_header() {
        return app_header;
    }

    public void setApp_header(String app_header) {
        this.app_header = app_header;
    }

    public String getTrx_type() {
        return trx_type;
    }

    public void setTrx_type(String trx_type) {
        this.trx_type = trx_type;
    }
}
