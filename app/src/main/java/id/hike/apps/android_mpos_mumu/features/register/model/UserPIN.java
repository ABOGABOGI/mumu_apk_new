package id.hike.apps.android_mpos_mumu.features.register.model;

public class UserPIN {
    private String pin;
    private String confirmPin;

    public UserPIN(String pin, String confirmPin) {
        this.pin = pin;
        this.confirmPin = confirmPin;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getConfirmPin() {
        return confirmPin;
    }

    public void setConfirmPin(String confirmPin) {
        this.confirmPin = confirmPin;
    }

    @Override
    public String toString() {
        return "UserPIN{" +
                "pin='" + pin + '\'' +
                ", confirmPin='" + confirmPin + '\'' +
                '}';
    }
}
