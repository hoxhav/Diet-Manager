package edu.rit.swen_383_g5.DietManager.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 9.4.2019
 */
public class Loader {

    private ArrayList<Food> basics = new ArrayList<Food>();
    private ArrayList<Food> recipes = new ArrayList<Food>();
    private ArrayList<Exercise> exercises = new ArrayList<Exercise>();

    public boolean loadFoods() {
        basics.clear();
        recipes.clear();
        try {
            Reader reader = new Reader();
            ArrayList<String> data = reader.readAll();
            for (int i = 0; i < data.size(); i++) {
                String[] params = data.get(i).split(",");
                if (params[0].equals("b") && !data.get(i).equals("")) {
                    Food hold = FoodFactory.create(data.get(i));
                    basics.add(hold);
                    data.remove(i);
                    i--;
                }
            }

            for (int i = 0; i < data.size(); i++) {
                Food hold = FoodFactory.create(data.get(i));
                String[] params = data.get(i).split(",");
                for (int j = 2; j < params.length; j++) {
                    if (j % 2 == 0 && !data.get(i).equals("")) {
                        for (Food basic : basics) {
                            if (basic != null && basic.getName().equals(params[j])) {
                                hold.addFood(basic, Double.parseDouble(params[j + 1]));
                            }
                        }
                        for (Food recipe : recipes) {
                            if (recipe != null && recipe.getName().equals(params[j])) {
                                hold.addFood(recipe, Double.parseDouble(params[j + 1]));
                            }
                        }
                    }
                }
                recipes.add(hold);
            }

            System.out.println("HERE");

            if (!basics.isEmpty() && !recipes.isEmpty()) {
                return true;
            }
            return false;
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't load");
            return false;
        } catch (IOException e) {
            System.out.println("Couldn't load");
            return false;
        }
    }

    public ArrayList<Food> getBasics() {
        return basics;
    }

    public ArrayList<Food> getRecipes() {
        return recipes;
    }

    public void loadExercises() {
        exercises.clear();
        try {
            Reader reader = new Reader();
            ArrayList<String> data = reader.readExercise();
            for (int i = 0; i < data.size(); i++) {
                String[] params = data.get(i).split(",");
                if (!data.get(i).equals("")) {
                    Exercise hold = ExerciseFactory.create(data.get(i));
                    exercises.add(hold);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Exercise> getExercises() {
        loadExercises();
        return this.exercises;
    }

    public Exercise getExercise(String name) {
        System.out.println(name);
        for (Exercise exercise : exercises) {
            if (exercise != null && exercise.getExerciseName().equals(name)) {
                return exercise;
            }
        }
        return null;
    }

    public Food loadNew(String abb) {
        Food hold = FoodFactory.create(abb);
        String[] params = abb.split(",");
        if (params[0].equals("b")) {
            basics.add(hold);
        } else if (params[0].equals("r")) {
            for (int j = 2; j < params.length; j++) {
                if (j % 2 == 0) {
                    for (Food basic : basics) {
                        if (basic != null && basic.getName().equals(params[j])) {
                            hold.addFood(basic, Double.parseDouble(params[j + 1]));
                        }
                    }
                    for (Food recipe : recipes) {
                        if (recipe != null && recipe.getName().equals(params[j])) {
                            hold.addFood(recipe, Double.parseDouble(params[j + 1]));
                        }
                    }
                }
            }
            recipes.add(hold);
        } else {
            return null;
        }
        return hold;
    }

    public Exercise loadNewEx(String abb) {
        Exercise ex = ExerciseFactory.create(abb);
        exercises.add(ex);
        return ex;
    }

    public boolean loadDaily(DailyInformation info) {
        info.clear();
        try {
            Reader reader = new Reader();
            ArrayList<String> data = reader.readDaily();
            for (int i = 0; i < data.size(); i++) {
                String[] params = data.get(i).split(",");
                if (params[0].equals(info.getYear()) && Integer.parseInt(params[1]) == Integer.parseInt(info.getMonth()) && Integer.parseInt(params[2]) == Integer.parseInt(info.getDay())) {
                    if (params[3].equals("w")) {
                        info.setWeight(Double.parseDouble(params[4]));
                    } else if (params[3].equals("c")) {
                        info.setDesiredCalories(Double.parseDouble(params[4]));
                    } else if (params[3].equals("f")) {
                        for (int j = 4; j < params.length; j++) {
                            if (j % 2 == 0) {
                                info.addFood(getFood(params[j]), Double.parseDouble(params[j + 1]));
                            }
                        }
                    } else if (params[3].equals("e")) {      
                        info.addExercise(getExercise(params[4]), Double.parseDouble(params[5]));   
                    }
                }
            }

            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't load");
            return false;
        } catch (IOException e) {
            System.out.println("Couldn't load");
            return false;
        }
    }

    public Food getFood(String name) {
        for (Food basic : basics) {
            if (basic != null && basic.getName().equals(name)) {
                return basic;
            }
        }
        for (Food recipe : recipes) {
            if (recipe != null && recipe.getName().equals(name)) {
                return recipe;
            }
        }
        return null;
    }

    public void removeFood(String name) {
        Food hold = getFood(name);
        if (hold instanceof Recipe) {
            recipes.remove(hold);
        } else if (hold instanceof BasicFood) {
            basics.remove(hold);
        } else {
        }
    }

    public void removeExercise(String name) {
        Exercise hold = getExercise(name);
        exercises.remove(hold);
    }
}
