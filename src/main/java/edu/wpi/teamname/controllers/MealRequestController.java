package edu.wpi.teamname.controllers;

import edu.wpi.teamname.ORMClasses.MealRequest;
import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
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

  @FXML MFXDatePicker mealDate;

  // TextFields
  @FXML MFXTextField mealTimeOfDeliver;
  @FXML MFXTextField mealEmployeeIDData;
  @FXML MFXTextField mealDeliveryLocationData;
  @FXML MFXTextField mealPersonOrderingForData;
  @FXML MFXTextField mealNotesData;
  @FXML ChoiceBox<String> mealFoodChoice;
  @FXML ChoiceBox<String> serviceRequestChoiceBox;

  ObservableList<String> list =
      FXCollections.observableArrayList(
          "Conference Room Request Form",
          "Flowers Request Form",
          "Furniture Request Form",
          "Meal Request Form",
          "Office Supplies Request Form");

  ObservableList<String> foodList =
      FXCollections.observableArrayList(
          "Fenway Franks",
          "Choco Taco",
          "Salt-Based Steak",
          "Bisquit",
          "Shrimp Fried Rice",
          "Beef Wellington",
          "Spaghetii Taco",
          "Mac and Cheese Pizza",
          "Cavatappi",
          "One Singular Oyster",
          "CC Buritto Bowl (w/ Siracha)");

  @FXML
  public void initialize() {
    mealSubmitButton.setOnMouseClicked(event -> Navigation.navigate(Screen.MEAL_REQUEST_SUBMIT));
    signagePageButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE_PAGE));
    backToHomeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    exitButton.setOnMouseClicked(event -> exit());

    mealSubmitButton.setOnMouseClicked(
        event -> {
          try {
            storeMealValues();
          } catch (SQLException e) {
            throw new RuntimeException(e);
          }
        });

    //  mealNameData.getText();
    mealEmployeeIDData.getText();
    mealDeliveryLocationData.getText();
    mealPersonOrderingForData.getText();
    mealNotesData.getText();
    mealFoodChoice.setItems(foodList);
    serviceRequestChoiceBox.setOnAction(event -> loadServiceRequestForm());
    mealDate.getValue();
    mealTimeOfDeliver.getText();
  }

  public void exit() {
    Platform.exit();
  }

  public void storeMealValues() throws SQLException {
    MealRequest mr = new MealRequest();

    mr.setEmpid(Integer.parseInt(mealEmployeeIDData.getText()));
    mr.setServ_by(1);
    // assume for now they are going to input a node number, so parseInt
    mr.setLocation(Integer.parseInt(mealDeliveryLocationData.getText()));
    mr.setRecipient(mealPersonOrderingForData.getText());
    mr.setNote(mealNotesData.getText());
    mr.setDeliveryDate(Date.valueOf(mealDate.getValue()));
    mr.setDeliveryTime(StringToTime(mealTimeOfDeliver.getText()));
    mr.setOrder(mealFoodChoice.getValue());

    System.out.println(
        "Employee ID: "
            + mr.getEmpid()
            + "\nDelivery Location: "
            + mr.getLocation()
            + "\nOrder: "
            + mr.getOrder()
            + "\nNote: "
            + mr.getNote()
            + "\nRecipient: "
            + mr.getRecipient()
            + "\nDelivery Date: "
            + mr.getDeliveryDate()
            + "\nDelivery Time: "
            + mr.getDeliveryTime());

    //    MealRequestDAO mealRequestDAO = new MealRequestDAO();
    //    mealRequestDAO.insert(mr);
  }

  public Time StringToTime(String s) {

    String[] hourMin = s.split(":", 2);
    Time t = new Time(Integer.parseInt(hourMin[0]), Integer.parseInt(hourMin[1]), 00);
    return t;
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
