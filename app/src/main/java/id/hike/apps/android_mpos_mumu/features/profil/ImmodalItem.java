package id.hike.apps.android_mpos_mumu.features.profil;

public class ImmodalItem{
	private Long idModal;
	private Long nominalSetor;
	private String userSetor;

	public void setIdModal(Long idModal){
		this.idModal = idModal;
	}

	public Long getIdModal(){
		return idModal;
	}

	public void setNominalSetor(Long nominalSetor){
		this.nominalSetor = nominalSetor;
	}

	public Long getNominalSetor(){
		return nominalSetor;
	}

	public void setUserSetor(String userSetor){
		this.userSetor = userSetor;
	}

	public String getUserSetor(){
		return userSetor;
	}

	public ImmodalItem(Long idModal, Long nominalSetor, String userSetor) {
		this.idModal = idModal;
		this.nominalSetor = nominalSetor;
		this.userSetor = userSetor;
	}
}
