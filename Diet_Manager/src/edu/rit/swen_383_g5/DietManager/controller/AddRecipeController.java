package edu.rit.swen_383_g5.DietManager.controller;

import edu.rit.swen_383_g5.DietManager.model.Food;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The controller for the recipe adder view screen
 *
 * @version 9.4.2019
 */
public class AddRecipeController implements Initializable {

    @FXML
    private Button addButton;
    @FXML
    private MenuButton selectFoodDropdown;
    @FXML
    private Label amountLabel;
    @FXML
    private TextField amount;
    @FXML
    private Button addButton1;
    @FXML
    private Button backButton;

    private Controller controller;
    private String abbreviation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void initSelect() {
        if (controller != null) {
            selectFoodDropdown.getItems().clear();
            for (Food food : controller.getLoader().getBasics()) {
                if (food != null) {
                    MenuItem menuItem = new MenuItem(food.getName());
                    selectFoodDropdown.getItems().add(menuItem);
                    menuItem.setOnAction(change);
                }
            }
            for (Food food : controller.getLoader().getRecipes()) {
                if (food != null) {
                    MenuItem menuItem = new MenuItem(food.getName());
                    selectFoodDropdown.getItems().add(menuItem);
                    menuItem.setOnAction(change);
                }
            }
        }
    }

    EventHandler<ActionEvent> change = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
            selectFoodDropdown.setText(((MenuItem) e.getSource()).getText());
        }
    };

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent backToSecond = FXMLLoader.load(getClass().getResource("/edu/rit/swen_383_g5/DietManager/view/SecondScreen.fxml"));
        Scene backScene = new Scene(backToSecond);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(backScene);
        window.show();
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

    public void setData(Controller controller, String _recipeName) {
        this.controller = controller;
        this.abbreviation = "r," + _recipeName;
        initSelect();
    }

    @FXML
    private void addRecipe(ActionEvent event) {
        if (!selectFoodDropdown.getText().equals("Select Food") && amount.getText().matches("-?\\d+(\\.\\d+)?")) {
            abbreviation += "," + selectFoodDropdown.getText() + "," + amount.getText();
            remove(new ActionEvent());
        } else {
            System.out.println("Sorry can't do ");
            remove(new ActionEvent());
        }
    }

    @FXML
    private void remove(ActionEvent event) {
        selectFoodDropdown.setText("Select Food");
        amount.setText("");
    }

    @FXML
    private void submit(ActionEvent event) throws IOException {
        controller.addFoodToStorage(abbreviation);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/edu/rit/swen_383_g5/DietManager/view/SecondScreen.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(AddRecipeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SecondScreenController hold = loader.getController();
        hold.setController(controller);
        Parent secondScreen = loader.getRoot();
        Scene secondScreenScene = new Scene(secondScreen);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(secondScreenScene);
        window.show();
    }
}
