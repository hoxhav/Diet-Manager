package edu.rit.swen_383_g5.DietManager.model;

/**
 * Food interface that will be implemented by Recipe and BasicFood classes.
 * This class is also a Component in the Composite Pattern and contains
 * methods which will be overridden in the concrete classes that will implement
 * this interface.
 * 
 * @version 9.4.2019
 */
public interface Food {
    
    String getName();
    double getCalories();
    double getFat();
    double getCarb();
    double getProtein();
    String getAbbreviation();
    void addFood(Food f, Double d);
    
}
