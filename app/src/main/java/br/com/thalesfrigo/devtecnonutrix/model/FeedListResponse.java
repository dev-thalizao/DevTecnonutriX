package br.com.thalesfrigo.devtecnonutrix.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.thalesfrigo.devtecnonutrix.model.Feed;

/**
 * Created by thalesfrigo on 12/21/16.
 */

public class FeedListResponse {

    @SerializedName("items")
    private List<Feed> feeds;

    public List<Feed> getFeeds() {
        return feeds;
    }
}
