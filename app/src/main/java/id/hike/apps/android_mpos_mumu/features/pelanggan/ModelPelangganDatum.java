
package id.hike.apps.android_mpos_mumu.features.pelanggan;

import com.google.gson.annotations.SerializedName;

public class ModelPelangganDatum {

    @SerializedName("address")
    private Object mAddress;
    @SerializedName("createdBy")
    private String mCreatedBy;
    @SerializedName("createdDate")
    private String mCreatedDate;
    @SerializedName("customer_id")
    private long mCustomerId;
    @SerializedName("customerType")
    private Object mCustomerType;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("idCardNumber")
    private Object mIdCardNumber;
    @SerializedName("idCardType")
    private Object mIdCardType;
    @SerializedName("lastRequest")
    private Object mLastRequest;
    @SerializedName("lastUpdated")
    private Long mLastUpdated;
    @SerializedName("name")
    private String mName;
    @SerializedName("password")
    private Object mPassword;
    @SerializedName("phone")
    private String mPhone;
    @SerializedName("status")
    private Object mStatus;
    @SerializedName("updatedBy")
    private String mUpdatedBy;
    @SerializedName("updatedDate")
    private Object mUpdatedDate;
    @SerializedName("urlAvatar")
    private Object mUrlAvatar;

    public Object getAddress() {
        return mAddress;
    }

    public void setAddress(Object address) {
        mAddress = address;
    }

    public String getCreatedBy() {
        return mCreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        mCreatedBy = createdBy;
    }

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        mCreatedDate = createdDate;
    }

    public Long getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(Long customerId) {
        mCustomerId = customerId;
    }

    public Object getCustomerType() {
        return mCustomerType;
    }

    public void setCustomerType(Object customerType) {
        mCustomerType = customerType;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public Object getIdCardNumber() {
        return mIdCardNumber;
    }

    public void setIdCardNumber(Object idCardNumber) {
        mIdCardNumber = idCardNumber;
    }

    public Object getIdCardType() {
        return mIdCardType;
    }

    public void setIdCardType(Object idCardType) {
        mIdCardType = idCardType;
    }

    public Object getLastRequest() {
        return mLastRequest;
    }

    public void setLastRequest(Object lastRequest) {
        mLastRequest = lastRequest;
    }

    public Long getLastUpdated() {
        return mLastUpdated;
    }

    public void setLastUpdated(Long lastUpdated) {
        mLastUpdated = lastUpdated;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Object getPassword() {
        return mPassword;
    }

    public void setPassword(Object password) {
        mPassword = password;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public Object getStatus() {
        return mStatus;
    }

    public void setStatus(Object status) {
        mStatus = status;
    }

    public String getUpdatedBy() {
        return mUpdatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        mUpdatedBy = updatedBy;
    }

    public Object getUpdatedDate() {
        return mUpdatedDate;
    }

    public void setUpdatedDate(Object updatedDate) {
        mUpdatedDate = updatedDate;
    }

    public Object getUrlAvatar() {
        return mUrlAvatar;
    }

    public void setUrlAvatar(Object urlAvatar) {
        mUrlAvatar = urlAvatar;
    }

}
