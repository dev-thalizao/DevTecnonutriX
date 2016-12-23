package br.com.thalesfrigo.devtecnonutrix.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.DrawableMarginSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import br.com.thalesfrigo.devtecnonutrix.R;
import br.com.thalesfrigo.devtecnonutrix.model.Feed;
import br.com.thalesfrigo.devtecnonutrix.model.User;
import br.com.thalesfrigo.devtecnonutrix.presenter.UserDetailPresenter;
import br.com.thalesfrigo.devtecnonutrix.util.LoadMode;
import br.com.thalesfrigo.devtecnonutrix.view.adapter.UserDetailViewAdapter;
import br.com.thalesfrigo.devtecnonutrix.view.callback.FeedListCallback;
import br.com.thalesfrigo.devtecnonutrix.view.component.InfiniteScrollListener;
import br.com.thalesfrigo.devtecnonutrix.view.component.MarginDecoration;
import br.com.thalesfrigo.devtecnonutrix.view.contract.BaseActivityView;
import br.com.thalesfrigo.devtecnonutrix.view.contract.UserDetailView;

/**
 * Created by thalesfrigo on 12/22/16.
 */

public class UserDetailFragment extends Fragment implements UserDetailView {

    private static final String TAG = UserDetailFragment.class.getSimpleName();
    private View mRootView;
    private BaseActivityView mBaseView;
    private User user;

    private String mTitle;

    private UserDetailPresenter userDetailPresenter;
    private RecyclerView recyclerView;
    private UserDetailViewAdapter recyclerViewAdapter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private InfiniteScrollListener infiniteScrollListener;

    public UserDetailFragment() {
    }

    public UserDetailFragment(BaseActivityView mBaseView) {
        this.mBaseView = mBaseView;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRootView = inflater.inflate(R.layout.fragment_user, container, false);
        user = getArguments().getParcelable("user_parcel");
        renderView();
        init();

        return mRootView;
    }

    private void renderView(){
        recyclerView = (RecyclerView) mRootView.findViewById(R.id.user_detail_list);
        progressBar = (ProgressBar) mRootView.findViewById(R.id.user_detail_list_progress);
        swipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.user_detail_list_swipe);
    }

    private void init(){
        // Configure the presenter
        userDetailPresenter = new UserDetailPresenter();
        userDetailPresenter.attachView(this);
        userDetailPresenter.getFeedWithUser(user, LoadMode.REFRESH);

        // Configure the recyclerView
        final GridLayoutManager layoutManager = new GridLayoutManager(mRootView.getContext(), 3);

        // span
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return recyclerViewAdapter.isHeader(position) ? layoutManager.getSpanCount() : 1;
            }
        });

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new MarginDecoration(mRootView.getContext()));
        recyclerView.setItemAnimator(null);

        // Configure the adapter
        recyclerViewAdapter = new UserDetailViewAdapter(mRootView.getContext(), new FeedListCallback(){
            @Override
            public void onClick(Feed feed) {
                Log.i(TAG, "Feed clicked: " + feed.getHash());
                FeedDetailFragment feedDetailFragment = new FeedDetailFragment(mBaseView);

                Bundle bundle = new Bundle();
                bundle.putParcelable("feed_parcel", feed);
                feedDetailFragment.setArguments(bundle);

                mBaseView.changeFragment(feedDetailFragment, "Detail");
            }
        });
        recyclerViewAdapter.setHasStableIds(true);
        recyclerView.setAdapter(recyclerViewAdapter);

        // Init pull to refresh
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                userDetailPresenter.getFeedWithUser(user, LoadMode.REFRESH);
            }
        });

        // Init infinite scroll
        recyclerView.addOnScrollListener(new InfiniteScrollListener(layoutManager) {
            @Override
            public void didReachTheEnd() {
                Log.d(TAG, "increment feed");
                userDetailPresenter.getFeedWithUser(user, LoadMode.SCROLL);
            }
        });
    }

    @Override
    public void startProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishProress() {
        progressBar.setVisibility(View.INVISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void updateFeed(User user, List<Feed> feeds, LoadMode loadMode) {
        recyclerViewAdapter.setUser(user);

        if(loadMode == LoadMode.REFRESH){
            recyclerViewAdapter.setFeeds(feeds);
        } else {
            List<Feed> auxFeedList = recyclerViewAdapter.getFeeds();
            auxFeedList.addAll(feeds);
            recyclerViewAdapter.setFeeds(auxFeedList);
        }
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void setEmptyFeed() {

    }

    @Override
    public void showErrorMessage(String message) {

    }


    private void addInfiniteScrollListener(){
        recyclerView.addOnScrollListener(infiniteScrollListener);
    }

    private void removeIniniteScrollListener(){
        recyclerView.clearOnScrollListeners();
    }
}
