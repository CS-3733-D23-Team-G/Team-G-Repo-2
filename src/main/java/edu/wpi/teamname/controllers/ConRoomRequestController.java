package edu.wpi.teamname.controllers;

import edu.wpi.teamname.ORMClasses.ConferenceRoomRequest;
import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.sql.Date;
import java.sql.Time;
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

  @FXML MFXTextField roomEmployeeIDData;
  @FXML MFXTextField roomMeetingPurposeData;
  // @FXML MFXTextField roomDateData;
  @FXML MFXTextField roomTimeData;
  @FXML MFXTextField roomNumberData;
  @FXML MFXDatePicker roomDatePicker;
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
    roomConfirm.setOnMouseClicked(
        event -> {
          storeRoomValues();
          Navigation.navigate(Screen.ROOM_REQUEST_SUBMIT);
        });
    roomEmployeeIDData.getText();
    roomMeetingPurposeData.getText();
    // roomDateData.getText();
    roomTimeData.getText();
    roomNumberData.getText();
    serviceRequestChoiceBox.setItems(list);
    serviceRequestChoiceBox.setOnAction(event -> loadServiceRequestForm());

    // ConferenceRoomRequestDAO conferenceRoomRequestDAO = new ConferenceRoomRequestDAO();
    // conferenceRoomRequestDAO.insert(crr);
  }

  public void storeRoomValues() {
    ConferenceRoomRequest crr = new ConferenceRoomRequest();
    crr.setEmpid(Integer.parseInt(roomEmployeeIDData.getText()));
    crr.setServ_by(1);
    crr.setLocation(Integer.parseInt(roomNumberData.getText()));
    crr.setPurpose(roomMeetingPurposeData.getText());
    crr.setMeeting_date(Date.valueOf(roomDatePicker.getValue()));
    crr.setMeeting_time(StringToTime(roomTimeData.getText()));

    System.out.println(
        "Employee ID: "
            + crr.getEmpid()
            + "\nDelivery Location: "
            + crr.getLocation()
            + "\nMeeting Time: "
            + crr.getMeeting_time()
            + "\nMeeting Date: "
            + crr.getMeeting_date()
            + "\nMeeting Purpose: "
            + crr.getPurpose());
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

  public Time StringToTime(String s) {

    String[] hourMin = s.split(":", 2);
    Time t = new Time(Integer.parseInt(hourMin[0]), Integer.parseInt(hourMin[1]), 00);
    return t;
  }

  public void roomExit() {
    Platform.exit();
  }
}
