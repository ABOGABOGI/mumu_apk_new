package id.hike.apps.android_mpos_mumu.features.summary.model;

import java.util.List;

public class ReqBatchDeleteItem{
	private List<ReqSalesItemBatal> trsales;
	private List<ReqsalesItemListBatal> trsalesitem;

	public ReqBatchDeleteItem(List<ReqSalesItemBatal> trsales, List<ReqsalesItemListBatal> trsalesitem) {
		this.trsales = trsales;
		this.trsalesitem = trsalesitem;
	}
}