package id.hike.apps.android_mpos_mumu.features.receipt_invoice;


import com.google.gson.annotations.SerializedName;

public class ModelNotification{

	@SerializedName("message")
	private boolean message;

	@SerializedName("status")
	private String status;

	public void setMessage(boolean message){
		this.message = message;
	}

	public boolean isMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}