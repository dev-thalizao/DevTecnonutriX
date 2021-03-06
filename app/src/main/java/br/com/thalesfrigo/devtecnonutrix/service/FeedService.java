package br.com.thalesfrigo.devtecnonutrix.service;

import java.util.Map;

import br.com.thalesfrigo.devtecnonutrix.networking.FeedListResponse;
import br.com.thalesfrigo.devtecnonutrix.networking.FeedResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by thalesfrigo on 12/20/16.
 */

public interface FeedService {

    @GET("feed")
    Call<FeedListResponse> getFeeds();

    @GET("feed")
    Call<FeedListResponse> getFeeds(@QueryMap Map<String, String> options);

    @GET("feed/{feedHash}")
    Call<FeedResponse> getFeed(@Path("feedHash") String feedHash);
}
