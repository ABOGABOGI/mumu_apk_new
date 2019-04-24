package id.hike.apps.android_mpos_mumu.features.register.model;

import com.google.gson.annotations.SerializedName;

public class ResponseUser {
    @SerializedName("status")
    private int status;

    @SerializedName("user")
    private User user;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ResponseUser{" +
                "status=" + status +
                ", user=" + user +
                '}';
    }
}
