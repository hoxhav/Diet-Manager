/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen_383_g5.DietManager.model;

public class ExerciseOutput {

    private String name;
    private String calories;
    private String time;

    public ExerciseOutput(String name, String calories, String time) {
        this.name = name;
        this.calories = calories;
        this.time = time;
    }

    public ExerciseOutput() {
    }

    /**
     * @return the exerciseName
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param exerciseName the exerciseName to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the calories
     */
    public String getCalories() {
        return this.calories;
    }

    /**
     * @param calories the calories to set
     */
    public void setCalories(String calories) {
        this.calories = calories;
    }
    
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}