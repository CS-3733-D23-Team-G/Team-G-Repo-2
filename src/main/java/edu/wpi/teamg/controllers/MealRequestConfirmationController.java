package edu.wpi.teamname.controllers;

import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class MealRequestConfirmationController {

  @FXML MFXButton backToHomeButton;
  @FXML MFXComboBox<String> serviceRequestComboBox;
  @FXML MFXButton signagePageButton;
  @FXML MFXButton exitButton;

  ObservableList<String> list =
      FXCollections.observableArrayList(
          "Meal Request Form",
          "Furniture Request Form",
          "Conference Room Request Form",
          "Flowers Request Form",
          "Office Supplies Request Form");

  @FXML
  public void initialize() {
    serviceRequestComboBox.setItems(list);
    signagePageButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE_PAGE));
    backToHomeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    exitButton.setOnMouseClicked(event -> exit());
  }

  public void exit() {
    Platform.exit();
  }
}
