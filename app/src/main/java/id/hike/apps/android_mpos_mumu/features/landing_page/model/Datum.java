package id.hike.apps.android_mpos_mumu.features.landing_page.model;

import com.google.gson.annotations.SerializedName;

public class Datum {

	/*@SerializedName("address")
	private String address;*/

	@SerializedName("phone")
	private String phone;

	/*@SerializedName("qty")
	private String qty;*/

	@SerializedName("name")
	private String name;

	@SerializedName("trans_id")
	private String transId;

	/*@SerializedName("item_status")
	private String itemStatus;*/

	@SerializedName("nego_disc")
	private String negoDisc;

	@SerializedName("total_payment")
	private String totalPayment;

	@SerializedName("email")
	private String email;

	public String getEmail() {
		return email;
	}

	public String getNegoDisc() {
		return negoDisc;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setTransId(String transId){
		this.transId = transId;
	}

	public String getTransId(){
		return transId;
	}

	public void setTotalPayment(String totalPayment){
		this.totalPayment = totalPayment;
	}

	public String getTotalPayment(){
		return totalPayment;
	}
}