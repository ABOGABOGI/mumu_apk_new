package id.hike.apps.android_mpos_mumu.features.summary.model;

public class ReqGlobalSales {
    private String updatedBy;
    private String lastRequest;
    private String totalItem;
    private String transId;
    private String outletId;
    private String change;
    private String totalDisc;
    private String remark;
    private String negoDisc;
    private String updatedDate;
    private String userId;
    private String custPay;
    private String totalPayment;
    private String createdDate;
    private String createdBy;
    private String lastUpdate;
    private String customerId;
    private String totalTrans;
    private String transStatus;
    private String appsId;
    private String deviceId;
    private String imei;
    private String storeId;

    public ReqGlobalSales(String updatedBy, String lastRequest, String totalItem, String transId, String outletId, String change, String totalDisc, String remark, String negoDisc, String updatedDate, String userId, String custPay, String totalPayment, String createdDate, String createdBy, String lastUpdate, String customerId, String totalTrans, String transStatus,
                          String appsId, String deviceId, String imei, String storeId) {
        this.updatedBy = updatedBy;
        this.lastRequest = lastRequest;
        this.totalItem = totalItem;
        this.transId = transId;
        this.outletId = outletId;
        this.change = change;
        this.totalDisc = totalDisc;
        this.remark = remark;
        this.negoDisc = negoDisc;
        this.updatedDate = updatedDate;
        this.userId = userId;
        this.custPay = custPay;
        this.totalPayment = totalPayment;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.customerId = customerId;
        this.totalTrans = totalTrans;
        this.transStatus = transStatus;
        this.appsId=appsId;
        this.deviceId=deviceId;
        this.imei=imei;
        this.storeId=storeId;
    }
}
