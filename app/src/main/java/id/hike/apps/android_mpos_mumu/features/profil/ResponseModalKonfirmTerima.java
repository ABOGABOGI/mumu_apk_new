package id.hike.apps.android_mpos_mumu.features.profil;

import com.google.gson.annotations.SerializedName;

public class ResponseModalKonfirmTerima{

	@SerializedName("data")
	private Data data;

	@SerializedName("remark")
	private String remark;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return remark;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}