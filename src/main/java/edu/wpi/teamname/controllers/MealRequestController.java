package edu.wpi.teamname.controllers;

import edu.wpi.teamname.ORMClasses.MealRequest;
import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    mealSubmitButton.setOnMouseClicked(event -> storeMealValues());

    //  mealNameData.getText();
    mealEmployeeIDData.getText();
    mealDeliveryLocationData.getText();
    mealPersonOrderingForData.getText();
    mealNotesData.getText();
    mealFoodChoice.setItems(foodList);
    serviceRequestChoiceBox.setOnAction(event -> loadServiceRequestForm());
    mealDate.getCurrentDate();
    mealTimeOfDeliver.getText();
  }

  public void exit() {
    Platform.exit();
  }

  public MealRequest storeMealValues() {
    MealRequest mr = new MealRequest();
    mr.setReqid(Integer.parseInt(mealEmployeeIDData.getText()));
    // assume for now they are going to input a node number, so parseInt
    mr.setLocation(Integer.parseInt(mealDeliveryLocationData.getText()));
    mr.setRecipient(mealPersonOrderingForData.getText());
    mr.setNote(mealNotesData.getText());
    mr.setDateTime(
        LocalDateTime.of(mealDate.getCurrentDate(), StringToTime(mealTimeOfDeliver.getText())));
    mr.setOrder(mealFoodChoice.getValue());
    System.out.println(
        "Employee ID: "
            + mr.getReqid()
            + "\nDelivery Location: "
            + mr.getLocation()
            + "\nOrder: "
            + mr.getOrder()
            + "\nNote: "
            + mr.getNote()
            + "\nRecipient: "
            + mr.getRecipient()
            + "\nDelivery DateTime: "
            + mr.getDateTime());
    return mr;
  }

  public LocalTime StringToTime(String s) {

    String[] hourMin = s.split(":", 2);
    LocalTime t = LocalTime.of(Integer.parseInt(hourMin[0]), Integer.parseInt(hourMin[1]));
    return t;
  }

  //
  //  public MealRequestForm storeMealValues() {
  //    String mealName = mealNameData.getText();
  //    String mealEmployeeID = mealEmployeeIDData.getText();
  //    String mealLocation = mealDeliveryLocationData.getText();
  //    String mealOrderer = mealPersonOrderingForData.getText();
  //    String mealNotes = mealNotesData.getText();
  //    String foodChoice = mealFoodChoice.getText();
  //
  //    System.out.println(
  //        "Answers: "
  //            + mealName
  //            + " "
  //            + mealEmployeeID
  //            + " "
  //            + mealLocation
  //            + " "
  //            + mealOrderer
  //            + " "
  //            + mealNotes
  //            + " "
  //            + foodChoice
  //            + " ");
  //
  //    MealRequestForm mealForm =
  //        new MealRequestForm(
  //            mealName, mealEmployeeID, mealLocation, mealOrderer, mealNotes, foodChoice);
  //    printMealForms(mealForm);
  //
  //    return mealForm;
  //  }
  //
  //  public void printMealForms(MealRequestForm form) {
  //
  //    System.out.println(
  //        form.mealName
  //            + " "
  //            + form.mealEmployeeID
  //            + " "
  //            + form.mealLocation
  //            + " "
  //            + form.mealOrderer
  //            + " "
  //            + form.mealNotes
  //            + " "
  //            + form.mealFoodChoice);
  //  }

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
