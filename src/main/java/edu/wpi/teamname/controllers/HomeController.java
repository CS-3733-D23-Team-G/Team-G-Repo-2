package edu.wpi.teamname.controllers;

import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class HomeController {

  @FXML MFXButton signagePageButton;
  @FXML MFXButton exitButton;

  @FXML MFXButton homeToCon;
  @FXML MFXButton homeToConConfirm;
  @FXML MFXButton homeToFurn;
  @FXML MFXButton homeToFurnConfirm;
  @FXML MFXButton homeToFlow;
  @FXML MFXButton homeToFlowConfirm;
  @FXML MFXButton homeToMeal;
  @FXML MFXButton homeToMealConfirm;
  @FXML MFXButton homeToOffSupp;
  @FXML MFXButton homeToOffSuppConfirm;
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
    signagePageButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE_PAGE));
    exitButton.setOnMouseClicked(event -> exit());

    homeToCon.setOnMouseClicked(event -> Navigation.navigate(Screen.ROOM_REQUEST));
    homeToConConfirm.setOnMouseClicked(event -> Navigation.navigate(Screen.ROOM_REQUEST_SUBMIT));
    homeToFurn.setOnMouseClicked(event -> Navigation.navigate(Screen.FURNITURE_REQUEST));
    homeToFurnConfirm.setOnMouseClicked(
        event -> Navigation.navigate(Screen.FURNITURE_REQUEST_SUBMIT));
    homeToFlow.setOnMouseClicked(event -> Navigation.navigate(Screen.FLOWERS_REQUEST));
    homeToFlowConfirm.setOnMouseClicked(
        event -> Navigation.navigate(Screen.FLOWERS_REQUEST_SUBMIT));
    homeToMeal.setOnMouseClicked(event -> Navigation.navigate(Screen.MEAL_REQUEST));
    homeToMealConfirm.setOnMouseClicked(event -> Navigation.navigate(Screen.MEAL_REQUEST_SUBMIT));
    homeToOffSupp.setOnMouseClicked(event -> Navigation.navigate(Screen.SUPPLIES_REQUEST));
    homeToOffSuppConfirm.setOnMouseClicked(
        event -> Navigation.navigate(Screen.SUPPLIES_REQUEST_SUBMIT));
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
}
