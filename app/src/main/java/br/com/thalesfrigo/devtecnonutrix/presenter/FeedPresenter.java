package br.com.thalesfrigo.devtecnonutrix.presenter;

import android.util.Log;

import br.com.thalesfrigo.devtecnonutrix.model.FeedListResponse;
import br.com.thalesfrigo.devtecnonutrix.networking.BaseNetworkingConfig;
import br.com.thalesfrigo.devtecnonutrix.service.FeedService;
import br.com.thalesfrigo.devtecnonutrix.view.FeedView;
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
        final FeedView feedView = this.feedView;

        feedView.startProgress();
        Call<FeedListResponse> call = this.feedService.getFeeds();
        call.enqueue(new Callback<FeedListResponse>() {
            @Override
            public void onResponse(Call<FeedListResponse> call, Response<FeedListResponse> response) {
                feedView.updateFeed(response.body().getFeeds());
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
}