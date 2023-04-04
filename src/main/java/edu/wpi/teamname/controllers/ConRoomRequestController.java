package edu.wpi.teamname.controllers;

import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ConRoomRequestController {

  // buttons
  @FXML MFXButton backToHomeButton;
  @FXML MFXButton exitButton;
  @FXML MFXButton signagePageButton;
  @FXML MFXButton roomConfirm;
  @FXML MFXButton roomClearAll;

  // Text Fields
  @FXML TextField roomEmployeeIDData;
  @FXML TextField roomMeetingPurposeData;
  @FXML DatePicker roomDateData;
  @FXML ChoiceBox<String> roomTimeData;
  @FXML ChoiceBox<String> roomNumberData;

  @FXML ChoiceBox<String> serviceRequestChoiceBox;

  ObservableList<String> list =
      FXCollections.observableArrayList(
          "Conference Room Request Form",
          "Flowers Request Form",
          "Furniture Request Form",
          "Meal Request Form",
          "Office Supplies Request Form");
  ObservableList<String> roomTimeDataList =
      FXCollections.observableArrayList(
          "noon", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
  ObservableList<String> roomNumberDataList =
      FXCollections.observableArrayList(
          "noon", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

  @FXML
  public void initialize() {
    backToHomeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    signagePageButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE_PAGE));
    exitButton.setOnMouseClicked(event -> roomExit());
    roomConfirm.setOnMouseClicked(event -> Navigation.navigate(Screen.ROOM_REQUEST_SUBMIT));

    roomEmployeeIDData.getText();
    roomMeetingPurposeData.getText();
    roomNumberData.setValue("noon");
    roomNumberData.setItems(roomNumberDataList);
    roomTimeData.setValue("noon");
    roomTimeData.setItems(roomTimeDataList);
    roomTimeData.getValue();
    roomNumberData.getValue();
    serviceRequestChoiceBox.setItems(list);
    serviceRequestChoiceBox.setOnAction(event -> loadServiceRequestForm());
    roomClearAll.setOnAction(event -> clearAllData());
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

  public void clearAllData() {
    roomEmployeeIDData.setText("");
    roomMeetingPurposeData.setText("");
    roomDateData.setText("");
    roomTimeData.setText("");
    roomNumberData.setText("");
    return;
  }

  public void roomExit() {
    Platform.exit();
  }
  /*
  public ConRoomRequestForm storeConRoomValues() {
    //    String employeeName = roomEmployeeNameData.getText();
    String employeeID = roomEmployeeIDData.getText();
    String meetingPurpose = roomMeetingPurposeData.getText();
    String scheduledDate = roomDateData.getValue().toString();
    String scheduledTime = roomTimeData.getValue();
    String scheduledRoomNumber = roomNumberData.getValue();

    System.out.println(
        "Answers: "
            //            + employeeName
            //            + " "
            + employeeID
            + " "
            + meetingPurpose
            + " "
            + scheduledDate
            + " "
            + scheduledTime
            + " "
            + scheduledRoomNumber
            + " ");

    ConRoomRequestForm form =
        new ConRoomRequestForm(
            "employeeName",
            employeeID,
            meetingPurpose,
            scheduledDate,
            scheduledTime,
            scheduledRoomNumber);

    printConRoomForms(form);

    return form;
  }

  public void printConRoomForms(ConRoomRequestForm form) {

    System.out.println(
        "form.employeeName"
            + " "
            + form.employeeID
            + " "
            + form.meetingPurpose
            + " "
            + form.roomDate
            + " "
            + form.roomTime
            + " "
            + form.roomNumber);
  }
  */
}
