package id.hike.apps.android_mpos_mumu.features.profil;

import java.util.List;

public class ReqModalKonfirmTerima{
	private List<ImmodalKonfirmTerima> immodal;

	public void setImmodal(List<ImmodalKonfirmTerima> immodal){
		this.immodal = immodal;
	}

	public List<ImmodalKonfirmTerima> getImmodal(){
		return immodal;
	}

	public ReqModalKonfirmTerima(List<ImmodalKonfirmTerima> immodal) {
		this.immodal = immodal;
	}
}