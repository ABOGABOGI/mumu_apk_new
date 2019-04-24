package id.hike.apps.android_mpos_mumu.features.summary.model;

import java.util.List;

public class ReqGlobal{
	private List<ReqGlobalSales> trsales;
	private List<ReqGlobalSalesItem> trsalesitem;

	public ReqGlobal(List<ReqGlobalSales> trsales, List<ReqGlobalSalesItem> trsalesitem) {
		this.trsales = trsales;
		this.trsalesitem = trsalesitem;
	}
}