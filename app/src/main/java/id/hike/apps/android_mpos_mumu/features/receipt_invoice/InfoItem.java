package id.hike.apps.android_mpos_mumu.features.receipt_invoice;

import com.google.gson.annotations.SerializedName;

public class InfoItem{

	@SerializedName("url_path_logo")
	private Object urlPathLogo;

	@SerializedName("outlet_id")
	private int outletId;

	@SerializedName("last_update")
	private Object lastUpdate;

	@SerializedName("updated_by")
	private Object updatedBy;

	@SerializedName("created_date")
	private Object createdDate;

	@SerializedName("updated_date")
	private Object updatedDate;

	@SerializedName("message")
	private String message;

	@SerializedName("created_by")
	private Object createdBy;

	@SerializedName("last_request")
	private Object lastRequest;

	public void setUrlPathLogo(Object urlPathLogo){
		this.urlPathLogo = urlPathLogo;
	}

	public Object getUrlPathLogo(){
		return urlPathLogo;
	}

	public void setOutletId(int outletId){
		this.outletId = outletId;
	}

	public int getOutletId(){
		return outletId;
	}

	public void setLastUpdate(Object lastUpdate){
		this.lastUpdate = lastUpdate;
	}

	public Object getLastUpdate(){
		return lastUpdate;
	}

	public void setUpdatedBy(Object updatedBy){
		this.updatedBy = updatedBy;
	}

	public Object getUpdatedBy(){
		return updatedBy;
	}

	public void setCreatedDate(Object createdDate){
		this.createdDate = createdDate;
	}

	public Object getCreatedDate(){
		return createdDate;
	}

	public void setUpdatedDate(Object updatedDate){
		this.updatedDate = updatedDate;
	}

	public Object getUpdatedDate(){
		return updatedDate;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setCreatedBy(Object createdBy){
		this.createdBy = createdBy;
	}

	public Object getCreatedBy(){
		return createdBy;
	}

	public void setLastRequest(Object lastRequest){
		this.lastRequest = lastRequest;
	}

	public Object getLastRequest(){
		return lastRequest;
	}
}