package id.hike.apps.android_mpos_mumu.features.register.model;

import com.google.gson.annotations.SerializedName;

public class RegisterData {
    @SerializedName("fullname")
    private String fullname;

    @SerializedName("phone")
    private String phone;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public String getFullname() {
        return fullname;
    }

    public RegisterData setFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public RegisterData setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterData setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterData setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "RegisterData{" +
                "fullname='" + fullname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
