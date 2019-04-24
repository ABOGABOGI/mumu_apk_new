package id.hike.apps.android_mpos_mumu.features.landing_page.model;

import com.google.gson.annotations.SerializedName;

public class ModelEditTransDataItem {

	@SerializedName("trans_date")
	private String transDate;

	@SerializedName("baseline_stock")
	private int baselineStock;

	@SerializedName("baseline_stock_status")
	private String baselineStockStatus;

	@SerializedName("item_trans_id")
	private String itemTransId;

	@SerializedName("price_disc")
	private long priceDisc;

	@SerializedName("detail_id")
	private long detailId;

	@SerializedName("item_status")
	private String itemStatus;

	@SerializedName("promo_id")
	private String promoId;

	@SerializedName("product_name")
	private String productName;

	@SerializedName("pcnt_disc")
	private long pcntDisc;

	@SerializedName("satuan")
	private String satuan;

	@SerializedName("product_id")
	private Long productId;

	@SerializedName("qty")
	private Long qty;

	@SerializedName("last_update")
	private String lastUpdate;

	@SerializedName("base_price")
	private Long basePrice;

	@SerializedName("sales_price")
	private Long salesPrice;

	@SerializedName("last_request")
	private long lastRequest;

	@SerializedName("stock")
	private long stock;

	@SerializedName("max_price")
	private long maxPrice;

	@SerializedName("min_price")
	private long minPrice;

	public long getMinPrice() {
		return minPrice;
	}

	public long getMaxPrice() {
		return maxPrice;
	}

	public long getStock() {
		return stock;
	}

	public void setTransDate(String transDate){
		this.transDate = transDate;
	}

	public String getTransDate(){
		return transDate;
	}

	public void setBaselineStock(int baselineStock){
		this.baselineStock = baselineStock;
	}

	public int getBaselineStock(){
		return baselineStock;
	}

	public void setBaselineStockStatus(String baselineStockStatus){
		this.baselineStockStatus = baselineStockStatus;
	}

	public String getBaselineStockStatus(){
		return baselineStockStatus;
	}

	public void setItemTransId(String itemTransId){
		this.itemTransId = itemTransId;
	}

	public String getItemTransId(){
		return itemTransId;
	}

	public void setPriceDisc(long priceDisc){
		this.priceDisc = priceDisc;
	}

	public long getPriceDisc(){
		return priceDisc;
	}

	public void setDetailId(long detailId){
		this.detailId = detailId;
	}

	public long getDetailId(){
		return detailId;
	}

	public void setItemStatus(String itemStatus){
		this.itemStatus = itemStatus;
	}

	public String getItemStatus(){
		return itemStatus;
	}

	public void setPromoId(String promoId){
		this.promoId = promoId;
	}

	public String getPromoId(){
		return promoId;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setPcntDisc(long pcntDisc){
		this.pcntDisc = pcntDisc;
	}

	public long getPcntDisc(){
		return pcntDisc;
	}

	public void setSatuan(String satuan){
		this.satuan = satuan;
	}

	public String getSatuan(){
		return satuan;
	}

	public void setProductId(Long productId){
		this.productId = productId;
	}

	public Long getProductId(){
		return productId;
	}

	public void setQty(Long qty){
		this.qty = qty;
	}

	public Long getQty(){
		return qty;
	}

	public void setLastUpdate(String lastUpdate){
		this.lastUpdate = lastUpdate;
	}

	public String getLastUpdate(){
		return lastUpdate;
	}

	public void setBasePrice(Long basePrice){
		this.basePrice = basePrice;
	}

	public Long getBasePrice(){
		return basePrice;
	}

	public void setSalesPrice(Long salesPrice){
		this.salesPrice = salesPrice;
	}

	public Long getSalesPrice(){
		return salesPrice;
	}

	public void setLastRequest(long lastRequest){
		this.lastRequest = lastRequest;
	}

	public long getLastRequest(){
		return lastRequest;
	}
}