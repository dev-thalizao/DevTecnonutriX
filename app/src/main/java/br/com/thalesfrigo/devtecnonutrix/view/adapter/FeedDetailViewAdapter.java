package br.com.thalesfrigo.devtecnonutrix.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import br.com.thalesfrigo.devtecnonutrix.R;
import br.com.thalesfrigo.devtecnonutrix.model.Feed;
import br.com.thalesfrigo.devtecnonutrix.model.Food;
import br.com.thalesfrigo.devtecnonutrix.util.DateUtil;
import br.com.thalesfrigo.devtecnonutrix.view.callback.UserProfileCallback;
import br.com.thalesfrigo.devtecnonutrix.view.holder.FeedViewHolder;
import br.com.thalesfrigo.devtecnonutrix.view.holder.FoodViewFolder;

/**
 * Created by thalesfrigo on 12/22/16.
 */

public class FeedDetailViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    static final int FEED_TYPE = 0;
    static final int FOOD_TYPE = 1;

    private Context context;
    private Feed feed;
    private final UserProfileCallback userProfileCallback;

    public FeedDetailViewAdapter(Context context, Feed feed, UserProfileCallback userProfileCallback) {
        this.context = context;
        this.feed = feed;
        this.userProfileCallback = userProfileCallback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        if(viewType == FEED_TYPE){
            final View view = layoutInflater.inflate(R.layout.item_feed, parent, false);
            return new FeedViewHolder(view);
        } else {
            final View view = layoutInflater.inflate(R.layout.item_food, parent, false);
            return new FoodViewFolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        if(viewType == FEED_TYPE){
            FeedViewHolder feedViewHolder = (FeedViewHolder)holder;
            feedViewHolder.getProfileNameTextView().setText(feed.getUser().getName());
            feedViewHolder.getProfileGoalTextView().setText(feed.getUser().getGoal());

            // Download user imageurl
            Glide.with(context)
                    .load(feed.getUser().getImageUrl())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .skipMemoryCache(false)
                    .into(feedViewHolder.getProfileImageView());

            // Bind callback
            feedViewHolder.profileClick(feed.getUser(), userProfileCallback);

            // Download feed imageUrl
            Glide.with(context)
                    .load(feed.getImageUrl())
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .skipMemoryCache(false)
                    .into(feedViewHolder.getMealImageView());

            feedViewHolder.setFeedForDetail();
        } else {
            FoodViewFolder foodViewFolder = (FoodViewFolder)holder;

            if(position != getItemCount() - 1){
                Food food = feed.getFoods().get(position - 1);
                foodViewFolder.getFoodName().setText(food.getDescription());
                foodViewFolder.setFoodForDescription(context.getString(R.string.food_amount, String.format("%f", food.getAmount()), food.getMeasure(), String.format("%.2f", food.getWeight())));
                foodViewFolder.getEnergyValue().setText(context.getString(R.string.energy_value, String.format("%.2f", food.getEnergy())));
                foodViewFolder.getCarbValue().setText(context.getString(R.string.carb_value, String.format("%.2f", food.getCarbohydrate())));
                foodViewFolder.getProtValue().setText(context.getString(R.string.prot_value, String.format("%.2f", food.getProtein())));
                foodViewFolder.getFatValue().setText(context.getString(R.string.fat_value, String.format("%.2f", food.getFat())));
            } else {
                foodViewFolder.setFoodForTotal(context.getString(R.string.total_nutrients));
                foodViewFolder.getEnergyValue().setText(context.getString(R.string.energy_value, String.format("%.2f", feed.getEnergy())));
                foodViewFolder.getCarbValue().setText(context.getString(R.string.carb_value, String.format("%.2f", feed.getCarbohydrate())));
                foodViewFolder.getProtValue().setText(context.getString(R.string.prot_value, String.format("%.2f", feed.getProtein())));
                foodViewFolder.getFatValue().setText(context.getString(R.string.fat_value, String.format("%.2f", feed.getFat())));
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return FEED_TYPE;
        } else {
            return FOOD_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        if(feed.getFoods() != null)
            return feed.getFoods().size() + 2;

        return 2;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }
}
