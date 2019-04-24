package id.hike.apps.android_mpos_mumu.features.landing_page.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 07/07/17.
 */

public class ModelMotivasi {
    @SerializedName("data")
    @Expose
    public List<ModelMotivasiDatum> data = null;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("status")
    @Expose
    public Boolean status;
}
