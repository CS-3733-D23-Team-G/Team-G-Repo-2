package edu.wpi.teamname.controllers;

import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import edu.wpi.teamname.pathFinding.Edge;
import edu.wpi.teamname.pathFinding.Graph;
import edu.wpi.teamname.pathFinding.Node;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.util.ArrayList;
import java.util.Random;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class SignagePageController {

  @FXML MFXButton backToHomeButton;
  @FXML ChoiceBox<String> serviceRequestChoiceBox;
  @FXML MFXButton signagePageButton;
  @FXML MFXButton exitButton;
  @FXML MFXButton pathFindButton;

  @FXML MFXTextField startLoc;

  @FXML MFXTextField endLoc;

  @FXML MFXTextField results;

  ObservableList<String> list =
      FXCollections.observableArrayList(
          "Conference Room Request Form",
          "Flowers Request Form",
          "Furniture Request Form",
          "Meal Request Form",
          "Office Supplies Request Form");

  @FXML
  public void initialize() {
    serviceRequestChoiceBox.setItems(list);
    signagePageButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE_PAGE));
    backToHomeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    exitButton.setOnMouseClicked(event -> exit());
    serviceRequestChoiceBox.setOnAction(event -> loadServiceRequestForm());
    pathFindButton.setOnMouseClicked(event -> processAStarAlg());

    startLoc.getText();
    endLoc.getText();
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

  public void processAStarAlg() {
    ArrayList<String> path = new ArrayList<>();

    int startNode = Integer.parseInt(startLoc.getText());
    int endNode = Integer.parseInt(endLoc.getText());

    Node[] N1 = new Node[10];
    Random r = new Random(5591);
    for (int i = 0; i < 10; i++) {
      N1[i] =
          new Node(
              String.valueOf(i),
              (int) r.nextInt(100) + i,
              (int) r.nextInt(100) + i,
              "L1",
              "fsadfasd",
              "dsfajd;",
              "jk;ldsjf",
              "dsfaj;sldk");
    }
    Edge[] E1 = new Edge[10];
    E1[0] = new Edge("e1", N1[0], N1[1]);
    E1[1] = new Edge("e2", N1[1], N1[2]);
    E1[2] = new Edge("e3", N1[1], N1[3]);
    E1[3] = new Edge("e4", N1[2], N1[4]);
    E1[4] = new Edge("e5", N1[3], N1[4]);
    E1[5] = new Edge("e6", N1[4], N1[5]);
    E1[6] = new Edge("e7", N1[5], N1[6]);
    E1[7] = new Edge("e8", N1[6], N1[7]);
    E1[8] = new Edge("e9", N1[5], N1[8]);
    E1[9] = new Edge("e10", N1[8], N1[9]);
    Graph G1 = new Graph(N1, E1);
    int[][] Adj = G1.createWeightedAdj();
    // new int[10][10];

    path = G1.aStarAlg(G1.createWeightedAdj(), startNode, endNode);

    setPath(path);
  }

  public void setPath(ArrayList<String> path) {

    /*
    for (int i = 0; i < path.size(); i++) {
      results.setText(path.get(i) + " ");
    }

     */

    results.setText(String.valueOf(path));
  }

  public void exit() {
    Platform.exit();
  }
}