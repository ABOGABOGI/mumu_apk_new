package id.hike.apps.android_mpos_mumu.features.aktifitas.model;

import com.google.gson.annotations.SerializedName;

public class ResAktifitasData {

	@SerializedName("trans_date")
	private String transDate;

	@SerializedName("address")
	private Object address;

	@SerializedName("phone")
	private String phone;

	@SerializedName("name")
	private String name;

	@SerializedName("trans_id")
	private long transId;

	@SerializedName("trans_status")
	private String transStatus;

	@SerializedName("total_trans")
	private Double totalTrans;

	public void setTransDate(String transDate){
		this.transDate = transDate;
	}

	public String getTransDate(){
		return transDate;
	}

	public void setAddress(Object address){
		this.address = address;
	}

	public Object getAddress(){
		return address;
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

	public void setTransId(long transId){
		this.transId = transId;
	}

	public long getTransId(){
		return transId;
	}

	public void setTransStatus(String transStatus){
		this.transStatus = transStatus;
	}

	public String getTransStatus(){
		return transStatus;
	}

	public void setTotalTrans(Double totalTrans){
		this.totalTrans = totalTrans;
	}

	public Double getTotalTrans(){
		return totalTrans;
	}

	public ResAktifitasData(String transDate, Object address, String phone, String name, long transId, String transStatus, Double totalTrans) {
		this.transDate = transDate;
		this.address = address;
		this.phone = phone;
		this.name = name;
		this.transId = transId;
		this.transStatus = transStatus;
		this.totalTrans = totalTrans;
	}
}