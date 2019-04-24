package id.hike.apps.android_mpos_mumu.features.summary.model;

public class ReqsalesItemListBatal {
	private String itemTransId;
	private String productId;
	private String qty;
	private Long salesPrice;
	private Long basePrice;

	public ReqsalesItemListBatal(String itemTransId, String productId, String qty, Long salesPrice, Long basePrice) {
		this.itemTransId = itemTransId;
		this.productId = productId;
		this.qty = qty;
		this.salesPrice = salesPrice;
		this.basePrice = basePrice;
	}
}
