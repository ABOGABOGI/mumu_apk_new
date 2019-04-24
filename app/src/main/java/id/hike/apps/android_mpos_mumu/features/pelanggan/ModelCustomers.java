package id.hike.apps.android_mpos_mumu.features.pelanggan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by getwiz on 13/06/17.
 */

public class ModelCustomers {
    public ModelCustomers(int perPage, int total, List<DataItem> data, int lastPage, Object nextPageUrl, int from, int to, Object prevPageUrl, int currentPage) {
        this.perPage = perPage;
        this.total = total;
        this.data = data;
        this.lastPage = lastPage;
        this.nextPageUrl = nextPageUrl;
        this.from = from;
        this.to = to;
        this.prevPageUrl = prevPageUrl;
        this.currentPage = currentPage;
    }

    public ModelCustomers() {
    }

    @SerializedName("per_page")
    @Expose
    private int perPage;

    @SerializedName("total")
    @Expose
    private int total;

    @SerializedName("data")
    @Expose
    private List<DataItem> data;

    @SerializedName("last_page")
    @Expose
    private int lastPage;

    @SerializedName("next_page_url")
    @Expose
    private Object nextPageUrl;

    @SerializedName("from")
    @Expose
    private int from;

    @SerializedName("to")
    @Expose
    private int to;

    @SerializedName("prev_page_url")
    @Expose
    private Object prevPageUrl;

    @SerializedName("current_page")
    @Expose
    private int currentPage;

    public int getPerPage(){
        return perPage;
    }

    public int getTotal(){
        return total;
    }

    public List<DataItem> getData(){
        return data;
    }

    public int getLastPage(){
        return lastPage;
    }

    public Object getNextPageUrl(){
        return nextPageUrl;
    }

    public int getFrom(){
        return from;
    }

    public int getTo(){
        return to;
    }

    public Object getPrevPageUrl(){
        return prevPageUrl;
    }

    public int getCurrentPage(){
        return currentPage;
    }

}