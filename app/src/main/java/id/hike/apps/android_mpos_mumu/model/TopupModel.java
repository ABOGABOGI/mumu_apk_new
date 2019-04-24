package id.hike.apps.android_mpos_mumu.model;

public class TopupModel {
    private String type;
    private String accountType;
    private String account;
    private String institutionCode;
    private String product;
    private String billNumber;
    private String trxId;
    private String retrieval;
    private String sign;
    private String resultCode;
    private String resultDesc;
    private String expired_date;
    private String nilai_transfer;
    private String no_rekening_tujuan;
    private String no_rekening_anggota;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public String getRetrieval() {
        return retrieval;
    }

    public void setRetrieval(String retrieval) {
        this.retrieval = retrieval;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(String expired_date) {
        this.expired_date = expired_date;
    }

    public String getNilai_transfer() {
        return nilai_transfer;
    }

    public void setNilai_transfer(String nilai_transfer) {
        this.nilai_transfer = nilai_transfer;
    }

    public String getNo_rekening_tujuan() {
        return no_rekening_tujuan;
    }

    public void setNo_rekening_tujuan(String no_rekening_tujuan) {
        this.no_rekening_tujuan = no_rekening_tujuan;
    }

    public String getNo_rekening_anggota() {
        return no_rekening_anggota;
    }

    public void setNo_rekening_anggota(String no_rekening_anggota) {
        this.no_rekening_anggota = no_rekening_anggota;
    }
}
