
package id.hike.apps.android_mpos_mumu.features.landing_page.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ModelTransaksiBaru {

    @SerializedName("data")
    private Long mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private Boolean mStatus;

    @SerializedName("transdate")
    private Long mTransDate;

    public Long getmTransDate() {
        return mTransDate;
    }

    public void setmTransDate(Long mTransDate) {
        this.mTransDate = mTransDate;
    }

    public Long getData() {
        return mData;
    }

    public void setData(Long data) {
        mData = data;
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
