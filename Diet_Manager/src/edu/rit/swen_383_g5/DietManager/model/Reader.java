package edu.rit.swen_383_g5.DietManager.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @version 9.4.2019
 */
public class Reader {
    
    private FileReader fw;
    private BufferedReader br;
    private File receipeFile,dailyfile, exerciseFile;
    
    /**
     * Initializes the two .csv files
     * @throws FileNotFoundException 
     */
    public Reader() throws FileNotFoundException {
        dailyfile = new File("log.csv");
        receipeFile = new File("recipefoods.csv");
        exerciseFile = new File("exercise.csv");
        
    }
    
    /**
     * 
     * @return the dailyfile
     * @throws IOException 
     */
    public ArrayList<String> readDaily() throws IOException{
        return read(dailyfile);
    }
    
    /**
     * 
     * @return the recipe file
     * @throws IOException 
     */
    public ArrayList<String> readAll() throws IOException{
        return read(receipeFile);
    }
    
    public ArrayList<String> readExercise() throws IOException {
        return read(exerciseFile);
    }
     
    /**
     * Method used to get rows for particular file
     * @param file the file to be read
     * @return list of rows from csv file
     * @throws IOException 
     */
    public ArrayList<String> read(File file) throws IOException{
        fw = new FileReader(file);
        br = new BufferedReader(fw);
        ArrayList<String> list = new ArrayList<>();
        String line ="";
        
        while((line = br.readLine()) != null) {
            list.add(line);
        }
        
        return list;
    }
}