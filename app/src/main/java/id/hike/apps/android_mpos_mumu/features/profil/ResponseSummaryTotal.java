package id.hike.apps.android_mpos_mumu.features.profil;

import com.google.gson.annotations.SerializedName;

public class ResponseSummaryTotal{

	@SerializedName("data")
	private long data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	@SerializedName("modalAwalHarian")
	private String modalAwalHarian;

	public long getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

	public boolean isStatus() {
		return status;
	}

	public String getModalAwalHarian() {
		return modalAwalHarian;
	}
}