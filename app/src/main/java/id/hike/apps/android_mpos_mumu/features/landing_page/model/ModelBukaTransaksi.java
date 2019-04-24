package id.hike.apps.android_mpos_mumu.features.landing_page.model;

import com.google.gson.annotations.SerializedName;

public class ModelBukaTransaksi{

	@SerializedName("data")
	private boolean data;

	@SerializedName("transid")
	private long transid;

	@SerializedName("message")
	private String message;

	public void setData(boolean data){
		this.data = data;
	}

	public boolean isData(){
		return data;
	}

	public void setTransid(long transid){
		this.transid = transid;
	}

	public long getTransid(){
		return transid;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}