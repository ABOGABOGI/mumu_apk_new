
package id.hike.apps.android_mpos_mumu.features.receipt_invoice;

import com.google.gson.annotations.SerializedName;

public class ModelStrukProduk {

    @SerializedName("product_name")
    private String mProductName;
    @SerializedName("qty")
    private Long mQty;
    @SerializedName("sales_price")
    private Long mSalesPrice;

    public String getProductName() {
        return mProductName;
    }

    public void setProductName(String productName) {
        mProductName = productName;
    }

    public Long getQty() {
        return mQty;
    }

    public void setQty(Long qty) {
        mQty = qty;
    }

    public Long getSalesPrice() {
        return mSalesPrice;
    }

    public void setSalesPrice(Long salesPrice) {
        mSalesPrice = salesPrice;
    }

}
