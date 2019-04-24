package id.hike.apps.android_mpos_mumu.model;

import java.io.Serializable;

public class Response<Any> implements Serializable {

    private int status = 200;
    private String message;
    private Any data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Any getData() {
        return data;
    }

    public void setData(Any data) {
        this.data = data;
    }
}
