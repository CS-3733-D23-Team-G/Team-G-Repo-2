package edu.wpi.teamg.controllers;

import edu.wpi.teamg.DAOs.MealRequestDAO;
import edu.wpi.teamg.ORMClasses.ConferenceRoomRequest;
import edu.wpi.teamg.ORMClasses.MealRequest;
import edu.wpi.teamg.navigation.Navigation;
import edu.wpi.teamg.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.HashMap;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FormStatusController {

  // Heading
  @FXML MFXButton backToHomeButton;
  @FXML ChoiceBox<String> serviceRequestChoiceBox;
  @FXML MFXButton signagePageButton;
  @FXML MFXButton exitButton;

  // Tables
  @FXML TableView<MealRequest> mainTable;
  @FXML TableView<MealRequest> mealTable;
  @FXML TableView<ConferenceRoomRequest> roomTable;

  // Main Table
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

  //  @FXML TableColumn<ConferenceRoomRequest, Date> mainRoomDate;
  //  @FXML TableColumn<ConferenceRoomRequest, Time> mainRoomTime;
  //  @FXML TableColumn<ConferenceRoomRequest, String> mainRoomPurpose;

  // Meal Table
  @FXML TableColumn<MealRequest, Integer> mealEmpID;
  @FXML TableColumn<MealRequest, Integer> mealLocation1;
  @FXML TableColumn<MealRequest, Integer> mealReqID;
  @FXML TableColumn<MealRequest, Integer> mealServeBy;
  @FXML TableColumn<MealRequest, String> mealStatus;
  @FXML TableColumn<MealRequest, String> mealRecipient;
  @FXML TableColumn<MealRequest, String> mealOrder;
  @FXML TableColumn<MealRequest, String> mealNote;
  @FXML TableColumn<MealRequest, Date> mealDate;
  @FXML TableColumn<MealRequest, Time> mealTime;

  // room Table
  @FXML TableColumn<ConferenceRoomRequest, Integer> roomEmpID;
  @FXML TableColumn<ConferenceRoomRequest, Integer> roomLocation1;
  @FXML TableColumn<ConferenceRoomRequest, Integer> roomReqID;
  @FXML TableColumn<ConferenceRoomRequest, Integer> roomServeBy;
  @FXML TableColumn<ConferenceRoomRequest, String> roomStatus;
  @FXML TableColumn<ConferenceRoomRequest, Date> roomDate;
  @FXML TableColumn<ConferenceRoomRequest, Time> roomTime;
  @FXML TableColumn<ConferenceRoomRequest, String> roomPurpose;

  @FXML Button allRequestTableButton;
  @FXML Button mealTableButton;
  @FXML Button roomTableButton;

  ObservableList<String> list =
      FXCollections.observableArrayList(
          "Conference Room Request Form",
          "Flowers Request Form",
          "Furniture Request Form",
          "Meal Request Form",
          "Office Supplies Request Form");

  ObservableList<MealRequest> testList;
  ObservableList<MealRequest> testMealList;
  ObservableList<ConferenceRoomRequest> testRoomList;

  MealRequestDAO mealRequests = new MealRequestDAO();

  @FXML
  public void initialize() throws SQLException {
    serviceRequestChoiceBox.setItems(list);
    signagePageButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE_PAGE));
    backToHomeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    exitButton.setOnMouseClicked(event -> exit());
    serviceRequestChoiceBox.setOnAction(event -> loadServiceRequestForm());

    allRequestTableButton.setOnMouseClicked(event -> loadAllRequestTable());
    mealTableButton.setOnMouseClicked(event -> loadMealTable());
    roomTableButton.setOnMouseClicked(event -> loadRoomTable());

    HashMap<Integer, MealRequest> testingMealHash = this.getHashMapMeal();
    testingMealHash.forEach(
        (i, m) -> {
          System.out.println("Request ID:" + m.getReqid());
          System.out.println("Employee ID:" + m.getEmpid());
          System.out.println("Delivery date:" + m.getDeliveryDate());
          System.out.println("Delivery time:" + m.getDeliveryTime());
          System.out.println("note:" + m.getNote());
          System.out.println("meal:" + m.getEmpid());
          System.out.println();
        });

    MealRequest testItem1 = new MealRequest();
    testItem1.setEmpid(2);
    testItem1.setReqid(1);
    testItem1.setLocation(3);
    testItem1.setServ_by(2);
    testItem1.setStatus("Status");
    testItem1.setRecipient("Andrew");
    testItem1.setOrder("Food");
    testItem1.setNote("Please Let This Work");
    testItem1.setDeliveryDate(new Date(3, 2, 1));
    testItem1.setDeliveryTime(new Time(1, 2, 3));

    MealRequest testItem2 = new MealRequest();
    testItem2.setEmpid(2);
    testItem2.setReqid(1);
    testItem2.setLocation(3);
    testItem2.setServ_by(2);
    testItem2.setStatus("Status");
    testItem2.setRecipient("Andrew");
    testItem2.setOrder("Food");
    testItem2.setNote("Please Let This Work");

    testItem2.setDeliveryDate(new Date(3, 2, 1));
    testItem2.setDeliveryTime(new Time(1, 2, 3));

    ConferenceRoomRequest testItem3 = new ConferenceRoomRequest();
    testItem3.setEmpid(2);
    testItem3.setReqid(1);
    testItem3.setLocation(3);
    testItem3.setServ_by(2);
    testItem3.setStatus("Status");
    testItem3.setMeeting_date(new Date(3, 2, 1));
    testItem3.setMeeting_time(new Time(1, 2, 3));
    testItem3.setPurpose("To Work Please");

    testList = FXCollections.observableArrayList(testItem1, testItem1);
    testMealList = FXCollections.observableArrayList(testItem2);
    testRoomList = FXCollections.observableArrayList(testItem3);
    mainTable.setItems(testList);
    mealTable.setItems(testMealList);
    roomTable.setItems(testRoomList);

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

    mealReqID.setCellValueFactory(new PropertyValueFactory<>("reqid"));
    mealEmpID.setCellValueFactory(new PropertyValueFactory<>("empid"));
    mealLocation1.setCellValueFactory(new PropertyValueFactory<>("location"));
    mealServeBy.setCellValueFactory(new PropertyValueFactory<>("serv_by"));
    mealStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    mealRecipient.setCellValueFactory(new PropertyValueFactory<>("recipient"));
    mealOrder.setCellValueFactory(new PropertyValueFactory<>("order"));
    mealNote.setCellValueFactory(new PropertyValueFactory<>("note"));
    mealDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
    mealTime.setCellValueFactory(new PropertyValueFactory<>("deliveryTime"));

    roomReqID.setCellValueFactory(new PropertyValueFactory<>("reqid"));
    roomEmpID.setCellValueFactory(new PropertyValueFactory<>("empid"));
    roomLocation1.setCellValueFactory(new PropertyValueFactory<>("location"));
    roomServeBy.setCellValueFactory(new PropertyValueFactory<>("serv_by"));
    roomStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    roomDate.setCellValueFactory(new PropertyValueFactory<>("meeting_date"));
    roomTime.setCellValueFactory(new PropertyValueFactory<>("meeting_time"));
    roomPurpose.setCellValueFactory(new PropertyValueFactory<>("purpose"));
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

  public HashMap getHashMapMeal() throws SQLException {

    HashMap<Integer, MealRequest> mealRequestHashMap = new HashMap<Integer, MealRequest>();

    try {
      mealRequestHashMap = mealRequests.getAll();
    } catch (SQLException e) {
      System.err.print(e.getErrorCode());
    }

    return mealRequestHashMap;
  }

  public void loadAllRequestTable() {
    mainTable.setVisible(true);
    mealTable.setVisible(false);
    roomTable.setVisible(false);
  }

  public void loadMealTable() {
    mealTable.setVisible(true);
    mainTable.setVisible(false);
    roomTable.setVisible(false);
  }

  public void loadRoomTable() {
    roomTable.setVisible(true);
    mainTable.setVisible(false);
    mealTable.setVisible(false);
  }

  public void exit() {
    Platform.exit();
  }
}
