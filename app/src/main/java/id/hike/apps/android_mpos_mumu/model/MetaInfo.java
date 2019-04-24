package id.hike.apps.android_mpos_mumu.model;

import java.io.Serializable;

public class MetaInfo implements Serializable {

    private String metakey;
    private int metatype;
    private String metavalue;
    private String metakategori;
    private String metafilter;
    private String metakodeproduk;
    private Integer metasorting;

    public String getMetakey() {
        return metakey;
    }

    public void setMetakey(String metakey) {
        this.metakey = metakey;
    }

    public int getMetatype() {
        return metatype;
    }

    public void setMetatype(int metatype) {
        this.metatype = metatype;
    }

    public String getMetavalue() {
        return metavalue;
    }

    public void setMetavalue(String metavalue) {
        this.metavalue = metavalue;
    }

    public String getMetakategori() {
        return metakategori;
    }

    public void setMetakategori(String metakategori) {
        this.metakategori = metakategori;
    }

    public String getMetakilter() {
        return metafilter;
    }

    public void setMetafilter(String metafilter) {
        this.metafilter = metafilter;
    }

    public String getMetakodeproduk() {
        return metakodeproduk;
    }

    public void setMetakodeproduk(String metakodeproduk) {
        this.metakodeproduk = metakodeproduk;
    }

    public Integer getMetasorting() {
        return metasorting;
    }

    public void setMetasorting(Integer metasorting) {
        this.metasorting = metasorting;
    }
}
