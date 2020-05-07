package edu.rit.swen_383_g5.DietManager.controller;

import edu.rit.swen_383_g5.DietManager.model.Exercise;
import edu.rit.swen_383_g5.DietManager.model.ExerciseOutput;
import edu.rit.swen_383_g5.DietManager.model.Food;
import edu.rit.swen_383_g5.DietManager.model.Output;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @version 9.4.2019
 */
public class SecondScreenController implements Initializable {

    /**
     * Attributes to Access Everything
     */
    @FXML
    private TableView tb;
    @FXML
    private TextField noCaloriesField;
    @FXML
    private MenuButton selectfoodDropdown;
    @FXML
    private TextField addMyFoodField;
    @FXML
    private TextField amount;
    @FXML
    private TextField addRecipeField;
    @FXML
    private TextField addWeightField;
    @FXML
    private TextField deleteFoodField;
    @FXML
    private Label desiredCals;
    @FXML
    private Label weightRes;
    @FXML
    private Label callDiff;
    @FXML
    private Text noCaloriesText;
    @FXML
    private Text addRecipeText;
    @FXML
    private Text addedFoodText;
    @FXML
    private Label addedWeightText;
    @FXML
    private TextField deleteRecipeField;
    @FXML
    private TableView exerciseTable;
    @FXML
    private TextField duration;
    @FXML
    private Button showDataButton;
    @FXML
    private DatePicker selectAnotherDate;
    @FXML
    private TextField addMyExercise;
    @FXML
    private MenuButton selectExercises;
    @FXML
    private Button addExercises;

    /**
     * Initializes the controller class.
     */
    private Controller controller;

    String anotherDate = "";
    LocalDate date = null;
    Alert alert = null;

