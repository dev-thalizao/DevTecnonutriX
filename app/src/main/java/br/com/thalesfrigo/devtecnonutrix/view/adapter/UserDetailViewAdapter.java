package br.com.thalesfrigo.devtecnonutrix.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import br.com.thalesfrigo.devtecnonutrix.R;
import br.com.thalesfrigo.devtecnonutrix.model.Feed;
import br.com.thalesfrigo.devtecnonutrix.model.User;
import br.com.thalesfrigo.devtecnonutrix.view.callback.FeedListCallback;
import br.com.thalesfrigo.devtecnonutrix.view.holder.UserDetailFeedViewHolder;
import br.com.thalesfrigo.devtecnonutrix.view.holder.UserDetailViewHolder;

/**
 * Created by thalesfrigo on 12/22/16.
 */

public class UserDetailViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    static final int HEADER_TYPE = 0;
    static final int FEED_TYPE = 1;

    private Context context;
    private User user;
    private List<Feed> feeds;
    private final FeedListCallback feedListCallback;

    public UserDetailViewAdapter(Context context, FeedListCallback feedListCallback) {
        this.context = context;
        this.user = user;
        this.feedListCallback = feedListCallback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        if(viewType == HEADER_TYPE){
            final View view = layoutInflater.inflate(R.layout.item_user_detail, parent, false);
            return new UserDetailViewHolder(view);
        } else {
            final View view = layoutInflater.inflate(R.layout.item_feed_user_detail, parent, false);
            return new UserDetailFeedViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if(viewType == HEADER_TYPE){
            UserDetailViewHolder userDetailViewHolder = (UserDetailViewHolder)holder;
            userDetailViewHolder.getProfileNameTextView().setText(user.getName());
            userDetailViewHolder.getProfileGoalTextView().setText(user.getGoal());

            // Download user imageurl
            Glide.with(context)
                    .load(user.getImageUrl())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .skipMemoryCache(true)
                    .into(userDetailViewHolder.getProfileImageView());

        } else {
            Feed feed = feeds.get(position - 1);
            UserDetailFeedViewHolder userDetailFeedViewHolder = (UserDetailFeedViewHolder)holder;

            // Download feed imageurl
            Glide.with(context)
                    .load(feed.getImageUrl())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .skipMemoryCache(true)
                    .into(userDetailFeedViewHolder.getFeedItemImageView());

            userDetailFeedViewHolder.feedImageClick(feed, feedListCallback);
        }
    }

    public boolean isHeader(int position){
        return position == 0;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        if(isHeader(position)){
            return HEADER_TYPE;
        } else {
            return FEED_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        if(user != null && feeds != null){
            return feeds.size() + 1;
        } else if(user != null){
            return 1;
        } else {
            return 0;
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Feed> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<Feed> feeds) {
        this.feeds = feeds;
    }
}
