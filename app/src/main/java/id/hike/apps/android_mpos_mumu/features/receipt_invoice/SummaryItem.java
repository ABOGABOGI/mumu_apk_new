package id.hike.apps.android_mpos_mumu.features.receipt_invoice;

import com.google.gson.annotations.SerializedName;

public class SummaryItem{

	@SerializedName("item_trans_id")
	private String itemTransId;

	@SerializedName("qty")
	private int qty;

	@SerializedName("total_harga_nondisc")
	private long totalHargaNondisc;

	@SerializedName("created_date")
	private String createdDate;

	@SerializedName("nego_disc")
	private Long negoDisc;

    @SerializedName("payment_method")
    private String paymentMethod;

    @SerializedName("cust_pay")
    private Double custPay;

    @SerializedName("change")
    private Double change;

	public Long getNegoDisc() {
		return negoDisc;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public String getItemTransId() {
		return itemTransId;
	}

	public int getQty() {
		return qty;
	}

	public long getTotalHargaNondisc() {
		return totalHargaNondisc;
	}

	public String getCreatedDate() {
		return createdDate;
	}

    public Double getCustPay() {
        return custPay;
    }

    public Double getChange() {
        return change;
    }
}