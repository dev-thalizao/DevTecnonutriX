package br.com.thalesfrigo.devtecnonutrix.networking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.thalesfrigo.devtecnonutrix.model.Feed;
import br.com.thalesfrigo.devtecnonutrix.model.User;

/**
 * Created by thalesfrigo on 12/23/16.
 */

public class UserDetailResponse {

    @Expose
    private boolean success;

    @SerializedName("p")
    @Expose
    private int pageNumber;

    @SerializedName("t")
    @Expose
    private long timestamp;

    @SerializedName("profile")
    @Expose
    private User user;

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

    public User getUser() {
        return user;
    }

    public List<Feed> getFeeds() {
        return feeds;
    }
}
