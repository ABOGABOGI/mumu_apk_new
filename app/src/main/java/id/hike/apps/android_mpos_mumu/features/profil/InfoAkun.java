package id.hike.apps.android_mpos_mumu.features.profil;

import com.google.gson.annotations.SerializedName;

public class InfoAkun{

	@SerializedName("userAddress")
	private String userAddress;

	@SerializedName("store_id")
	private int storeId;

	@SerializedName("url_avatar")
	private String urlAvatar;

	@SerializedName("storeType")
	private String storeType;

	@SerializedName("phone")
	private String phone;

	@SerializedName("storeAddress")
	private String storeAddress;

	@SerializedName("logo_store")
	private String logoStore;

	@SerializedName("employee_name")
	private String employeeName;

	@SerializedName("storeName")
	private String storeName;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public void setUserAddress(String userAddress){
		this.userAddress = userAddress;
	}

	public String getUserAddress(){
		return userAddress;
	}

	public void setStoreId(int storeId){
		this.storeId = storeId;
	}

	public int getStoreId(){
		return storeId;
	}

	public void setUrlAvatar(String urlAvatar){
		this.urlAvatar = urlAvatar;
	}

	public String getUrlAvatar(){
		return urlAvatar;
	}

	public void setStoreType(String storeType){
		this.storeType = storeType;
	}

	public String getStoreType(){
		return storeType;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setStoreAddress(String storeAddress){
		this.storeAddress = storeAddress;
	}

	public String getStoreAddress(){
		return storeAddress;
	}

	public void setLogoStore(String logoStore){
		this.logoStore = logoStore;
	}

	public String getLogoStore(){
		return logoStore;
	}

	public void setEmployeeName(String employeeName){
		this.employeeName = employeeName;
	}

	public String getEmployeeName(){
		return employeeName;
	}

	public void setStoreName(String storeName){
		this.storeName = storeName;
	}

	public String getStoreName(){
		return storeName;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"InfoAkun{" + 
			"userAddress = '" + userAddress + '\'' + 
			",store_id = '" + storeId + '\'' + 
			",url_avatar = '" + urlAvatar + '\'' + 
			",storeType = '" + storeType + '\'' + 
			",phone = '" + phone + '\'' + 
			",storeAddress = '" + storeAddress + '\'' + 
			",logo_store = '" + logoStore + '\'' + 
			",employee_name = '" + employeeName + '\'' + 
			",storeName = '" + storeName + '\'' + 
			",email = '" + email + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}