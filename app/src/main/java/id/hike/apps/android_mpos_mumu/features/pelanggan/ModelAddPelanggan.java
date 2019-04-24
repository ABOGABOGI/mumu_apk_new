
package id.hike.apps.android_mpos_mumu.features.pelanggan;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ModelAddPelanggan {

    @SerializedName("data")
    private long mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private Boolean mStatus;
    @SerializedName("customerId")
    private Long mCustomerId;

    public Long getmCustomerId() {
        return mCustomerId;
    }

    public long getData() {
        return mData;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Boolean getStatus() {
        return mStatus;
    }

    public void setStatus(Boolean status) {
        mStatus = status;
    }

}
