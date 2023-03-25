package edu.wpi.teamg.controllers;

import edu.wpi.teamg.navigation.Navigation;
import edu.wpi.teamg.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;

public class MealServiceRequestController {

  @FXML MFXButton mealBackButton;

  @FXML
  public void initialize() {
    mealBackButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
  }
}
