package id.hike.apps.android_mpos_mumu.features.register.model;

public class WalletResponse {
    private String createdBy;
    private String updatedBy;
    private long createdDate;
    private long updatedDate;
    private String id;
    private String pin;
    private String holderName;
    private String holderPhone;
    private String holderEmail;
    private int counterFail;
    private int balance;
    private int status;
    private String scheme;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(long updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getHolderPhone() {
        return holderPhone;
    }

    public void setHolderPhone(String holderPhone) {
        this.holderPhone = holderPhone;
    }

    public String getHolderEmail() {
        return holderEmail;
    }

    public void setHolderEmail(String holderEmail) {
        this.holderEmail = holderEmail;
    }

    public int getCounterFail() {
        return counterFail;
    }

    public void setCounterFail(int counterFail) {
        this.counterFail = counterFail;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    @Override
    public String toString() {
        return "WalletResponse{" +
                "createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", id='" + id + '\'' +
                ", pin='" + pin + '\'' +
                ", holderName='" + holderName + '\'' +
                ", holderPhone='" + holderPhone + '\'' +
                ", holderEmail='" + holderEmail + '\'' +
                ", counterFail=" + counterFail +
                ", balance=" + balance +
                ", status=" + status +
                ", scheme='" + scheme + '\'' +
                '}';
    }
}
