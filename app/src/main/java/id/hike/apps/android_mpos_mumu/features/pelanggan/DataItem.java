package id.hike.apps.android_mpos_mumu.features.pelanggan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("address")
	@Expose
	private Object address;

	@SerializedName("updatedBy")
	@Expose
	private String updatedBy;

	@SerializedName("lastRequest")
	@Expose
	private Object lastRequest;

	@SerializedName("updatedDate")
	@Expose
	private Object updatedDate;

	@SerializedName("urlAvatar")
	@Expose
	private Object urlAvatar;

	@SerializedName("lastUpdated")
	@Expose
	private long lastUpdated;

	@SerializedName("customerType")
	@Expose
	private Object customerType;

	@SerializedName("idCardType")
	@Expose
	private Object idCardType;

	@SerializedName("password")
	@Expose
	private Object password;

	@SerializedName("createdDate")
	@Expose
	private long createdDate;

	@SerializedName("createdBy")
	@Expose
	private String createdBy;

	@SerializedName("phone")
	@Expose
	private String phone;

	@SerializedName("customerId")
	@Expose
	private int customerId;

	@SerializedName("idCardNumber")
	@Expose
	private Object idCardNumber;

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("email")
	@Expose
	private String email;

	@SerializedName("status")
	@Expose
	private Object status;

	public Object getAddress(){
		return address;
	}

	public String getUpdatedBy(){
		return updatedBy;
	}

	public Object getLastRequest(){
		return lastRequest;
	}

	public Object getUpdatedDate(){
		return updatedDate;
	}

	public Object getUrlAvatar(){
		return urlAvatar;
	}

	public long getLastUpdated(){
		return lastUpdated;
	}

	public Object getCustomerType(){
		return customerType;
	}

	public Object getIdCardType(){
		return idCardType;
	}

	public Object getPassword(){
		return password;
	}

	public long getCreatedDate(){
		return createdDate;
	}

	public String getCreatedBy(){
		return createdBy;
	}

	public String getPhone(){
		return phone;
	}

	public int getCustomerId(){
		return customerId;
	}

	public Object getIdCardNumber(){
		return idCardNumber;
	}

	public String getName(){
		return name;
	}

	public String getEmail(){
		return email;
	}

	public Object getStatus(){
		return status;
	}

	public DataItem() {
	}
}