package edu.wpi.teamname.controllers;

import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import edu.wpi.teamname.requestforms.MealRequestForm;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class MealRequestController {
  @FXML MFXButton mealSubmitButton;
  @FXML MFXButton signagePageButton;
  @FXML MFXButton backToHomeButton;
  @FXML MFXButton exitButton;

  // TextFields
  @FXML MFXTextField mealNameData;
  @FXML MFXTextField mealEmployeeIDData;
  @FXML MFXTextField mealDeliveryLocationData;
  @FXML MFXTextField mealPersonOrderingForData;
  @FXML MFXTextField mealNotesData;
  @FXML MFXTextField mealFoodChoice;

  @FXML ChoiceBox<String> serviceRequestChoiceBox;

  ObservableList<String> list =
      FXCollections.observableArrayList(
          "Conference Room Request Form",
          "Flowers Request Form",
          "Furniture Request Form",
          "Meal Request Form",
          "Office Supplies Request Form");

  @FXML
  public void initialize() {
    mealSubmitButton.setOnMouseClicked(event -> Navigation.navigate(Screen.MEAL_REQUEST_SUBMIT));
    signagePageButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE_PAGE));
    backToHomeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    exitButton.setOnMouseClicked(event -> exit());

    mealNameData.getText();
    mealEmployeeIDData.getText();
    mealDeliveryLocationData.getText();
    mealPersonOrderingForData.getText();
    mealNotesData.getText();
    mealFoodChoice.getText();
    serviceRequestChoiceBox.setItems(list);
    serviceRequestChoiceBox.setOnAction(event -> loadServiceRequestForm());
  }

  public void exit() {
    Platform.exit();
  }

  public void loadServiceRequestForm() {
    if (serviceRequestChoiceBox.getValue().equals("Meal Request Form")) {
      Navigation.navigate(Screen.MEAL_REQUEST);
    } else if (serviceRequestChoiceBox.getValue().equals("Furniture Request Form")) {
      Navigation.navigate(Screen.FURNITURE_REQUEST);
    } else if (serviceRequestChoiceBox.getValue().equals("Conference Room Request Form")) {
      Navigation.navigate(Screen.ROOM_REQUEST);
    } else if (serviceRequestChoiceBox.getValue().equals("Flowers Request Form")) {
      Navigation.navigate(Screen.FLOWERS_REQUEST);
    } else if (serviceRequestChoiceBox.getValue().equals("Office Supplies Request Form")) {
      Navigation.navigate(Screen.SUPPLIES_REQUEST);
    } else {
      return;
    }
  }

  public MealRequestForm storeMealValues() {
    String mealName = mealNameData.getText();
    String mealEmployeeID = mealEmployeeIDData.getText();
    String mealLocation = mealDeliveryLocationData.getText();
    String mealOrderer = mealPersonOrderingForData.getText();
    String mealNotes = mealNotesData.getText();
    String foodChoice = mealFoodChoice.getText();

    System.out.println(
        "Answers: "
            + mealName
            + " "
            + mealEmployeeID
            + " "
            + mealLocation
            + " "
            + mealOrderer
            + " "
            + mealNotes
            + " "
            + foodChoice
            + " ");

    MealRequestForm mealForm =
        new MealRequestForm(
            mealName, mealEmployeeID, mealLocation, mealOrderer, mealNotes, foodChoice);
    printMealForms(mealForm);

    return mealForm;
  }

  public void printMealForms(MealRequestForm form) {

    System.out.println(
        form.mealName
            + " "
            + form.mealEmployeeID
            + " "
            + form.mealLocation
            + " "
            + form.mealOrderer
            + " "
            + form.mealNotes
            + " "
            + form.mealFoodChoice);
  }
}
