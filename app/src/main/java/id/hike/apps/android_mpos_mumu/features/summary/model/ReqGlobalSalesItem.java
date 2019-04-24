package id.hike.apps.android_mpos_mumu.features.summary.model;

public class ReqGlobalSalesItem {
	private String itemTransId;
	private String productId;
	private String lastRequest;
	private String salesPrice;
	private String detailId;
	private String pcntDisc;
	private String itemStatus;
	private String qty;
	private String lastUpdate;
	private String transDate;
	private String priceDisc;
	private String promoId;
	private String basePrice;

	public ReqGlobalSalesItem(String itemTransId, String productId, String lastRequest, String salesPrice, String detailId, String pcntDisc, String itemStatus, String qty, String lastUpdate, String transDate, String priceDisc, String promoId, String basePrice) {
		this.itemTransId = itemTransId;
		this.productId = productId;
		this.lastRequest = lastRequest;
		this.salesPrice = salesPrice;
		this.detailId = detailId;
		this.pcntDisc = pcntDisc;
		this.itemStatus = itemStatus;
		this.qty = qty;
		this.lastUpdate = lastUpdate;
		this.transDate = transDate;
		this.priceDisc = priceDisc;
		this.promoId = promoId;
		this.basePrice = basePrice;
	}
}
