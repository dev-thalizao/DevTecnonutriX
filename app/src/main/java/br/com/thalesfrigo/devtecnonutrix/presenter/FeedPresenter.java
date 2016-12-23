package br.com.thalesfrigo.devtecnonutrix.presenter;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import br.com.thalesfrigo.devtecnonutrix.R;
import br.com.thalesfrigo.devtecnonutrix.model.Feed;
import br.com.thalesfrigo.devtecnonutrix.networking.FeedListResponse;
import br.com.thalesfrigo.devtecnonutrix.networking.BaseNetworkingConfig;
import br.com.thalesfrigo.devtecnonutrix.service.FeedService;
import br.com.thalesfrigo.devtecnonutrix.util.LoadMode;
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
                feedView.finishProress();

                FeedListResponse feedListResponse = response.body();
                if(feedListResponse.isSuccess()){
                    updateParamters(feedListResponse);

                    List<Feed> feeds = feedListResponse.getFeeds();
                    if(feeds.size() > 0){
                        feedView.updateFeed(feeds, LoadMode.SCROLL);
                    } else {
                        feedView.setEmptyFeed();
                    }
                    Log.d(TAG, response.toString());
                } else {
                    feedView.showErrorMessage(R.string.generic_error);
                    feedView.setEmptyFeed();
                }
            }

            @Override
            public void onFailure(Call<FeedListResponse> call, Throwable t) {
                feedView.finishProress();
                Log.e(TAG, t.toString());
                feedView.showErrorMessage(R.string.generic_error);
                feedView.setEmptyFeed();
            }
        });
    }

    public void incrementFeeds(final List<Feed> feeds){
        Map<String, String> options = new HashMap<>();
        options.put("p", String.valueOf(pageNumber));
        options.put("t", String.valueOf(timestamp));

        final FeedView feedView = this.feedView;

        feedView.startProgress();
        Call<FeedListResponse> call = this.feedService.getFeeds(options);
        call.enqueue(new Callback<FeedListResponse>() {
            @Override
            public void onResponse(Call<FeedListResponse> call, Response<FeedListResponse> response) {
                feedView.finishProress();

                FeedListResponse feedListResponse = response.body();
                if(feedListResponse.isSuccess()){
                    updateParamters(feedListResponse);
                    feeds.addAll(feedListResponse.getFeeds());

                    if(feeds.size() > 0){
                        feedView.updateFeed(feeds, LoadMode.SCROLL);
                    } else {
                        feedView.setEmptyFeed();
                    }
                    Log.d(TAG, response.toString());
                } else {
                    feedView.showErrorMessage(R.string.generic_error);
                    feedView.setEmptyFeed();
                }
            }

            @Override
            public void onFailure(Call<FeedListResponse> call, Throwable t) {
                feedView.finishProress();
                Log.e(TAG, t.toString());
                feedView.setEmptyFeed();
                feedView.showErrorMessage(R.string.generic_error);
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