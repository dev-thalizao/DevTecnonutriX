package br.com.thalesfrigo.devtecnonutrix.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by thalesfrigo on 12/20/16.
 */

public enum MealType {
    @SerializedName("0")
    @Expose
    BREAKFAST(0, "Café da manhã"),
    @SerializedName("1")
    @Expose
    MORNING_SNACK(1, "Lanche da manhã"),
    @SerializedName("2")
    @Expose
    LUNCH(2, "Almoço"),
    @SerializedName("3")
    @Expose
    AFTERNOON_SNACK(3, "Lanche da tarde"),
    @SerializedName("4")
    @Expose
    DINNER(4, "Jantar"),
    @SerializedName("5")
    @Expose
    SUPPER(5, "Ceia"),
    @SerializedName("6")
    @Expose
    PRE_WORKOUT(6, "Pré-treino"),
    @SerializedName("7")
    @Expose
    POST_WORKOUT(7, "Pós-treino");

    private int id;
    private String name;

    MealType(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static MealType getMealType(int id){
        for (MealType mt: MealType.values()) {
            if(mt.id == id)
                return mt;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
