package edu.wpi.teamg.controllers;

import edu.wpi.teamg.navigation.Navigation;
import edu.wpi.teamg.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class RoomServiceRequestController {

  @FXML MFXButton roomHomeButton;

  @FXML MFXButton roomFoodDeliverButton;
  @FXML MFXButton roomExitButton;
  @FXML MFXButton roomSignageButton;

  @FXML MFXButton roomConfirm;
  @FXML MFXButton roomClearAll;

  @FXML
  public void initialize() {
    roomHomeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    roomFoodDeliverButton.setOnMouseClicked(event -> Navigation.navigate(Screen.MEAL_REQUEST));
    roomSignageButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE_PAGE));
    roomExitButton.setOnMouseClicked(event -> roomExit());
    roomClearAll.setOnMouseClicked(event -> Navigation.navigate(Screen.ROOM_REQUEST));
    roomConfirm.setOnMouseClicked(event -> Navigation.navigate(Screen.ROOM_CONFIRM_PAGE));
  }

  public void roomExit() {
    Platform.exit();
  }
}
