package id.hike.apps.android_mpos_mumu.features.payment.model;

import com.google.gson.annotations.SerializedName;

public class ModelPaymentTypeItem {

	@SerializedName("domain_min_value")
	private Object domainMinValue;

	@SerializedName("domain_type")
	private String domainType;

	@SerializedName("last_updated")
	private Object lastUpdated;

	@SerializedName("domain_value")
	private String domainValue;

	@SerializedName("domain_max_value")
	private Object domainMaxValue;

	@SerializedName("domain_description")
	private String domainDescription;

	@SerializedName("created_by")
	private Object createdBy;

	@SerializedName("domain_id_detail")
	private String domainIdDetail;

	@SerializedName("domain_id")
	private String domainId;

	@SerializedName("domain_status")
	private String domainStatus;

	@SerializedName("updated_by")
	private String updatedBy;

	@SerializedName("updated_date")
	private String updatedDate;

	@SerializedName("created_date")
	private String createdDate;

	@SerializedName("last_request")
	private String lastRequest;

	public Object getDomainMinValue() {
		return domainMinValue;
	}

	public void setDomainMinValue(Object domainMinValue) {
		this.domainMinValue = domainMinValue;
	}

	public String getDomainType() {
		return domainType;
	}

	public void setDomainType(String domainType) {
		this.domainType = domainType;
	}

	public Object getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Object lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getDomainValue() {
		return domainValue;
	}

	public void setDomainValue(String domainValue) {
		this.domainValue = domainValue;
	}

	public Object getDomainMaxValue() {
		return domainMaxValue;
	}

	public void setDomainMaxValue(Object domainMaxValue) {
		this.domainMaxValue = domainMaxValue;
	}

	public String getDomainDescription() {
		return domainDescription;
	}

	public void setDomainDescription(String domainDescription) {
		this.domainDescription = domainDescription;
	}

	public Object getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Object createdBy) {
		this.createdBy = createdBy;
	}

	public String getDomainIdDetail() {
		return domainIdDetail;
	}

	public void setDomainIdDetail(String domainIdDetail) {
		this.domainIdDetail = domainIdDetail;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getDomainStatus() {
		return domainStatus;
	}

	public void setDomainStatus(String domainStatus) {
		this.domainStatus = domainStatus;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastRequest() {
		return lastRequest;
	}

	public void setLastRequest(String lastRequest) {
		this.lastRequest = lastRequest;
	}
}