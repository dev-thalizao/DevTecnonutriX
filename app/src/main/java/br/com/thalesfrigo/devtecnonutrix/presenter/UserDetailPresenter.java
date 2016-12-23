package br.com.thalesfrigo.devtecnonutrix.presenter;

import android.util.Log;

import java.util.ArrayList;

import br.com.thalesfrigo.devtecnonutrix.R;
import br.com.thalesfrigo.devtecnonutrix.model.Feed;
import br.com.thalesfrigo.devtecnonutrix.model.User;
import br.com.thalesfrigo.devtecnonutrix.networking.BaseNetworkingConfig;
import br.com.thalesfrigo.devtecnonutrix.networking.UserDetailResponse;
import br.com.thalesfrigo.devtecnonutrix.service.UserService;
import br.com.thalesfrigo.devtecnonutrix.util.LoadMode;
import br.com.thalesfrigo.devtecnonutrix.view.contract.UserDetailView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thalesfrigo on 12/22/16.
 */

public class UserDetailPresenter implements BasePresenter<UserDetailView> {

    public static final String TAG = "USER-DETAIL-PRESENTER";

    private UserDetailView userDetailView;
    private UserService userService;
    private int pageNumber;
    private String timestamp;
    private boolean requestIsFinished;

    public UserDetailPresenter(){
        this.userService = BaseNetworkingConfig.createService(UserService.class);
        this.pageNumber = -1;
        this.timestamp = null;
        this.requestIsFinished = true;
    }

    @Override
    public void attachView(UserDetailView view) {
        this.userDetailView = view;
    }

    @Override
    public void detachView() {
        this.userDetailView = null;
    }

    public void getFeedWithUser(User user, final LoadMode loadMode){

        if(!this.requestIsFinished){
            return;
        }

        updatePageNumber(loadMode);

        final UserDetailView userDetailView = this.userDetailView;

        userDetailView.startProgress();

        Call<UserDetailResponse> call = this.userService.getFeed(user.getId(), pageNumber, timestamp);
        call.enqueue(new Callback<UserDetailResponse>() {
            @Override
            public void onResponse(Call<UserDetailResponse> call, Response<UserDetailResponse> response) {
                UserDetailResponse userDetailResponse = response.body();

                if(userDetailResponse.isSuccess()){
                    //Update timestamp
                    timestamp = String.valueOf(userDetailResponse.getTimestamp());

                    userDetailView.updateFeed(userDetailResponse.getUser(), userDetailResponse.getFeeds(), loadMode);
                    userDetailView.finishProress();
                    Log.d(TAG, response.toString());
                } else {
                    userDetailView.updateFeed(userDetailResponse.getUser(), new ArrayList<Feed>(), loadMode);
                    userDetailView.showErrorMessage(R.string.generic_error);
                }
            }

            @Override
            public void onFailure(Call<UserDetailResponse> call, Throwable t) {
                userDetailView.finishProress();
                userDetailView.showErrorMessage(R.string.generic_error);
                Log.e(TAG, t.toString());
            }
        });
    }

    public void updatePageNumber(LoadMode loadMode){
        if(loadMode == LoadMode.REFRESH){
            pageNumber = 0;
        } else {
            pageNumber++;
        }
    }
}
