package edu.wpi.teamg.controllers;

import edu.wpi.teamg.navigation.Navigation;
import edu.wpi.teamg.navigation.Screen;
import edu.wpi.teamg.requestforms.ConferenceRoomRequestForm;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class RoomServiceRequestController {

  // buttons
  @FXML MFXButton roomHomeButton;
  @FXML MFXButton roomFoodDeliverButton;
  @FXML MFXButton roomExitButton;
  @FXML MFXButton roomSignageButton;
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
    roomHomeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    roomFoodDeliverButton.setOnMouseClicked(event -> Navigation.navigate(Screen.MEAL_REQUEST));
    roomSignageButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE_PAGE));
    roomExitButton.setOnMouseClicked(event -> roomExit());
    roomClearAll.setOnMouseClicked(event -> Navigation.navigate(Screen.ROOM_REQUEST));
    roomConfirm.setOnMouseClicked(event -> Navigation.navigate(Screen.ROOM_CONFIRM_PAGE));
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

  public ConferenceRoomRequestForm storeConRoomValues() {
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

    ConferenceRoomRequestForm conRoomForm =
        new ConferenceRoomRequestForm(
            employeeName,
            employeeID,
            meetingPurpose,
            scheduledDate,
            scheduledTime,
            scheduledRoomNumber);

    printConRoomForms(conRoomForm);

    return conRoomForm;
  }

  public void printConRoomForms(ConferenceRoomRequestForm form) {

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
