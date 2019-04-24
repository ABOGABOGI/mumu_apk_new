
package id.hike.apps.android_mpos_mumu.features.payment.model;

import com.google.gson.annotations.SerializedName;


public class ResDataSuccess {

    @SerializedName("data")
    private Boolean mData;
    @SerializedName("message")
    private String mMessage;

    public Boolean getData() {
        return mData;
    }

    public void setData(Boolean data) {
        mData = data;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

}
