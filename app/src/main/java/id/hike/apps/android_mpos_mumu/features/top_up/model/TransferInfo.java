package id.hike.apps.android_mpos_mumu.features.top_up.model;

public class TransferInfo {
    private int amount;
    private String accountDest;

    public TransferInfo(int amount, String accountDest) {
        this.amount = amount;
        this.accountDest = accountDest;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAccountDest() {
        return accountDest;
    }

    public void setAccountDest(String accountDest) {
        this.accountDest = accountDest;
    }

    @Override
    public String toString() {
        return "TransferInfo{" +
                "amount=" + amount +
                ", accountDest='" + accountDest + '\'' +
                '}';
    }
}
