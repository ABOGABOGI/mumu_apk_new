package id.hike.apps.android_mpos_mumu.features.summary.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResCheckListStock{

	@SerializedName("data")
	private List<ResCheckListStockItem> data;

	@SerializedName("message")
	private String message;

	//TODO: nanti diunblock karena ini belum diupload dimas server transaksinya
	@SerializedName("status")
	private boolean status;

	public void setData(List<ResCheckListStockItem> data){
		this.data = data;
	}

	public List<ResCheckListStockItem> getData(){
		return data;
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