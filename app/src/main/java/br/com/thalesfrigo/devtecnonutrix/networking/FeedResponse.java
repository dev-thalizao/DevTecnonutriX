package br.com.thalesfrigo.devtecnonutrix.networking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import br.com.thalesfrigo.devtecnonutrix.model.Feed;

/**
 * Created by thalesfrigo on 12/22/16.
 */

public class FeedResponse {

    @Expose
    private boolean success;

    @SerializedName("item")
    @Expose
    private Feed feed;

    public boolean isSuccess() {
        return success;
    }

    public Feed getFeed() {
        return feed;
    }
}
