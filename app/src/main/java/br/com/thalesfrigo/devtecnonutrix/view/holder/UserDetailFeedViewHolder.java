package br.com.thalesfrigo.devtecnonutrix.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import br.com.thalesfrigo.devtecnonutrix.R;
import br.com.thalesfrigo.devtecnonutrix.model.Feed;
import br.com.thalesfrigo.devtecnonutrix.view.callback.FeedListCallback;

/**
 * Created by thalesfrigo on 12/23/16.
 */

public class UserDetailFeedViewHolder extends RecyclerView.ViewHolder {

    private ImageView feedItemImageView;

    public UserDetailFeedViewHolder(View view) {
        super(view);
        feedItemImageView = (ImageView) view.findViewById(R.id.item_image_feed);
    }

    public ImageView getFeedItemImageView() {
        return feedItemImageView;
    }

    public void feedImageClick(final Feed feed, final FeedListCallback callback) {
        feedItemImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callback != null){
                    callback.onClick(feed);
                }
            }
        });
    }
}
