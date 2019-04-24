package id.hike.apps.android_mpos_mumu.global.global_model;

/**
 * Created by root on 14/07/17.
 */

public class ModelTransaksi {
    public Integer transaId;
    public String productName;
    public String username;
    public long productId;
    public long basePrice;
    public long salesPrice;
    public Integer stock;

    public int transaStatus_flag;
    public int transItemStatus_flag;

    public Integer baselineStock;
    public Integer jumlahBeli;
    public String pictUrlPath;
    public String category;
    public String brand;
    public String satuan;
    public String upTime;

    public Long maxPrice;
    public Long minPrice;

    public ModelTransaksi(Integer transaId, String productName, String username, long productId, long basePrice, long salesPrice, Integer stock, int transaStatus_flag, int transItemStatus_flag, Integer baselineStock, Integer jumlahBeli, String pictUrlPath, String category, String brand, String satuan, String upTime, Long maxPrice, Long minPrice) {
        this.transaId = transaId;
        this.productName = productName;
        this.username = username;
        this.productId = productId;
        this.basePrice = basePrice;
        this.salesPrice = salesPrice;
        this.stock = stock;
        this.transaStatus_flag = transaStatus_flag;
        this.transItemStatus_flag = transItemStatus_flag;
        this.baselineStock = baselineStock;
        this.jumlahBeli = jumlahBeli;
        this.pictUrlPath = pictUrlPath;
        this.category = category;
        this.brand = brand;
        this.satuan = satuan;
        this.upTime = upTime;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
    }
}
