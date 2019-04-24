package id.hike.apps.android_mpos_mumu.features.profil;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("userTerima")
	private String userTerima;

	@SerializedName("updatedBy")
	private String updatedBy;

	@SerializedName("lastRequest")
	private String lastRequest;

	@SerializedName("outletId")
	private Integer outletId;

	@SerializedName("remark")
	private String remark;

	@SerializedName("updatedDate")
	private String updatedDate;

	@SerializedName("nominalKirim")
	private Long nominalKirim;

	@SerializedName("idModal")
	private Long idModal;

	@SerializedName("nominalTerima")
	private Long nominalTerima;

	@SerializedName("createdDate")
	private String createdDate;

	@SerializedName("createdBy")
	private String createdBy;

	@SerializedName("nominalSetor")
	private Long nominalSetor;

	@SerializedName("userSetor")
	private Long userSetor;

	@SerializedName("lastUpdate")
	private String lastUpdate;

	@SerializedName("tglmodal")
	private Long tglmodal;

	@SerializedName("status")
	private int status;

	public void setUserTerima(String userTerima){
		this.userTerima = userTerima;
	}

	public String getUserTerima(){
		return userTerima;
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

	public void setOutletId(Integer outletId){
		this.outletId = outletId;
	}

	public Integer getOutletId(){
		return outletId;
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

	public void setNominalKirim(Long nominalKirim){
		this.nominalKirim = nominalKirim;
	}

	public Long getNominalKirim(){
		return nominalKirim;
	}

	public void setIdModal(Long idModal){
		this.idModal = idModal;
	}

	public Long getIdModal(){
		return idModal;
	}

	public void setNominalTerima(Long nominalTerima){
		this.nominalTerima = nominalTerima;
	}

	public Long getNominalTerima(){
		return nominalTerima;
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

	public void setNominalSetor(Long nominalSetor){
		this.nominalSetor = nominalSetor;
	}

	public Long getNominalSetor(){
		return nominalSetor;
	}

	public void setUserSetor(long userSetor){
		this.userSetor = userSetor;
	}

	public Long getUserSetor(){
		return userSetor;
	}

	public void setLastUpdate(String lastUpdate){
		this.lastUpdate = lastUpdate;
	}

	public String getLastUpdate(){
		return lastUpdate;
	}

	public void setTglmodal(Long tglmodal){
		this.tglmodal = tglmodal;
	}

	public Long getTglmodal(){
		return tglmodal;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}