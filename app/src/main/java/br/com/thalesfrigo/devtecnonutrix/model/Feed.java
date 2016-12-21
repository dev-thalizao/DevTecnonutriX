package br.com.thalesfrigo.devtecnonutrix.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by thalesfrigo on 12/20/16.
 */

//{
//        "feedHash": "3253969-2016-12-21-2",
//        "id": 21037056,
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
//        "meal": 2,
//        "date": "2016-12-21",
//        "liked": false,
//        "likes_count": 1,
//        "comments_count": 1,
//        "image": "https://tnapp.blob.core.windows.net/meals/3253969-2016-12-21-2.jpg",
//        "comment": null,
//        "place": null,
//        "address": null,
//        "energy": 468.303,
//        "carbohydrate": 56.623,
//        "fat": 16.2925,
//        "protein": 23.8915,
//        "fat_trans": 0.16825,
//        "fat_sat": 6.9705,
//        "fiber": 5.035,
//        "sugar": 4.986,
//        "sodium": 536.551,
//        "calcium": 82.704,
//        "iron": 2.3285,
//        "moderation": 0,
//        "badge": null,
//        "locale": "pt"
//        }

public class Feed {

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

    private List<Food> foods;

    @Expose
    private Food totalNutrients;

    @SerializedName("profile")
    @Expose
    private User user;

    public Feed(String hash, String imageUrl, MealType mealType, Date mealDate, Food totalNutrients, User user) {
        this.hash = hash;
        this.imageUrl = imageUrl;
        this.mealType = mealType;
        this.mealDate = mealDate;
        this.totalNutrients = totalNutrients;
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

    public Food getTotalNutrients() {
        return totalNutrients;
    }

    public void setTotalNutrients(Food totalNutrients) {
        this.totalNutrients = totalNutrients;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
