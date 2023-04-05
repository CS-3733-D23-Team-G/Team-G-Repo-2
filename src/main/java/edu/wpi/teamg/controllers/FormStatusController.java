package edu.wpi.teamg.controllers;

import edu.wpi.teamg.ORMClasses.MealRequest;
import edu.wpi.teamg.ORMClasses.StatusTypeEnum;
import edu.wpi.teamg.navigation.Navigation;
import edu.wpi.teamg.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FormStatusController {

  @FXML MFXButton backToHomeButton;
  @FXML ChoiceBox<String> serviceRequestChoiceBox;
  @FXML MFXButton signagePageButton;
  @FXML MFXButton exitButton;

  @FXML TableView<MealRequest> mainTable;
  @FXML TableColumn<MealRequest, Integer> empID;
  @FXML TableColumn<MealRequest, Integer> location1;
  @FXML TableColumn<MealRequest, Integer> reqID;
  @FXML TableColumn<MealRequest, Integer> serveBy;
  @FXML TableColumn<MealRequest, String> status;
  @FXML TableColumn<MealRequest, String> recipient;
  @FXML TableColumn<MealRequest, String> order;
  @FXML TableColumn<MealRequest, String> note;
  @FXML TableColumn<MealRequest, Date> date;
  @FXML TableColumn<MealRequest, Time> time;

  ObservableList<String> list =
      FXCollections.observableArrayList(
          "Conference Room Request Form",
          "Flowers Request Form",
          "Furniture Request Form",
          "Meal Request Form",
          "Office Supplies Request Form");

  ObservableList<MealRequest> testList;

  public FormStatusController() throws ParseException {}

  @FXML
  public void initialize() {
    serviceRequestChoiceBox.setItems(list);
    signagePageButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE_PAGE));
    backToHomeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    exitButton.setOnMouseClicked(event -> exit());
    serviceRequestChoiceBox.setOnAction(event -> loadServiceRequestForm());

    MealRequest testItem1 = new MealRequest();
    testItem1.setEmpid(2);
    testItem1.setReqid(1);
    testItem1.setLocation(3);
    testItem1.setServ_by(2);
    testItem1.setStatus(StatusTypeEnum.blank);
    testItem1.setRecipient("Andrew");
    testItem1.setOrder("Food");
    testItem1.setNote("Please Let This Work");

    testItem1.setDeliveryDate(new Date(3, 2, 1));
    testItem1.setDeliveryTime(new Time(1, 2, 3));
    testList = FXCollections.observableArrayList(testItem1);
    mainTable.setItems(testList);
    reqID.setCellValueFactory(new PropertyValueFactory<>("reqid"));
    empID.setCellValueFactory(new PropertyValueFactory<>("empid"));
    location1.setCellValueFactory(new PropertyValueFactory<>("location"));
    serveBy.setCellValueFactory(new PropertyValueFactory<>("serv_by"));
    status.setCellValueFactory(new PropertyValueFactory<>("status"));
    recipient.setCellValueFactory(new PropertyValueFactory<>("recipient"));
    order.setCellValueFactory(new PropertyValueFactory<>("order"));
    note.setCellValueFactory(new PropertyValueFactory<>("note"));
    date.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
    time.setCellValueFactory(new PropertyValueFactory<>("deliveryTime"));
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

  public void exit() {
    Platform.exit();
  }
}
