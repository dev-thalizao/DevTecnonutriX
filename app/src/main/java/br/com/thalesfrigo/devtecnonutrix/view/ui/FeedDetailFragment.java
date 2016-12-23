package br.com.thalesfrigo.devtecnonutrix.view.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import br.com.thalesfrigo.devtecnonutrix.R;
import br.com.thalesfrigo.devtecnonutrix.model.Feed;
import br.com.thalesfrigo.devtecnonutrix.model.User;
import br.com.thalesfrigo.devtecnonutrix.presenter.FeedDetailPresenter;
import br.com.thalesfrigo.devtecnonutrix.view.adapter.FeedDetailViewAdapter;
import br.com.thalesfrigo.devtecnonutrix.view.callback.UserProfileCallback;
import br.com.thalesfrigo.devtecnonutrix.view.contract.BaseActivityView;
import br.com.thalesfrigo.devtecnonutrix.view.contract.FeedDetailView;

public class FeedDetailFragment extends Fragment implements FeedDetailView {

    private static final String TAG = FeedDetailFragment.class.getSimpleName();
    private View mRootView;
    private BaseActivityView mBaseView;
    private Feed feed;
    private String mTitle;

    private FeedDetailPresenter feedDetailPresenter;
    private RecyclerView recyclerView;
    private FeedDetailViewAdapter recyclerViewAdapter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;


    public FeedDetailFragment() {
        // Required empty public constructor
    }

    public FeedDetailFragment(BaseActivityView mBaseView) {
        this.mBaseView = mBaseView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_feed_detail, container, false);

        feed = getArguments().getParcelable("feed_parcel");

        renderView();
        init();

        Log.d(TAG, feed.toString());

        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.feed_detail_fragment_title));
    }

    private void renderView(){
        recyclerView = (RecyclerView) mRootView.findViewById(R.id.feed_list);
        progressBar = (ProgressBar) mRootView.findViewById(R.id.feed_list_progress);
        swipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.feed_list_swipe);
    }


    private void init() {
        // Configure the presenter
        feedDetailPresenter = new FeedDetailPresenter();
        feedDetailPresenter.attachView(this);
        feedDetailPresenter.getFeed(feed);

        // Configure the recyclerView
        final LinearLayoutManager layoutManager = new LinearLayoutManager(mRootView.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(null);

        // Configure the adapter
        recyclerViewAdapter = new FeedDetailViewAdapter(mRootView.getContext(), feed, new UserProfileCallback(){
            @Override
            public void onClick(User user) {
                Log.i(TAG, "Profile user clicked: " + user);

                UserDetailFragment userDetailFragment = new UserDetailFragment(mBaseView);

                Bundle bundle = new Bundle();
                bundle.putParcelable("user_parcel", user);
                userDetailFragment.setArguments(bundle);

                mBaseView.changeFragment(userDetailFragment, "User Detail");
            }
        });
        recyclerViewAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(recyclerViewAdapter);

        // Init pull to refresh
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                feedDetailPresenter.getFeed(feed);
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
    public void updateFeed(Feed feed) {
        this.feed = feed;
        recyclerViewAdapter.setFeed(feed);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String message) {

    }


}
