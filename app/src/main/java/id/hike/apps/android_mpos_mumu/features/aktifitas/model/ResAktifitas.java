package id.hike.apps.android_mpos_mumu.features.aktifitas.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResAktifitas {

	@SerializedName("data")
	private List<ResAktifitasData> data;

	public void setData(List<ResAktifitasData> data){
		this.data = data;
	}

	public List<ResAktifitasData> getData(){
		return data;
	}
}