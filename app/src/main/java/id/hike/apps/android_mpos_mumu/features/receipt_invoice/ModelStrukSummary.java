
package id.hike.apps.android_mpos_mumu.features.receipt_invoice;

import com.google.gson.annotations.SerializedName;

public class ModelStrukSummary {

    @SerializedName("diskon")
    private Object mDiskon;
    @SerializedName("pcnt_disc")
    private Object mPcntDisc;
    @SerializedName("ppn")
    private Object mPpn;
    @SerializedName("total_harga")
    private Object mTotalHarga;
    @SerializedName("total_harga_ppn")
    private Object mTotalHargaPpn;
    @SerializedName("total_qty")
    private Long mTotalQty;

    public Object getDiskon() {
        return mDiskon;
    }

    public void setDiskon(Object diskon) {
        mDiskon = diskon;
    }

    public Object getPcntDisc() {
        return mPcntDisc;
    }

    public void setPcntDisc(Object pcntDisc) {
        mPcntDisc = pcntDisc;
    }

    public Object getPpn() {
        return mPpn;
    }

    public void setPpn(Object ppn) {
        mPpn = ppn;
    }

    public Object getTotalHarga() {
        return mTotalHarga;
    }

    public void setTotalHarga(Object totalHarga) {
        mTotalHarga = totalHarga;
    }

    public Object getTotalHargaPpn() {
        return mTotalHargaPpn;
    }

    public void setTotalHargaPpn(Object totalHargaPpn) {
        mTotalHargaPpn = totalHargaPpn;
    }

    public Long getTotalQty() {
        return mTotalQty;
    }

    public void setTotalQty(Long totalQty) {
        mTotalQty = totalQty;
    }

}
