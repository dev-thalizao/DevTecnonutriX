package br.com.thalesfrigo.devtecnonutrix.view.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;

import java.util.List;

import br.com.thalesfrigo.devtecnonutrix.R;
import br.com.thalesfrigo.devtecnonutrix.model.Feed;
import br.com.thalesfrigo.devtecnonutrix.presenter.FeedPresenter;
import br.com.thalesfrigo.devtecnonutrix.presenter.adapter.FeedViewAdapter;
import br.com.thalesfrigo.devtecnonutrix.view.FeedView;

public class FeedActivity extends BaseActivity implements FeedView {

    public static final String TAG = "FEED-ACTIVITY";

    private FeedPresenter feedPresenter;
    private RecyclerView recyclerView;
    private FeedViewAdapter recyclerViewAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        renderView();
        init();
    }

    @Override
    public void renderView(){
        recyclerView = (RecyclerView) findViewById(R.id.feed_list);
        progressBar = (ProgressBar) findViewById(R.id.feed_list_progress);
    }

    @Override
    protected void init() {
        // Configure the presenter
        feedPresenter = new FeedPresenter();
        feedPresenter.attachView(this);
        feedPresenter.getFeeds();

        // Configure the recyclerView
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void startProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishProress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateFeed(List<Feed> feeds) {
        recyclerViewAdapter = new FeedViewAdapter(getApplicationContext(), feeds, new FeedViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(Feed feed) {
                Log.i(TAG, "Feed clicked: " + feed.getHash());
            }
        });
        recyclerViewAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void setEmptyFeed() {

    }

    @Override
    public void showErrorMessage(String message) {

    }
}
