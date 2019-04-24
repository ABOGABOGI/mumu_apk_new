package id.hike.apps.android_mpos_mumu.features.payment.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResPaymentType {

	@SerializedName("data")
	private List<ModelPaymentTypeItem> data;

	@SerializedName("message")
	private String message;

	public void setData(List<ModelPaymentTypeItem> data){
		this.data = data;
	}

	public List<ModelPaymentTypeItem> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}