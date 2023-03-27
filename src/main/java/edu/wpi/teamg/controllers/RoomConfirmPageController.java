package edu.wpi.teamg.controllers;

import edu.wpi.teamg.navigation.Navigation;
import edu.wpi.teamg.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class RoomConfirmPageController {
  @FXML MFXButton rcpHomeButton;

  @FXML MFXButton rcpFoodDeliverButton;
  @FXML MFXButton rcpExitButton;
  @FXML MFXButton rcpSignageButton;

  @FXML
  public void initialize() {
    rcpHomeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    rcpFoodDeliverButton.setOnMouseClicked(event -> Navigation.navigate(Screen.MEAL_REQUEST));
    rcpSignageButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE_PAGE));
    rcpExitButton.setOnMouseClicked(event -> roomExit());
  }

  public void roomExit() {
    Platform.exit();
  }
}
