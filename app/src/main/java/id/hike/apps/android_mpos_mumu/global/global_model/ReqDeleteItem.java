package id.hike.apps.android_mpos_mumu.global.global_model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 24/08/17.
 */

public class ReqDeleteItem {
    List<ReqDeleteItemList> trsalesitem=new ArrayList<>();

    public ReqDeleteItem(List<ReqDeleteItemList> trsalesitem) {
        this.trsalesitem = trsalesitem;
    }

    public class ReqDeleteItemList{
        long detailId;
        String itemStatus;

        public ReqDeleteItemList(long detailId, String itemStatus) {
            this.detailId = detailId;
            this.itemStatus = itemStatus;
        }
    }
}
