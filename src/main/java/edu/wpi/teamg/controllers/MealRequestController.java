package edu.wpi.teamname.controllers;

import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import edu.wpi.teamname.requestforms.MealRequestForm;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;

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
  }

  public void exit() {
    Platform.exit();
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
