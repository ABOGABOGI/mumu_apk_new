package id.hike.apps.android_mpos_mumu.features.landing_page.model;


import com.google.gson.annotations.SerializedName;


public class ModelEditTransTrsales {

	@SerializedName("negoDisc")
	private Long negoDisc;

	@SerializedName("updatedBy")
	private String updatedBy;

	@SerializedName("lastRequest")
	private String lastRequest;

	@SerializedName("totalItem")
	private int totalItem;

	@SerializedName("totalPrice")
	private long totalPrice;

	@SerializedName("transId")
	private long transId;

	@SerializedName("outletId")
	private int outletId;

	@SerializedName("change")
	private long change;

	@SerializedName("totalDisc")
	private int totalDisc;

	@SerializedName("remark")
	private String remark;

	@SerializedName("updatedDate")
	private String updatedDate;

	@SerializedName("userId")
	private String userId;

	@SerializedName("custPay")
	private long custPay;

	@SerializedName("totalPayment")
	private int totalPayment;

	@SerializedName("createdDate")
	private String createdDate;

	@SerializedName("createdBy")
	private String createdBy;

	@SerializedName("lastUpdate")
	private String lastUpdate;

	@SerializedName("transDate")
	private String transDate;

	@SerializedName("customerId")
	private int customerId;

	@SerializedName("totalTrans")
	private int totalTrans;

	@SerializedName("transStatus")
	private String transStatus;

	public void setNegoDisc(Long negoDisc){
		this.negoDisc = negoDisc;
	}

	public Long getNegoDisc(){
		return negoDisc;
	}

	public void setUpdatedBy(String updatedBy){
		this.updatedBy = updatedBy;
	}

	public String getUpdatedBy(){
		return updatedBy;
	}

	public void setLastRequest(String lastRequest){
		this.lastRequest = lastRequest;
	}

	public String getLastRequest(){
		return lastRequest;
	}

	public void setTotalItem(int totalItem){
		this.totalItem = totalItem;
	}

	public int getTotalItem(){
		return totalItem;
	}

	public void setTotalPrice(long totalPrice){
		this.totalPrice = totalPrice;
	}

	public long getTotalPrice(){
		return totalPrice;
	}

	public void setTransId(long transId){
		this.transId = transId;
	}

	public long getTransId(){
		return transId;
	}

	public void setOutletId(int outletId){
		this.outletId = outletId;
	}

	public int getOutletId(){
		return outletId;
	}

	public void setChange(long change){
		this.change = change;
	}

	public long getChange(){
		return change;
	}

	public void setTotalDisc(int totalDisc){
		this.totalDisc = totalDisc;
	}

	public int getTotalDisc(){
		return totalDisc;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return remark;
	}

	public void setUpdatedDate(String updatedDate){
		this.updatedDate = updatedDate;
	}

	public String getUpdatedDate(){
		return updatedDate;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setCustPay(long custPay){
		this.custPay = custPay;
	}

	public long getCustPay(){
		return custPay;
	}

	public void setTotalPayment(int totalPayment){
		this.totalPayment = totalPayment;
	}

	public int getTotalPayment(){
		return totalPayment;
	}

	public void setCreatedDate(String createdDate){
		this.createdDate = createdDate;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

	public String getCreatedBy(){
		return createdBy;
	}

	public void setLastUpdate(String lastUpdate){
		this.lastUpdate = lastUpdate;
	}

	public String getLastUpdate(){
		return lastUpdate;
	}

	public void setTransDate(String transDate){
		this.transDate = transDate;
	}

	public String getTransDate(){
		return transDate;
	}

	public void setCustomerId(int customerId){
		this.customerId = customerId;
	}

	public int getCustomerId(){
		return customerId;
	}

	public void setTotalTrans(int totalTrans){
		this.totalTrans = totalTrans;
	}

	public int getTotalTrans(){
		return totalTrans;
	}

	public void setTransStatus(String transStatus){
		this.transStatus = transStatus;
	}

	public String getTransStatus(){
		return transStatus;
	}
}