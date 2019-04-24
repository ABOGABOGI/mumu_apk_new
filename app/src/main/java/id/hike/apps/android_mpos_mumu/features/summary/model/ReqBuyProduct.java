package id.hike.apps.android_mpos_mumu.features.summary.model;

/**
 * Created by root on 21/07/17.
 */

public class ReqBuyProduct {
    public long itemTransId;
    public long productId;
    public String itemStatus;
    public Integer qty;
    public String promoId;
    public Double basePrice;
    public Double salesPrice;
    public Double pcntDisc;
    public Double priceDisc;
    public String lastRequest;
    public String lastUpdate;
    public Integer detailId;
    public Integer baselineStock;
    public Integer stock;

    public Long maxPrice;

    public ReqBuyProduct(long itemTransId, long productId, String itemStatus, Integer qty, String promoId, Double basePrice, Double salesPrice, Double pcntDisc, Double priceDisc, String lastRequest, String lastUpdate, int detailId, Integer baselineStock, Integer stock, Long maxPrice) {
        this.itemTransId = itemTransId;
        this.productId = productId;
        this.itemStatus = itemStatus;
        this.qty = qty;
        this.promoId = promoId;
        this.basePrice = basePrice;
        this.salesPrice = salesPrice;
        this.pcntDisc = pcntDisc;
        this.priceDisc = priceDisc;
        this.lastRequest = lastRequest;
        this.lastUpdate = lastUpdate;
        this.detailId = detailId;
        this.baselineStock = baselineStock;
        this.stock = stock;
        this.maxPrice = maxPrice;
    }
}
