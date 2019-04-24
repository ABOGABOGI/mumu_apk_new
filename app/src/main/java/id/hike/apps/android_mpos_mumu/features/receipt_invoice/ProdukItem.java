package id.hike.apps.android_mpos_mumu.features.receipt_invoice;

import com.google.gson.annotations.SerializedName;

public class ProdukItem{

	@SerializedName("qty")
	private int qty;

	@SerializedName("sales_price")
	private long salesPrice;

	@SerializedName("category")
	private String category;

	@SerializedName("satuan")
	private String satuan;

	@SerializedName("product_name")
	private String productName;

	public String getSatuan() {
		return satuan;
	}

	public void setSatuan(String satuan) {
		this.satuan = satuan;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setQty(int qty){
		this.qty = qty;
	}

	public int getQty(){
		return qty;
	}

	public void setSalesPrice(long salesPrice){
		this.salesPrice = salesPrice;
	}

	public long getSalesPrice(){
		return salesPrice;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}
}