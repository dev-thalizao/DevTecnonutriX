package br.com.thalesfrigo.devtecnonutrix.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.thalesfrigo.devtecnonutrix.R;
import br.com.thalesfrigo.devtecnonutrix.model.Feed;
import br.com.thalesfrigo.devtecnonutrix.model.User;
import br.com.thalesfrigo.devtecnonutrix.view.callback.FeedListCallback;
import br.com.thalesfrigo.devtecnonutrix.view.callback.UserProfileCallback;
import br.com.thalesfrigo.devtecnonutrix.view.component.CircleImageView;

/**
 * Created by thalesfrigo on 12/22/16.
 */

public class FeedViewHolder extends RecyclerView.ViewHolder {

    private TextView profileNameTextView;
    private CircleImageView profileImageView;
    private TextView profileGoalTextView;
    private ImageView mealImageView;
    private LinearLayout footerView;
    private TextView mealStatusTextView;
    private TextView mealEnergyTextView;

    public FeedViewHolder(View view) {
        super(view);
        profileNameTextView = (TextView) view.findViewById(R.id.profile_name);
        profileImageView = (CircleImageView) view.findViewById(R.id.profile_image);
        profileGoalTextView = (TextView) view.findViewById(R.id.profile_goal);
        mealImageView = (ImageView) view.findViewById(R.id.meal_image);
        footerView = (LinearLayout) view.findViewById(R.id.footer_view);
        mealStatusTextView = (TextView) view.findViewById(R.id.meal_status);
        mealEnergyTextView = (TextView) view.findViewById(R.id.meal_energy);
    }

    public void feedImageClick(final Feed feed, final FeedListCallback callback) {
        mealImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callback != null){
                    callback.onClick(feed);
                }
            }
        });
    }

    public void profileClick(final User user, final UserProfileCallback callback) {
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callback != null){
                    callback.onClick(user);
                }
            }
        });
    }

    public void setFeedForDetail(){
        footerView.setVisibility(View.GONE);
    }

    public TextView getProfileNameTextView() {
        return profileNameTextView;
    }

    public CircleImageView getProfileImageView() {
        return profileImageView;
    }

    public TextView getProfileGoalTextView() {
        return profileGoalTextView;
    }

    public ImageView getMealImageView() {
        return mealImageView;
    }

    public TextView getMealStatusTextView() {
        return mealStatusTextView;
    }

    public TextView getMealEnergyTextView() {
        return mealEnergyTextView;
    }
}
