package id.hike.apps.android_mpos_mumu.features.landing_page.model;

import java.util.List;

public class ReqBukaTransaksi{
	private List<TrSalesItemBukaTransaksi> trsales;

	public void setTrsales(List<TrSalesItemBukaTransaksi> trsales){
		this.trsales = trsales;
	}

	public List<TrSalesItemBukaTransaksi> getTrsales(){
		return trsales;
	}

	public ReqBukaTransaksi(List<TrSalesItemBukaTransaksi> trsales) {
		this.trsales = trsales;
	}
}