package id.hike.apps.android_mpos_mumu.model;

import java.io.Serializable;

public class BaseIsoResponse implements Serializable {

    private String h39;
    private String h62;

    public String getH39() {
        return h39;
    }

    public void setH39(String h39) {
        this.h39 = h39;
    }

    public String getH62() {
        return h62;
    }

    public void setH62(String h62) {
        this.h62 = h62;
    }
}
