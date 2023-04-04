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
  @FXML MFXDatePicker datePicker;
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
  // ObservableList<String> roomTimeDataList =
  //    FXCollections.observableArrayList(
  //        "12:00", "12:30", "1:00", "1:30");
  // ObservableList<String> roomNumberDataList =
  //    FXCollections.observableArrayList(
  //        "1", "2", "3", "4", "5", "6");

  @FXML
  public void initialize() {
    backToHomeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    signagePageButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE_PAGE));
    exitButton.setOnMouseClicked(event -> roomExit());
    roomConfirm.setOnMouseClicked(
        event -> {
          Navigation.navigate(Screen.ROOM_REQUEST_SUBMIT);
          storeRoomValues();
        });

    datePicker.setText("");

    roomEmployeeIDData.getText();
    roomMeetingPurposeData.getText();
    roomNumberData.getText();
    roomTimeData.getText();
    // roomNumberData.setValue("noon");
    // roomNumberData.setItems(roomNumberDataList);
    // roomTimeData.setValue("noon");
    // roomTimeData.setItems(roomTimeDataList);
    // roomTimeData.getValue();
    // roomNumberData.getValue();
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

  public void storeRoomValues() {
    ConferenceRoomRequest crr = new ConferenceRoomRequest();

    crr.setEmpid(Integer.parseInt(roomEmployeeIDData.getText()));
    crr.setServ_by(1);
    // assume for now they are going to input a node number, so parseInt
    crr.setLocation(Integer.parseInt(roomNumberData.getText()));
    crr.setPurpose(roomMeetingPurposeData.getText());
    crr.setMeeting_date(Date.valueOf(datePicker.getValue()));
    crr.setMeeting_time(StringToTime(roomTimeData.getText()));

    System.out.println(
        "Employee ID: "
            + crr.getEmpid()
            + "\nMeeting Location: "
            + crr.getLocation()
            + "\nPurpose: "
            + crr.getPurpose()
            //                    + "\nNote: "
            //                    + crr.getNote()
            //                    + "\nRecipient: "
            //                    + crr.getRecipient()
            + "\nMeeting Date: "
            + crr.getMeeting_date()
            + "\nMeeting Time: "
            + crr.getMeeting_time());

    //    MealRequestDAO mealRequestDAO = new MealRequestDAO();
    //    mealRequestDAO.insert(mr);
  }

  public void clearAllData() {
    roomEmployeeIDData.setText("");
    roomMeetingPurposeData.setText("");
    datePicker.setText("");
    roomTimeData.setText("");
    roomNumberData.setText("");
    return;
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
