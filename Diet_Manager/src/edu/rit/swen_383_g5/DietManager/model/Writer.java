package edu.rit.swen_383_g5.DietManager.model;

/**
 * Serves as the parent of writers
 *
 * @version 9.4.2019
 */
public abstract class Writer {

    public void write(DailyInformation di) {
    }

    public void write(Food food) {
    }

    public void write(Exercise exer) {
    }

    public void closeStream() {
    }

    public void writeAbb(String food) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(String abb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
