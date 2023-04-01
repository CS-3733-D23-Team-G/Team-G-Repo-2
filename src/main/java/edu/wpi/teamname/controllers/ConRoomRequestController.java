package edu.wpi.teamname.controllers;

import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import edu.wpi.teamname.requestforms.ConRoomRequestForm;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class ConRoomRequestController {

  // buttons
  @FXML MFXButton backToHomeButton;
  @FXML MFXButton exitButton;
  @FXML MFXButton signagePageButton;
  @FXML MFXButton roomConfirm;
  @FXML MFXButton roomClearAll;

  // Text Fields
  @FXML MFXTextField roomEmployeeNameData;
  @FXML MFXTextField roomEmployeeIDData;
  @FXML MFXTextField roomMeetingPurposeData;
  @FXML MFXTextField roomDateData;
  @FXML MFXTextField roomTimeData;
  @FXML MFXTextField roomNumberData;
  @FXML ChoiceBox<String> serviceRequestChoiceBox;

  ObservableList<String> list =
      FXCollections.observableArrayList(
          "Conference Room Request Form",
          "Flowers Request Form",
          "Furniture Request Form",
          "Meal Request Form",
          "Office Supplies Request Form");

  @FXML
  public void initialize() {
    backToHomeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    signagePageButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE_PAGE));
    exitButton.setOnMouseClicked(event -> roomExit());
    roomClearAll.setOnMouseClicked(event -> Navigation.navigate(Screen.ROOM_REQUEST));
    roomConfirm.setOnMouseClicked(event -> Navigation.navigate(Screen.ROOM_REQUEST_SUBMIT));
    roomEmployeeNameData.getText();
    roomEmployeeIDData.getText();
    roomMeetingPurposeData.getText();
    roomDateData.getText();
    roomTimeData.getText();
    roomNumberData.getText();
    serviceRequestChoiceBox.setItems(list);
    serviceRequestChoiceBox.setOnAction(event -> loadServiceRequestForm());
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

  public void roomExit() {
    Platform.exit();
  }

  public ConRoomRequestForm storeConRoomValues() {
    String employeeName = roomEmployeeNameData.getText();
    String employeeID = roomEmployeeIDData.getText();
    String meetingPurpose = roomMeetingPurposeData.getText();
    String scheduledDate = roomDateData.getText();
    String scheduledTime = roomTimeData.getText();
    String scheduledRoomNumber = roomNumberData.getText();

    System.out.println(
        "Answers: "
            + employeeName
            + " "
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
            employeeName,
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
        form.employeeName
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
}
