package id.hike.apps.android_mpos_mumu.features.landing_page.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResEditTrans {

	@SerializedName("trsales")
	private ModelEditTransTrsales modelEditTransTrsales;

	@SerializedName("data")
	private List<ModelEditTransDataItem> data;

	@SerializedName("message")
	private String message;

	public void setModelEditTransTrsales(ModelEditTransTrsales modelEditTransTrsales){
		this.modelEditTransTrsales = modelEditTransTrsales;
	}

	public ModelEditTransTrsales getModelEditTransTrsales(){
		return modelEditTransTrsales;
	}

	public void setData(List<ModelEditTransDataItem> data){
		this.data = data;
	}

	public List<ModelEditTransDataItem> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}