package id.hike.apps.android_mpos_mumu.features.profil;

import com.google.gson.annotations.SerializedName;

public class ModelProfil{

	@SerializedName("data")
	private Data data;

	@SerializedName("status")
	private boolean status;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}