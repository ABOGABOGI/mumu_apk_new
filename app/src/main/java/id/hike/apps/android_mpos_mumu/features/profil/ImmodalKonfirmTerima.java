package id.hike.apps.android_mpos_mumu.features.profil;

public class ImmodalKonfirmTerima {
	private String userTerima;
	private Long idModal;
	private Long nominalTerima;
	private String createdBy;
	private Integer outletId;

	public ImmodalKonfirmTerima(String userTerima, Long idModal, Long nominalTerima, String createdBy, Integer outletId) {
		this.userTerima = userTerima;
		this.idModal = idModal;
		this.nominalTerima = nominalTerima;
		this.createdBy = createdBy;
		this.outletId = outletId;
	}

	public void setUserTerima(String userTerima){
		this.userTerima = userTerima;
	}

	public String getUserTerima(){
		return userTerima;
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

	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

	public String getCreatedBy(){
		return createdBy;
	}

	public void setOutletId(Integer outletId){
		this.outletId = outletId;
	}

	public Integer getOutletId(){
		return outletId;
	}
}
