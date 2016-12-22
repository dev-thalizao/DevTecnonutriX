package br.com.thalesfrigo.devtecnonutrix.presenter;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.thalesfrigo.devtecnonutrix.model.Feed;
import br.com.thalesfrigo.devtecnonutrix.model.FeedListResponse;
import br.com.thalesfrigo.devtecnonutrix.networking.BaseNetworkingConfig;
import br.com.thalesfrigo.devtecnonutrix.service.FeedService;
import br.com.thalesfrigo.devtecnonutrix.view.contract.FeedView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thalesfrigo on 12/20/16.
 */

public class FeedPresenter implements BasePresenter<FeedView> {

    public static final String TAG = "FEED-PRESENTER";

    private FeedView feedView;
    private FeedService feedService;

    private int pageNumber;
    private long timestamp;

    public FeedPresenter(){
        this.feedService = BaseNetworkingConfig.createService(FeedService.class);
    }

    @Override
    public void attachView(FeedView view) {
        this.feedView = view;
    }

    @Override
    public void detachView() {
        this.feedView = null;
    }

    public void getFeeds(){
        // Reset scroll fields
        updateParamters(null);

        final FeedView feedView = this.feedView;
        feedView.startProgress();
        
        Call<FeedListResponse> call = this.feedService.getFeeds();
        call.enqueue(new Callback<FeedListResponse>() {
            @Override
            public void onResponse(Call<FeedListResponse> call, Response<FeedListResponse> response) {

                FeedListResponse feedListResponse = response.body();
                updateParamters(feedListResponse);

                feedView.updateFeed(feedListResponse.getFeeds());
                feedView.finishProress();
                Log.d(TAG, response.toString());
            }

            @Override
            public void onFailure(Call<FeedListResponse> call, Throwable t) {
                feedView.finishProress();
                Log.e(TAG, t.toString());
            }
        });
    }

    public void incrementFeeds(final List<Feed> feeds){
        Map<String, String> options = new HashMap<>();
        options.put("p", String.valueOf(pageNumber));
        options.put("t", String.valueOf(timestamp));

        final FeedView feedView = this.feedView;

        Log.d(TAG, "Previous array - Size: " + feeds.size() +  "\n" + feeds);


        feedView.startProgress();
        Call<FeedListResponse> call = this.feedService.getFeeds(options);
        call.enqueue(new Callback<FeedListResponse>() {
            @Override
            public void onResponse(Call<FeedListResponse> call, Response<FeedListResponse> response) {

                FeedListResponse feedListResponse = response.body();
                updateParamters(feedListResponse);
                feeds.addAll(feedListResponse.getFeeds());

                Log.d(TAG, "Next array - Size: " + feeds.size() +  "\n" + feeds);

                feedView.updateFeed(feeds);
                feedView.finishProress();
                Log.d(TAG, response.toString());
            }

            @Override
            public void onFailure(Call<FeedListResponse> call, Throwable t) {
                feedView.finishProress();
                Log.e(TAG, t.toString());
            }
        });
    }

    private void updateParamters(FeedListResponse feedListResponse){
        if(feedListResponse != null){
            pageNumber++;
            timestamp = feedListResponse.getTimestamp();
        } else {
            pageNumber = 0;
            timestamp = 0;
        }
    }
}