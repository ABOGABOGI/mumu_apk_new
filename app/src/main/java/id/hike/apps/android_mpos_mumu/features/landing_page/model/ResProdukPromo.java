package id.hike.apps.android_mpos_mumu.features.landing_page.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by M Alvin Syahrin on 08/06/2018.
 */

public class ResProdukPromo {

    /**
     * promoProdId : 1
     * productId : 885529106
     * productName : Roti Goreng
     * pictUrl : 1883679813bakso.jpg
     * outletId : 193
     * priceSale : 3200
     * priceNormal : 4000
     * promoListId : 1
     * discType : pr
     * discVal : 20
     * startDate : 2018-06-07T08:03:00.000+0000
     * endDate : 2018-06-08T19:01:00.000+0000
     */

    @SerializedName("promoProdId")
    private int promoProdId;
    @SerializedName("productId")
    private int productId;
    @SerializedName("productName")
    private String productName;
    @SerializedName("pictUrl")
    private String pictUrl;
    @SerializedName("outletId")
    private int outletId;
    @SerializedName("priceSale")
    private int priceSale;
    @SerializedName("priceNormal")
    private int priceNormal;
    @SerializedName("promoListId")
    private int promoListId; // promo id
    @SerializedName("discType")
    private String discType;
    @SerializedName("discVal")
    private int discVal;
    @SerializedName("startDate")
    private String startDate;
    @SerializedName("endDate")
    private String endDate;

    public int getPromoProdId() {
        return promoProdId;
    }

    public void setPromoProdId(int promoProdId) {
        this.promoProdId = promoProdId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPictUrl() {
        return pictUrl;
    }

    public void setPictUrl(String pictUrl) {
        this.pictUrl = pictUrl;
    }

    public int getOutletId() {
        return outletId;
    }

    public void setOutletId(int outletId) {
        this.outletId = outletId;
    }

    public int getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(int priceSale) {
        this.priceSale = priceSale;
    }

    public int getPriceNormal() {
        return priceNormal;
    }

    public void setPriceNormal(int priceNormal) {
        this.priceNormal = priceNormal;
    }

    public int getPromoListId() {
        return promoListId;
    }

    public void setPromoListId(int promoListId) {
        this.promoListId = promoListId;
    }

    public String getDiscType() {
        return discType;
    }

    public void setDiscType(String discType) {
        this.discType = discType;
    }

    public int getDiscVal() {
        return discVal;
    }

    public void setDiscVal(int discVal) {
        this.discVal = discVal;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
