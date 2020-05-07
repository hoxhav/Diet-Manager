/**
 * Daily Information Class.
 * Constructor which initializes every attribute.
 * Second Constructor used for DesiredCalories.
 * Getters for every attribute.
 * Setters for date, day,year,month,food map, weight, calories and desired Calories.
 * Add food method which puts the food and amount to the map.
 */
package edu.rit.swen_383_g5.DietManager.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 9.4.2019
 */
public class DailyInformation {

    private String year, month, day;
    private String date;
    private Map<Food, Double> foodMap;

    private Map<Exercise, Double> exerciseMap;

    private double weight;
    private double calories;
    private double desiredCalories;

    public DailyInformation() {
        year = null;
        month = null;
        day = null;
        foodMap = new HashMap<Food, Double>();

        exerciseMap = new HashMap<Exercise, Double>();

        weight = 0;
        calories = 0;
        desiredCalories = 0;
    }

    public DailyInformation(double desired) {
        this.desiredCalories = desired;
    }

    public DailyInformation(String date) {
        this.date = date;
    }

    public String getDateAbbreviation() {
        return this.getYear() + "," + this.getMonth() + "," + this.getDay();
    }

    public String getDate() {
        return this.getYear() + "-" + this.getMonth() + "-" + this.getDay();
    }

    public void setDate(String year, String month, String day) {
        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Map<Food, Double> getFoodMap() {
        return foodMap;
    }

    public void setFoodMap(HashMap<Food, Double> foodMap) {
        this.foodMap = foodMap;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        System.out.println("Weight: " + String.valueOf(weight));
        this.weight = weight;
    }

    public double getCalories() {
        double hold = 0;
        for (Map.Entry<Food, Double> entry : foodMap.entrySet()) {
            hold += entry.getKey().getCalories() * entry.getValue();
        }
        for (Map.Entry<Exercise, Double> entry : exerciseMap.entrySet()) {
            hold -= entry.getKey().getCalories() * (getWeight()/100.00) * (entry.getValue()/60);
        }
        return hold;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getDesiredCalories() {
        return desiredCalories;
    }

    public void setDesiredCalories(double desired) {
        System.out.println("Calories: " + String.valueOf(desired));
        this.desiredCalories = desired;
    }

    public void addFood(Food f, double amount) {
        if (foodMap.get(f) != null) {
            foodMap.put(f, foodMap.get(f) + amount);
        } else {
            foodMap.put(f, amount);
        }
    }

    public void addExercise(Exercise exercise, double minutes) {
        System.out.println("EXERCISE OBJECT: " + exercise.getExerciseName() + " MINUTES: " + minutes);
        if (exerciseMap.get(exercise) != null) {
            exerciseMap.put(exercise, exerciseMap.get(exercise) + minutes);
        } else {
            exerciseMap.put(exercise, minutes);
        }
        System.out.println("DAILY INFO: ");
        for (Exercise e : exerciseMap.keySet()) {
            System.out.println("NAME: " + e.getExerciseName());
            System.out.println("CALORIES: " + e.getCalories());
            System.out.println("MIN: " + exerciseMap.get(e).toString());
        }
    }

    public void clear() {
        foodMap.clear();
        exerciseMap.clear();
    }

    public Double removeFood(Food f, double amount) {
        if (foodMap.get(f) != null) {
            if (amount == foodMap.get(f) || foodMap.get(f) - amount < 0) {
                return foodMap.remove(f);
            } else if (foodMap.get(f) - amount > 0) {
                foodMap.put(f, foodMap.get(f) - amount);
                return foodMap.get(f) - amount;
            } else {
                return null;
            }
        }
        return null;
    }

    public Double removeExercise(Exercise e, double duration) {
        if (exerciseMap.get(e) != null) {
            if (duration == exerciseMap.get(e) || exerciseMap.get(e) - duration < 0) {
                return exerciseMap.remove(e);
            } else if (exerciseMap.get(e) - duration > 0) {
                exerciseMap.put(e, exerciseMap.get(e) - duration);
                return exerciseMap.get(e);
            } else {
                return null;
            }
        }
        return null;
    }

    public Map<Exercise, Double> getExerciseMap() {
        return this.exerciseMap;
    }

    public void setExerciseMap(HashMap<Exercise, Double> exerciseMap) {
        this.exerciseMap = exerciseMap;
    }
}
