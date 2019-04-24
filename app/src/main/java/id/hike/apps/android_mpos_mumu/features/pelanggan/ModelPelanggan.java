
package id.hike.apps.android_mpos_mumu.features.pelanggan;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ModelPelanggan {

    @SerializedName("data")
    private List<ModelPelangganDatum> mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private Boolean mStatus;
    @SerializedName("errCode")
    private Boolean mErrCode;

    public Boolean getmErrCode() {
        return mErrCode;
    }

    public List<ModelPelangganDatum> getData() {
        return mData;
    }

    public void setData(List<ModelPelangganDatum> data) {
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
