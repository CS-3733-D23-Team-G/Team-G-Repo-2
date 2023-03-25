package edu.wpi.teamg.controllers;

import edu.wpi.teamg.navigation.Navigation;
import edu.wpi.teamg.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class HomeController {

  @FXML MFXButton mealButton;
  @FXML MFXButton roomButton;
  @FXML MFXButton signagePageButton;

  @FXML MFXButton exitButton;

  @FXML
  public void initialize() {
    mealButton.setOnMouseClicked(event -> Navigation.navigate(Screen.MEAL_REQUEST));
    roomButton.setOnMouseClicked(event -> Navigation.navigate(Screen.ROOM_REQUEST));
    signagePageButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE_PAGE));
    exitButton.setOnMouseClicked(event -> exit());
  }

  public void exit() {
    Platform.exit();
  }
}
