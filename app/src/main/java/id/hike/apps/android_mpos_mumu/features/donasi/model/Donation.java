package id.hike.apps.android_mpos_mumu.features.donasi.model;

public class Donation {
    private String pin;
    private int amount;
    private String donationProgram;
    private String donationType;
    private String muzaki;
    private String phone;
    private String accountDest;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDonationProgram() {
        return donationProgram;
    }

    public void setDonationProgram(String donationProgram) {
        this.donationProgram = donationProgram;
    }

    public String getDonationType() {
        return donationType;
    }

    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }

    public String getMuzaki() {
        return muzaki;
    }

    public void setMuzaki(String muzaki) {
        this.muzaki = muzaki;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccountDest() {
        return accountDest;
    }

    public void setAccountDest(String accountDest) {
        this.accountDest = accountDest;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "pin='" + pin + '\'' +
                ", amount=" + amount +
                ", donationProgram='" + donationProgram + '\'' +
                ", donationType='" + donationType + '\'' +
                ", muzaki='" + muzaki + '\'' +
                ", phone='" + phone + '\'' +
                ", accountDest='" + accountDest + '\'' +
                '}';
    }
}
