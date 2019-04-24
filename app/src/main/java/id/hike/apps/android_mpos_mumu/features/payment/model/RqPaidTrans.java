package id.hike.apps.android_mpos_mumu.features.payment.model;

public class RqPaidTrans {
    String userId;
    long outletId;
    String transDate;
    String createdBy;
    String paymentMethod;
    String transId;

    public RqPaidTrans(String userId, long outletId, String transDate, String createdBy, String paymentMethod, String transID) {
        this.userId = userId;
        this.outletId = outletId;
        this.transDate = transDate;
        this.createdBy = createdBy;
        this.paymentMethod = paymentMethod;
        this.transId = transID;
    }
}
