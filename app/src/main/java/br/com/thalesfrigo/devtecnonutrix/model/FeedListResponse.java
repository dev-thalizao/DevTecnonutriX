package br.com.thalesfrigo.devtecnonutrix.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.thalesfrigo.devtecnonutrix.model.Feed;

/**
 * Created by thalesfrigo on 12/21/16.
 */

public class FeedListResponse {

    @Expose
    private boolean success;

    @SerializedName("p")
    @Expose
    private int pageNumber;

    @SerializedName("t")
    @Expose
    private long timestamp;

    @SerializedName("items")
    private List<Feed> feeds;

    public boolean isSuccess() {
        return success;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public List<Feed> getFeeds() {
        return feeds;
    }
}
