/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen_383_g5.DietManager.model;

/**
 */
public class ExerciseFactory {

    /**
     * Takes a row, and based on first char e for exercise and creates the
     * object
     *
     * @param e the row
     * @return
     */
    public static Exercise create(String e) {
        Exercise exercise = null;
        String[] params = e.split(",");
        if (params[0].equals("e")) {
            exercise = new Exercise(params[1], Double.parseDouble(params[2]));
        } 
        return exercise;
    }
}
