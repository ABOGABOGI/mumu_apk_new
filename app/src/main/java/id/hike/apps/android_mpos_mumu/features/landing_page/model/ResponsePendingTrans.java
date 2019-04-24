
package id.hike.apps.android_mpos_mumu.features.landing_page.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ResponsePendingTrans {

    @SerializedName("data")
    private List<Datum> mData;
    @SerializedName("message")
    private String mMessage;

    public List<Datum> getData() {
        return mData;
    }

    public void setData(List<Datum> data) {
        mData = data;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

}
