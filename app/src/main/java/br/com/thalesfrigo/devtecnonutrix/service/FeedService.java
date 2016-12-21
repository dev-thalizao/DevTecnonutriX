package br.com.thalesfrigo.devtecnonutrix.service;

import br.com.thalesfrigo.devtecnonutrix.model.Feed;
import br.com.thalesfrigo.devtecnonutrix.model.FeedListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by thalesfrigo on 12/20/16.
 */

public interface FeedService {

    @GET("feed")
    Call<FeedListResponse> getFeeds(); // @QueryMap Map<String, String> options

    @GET("feed/{feedHash}")
    Call<Feed> getFeed(@Path("feedHash") String feedHash);
}
