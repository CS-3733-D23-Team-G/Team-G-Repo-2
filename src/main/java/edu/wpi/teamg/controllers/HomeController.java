package edu.wpi.teamg.controllers;

import edu.wpi.teamg.navigation.Navigation;
import edu.wpi.teamg.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;

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
  }

  public void exit() {
    Platform.exit();
  }
}
