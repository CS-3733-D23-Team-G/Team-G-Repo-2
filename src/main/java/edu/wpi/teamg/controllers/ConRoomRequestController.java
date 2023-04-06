package edu.wpi.teamg.controllers;

import edu.wpi.teamg.navigation.Navigation;
import edu.wpi.teamg.navigation.Screen;
import edu.wpi.teamname.requestforms.ConRoomRequestForm;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;

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
