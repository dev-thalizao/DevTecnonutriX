package br.com.thalesfrigo.devtecnonutrix.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.thalesfrigo.devtecnonutrix.R;
import br.com.thalesfrigo.devtecnonutrix.view.component.CircleImageView;

/**
 * Created by thalesfrigo on 12/22/16.
 */

public class UserDetailViewHolder extends RecyclerView.ViewHolder {

    private TextView profileNameTextView;
    private CircleImageView profileImageView;
    private TextView profileGoalTextView;

    public UserDetailViewHolder(View view) {
        super(view);
        profileNameTextView = (TextView) view.findViewById(R.id.profile_name);
        profileImageView = (CircleImageView) view.findViewById(R.id.profile_image);
        profileGoalTextView = (TextView) view.findViewById(R.id.profile_goal);
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
}
