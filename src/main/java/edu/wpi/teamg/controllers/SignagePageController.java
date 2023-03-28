package edu.wpi.teamg.controllers;

import edu.wpi.teamg.navigation.Navigation;
import edu.wpi.teamg.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class SignagePageController {

  @FXML MFXButton signagePageBackButton;
  @FXML TextArea signageText;
  @FXML MFXButton mealButton;
  @FXML MFXButton roomRequest;

  public void initialize() {
    signagePageBackButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    mealButton.setOnMouseClicked(event -> Navigation.navigate(Screen.MEAL_REQUEST));
    roomRequest.setOnMouseClicked(event -> Navigation.navigate(Screen.ROOM_REQUEST));
  }
}
