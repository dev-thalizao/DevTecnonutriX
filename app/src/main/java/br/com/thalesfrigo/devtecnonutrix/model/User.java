package br.com.thalesfrigo.devtecnonutrix.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by thalesfrigo on 12/20/16.
 */

public class User implements Parcelable {


    //        "profile": {
//        "id": 3253969,
//        "name": "Taylane Nascimento",
//        "image": "https://tnapp.blob.core.windows.net/profiles/3253969.jpg",
//        "general_goal": "Perder peso",
//        "following": false,
//        "badge": null,
//        "locale": "pt",
//        "items_count": 4,
//        "followers_count": 5,
//        "followings_count": 1
//        },

    @SerializedName("id")
    @Expose
    private String id;

    @Expose
    private String name;

    @SerializedName("image")
    @Expose
    private String imageUrl;

    @SerializedName("general_goal")
    @Expose
    private String goal;

    private List<Feed> feeds;

    public User(String name, String imageUrl, String goal) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.goal = goal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public List<Feed> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<Feed> feeds) {
        this.feeds = feeds;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.imageUrl);
        dest.writeString(this.goal);
        dest.writeTypedList(this.feeds);
    }

    protected User(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.imageUrl = in.readString();
        this.goal = in.readString();
        this.feeds = in.createTypedArrayList(Feed.CREATOR);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
