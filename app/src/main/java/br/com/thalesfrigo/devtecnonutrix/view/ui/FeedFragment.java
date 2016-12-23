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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.thalesfrigo.devtecnonutrix.R;
import br.com.thalesfrigo.devtecnonutrix.model.Feed;
import br.com.thalesfrigo.devtecnonutrix.model.User;
import br.com.thalesfrigo.devtecnonutrix.presenter.FeedPresenter;
import br.com.thalesfrigo.devtecnonutrix.util.DateUtil;
import br.com.thalesfrigo.devtecnonutrix.util.LoadMode;
import br.com.thalesfrigo.devtecnonutrix.view.adapter.FeedViewAdapter;
import br.com.thalesfrigo.devtecnonutrix.view.callback.FeedListCallback;
import br.com.thalesfrigo.devtecnonutrix.view.callback.UserProfileCallback;
import br.com.thalesfrigo.devtecnonutrix.view.component.InfiniteScrollListener;
import br.com.thalesfrigo.devtecnonutrix.view.contract.BaseActivityView;
import br.com.thalesfrigo.devtecnonutrix.view.contract.FeedView;


public class FeedFragment extends Fragment implements FeedView {

    public static final String TAG = FeedFragment.class.getSimpleName();
    private View mRootView;
    private BaseActivityView mBaseView;
    private String mTitle;

    private FeedPresenter feedPresenter;
    private RecyclerView recyclerView;
    private FeedViewAdapter recyclerViewAdapter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView feedNotAvailable;

    public FeedFragment() {
        // Required empty public constructor
    }

    public FeedFragment(BaseActivityView mBaseView){
        this.mBaseView = mBaseView;;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(mRootView == null){
            mRootView = inflater.inflate(R.layout.fragment_feed, container, false);
            renderView();
            init();
        }
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.feed_fragment_title));
    }

    private void renderView(){
        recyclerView = (RecyclerView) mRootView.findViewById(R.id.feed_list);
        progressBar = (ProgressBar) mRootView.findViewById(R.id.feed_list_progress);
        swipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.feed_list_swipe);
        feedNotAvailable = (TextView) mRootView.findViewById(R.id.feed_not_available);
    }


    private void init() {
        // Configure the presenter
        feedPresenter = new FeedPresenter();
        feedPresenter.attachView(this);
        feedPresenter.getFeeds();

        // Configure the recyclerView
        final LinearLayoutManager layoutManager = new LinearLayoutManager(mRootView.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(null);

        // Configure the adapter
        recyclerViewAdapter = new FeedViewAdapter(mRootView.getContext(), new FeedListCallback() {
            @Override
            public void onClick(Feed feed) {
                Log.i(TAG, "Feed clicked: " + feed.getHash());
                FeedDetailFragment feedDetailFragment = new FeedDetailFragment(mBaseView);

                Bundle bundle = new Bundle();
                bundle.putParcelable("feed_parcel", feed);
                feedDetailFragment.setArguments(bundle);
                String title = getString(R.string.feed_detail_fragment_title, feed.getMealType().toString(), DateUtil.formatDate(feed.getMealDate(), "dd/MM/yyyy"));

                mBaseView.changeFragment(feedDetailFragment, FeedDetailFragment.TAG, title);
            }
        }, new UserProfileCallback() {
            @Override
            public void onClick(User user) {
                Log.i(TAG, "Profile user clicked: " + user);

                UserDetailFragment userDetailFragment = new UserDetailFragment(mBaseView);

                Bundle bundle = new Bundle();
                bundle.putParcelable("user_parcel", user);
                userDetailFragment.setArguments(bundle);

                mBaseView.changeFragment(userDetailFragment, UserDetailFragment.TAG, user.getName());
            }
        });

        recyclerViewAdapter.setFeeds(new ArrayList<Feed>());
        recyclerView.setAdapter(recyclerViewAdapter);

        // Init pull to refresh
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                feedPresenter.getFeeds();
            }
        });

        // Init infinite scroll
        recyclerView.addOnScrollListener(new InfiniteScrollListener(layoutManager) {
            @Override
            public void didReachTheEnd() {
                Log.d(TAG, "increment feed");
                feedPresenter.incrementFeeds(recyclerViewAdapter.getFeeds());
            }
        });
    }

    @Override
    public void startProgress() {
        progressBar.setVisibility(View.VISIBLE);
        feedNotAvailable.setVisibility(View.INVISIBLE);
    }

    @Override
    public void finishProress() {
        progressBar.setVisibility(View.INVISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void updateFeed(List<Feed> feeds, LoadMode loadMode) {
        recyclerViewAdapter.setFeeds(feeds);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void setEmptyFeed() {
        if(recyclerViewAdapter.getItemCount() == 0){
            feedNotAvailable.setVisibility(View.VISIBLE);
        } else {
            feedNotAvailable.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showErrorMessage(int messageResourceId) {
        mBaseView.showMessage(getView(), messageResourceId);
    }


}
