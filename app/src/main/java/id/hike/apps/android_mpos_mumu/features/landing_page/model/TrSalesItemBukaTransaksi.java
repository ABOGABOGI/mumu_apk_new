package id.hike.apps.android_mpos_mumu.features.landing_page.model;

public class TrSalesItemBukaTransaksi {
	private String outletId;
	private long customerId;
	private String userId;

	public TrSalesItemBukaTransaksi(String outletId, long customerId, String userId) {
		this.outletId = outletId;
		this.customerId = customerId;
		this.userId = userId;
	}
}
