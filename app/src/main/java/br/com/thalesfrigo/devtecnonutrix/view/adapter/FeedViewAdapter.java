package br.com.thalesfrigo.devtecnonutrix.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import br.com.thalesfrigo.devtecnonutrix.R;
import br.com.thalesfrigo.devtecnonutrix.model.Feed;
import br.com.thalesfrigo.devtecnonutrix.util.DateUtil;
import br.com.thalesfrigo.devtecnonutrix.view.callback.FeedListCallback;
import br.com.thalesfrigo.devtecnonutrix.view.callback.UserProfileCallback;
import br.com.thalesfrigo.devtecnonutrix.view.component.CircleImageView;
import br.com.thalesfrigo.devtecnonutrix.view.holder.FeedViewHolder;

/**
 * Created by thalesfrigo on 12/21/16.
 */

public class FeedViewAdapter extends RecyclerView.Adapter<FeedViewHolder> {

    private Context context;
    private List<Feed> feeds;
    private final FeedListCallback feedListCallback;
    private final UserProfileCallback userProfileCallback;

    public FeedViewAdapter(Context context, FeedListCallback feedListCallback, UserProfileCallback userProfileCallback) {
        this.context = context;
        this.feedListCallback = feedListCallback;
        this.userProfileCallback = userProfileCallback;
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.item_feed, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {
        final Feed feed = feeds.get(position);

        holder.getProfileNameTextView().setText(feed.getUser().getName());
        holder.getProfileGoalTextView().setText(feed.getUser().getGoal());

        // Download user imageurl
        Glide.with(context)
                .load(feed.getUser().getImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(true)
                .into(holder.getProfileImageView());

        // Bind callback
        holder.profileClick(feed.getUser(), userProfileCallback);

        holder.getMealStatusTextView().setText(context.getString(R.string.meal_status, feed.getMealType(), DateUtil.formatDate(feed.getMealDate(), "dd/MM/yyyy")));
        holder.getMealEnergyTextView().setText(context.getString(R.string.energy_value, String.format("%.2f", feed.getEnergy())));

        // Download feed imageUrl
        Glide.with(context)
                .load(feed.getImageUrl())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(true)
                .into(holder.getMealImageView());

        // Bind callback
        holder.feedImageClick(feed, feedListCallback);
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    public List<Feed> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<Feed> feeds) {
        this.feeds = feeds;
    }


}