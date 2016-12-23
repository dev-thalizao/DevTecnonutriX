package br.com.thalesfrigo.devtecnonutrix.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by thalesfrigo on 12/20/16.
 */
public class Feed implements Parcelable {

    @SerializedName("feedHash")
    @Expose
    private String hash;

    @SerializedName("image")
    @Expose
    private String imageUrl;

    @SerializedName("meal")
    @Expose
    private MealType mealType;

    @SerializedName("date")
    @Expose
    private Date mealDate;

    @SerializedName("foods")
    @Expose
    private List<Food> foods;

    @Expose
    private float energy;

    @Expose
    private float carbohydrate;

    @Expose
    private float fat;

    @Expose
    private float protein;

    @SerializedName("profile")
    @Expose
    private User user;

    public Feed(String hash, String imageUrl, MealType mealType, Date mealDate, float energy, User user) {
        this.hash = hash;
        this.imageUrl = imageUrl;
        this.mealType = mealType;
        this.mealDate = mealDate;
        this.energy = energy;
        this.user = user;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public Date getMealDate() {
        return mealDate;
    }

    public void setMealDate(Date mealDate) {
        this.mealDate = mealDate;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public float getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(float carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return hash + ": " + user.getName();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.hash);
        dest.writeString(this.imageUrl);
        dest.writeInt(this.mealType == null ? -1 : this.mealType.ordinal());
        dest.writeLong(this.mealDate != null ? this.mealDate.getTime() : -1);
        dest.writeTypedList(this.foods);
        dest.writeFloat(this.energy);
        dest.writeFloat(this.carbohydrate);
        dest.writeFloat(this.fat);
        dest.writeFloat(this.protein);
        dest.writeParcelable(this.user, flags);
    }

    protected Feed(Parcel in) {
        this.hash = in.readString();
        this.imageUrl = in.readString();
        int tmpMealType = in.readInt();
        this.mealType = tmpMealType == -1 ? null : MealType.values()[tmpMealType];
        long tmpMealDate = in.readLong();
        this.mealDate = tmpMealDate == -1 ? null : new Date(tmpMealDate);
        this.foods = in.createTypedArrayList(Food.CREATOR);
        this.energy = in.readFloat();
        this.carbohydrate = in.readFloat();
        this.fat = in.readFloat();
        this.protein = in.readFloat();
        this.user = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<Feed> CREATOR = new Creator<Feed>() {
        @Override
        public Feed createFromParcel(Parcel source) {
            return new Feed(source);
        }

        @Override
        public Feed[] newArray(int size) {
            return new Feed[size];
        }
    };
}
