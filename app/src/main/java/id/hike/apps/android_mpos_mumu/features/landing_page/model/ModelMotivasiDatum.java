package id.hike.apps.android_mpos_mumu.features.landing_page.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 07/07/17.
 */

public class ModelMotivasiDatum {
    @SerializedName("motivationId")
    @Expose
    public Integer motivationId;
    @SerializedName("outletId")
    @Expose
    public Integer outletId;
    @SerializedName("descriptions")
    @Expose
    public String descriptions;
    @SerializedName("startedAt")
    @Expose
    public Object startedAt;
    @SerializedName("endedAt")
    @Expose
    public Object endedAt;
    @SerializedName("createdBy")
    @Expose
    public Object createdBy;
    @SerializedName("createdDate")
    @Expose
    public Object createdDate;
    @SerializedName("updatedBy")
    @Expose
    public Object updatedBy;
    @SerializedName("updatedDate")
    @Expose
    public String updatedDate;

}
