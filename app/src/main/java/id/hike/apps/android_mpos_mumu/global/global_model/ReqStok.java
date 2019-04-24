package id.hike.apps.android_mpos_mumu.global.global_model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 23/08/17.
 */

public class ReqStok {

    public List<ReqStokList> trsalesitem =new ArrayList<>();

    public List<ReqStokList> getTrsalesitem() {
        return trsalesitem;
    }

    public ReqStok() {
    }

    public ReqStok(List<ReqStokList> trsalesitem) {
        this.trsalesitem = trsalesitem;
    }

    public static class ReqStokList{
        Integer qty, summaryQty;
        Long productId;

        public ReqStokList() {
        }

        public ReqStokList(Integer qty, Integer summaryQty, Long productId) {
            this.qty = qty;
            this.summaryQty = summaryQty;
            this.productId = productId;
        }
    }
}
