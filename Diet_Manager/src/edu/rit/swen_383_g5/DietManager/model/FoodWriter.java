package edu.rit.swen_383_g5.DietManager.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 9.4.2019
 */
public class FoodWriter extends Writer {

    private PrintWriter writer;
    private File foodFile;
    private StringBuilder sb;

    /**
     * Initializes the file, StringBuilder and Writing Stream
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public FoodWriter() throws FileNotFoundException, IOException {
        foodFile = new File("recipefoods.csv");
        writer = new PrintWriter(new BufferedWriter(new FileWriter(foodFile, true)));
        sb = new StringBuilder();
    }

    /**
     * Writes into the recipe foods csv file
     *
     * @param food interface
     */
    @Override
    public void write(Food food) {
        sb.append("\n").append(food.getAbbreviation());
        writer.print(sb.toString());
        writer.flush();
    }
    @Override
    public void writeAbb(String food) {
        sb.append(food);
        writer.println(sb.toString());
        writer.flush();
    }

    @Override
    public void closeStream() {
        if (writer != null) {
            writer.close();
        }
    }

    /**
     * Deletes food
     *
     * @param food
     */
    @Override
    public void delete(String food) {
        try {
            Reader fr = new Reader();
            ArrayList<String> data = fr.readAll();
            for (String line : data) {
                if (!line.equals(food)) {
                    sb.append(line).append("\n");
                }
            }
            writer = new PrintWriter(new BufferedWriter(new FileWriter(foodFile, false)));
            writer.println(sb.toString());
            writer.flush();
        } catch (IOException e) {
            Logger.getLogger(FoodWriter.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
