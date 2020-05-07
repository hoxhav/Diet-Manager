/**
 * BasicFood class which implements the Food Interface. 
 * Has Two constructors, one for Name and second one for everything
 * Has getters for every parameter. 
 * Uses the addFood method to throw new UnsupportedOperationException. 
 */ 

package edu.rit.swen_383_g5.DietManager.model;

/**
 * The leaf for the composite which holds BasicFood data
 * @version 9.4.2019
 */
public class BasicFood implements Food{
    private String name;
    private double calories,fat,carb,protein;

    public BasicFood(String name, double calories, double fat, double carb, double protein) {
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.carb = carb;
        this.protein = protein;
    }

    public BasicFood(String name) {
        this.name = name;
    }
   
    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getCalories() {
        return calories;
    }

    @Override
    public double getFat() {
        return fat;
    }

    @Override
    public double getCarb() {
        return carb;
    }

    @Override
    public double getProtein() {
        return protein;
    }

    @Override
    public String getAbbreviation() {
        return "b," + this.getName() + "," + this.getCalories() + "," + this.getFat() + "," + this.getCarb() + "," + this.getProtein();
    }

    @Override
    public void addFood(Food f, Double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
