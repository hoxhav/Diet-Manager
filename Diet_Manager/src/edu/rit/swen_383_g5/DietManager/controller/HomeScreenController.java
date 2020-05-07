package edu.rit.swen_383_g5.DietManager.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * @version 9.4.2019
 */
public class HomeScreenController implements Initializable {

    @FXML
    private DatePicker datePicker;
    @FXML
    private Button continueButton;
    @FXML

    public Controller controller;

    public HomeScreenController() {
        datePicker = new DatePicker();
    }

    @FXML
    private void handleContinueButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/edu/rit/swen_383_g5/DietManager/view/SecondScreen.fxml"));
        loader.load();
        SecondScreenController hold = loader.getController();

        Parent secondScreen = loader.getRoot();
        Scene secondScreenScene = new Scene(secondScreen);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String[] params;
        if (datePicker.getValue() == null) {
            datePicker.setValue(LocalDate.now());
        }

        if (!datePicker.getValue().isAfter(LocalDate.now())) {
            controller = new Controller();
            params = String.valueOf(datePicker.getValue()).split("-");
            controller.setDate(params[0], params[1], params[2]);
            controller.initialLoad();
            hold.setController(controller);
            hold.setCalendar(datePicker);
            window.setScene(secondScreenScene);
            window.setResizable(false);
            window.sizeToScene();
            window.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid date");
            alert.setHeaderText(null);
            alert.setContentText("Please enter the valid date.");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        datePicker.setValue(LocalDate.now());
    }

    @FXML
    private void selectDate(ActionEvent event) {
    }
}
