package id.hike.apps.android_mpos_mumu.features.summary.model;

public class ReqSalesItemBatal {
	private String negoDisc;
	private Long totalPayment;
	private Long totalItem;
	private String createdBy;

	private String transId;
	private Long totalDisc;
	private String remark;
	private Long totalTrans;

	private String transStatus;
	private Long customerId;

	public ReqSalesItemBatal(String negoDisc, Long totalPayment, Long totalItem, String createdBy, String transId, Long totalDisc, String remark, String transStatus, Long totalTrans, Long customerId) {
		this.negoDisc = negoDisc;
		this.totalPayment = totalPayment;
		this.totalItem = totalItem;
		this.createdBy = createdBy;
		this.transId = transId;
		this.totalDisc = totalDisc;
		this.remark = remark;
		this.totalTrans = totalTrans;
		this.transStatus = transStatus;
		this.customerId = customerId;
	}
}
