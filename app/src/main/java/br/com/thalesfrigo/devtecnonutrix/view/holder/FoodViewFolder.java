package br.com.thalesfrigo.devtecnonutrix.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.thalesfrigo.devtecnonutrix.R;

/**
 * Created by thalesfrigo on 12/22/16.
 */

public class FoodViewFolder extends RecyclerView.ViewHolder {

    private TextView foodName;
    private TextView foodAmount;
    private TextView energyValue;
    private TextView carbValue;
    private TextView protValue;
    private TextView fatValue;

    public FoodViewFolder(View view) {
        super(view);
        foodName = (TextView) view.findViewById(R.id.food_name);
        foodAmount = (TextView) view.findViewById(R.id.food_amount);
        energyValue = (TextView) view.findViewById(R.id.energy_value);
        carbValue = (TextView) view.findViewById(R.id.carb_value);
        protValue = (TextView) view.findViewById(R.id.prot_value);
        fatValue = (TextView) view.findViewById(R.id.fat_value);
    }

    public void setFoodForDescription(String text){
        foodAmount.setVisibility(View.VISIBLE);
        foodAmount.setText(text);
    }

    public void setFoodForTotal(String text){
        foodName.setText(text);
        foodName.setTextSize(20);
        foodAmount.setVisibility(View.INVISIBLE);
    }

    public TextView getFoodName() {
        return foodName;
    }

    public TextView getFoodAmount() {
        return foodAmount;
    }

    public TextView getEnergyValue() {
        return energyValue;
    }

    public TextView getCarbValue() {
        return carbValue;
    }

    public TextView getProtValue() {
        return protValue;
    }

    public TextView getFatValue() {
        return fatValue;
    }
}
