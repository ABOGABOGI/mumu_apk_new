package id.hike.apps.android_mpos_mumu.features.landing_page.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 07/07/17.
 */

public class ModelInformasiDatum {
    @SerializedName("store_id")
    @Expose
    public Integer storeId;
    @SerializedName("last_updated")
    @Expose
    public Object lastUpdated;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("enteprise_id")
    @Expose
    public Integer entepriseId;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("created_by")
    @Expose
    public String createdBy;
    @SerializedName("logo_url_path")
    @Expose
    public String logoUrlPath;
    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("updated_by")
    @Expose
    public Object updatedBy;
    @SerializedName("created_date")
    @Expose
    public Object createdDate;
    @SerializedName("updated_date")
    @Expose
    public Object updatedDate;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("last_request")
    @Expose
    public Object lastRequest;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("status")
    @Expose
    public String status;
}
