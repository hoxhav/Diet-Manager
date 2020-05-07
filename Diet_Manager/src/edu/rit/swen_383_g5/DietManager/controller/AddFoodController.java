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
 * The controller for adding foods screen
 *
 * @version 9.4.2019
 */
public class AddFoodController implements Initializable {

    @FXML
    private TextField caloriesTextField;
    @FXML
    private TextField addCarbsTextField;
    @FXML
    private TextField addProteinsTextField;
    @FXML
    private TextField addFatTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    private Controller controller;
    private String foodName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    @FXML
    /**
     * Button handler for add food button which uses the main controller to add
     * to the Daily Info, and then through that writes to the csv
     *
     */
    private void addMyFood(ActionEvent event) throws IOException {

        if (!caloriesTextField.getText().equals("")
                && !addFatTextField.getText().equals("")
                && !addCarbsTextField.getText().equals("")
                && !addProteinsTextField.getText().equals("")
                && this.isDouble(caloriesTextField.getText())
                && this.isDouble(addFatTextField.getText())
                && this.isDouble(addCarbsTextField.getText())
                && this.isDouble(addProteinsTextField.getText())) {

            controller.addFoodToStorage("b," + foodName + ","
                    + caloriesTextField.getText()
                    + "," + addFatTextField.getText() + ","
                    + addCarbsTextField.getText() + ","
                    + addProteinsTextField.getText());
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
            alert.setTitle("Empty inputs or not numbers");
            alert.setHeaderText(null);
            alert.setContentText("Please enter the correct data.");
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

    public void setData(Controller controller, String _foodName) {
        this.controller = controller;
        this.foodName = _foodName;
    }

}
