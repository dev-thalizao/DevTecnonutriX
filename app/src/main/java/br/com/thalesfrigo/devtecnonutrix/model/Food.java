package br.com.thalesfrigo.devtecnonutrix.model;

import com.google.gson.annotations.Expose;

/**
 * Created by thalesfrigo on 12/20/16.
 */

public class Food {
//    "energy": 468.303,
    //        "carbohydrate": 56.623,
//        "fat": 16.2925,
//        "protein": 23.8915,

    private String description;
    private String measure;
    private int amount;
    private float weight;
    @Expose
    private float energy;
    @Expose
    private float carbohydrate;
    @Expose
    private float fat;
    @Expose
    private float protein;

    public Food(String description, String measure, int amount, float weight, float energy, float carbohydrate, float fat, float protein) {
        this.description = description;
        this.measure = measure;
        this.amount = amount;
        this.weight = weight;
        this.energy = energy;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
        this.protein = protein;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
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
}
