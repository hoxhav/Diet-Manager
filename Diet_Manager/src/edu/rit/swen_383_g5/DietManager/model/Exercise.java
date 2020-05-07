/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen_383_g5.DietManager.model;

/**
 *
 * @author
 */
public class Exercise {
    private String exerciseName;
    private double calories;
    
    public Exercise(String exerciseName, double calories) {
        this.exerciseName = exerciseName;
        this.calories = calories;
    }

    /**
     * @return the exerciseName
     */
    public String getExerciseName() {
        return exerciseName;
    }

    /**
     * @param exerciseName the exerciseName to set
     */
    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    /**
     * @return the calories
     */
    public double getCalories() {
        return calories;
    }

    /**
     * @param calories the calories to set
     */
    public void setCalories(double calories) {
        this.calories = calories;
    }
    
    public String getAbbreviation() {
        return  "e," + this.getExerciseName() + "," + this.getCalories();
    }
    
}