    private boolean loaded = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Empty input");
        alert.setHeaderText(null);
    }

    @FXML
    private void addFoodButton(ActionEvent event) throws IOException {

        if (!(addMyFoodField.getText().equals(""))) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/edu/rit/swen_383_g5/DietManager/view/AddFood.fxml"));
            loader.load();
            AddFoodController hold = loader.getController();
            hold.setData(controller, addMyFoodField.getText());
            Parent addFoodScreen = loader.getRoot();
            Scene addFoodScene = new Scene(addFoodScreen);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(addFoodScene);
            window.show();
        } else {
            alert.setContentText("Please enter the food name you would like to add.");
            alert.showAndWait();
        }
    }

    /**
     * Remove recipes Buttons
     *
     * @param event
     */
    @FXML
    private void removeRecipes(ActionEvent event) throws IOException {
        controller.deleteFood(deleteRecipeField.getText());
        deleteRecipeField.setText("");
        initSelect();
    }

    /**
     * Add Calories Button
     *
     * @param event
     */
    @FXML
    private void addCalories(ActionEvent event) {
        if (!noCaloriesField.getText().equals("")) {
            controller.getInfo().setDesiredCalories(Double.parseDouble(noCaloriesField.getText()));
            desiredCals.setText(Double.toString(controller.getInfo().getDesiredCalories()));
            callDiff.setText(Double.toString(Double.parseDouble(desiredCals.getText()) - controller.getInfo().getCalories()));
            noCaloriesField.setText("");
            try {
                controller.writeDaily();
            } catch (IOException ex) {
                Logger.getLogger(SecondScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            alert.setContentText("Please enter the desired number of calories.");
            alert.showAndWait();
        }
    }

    /**
     * remove food Button
     *
     * @param event
     */
    @FXML
    private void removeFoodButton(ActionEvent event) {
        controller.deleteFood(deleteFoodField.getText());
        deleteFoodField.setText("");
        initSelect();
    }

    /**
     * Add recipe Button
     *
     * @param event
     */
    @FXML
    private void addRecipeButton(ActionEvent event) throws IOException {
        if (!(addRecipeField.getText().equals(""))) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/edu/rit/swen_383_g5/DietManager/view/AddRecipe.fxml"));
            loader.load();
            AddRecipeController hold = loader.getController();
            hold.setData(controller, addRecipeField.getText());
            Parent addRecipeScreen = loader.getRoot();
            Scene addRecipeScene = new Scene(addRecipeScreen);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(addRecipeScene);
            window.show();
        } else {
            alert.setContentText("Please enter the recipe name you would like to add.");
            alert.showAndWait();
        }
    }

    /**
     * Add weight Button
     *
     * @param event
     */
    @FXML
    private void addWeight(ActionEvent event) {
        controller.loadDaily();
        if (!addWeightField.getText().equals("")) {
            controller.getInfo().setWeight(Double.parseDouble(addWeightField.getText()));
            weightRes.setText(Double.toString(controller.getInfo().getWeight()));
            addWeightField.setText("");
            try {
                controller.writeDaily();
                tb.getItems().clear();
                tb.getColumns().clear();
                exerciseTable.getItems().clear();
                exerciseTable.getColumns().clear();
                loaded = true;
                showData(new ActionEvent());
            } catch (IOException ex) {
                Logger.getLogger(SecondScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            alert.setContentText("Please enter the desired number of calories.");
            alert.showAndWait();
        }
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

    public void setController(Controller controller) throws IOException {
        this.controller = controller;
        //initialize the first drop-down
        initSelect();
        initSelectExercises();
    }

    public void initSelectExercises() throws IOException {
        if (controller != null) {
            selectExercises.getItems().clear();
            for (Exercise exerciseObject : controller.getLoader().getExercises()) {
                MenuItem menuItem = new MenuItem(exerciseObject.getExerciseName());
                selectExercises.getItems().add(menuItem);
                menuItem.setOnAction(changeExerciseSelect);
            }
        }
    }

    public void initSelect() {
        controller.getLoader().loadFoods();
        if (controller != null) {
            selectfoodDropdown.getItems().clear();
            for (Food food : controller.getLoader().getBasics()) {
                if (food != null) {
                    MenuItem menuItem = new MenuItem(food.getName());
                    selectfoodDropdown.getItems().add(menuItem);
                    menuItem.setOnAction(change);
                }
            }
            for (Food food : controller.getLoader().getRecipes()) {
                if (food != null) {
                    MenuItem menuItem = new MenuItem(food.getName());
                    selectfoodDropdown.getItems().add(menuItem);
                    menuItem.setOnAction(change);
                }
            }
        }
    }

    EventHandler<ActionEvent> change = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
            selectfoodDropdown.setText(((MenuItem) e.getSource()).getText());
        }
    };

    EventHandler<ActionEvent> changeExerciseSelect = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
            selectExercises.setText(((MenuItem) e.getSource()).getText());
        }
    };

    @FXML
    private void showData(ActionEvent event) {
        System.out.println("Show Data button got clicked.");
        if (!loaded) {
            controller.loadDaily();
        }
        loaded = false;
        desiredCals.setText(Double.toString(controller.getInfo().getDesiredCalories()));
        callDiff.setText(Double.toString(Double.parseDouble(desiredCals.getText()) - controller.getInfo().getCalories()));
        weightRes.setText(Double.toString(controller.getInfo().getWeight()));
        TableColumn<String, Output> name = new TableColumn<>("Food Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<String, Output> amount = new TableColumn<>("Amount");
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<String, Output> calories = new TableColumn<>("Calories");
        calories.setCellValueFactory(new PropertyValueFactory<>("calories"));

        TableColumn<String, Output> fat = new TableColumn<>("Fats");
        fat.setCellValueFactory(new PropertyValueFactory<>("fat"));

        TableColumn<String, Output> carb = new TableColumn<>("Carbs");
        carb.setCellValueFactory(new PropertyValueFactory<>("carb"));

        TableColumn<String, Output> protein = new TableColumn<>("Proteins");
        protein.setCellValueFactory(new PropertyValueFactory<>("protein"));

        tb.getColumns().add(name);
        tb.getColumns().add(amount);
        tb.getColumns().add(calories);
        tb.getColumns().add(fat);
        tb.getColumns().add(carb);
        tb.getColumns().add(protein);

        controller.getInfo().getFoodMap().entrySet().forEach((entry) -> {
            tb.getItems().add(new Output(entry.getKey().getName(), "" + entry.getValue(), "" + entry.getKey().getCalories(), "" + entry.getKey().getFat(), "" + entry.getKey().getCarb(), "" + entry.getKey().getProtein()));
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
        showDataButton.setDisable(true);
        createExerciseTable();
    }

    public void createExerciseTable() {
        TableColumn<String, ExerciseOutput> name = new TableColumn<>("Exercise Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<String, ExerciseOutput> calories = new TableColumn<>("Calories");
        calories.setCellValueFactory(new PropertyValueFactory<>("calories"));

        TableColumn<String, ExerciseOutput> time = new TableColumn<>("Time");
        time.setCellValueFactory(new PropertyValueFactory<>("time"));

        exerciseTable.getColumns().add(name);
        exerciseTable.getColumns().add(calories);
        exerciseTable.getColumns().add(time);

        controller.getInfo().getExerciseMap().entrySet().forEach((entry) -> {
            exerciseTable.getItems().add(new ExerciseOutput(entry.getKey().getExerciseName(), "" + entry.getKey().getCalories(), Double.toString(entry.getValue())));
        });
    }

    @FXML
    private void setAnotherDate(ActionEvent event) {
        desiredCals.setText("");
        weightRes.setText("");
        callDiff.setText("");
        date = selectAnotherDate.getValue();
        anotherDate = String.valueOf(date);
        System.out.println("Another date: " + anotherDate);

        if (!date.isAfter(LocalDate.now())) {
            String[] params = anotherDate.split("-");
            controller.getInfo().setDesiredCalories(0);
            controller.getInfo().setWeight(0);
            controller.setDate(params[0], params[1], params[2]);
            controller.loadDaily();
            tb.getItems().clear();
            tb.getColumns().clear();
            exerciseTable.getItems().clear();
            exerciseTable.getColumns().clear();
            showDataButton.setDisable(false);
        } else {
            alert.setContentText("Please enter the valid date.");
            alert.showAndWait();

        }
    }

    public void setCalendar(DatePicker datePicker) {
        selectAnotherDate.setValue(datePicker.getValue());
    }

    @FXML
    private void putFoodToDaily(ActionEvent event) {
        controller.loadDaily();
        if (controller.getLoader().getFood(selectfoodDropdown.getText()) != null && amount.getText().matches("-?\\d+(\\.\\d+)?")) {
            controller.addFoodToDaily(controller.getLoader().getFood(selectfoodDropdown.getText()), Double.parseDouble(amount.getText()));
            try {
                controller.writeDaily();
                tb.getItems().clear();
                tb.getColumns().clear();
                exerciseTable.getItems().clear();
                exerciseTable.getColumns().clear();
                loaded = true;
                showData(new ActionEvent());
            } catch (IOException ex) {
                Logger.getLogger(SecondScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void removeFoodFromDaily(ActionEvent event) {
        controller.loadDaily();
        if (controller.getLoader().getFood(selectfoodDropdown.getText()) != null && amount.getText().matches("-?\\d+(\\.\\d+)?")) {
            controller.removeFoodFromDaily(controller.getLoader().getFood(selectfoodDropdown.getText()), Double.parseDouble(amount.getText()));
            try {
                controller.writeDaily();
                tb.getItems().clear();
                tb.getColumns().clear();
                exerciseTable.getItems().clear();
                exerciseTable.getColumns().clear();
                loaded = true;
                showData(new ActionEvent());
            } catch (IOException ex) {
                Logger.getLogger(SecondScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void addExercises(ActionEvent event) {
        if (!duration.getText().equals("")) {
            controller.loadDaily();
            System.out.println("ADDED");
            if (controller.getLoader().getExercise(selectExercises.getText()) != null && duration.getText().matches("-?\\d+(\\.\\d+)?")) {
                controller.addExcerciseToDaily(controller.getLoader().getExercise(selectExercises.getText()), Double.parseDouble(duration.getText()));
                try {
                    controller.writeDaily();
                    tb.getItems().clear();
                    tb.getColumns().clear();
                    exerciseTable.getItems().clear();
                    exerciseTable.getColumns().clear();
                    loaded = true;
                    showData(new ActionEvent());
                } catch (IOException ex) {
                    Logger.getLogger(SecondScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            alert.setContentText("Please enter the duration of the exercise in minutes.");
            alert.showAndWait();
        }
    }

    @FXML
    private void removeExercise(ActionEvent event) {
        controller.loadDaily();
        if (controller.getLoader().getExercise(selectExercises.getText()) != null && duration.getText().matches("-?\\d+(\\.\\d+)?")) {
            controller.removeExerciseFromDaily(controller.getLoader().getExercise(selectExercises.getText()), Double.parseDouble(duration.getText()));
            try {
                controller.writeDaily();
                tb.getItems().clear();
                tb.getColumns().clear();
                exerciseTable.getItems().clear();
                exerciseTable.getColumns().clear();
                loaded = true;
                showData(new ActionEvent());
            } catch (IOException ex) {
                Logger.getLogger(SecondScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Add Exercise Goes to the exercise screen.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void addExerciseButton(ActionEvent event) throws IOException {
        if (!(addMyExercise.getText().equals(""))) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/edu/rit/swen_383_g5/DietManager/view/AddExercise.fxml"));
            loader.load();
            AddExerciseController hold = loader.getController();
            hold.setData(controller, addMyExercise.getText());
            Parent addExerciseScreen = loader.getRoot();
            Scene addExerciseScene = new Scene(addExerciseScreen);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(addExerciseScene);
            window.show();
        } else {
            alert.setContentText("Please enter the exercise name you would like to add");
            alert.showAndWait();
        }

    }

    @FXML
    private void removeExerciseLogButton(ActionEvent event) throws IOException {
        if (!(addMyExercise.getText().equals(""))) {
            controller.deleteEx(addMyExercise.getText());
            addMyExercise.setText("");
            initSelectExercises();
        } else {
            alert.setContentText("Please enter the exercise name you would like to add");
            alert.showAndWait();
        }
    }
}
