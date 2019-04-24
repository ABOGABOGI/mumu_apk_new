package id.hike.apps.android_mpos_mumu.model;

import java.io.Serializable;

public class WalletCRP implements Serializable {

    private String trx_type;
    private String account_no;
    private String trx_date_time;
    private String system_trace_audit;
    private String pos_terminal_type;
    private String enc_pin;
    private String jenis_crp;
    private String otp;
    private String sign;

    public String getTrx_type() {
        return trx_type;
    }

    public void setTrx_type(String trx_type) {
        this.trx_type = trx_type;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getTrx_date_time() {
        return trx_date_time;
    }

    public void setTrx_date_time(String trx_date_time) {
        this.trx_date_time = trx_date_time;
    }

    public String getSystem_trace_audit() {
        return system_trace_audit;
    }

    public void setSystem_trace_audit(String system_trace_audit) {
        this.system_trace_audit = system_trace_audit;
    }

    public String getPos_terminal_type() {
        return pos_terminal_type;
    }

    public void setPos_terminal_type(String pos_terminal_type) {
        this.pos_terminal_type = pos_terminal_type;
    }

    public String getEnc_pin() {
        return enc_pin;
    }

    public void setEnc_pin(String enc_pin) {
        this.enc_pin = enc_pin;
    }

    public String getJenis_crp() {
        return jenis_crp;
    }

    public void setJenis_crp(String jenis_crp) {
        this.jenis_crp = jenis_crp;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
