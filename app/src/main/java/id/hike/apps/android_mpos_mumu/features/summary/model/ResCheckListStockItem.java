package id.hike.apps.android_mpos_mumu.features.summary.model;

import com.google.gson.annotations.SerializedName;

public class ResCheckListStockItem {

    public ResCheckListStockItem(String productId, String qty, String stock, String productName) {
        this.productId = productId;
        this.qty = qty;
        this.stock = stock;
        this.productName = productName;
    }

    @SerializedName("productId")
	private String productId;

	@SerializedName("qty")
	private String qty;

	@SerializedName("stock")
	private String stock;

	@SerializedName("productName")
	private String productName;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setQty(String qty){
		this.qty = qty;
	}

	public String getQty(){
		return qty;
	}

	public void setStock(String stock){
		this.stock = stock;
	}

	public String getStock(){
		return stock;
	}


}