package id.hike.apps.android_mpos_mumu.features.profil;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("employeeName")
	private String employeeName;

	@SerializedName("address")
	private String address;

	@SerializedName("updatedBy")
	private String updatedBy;

	@SerializedName("gender")
	private String gender;

	@SerializedName("lastRequest")
	private String lastRequest;

	@SerializedName("otpPin")
	private String otpPin;

	@SerializedName("updatedDate")
	private String updatedDate;

	@SerializedName("userId")
	private int userId;

	@SerializedName("urlAvatar")
	private String urlAvatar;

	@SerializedName("createdDate")
	private String createdDate;

	@SerializedName("pin")
	private String pin;

	@SerializedName("createdBy")
	private String createdBy;

	@SerializedName("phone")
	private String phone;

	@SerializedName("lastUpdate")
	private String lastUpdate;

	@SerializedName("email")
	private String email;

	@SerializedName("status")
	private String status;

	@SerializedName("username")
	private String username;

	public void setEmployeeName(String employeeName){
		this.employeeName = employeeName;
	}

	public String getEmployeeName(){
		return employeeName;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setUpdatedBy(String updatedBy){
		this.updatedBy = updatedBy;
	}

	public String getUpdatedBy(){
		return updatedBy;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setLastRequest(String lastRequest){
		this.lastRequest = lastRequest;
	}

	public String getLastRequest(){
		return lastRequest;
	}

	public void setOtpPin(String otpPin){
		this.otpPin = otpPin;
	}

	public String getOtpPin(){
		return otpPin;
	}

	public void setUpdatedDate(String updatedDate){
		this.updatedDate = updatedDate;
	}

	public String getUpdatedDate(){
		return updatedDate;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setUrlAvatar(String urlAvatar){
		this.urlAvatar = urlAvatar;
	}

	public String getUrlAvatar(){
		return urlAvatar;
	}

	public void setCreatedDate(String createdDate){
		this.createdDate = createdDate;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public void setPin(String pin){
		this.pin = pin;
	}

	public String getPin(){
		return pin;
	}

	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

	public String getCreatedBy(){
		return createdBy;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setLastUpdate(String lastUpdate){
		this.lastUpdate = lastUpdate;
	}

	public String getLastUpdate(){
		return lastUpdate;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}