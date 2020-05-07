package edu.rit.swen_383_g5.DietManager.controller;

import edu.rit.swen_383_g5.DietManager.model.DailyInformation;
import edu.rit.swen_383_g5.DietManager.model.Exercise;
import edu.rit.swen_383_g5.DietManager.model.FoodFactory;
import edu.rit.swen_383_g5.DietManager.model.Reader;
import edu.rit.swen_383_g5.DietManager.model.Food;
import edu.rit.swen_383_g5.DietManager.model.Loader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 3.4.2019
 * Controller for all of the screens.
 */
public class Controller {

    private Loader loader;
    private DailyInformation info;

    public Controller() {
        loader = new Loader();
        info = new DailyInformation();
    }

    public Loader getLoader() {
        return loader;
    }

    public DailyInformation getInfo() {
        return info;
    }

    public void setInfo(DailyInformation info) {
        this.info = info;
    }

    public void addFoodToStorage(String abb) {
        WriterFacade wr = new WriterFacade();
        wr.write(loader.loadNew(abb));
    }
    
     public void addExerciseToStorage(String abb) {
        WriterFacade wr = new WriterFacade();
        wr.write(loader.loadNewEx(abb));
    }

    public void addFoodToDaily(Food f, double amount) {
        info.addFood(f, amount);
    }
    
    public void addExcerciseToDaily(Exercise exercise, double minutes) {
        info.addExercise(exercise, minutes);
    }

    public void removeFoodFromDaily(Food f, double amount) {
        info.removeFood(f, amount);
    }
    
    public void removeExerciseFromDaily(Exercise e, double duration){
        info.removeExercise(e, duration);
    }

    public void setDate(String year, String month, String day) {
        System.out.println("Iz controllera: " + year + " " + month + " " + day);
        info.setDate(year, month, day);
    }

    public void loadDaily() {
        loader.loadDaily(info);
    }

    public void initialLoad() {
        loader.loadFoods();
    }
    
    public void loadExercise(){
        loader.loadExercises();
    }

    public void writeDaily() throws IOException {
        WriterFacade wr = new WriterFacade();
        wr.write(info);
    }

    public void deleteFood(String name) {
        if (loader.getFood(name) != null) {
            WriterFacade wr = new WriterFacade();
            wr.delete(loader.getFood(name).getAbbreviation());
            loader.removeFood(name);
        }
    }
    
    public void deleteEx(String name) {
        if (loader.getExercise(name) != null) {
            WriterFacade wr = new WriterFacade();
            wr.delete(loader.getExercise(name).getAbbreviation());
            loader.removeExercise(name);
        }
    }
}
