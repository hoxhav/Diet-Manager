/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * Class dealing with Exercises
 *
 * @version 21.04.2019
 */
public class ExerciseWriter extends Writer {

    private PrintWriter writer;
    private File exerciseFile;
    private StringBuilder sb;

    /**
     * Initializes the file, StringBuilder and Writing Stream
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public ExerciseWriter() throws FileNotFoundException, IOException {
        exerciseFile = new File("exercise.csv");
        writer = new PrintWriter(new BufferedWriter(new FileWriter(exerciseFile, true)));
        sb = new StringBuilder();
    }

    @Override
    public void write(Exercise exer) {
        sb.append(exer.getAbbreviation());
        writer.println(sb.toString());
        writer.flush();
    }

    @Override
    public void closeStream() {
        if (writer != null) {
            writer.close();
        }
    }
    
    @Override
    public void delete(String exercise) {
        try {
            Reader fr = new Reader();
            ArrayList<String> data = fr.readExercise();
            for (String line : data) {
                if (!line.equals(exercise)) {
                    sb.append(line).append("\n");
                }
            }
            writer = new PrintWriter(new BufferedWriter(new FileWriter(exerciseFile, false)));
            writer.println(sb.toString());
            writer.flush();
        } catch (IOException e) {
            Logger.getLogger(ExerciseWriter.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
