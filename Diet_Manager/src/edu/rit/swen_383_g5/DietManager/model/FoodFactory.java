package edu.rit.swen_383_g5.DietManager.model;

/**
 * @version 9.4.2019
 */
public class FoodFactory {
    
    /**
     * Takes a row, and based on first char b for basicfood and r for recipe creates the object
     * @param e the row
     * @return 
     */
    public static Food create(String e) {
        String[] params = e.split(",");
        if(params[0].equals("b")){
            return new BasicFood(params[1],Double.parseDouble(params[2]),Double.parseDouble(params[3]),Double.parseDouble(params[4]),Double.parseDouble(params[5]));
        }else if(params[0].equals("r")){
            Recipe hold = new Recipe(params[1]);
            return hold;
        }else{
            return null;
        }
    }
}
