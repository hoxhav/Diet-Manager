/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen_383_g5.DietManager.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 */
public class AddExerciseController implements Initializable {
    
    private Controller controller;
    private String abbreviation;
    private String exerciseName; 
    
    @FXML
    private Button addExerciseButton;
    @FXML
    private TextField caloriesTextField;
    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }    
    
    /**
     * A method used for inputs that we want a double, to check if user wrote
     * something which is not a number
     *
     * @param Takes a string
     * @return returns true if it is a double, false otherwise
     */
    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
    
    /**
     * AddExerciseCalories method which is used for adding the exercise. 
     * It adds the exercise to the storage via the controller. 
     * @param event
     * @throws IOException 
     */
    
    @FXML
    private void addExerciseCalories(ActionEvent event) throws IOException {
        
        if(!caloriesTextField.getText().equals("") && this.isDouble(caloriesTextField.getText())) {
            controller.addExerciseToStorage(exerciseName + "," + caloriesTextField.getText());
            controller.initialLoad();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/edu/rit/swen_383_g5/DietManager/view/SecondScreen.fxml"));
            loader.load();
            SecondScreenController hold = loader.getController();
            hold.setController(controller);
            Parent secondScreen = loader.getRoot();
            Scene secondScreenScene = new Scene(secondScreen);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(secondScreenScene);
            window.show();
        
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empty Inputs or not numbers");
            alert.setHeaderText(null);
            alert.setContentText("Please enter the correct data");
            alert.showAndWait();
        }
    
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent backToSecond = FXMLLoader.load(getClass().getResource("/edu/rit/swen_383_g5/DietManager/view/SecondScreen.fxml"));
        Scene backScene = new Scene(backToSecond);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(backScene);
        window.show();
    }
    
    public void setData(Controller controller, String _exerciseName) {
        this.controller = controller;
        this.exerciseName = "e," + _exerciseName;
    }
    
}
