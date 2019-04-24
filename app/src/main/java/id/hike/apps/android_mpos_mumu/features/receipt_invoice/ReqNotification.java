package id.hike.apps.android_mpos_mumu.features.receipt_invoice;

/**
 * Created by root on 04/08/17.
 */

public class ReqNotification {
    String email;
    String subject;
    String text;

    public ReqNotification(String email, String subject, String text) {
        this.email = email;
        this.subject = subject;
        this.text = text;
    }
}
