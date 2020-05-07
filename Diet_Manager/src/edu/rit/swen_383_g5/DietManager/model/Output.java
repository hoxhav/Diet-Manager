package edu.rit.swen_383_g5.DietManager.model;

/**
 * The class which defines tuples and attributes for the TableView JavaFX output in the SecondScreenController
 * @version 9.4.2019
 */
public class Output {
    private String name = null;
    private String amount = null;
    private String calories = null;
    private String fat = null;
    private String carb = null;
    private String protein = null;

    public Output() {
    }
        
    public Output(String name, String amount, String calories, String fat, String carb, String protein) {
        this.name = name;
        this.amount = amount;
        this.calories = calories;
        this.fat = fat;
        this.carb = carb;
        this.protein = protein;
    }    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getCarb() {
        return carb;
    }

    public void setCarb(String carb) {
        this.carb = carb;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }
    
    
}
