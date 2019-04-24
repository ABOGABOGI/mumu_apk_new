package id.hike.apps.android_mpos_mumu.features.profil;

import java.util.List;

public class ReqModalSetor{
	private List<ImmodalItem> immodal;

	public void setImmodal(List<ImmodalItem> immodal){
		this.immodal = immodal;
	}

	public List<ImmodalItem> getImmodal(){
		return immodal;
	}

	public ReqModalSetor(List<ImmodalItem> immodal) {
		this.immodal = immodal;
	}
}