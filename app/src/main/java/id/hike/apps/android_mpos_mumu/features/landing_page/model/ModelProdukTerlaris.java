
package id.hike.apps.android_mpos_mumu.features.landing_page.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ModelProdukTerlaris {
    @SerializedName("data")
    private List<ModelProdukTerlarisDatum2> mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private Boolean mStatus;
    @SerializedName("row")
    private String mRow;

    public List<ModelProdukTerlarisDatum2> getData() {
        return mData;
    }

    public void setData(List<ModelProdukTerlarisDatum2> data) {
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

    public String getmRow() {
        return mRow;
    }
}
