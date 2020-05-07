/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen_383_g5.DietManager.controller;

import edu.rit.swen_383_g5.DietManager.model.DailyInformation;
import edu.rit.swen_383_g5.DietManager.model.Exercise;
import edu.rit.swen_383_g5.DietManager.model.ExerciseWriter;
import edu.rit.swen_383_g5.DietManager.model.Food;
import edu.rit.swen_383_g5.DietManager.model.FoodWriter;
import edu.rit.swen_383_g5.DietManager.model.LogWriter;
import edu.rit.swen_383_g5.DietManager.model.Writer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class WriterFacade {
    
    private Writer writer;
    
    public void write(Object object) {
        try {
            if (object instanceof Food) {
                writer = new FoodWriter();
                writer.write((Food) object);

            } else if (object instanceof Exercise) {
                writer = new ExerciseWriter();
                writer.write((Exercise) object);
            } else if (object instanceof DailyInformation) {
                writer = new LogWriter();
                writer.write((DailyInformation) object);
            }else{
                System.out.println("Couldn't fit object");
            }
        } catch (IOException e) {
            Logger.getLogger(LogWriter.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void writeAbb(String food) {
        try {
            writer = new FoodWriter();
            writer.writeAbb(food);
        } catch (IOException ex) {
            Logger.getLogger(WriterFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(String abb) {
        try {
            String[] hold = abb.split(",");
            if(hold[0].equals("b") || hold[0].equals("r")) {
                writer = new FoodWriter();
                writer.delete(abb);
            } else if (hold[0].equals("e")) {
                writer = new ExerciseWriter();
                writer.delete(abb);
            }else{
                System.out.println("Couldn't fit object");
            }
        } catch (IOException e) {
            Logger.getLogger(LogWriter.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
