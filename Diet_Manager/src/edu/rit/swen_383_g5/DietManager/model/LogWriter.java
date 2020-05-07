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
public class LogWriter extends Writer {

    private PrintWriter writer;
    private File logFile;
    private StringBuilder sb;

    /**
     * Initializes the file, StringBuilder and Writing Stream
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public LogWriter() throws FileNotFoundException, IOException {
        logFile = new File("log.csv");
        writer = new PrintWriter(new BufferedWriter(new FileWriter(logFile, true)));
        sb = new StringBuilder();
    }

    /**
     * Writes daily intake
     *
     * @param di takes the Daily Info object and through that appends to the
     * log.csv file
     */
    @Override
    public void write(DailyInformation di) {
        ArrayList<String> hold = new ArrayList<>();
        try {
            Reader fr = new Reader();
            hold = fr.readDaily();
            boolean doneFood = false;
            boolean dayFound = false;
            for (String line : hold) {
                String[] split = line.split(",");
                if (split[0].equals(di.getYear()) && Integer.parseInt(split[1]) == Integer.parseInt(di.getMonth()) && Integer.parseInt(split[2]) == Integer.parseInt(di.getDay())) {
                } else {
                    sb.append(line).append("\n");
                }
            }
            if (di.getWeight() != 0) {
                sb.append(di.getDateAbbreviation()).append(",w,").append(di.getWeight()).append("\n");
            }
            if (di.getDesiredCalories() != 0) {
                sb.append(di.getDateAbbreviation()).append(",c,").append(di.getDesiredCalories()).append("\n");
            }
            if (!di.getFoodMap().isEmpty()) {
                di.getFoodMap().entrySet().forEach((entry) -> {
                    sb.append(di.getDateAbbreviation()).append(",f,").append(entry.getKey().getName()).append(",").append(entry.getValue()).append("\n");
                });
            }
            if (!di.getExerciseMap().isEmpty()) {
                di.getExerciseMap().entrySet().forEach((entry) -> {
                   sb.append(di.getDateAbbreviation()).append(",e,").append(entry.getKey().getExerciseName()).append(",").append(entry.getValue()).append("\n");
                });
            }

            writer = new PrintWriter(new BufferedWriter(new FileWriter(logFile, false)));
            writer.print(sb.toString());
        } catch (IOException ex) {
            Logger.getLogger(LogWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        writer.flush();
    }

    /**
     * Closes the writing stream
     */
    @Override
    public void closeStream() {
        if (writer != null) {
            writer.close();
        }
    }
}
