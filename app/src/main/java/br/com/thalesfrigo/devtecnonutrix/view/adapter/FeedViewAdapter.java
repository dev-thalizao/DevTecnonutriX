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

/**
 * Created by thalesfrigo on 12/21/16.
 */

public class FeedViewAdapter extends RecyclerView.Adapter<FeedViewAdapter.ViewHolder> {

    private Context context;
    private List<Feed> feeds;
    private final OnItemClickListener listener;

    public FeedViewAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.item_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Feed feed = feeds.get(position);

        holder.click(feed, listener);

        holder.profileNameTextView.setText(feed.getUser().getName());
        holder.profileGoalTextView.setText(feed.getUser().getGoal());

        // Download user imageurl
        Glide.with(context)
                .load(feed.getUser().getImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(true)
                .into(holder.profileImageView);

        holder.mealStatusTextView.setText(feed.getMealType() + " de " + DateUtil.formatDate(feed.getMealDate(), "dd/MM/yyyy"));
        holder.mealEnergyTextView.setText("5000 kcal");

        // Download feed imageUrl
        Glide.with(context)
                .load(feed.getImageUrl())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(true)
                .into(holder.mealImageView);
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

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView profileNameTextView;
        private ImageView profileImageView;
        private TextView profileGoalTextView;
        private ImageView mealImageView;
        private TextView mealStatusTextView;
        private TextView mealEnergyTextView;

        public ViewHolder(View view) {
            super(view);
            profileNameTextView = (TextView) view.findViewById(R.id.profile_name);
            profileImageView = (ImageView) view.findViewById(R.id.profile_image);
            profileGoalTextView = (TextView) view.findViewById(R.id.profile_goal);
            mealImageView = (ImageView) view.findViewById(R.id.meal_image);
            mealStatusTextView = (TextView) view.findViewById(R.id.meal_status);
            mealEnergyTextView = (TextView) view.findViewById(R.id.meal_energy);
        }

        public void click(final Feed feed, final OnItemClickListener listener) {
            mealImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(feed);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onClick(Feed feed);
    }
}