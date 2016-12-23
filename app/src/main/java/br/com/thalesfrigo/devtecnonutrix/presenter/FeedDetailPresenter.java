package br.com.thalesfrigo.devtecnonutrix.presenter;

import android.util.Log;

import br.com.thalesfrigo.devtecnonutrix.R;
import br.com.thalesfrigo.devtecnonutrix.model.Feed;
import br.com.thalesfrigo.devtecnonutrix.networking.BaseNetworkingConfig;
import br.com.thalesfrigo.devtecnonutrix.networking.FeedResponse;
import br.com.thalesfrigo.devtecnonutrix.service.FeedService;
import br.com.thalesfrigo.devtecnonutrix.view.contract.FeedDetailView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thalesfrigo on 12/22/16.
 */

public class FeedDetailPresenter implements BasePresenter<FeedDetailView> {

    public static final String TAG = "FEED-DETAIL-PRESENTER";

    private FeedDetailView feedDetailView;
    private FeedService feedService;

    public FeedDetailPresenter(){
        this.feedService = BaseNetworkingConfig.createService(FeedService.class);
    }

    @Override
    public void attachView(FeedDetailView view) {
        this.feedDetailView = view;
    }

    @Override
    public void detachView() {
        this.feedDetailView = null;
    }

    public void getFeed(Feed feed){
        this.feedDetailView.startProgress();

        final FeedDetailView feedDetailView = this.feedDetailView;

        Call<FeedResponse> call = this.feedService.getFeed(feed.getHash());
        call.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                FeedResponse feedResponse = response.body();

                feedDetailView.finishProress();

                if(feedResponse.isSuccess()){
                    feedDetailView.updateFeed(feedResponse.getFeed());
                    Log.d(TAG, response.toString());
                } else {
                    feedDetailView.showErrorMessage(R.string.generic_error);
                }
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
                feedDetailView.finishProress();
                feedDetailView.showErrorMessage(R.string.generic_error);
                Log.e(TAG, t.toString());
            }
        });
    }
}
