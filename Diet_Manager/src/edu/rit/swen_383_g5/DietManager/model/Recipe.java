package edu.rit.swen_383_g5.DietManager.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The leaf in the composite which holds the Recipe data
 * @version 9.4.2019
 */
public class Recipe implements Food {

    private Map<Food, Double> foodList;
    private String name;

    public Recipe(String name) {
        this.name = name;
        this.foodList = new HashMap<>();
    }

    @Override
    /**
     * Returns the name of the recipe
     */
    public String getName() {
        return name;
    }

    @Override
    /**
     * Returns the sum of calories of foods
     */
    public double getCalories() {
        double calories = 0;
        for (Food food : foodList.keySet()) {
            calories += food.getCalories();
        }
        return calories;
    }

    @Override
    /**
     * Returns the sum of fats of foods
     */
    public double getFat() {
        double fat = 0;
        for (Food food : foodList.keySet()) {
            fat += food.getFat();
        }
        return fat;
    }

    @Override
    /**
     * Returns the sum of carbs of foods
     */
    public double getCarb() {
        double carb = 0;
        for (Food food : foodList.keySet()) {
            carb += food.getCarb();
        }
        return carb;
    }

    @Override
    /**
     * Returns the sum of protein of foods
     */
    public double getProtein() {
        double protein = 0;
        for (Food food : foodList.keySet()) {
            protein += food.getProtein();
        }
        return protein;
    }

    @Override
    /**
     * Returns the abbreviation in comma seperated value of the food entries
     */
    public String getAbbreviation() {
        String abbr = "";
        for(HashMap.Entry<Food,Double> entry : foodList.entrySet()){
            abbr += "," + entry.getKey().getName() + "," + entry.getValue();
        }
        return "r," + this.getName() + abbr;
    }

    @Override
    /**
     * Adds food to the list
     */
    public void addFood(Food f, Double d) {
        foodList.put(f, d);
    }

}
