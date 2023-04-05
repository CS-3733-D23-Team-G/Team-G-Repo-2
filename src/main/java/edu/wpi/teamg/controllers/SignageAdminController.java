package edu.wpi.teamg.controllers;

import edu.wpi.teamg.DAOs.*;
import edu.wpi.teamg.Main;
import edu.wpi.teamg.navigation.Navigation;
import edu.wpi.teamg.navigation.Screen;
import edu.wpi.teamg.pathFinding.Edge;
import edu.wpi.teamg.pathFinding.Graph;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import net.kurobako.gesturefx.GesturePane;

public class SignageAdminController {

  @FXML MFXButton backToHomeButton;
  @FXML ChoiceBox<String> serviceRequestChoiceBox;
  @FXML MFXButton signagePageButton;
  @FXML MFXButton exitButton;
  @FXML MFXButton pathFindButton;
  // @FXML MFXButton importButton;

  // @FXML Label fileLabel;

  @FXML ChoiceBox<String> importDrop;
  @FXML ChoiceBox<String> exportDrop;

  @FXML MFXTextField startLoc;

  @FXML MFXTextField endLoc;

  @FXML MFXTextField results;

  @FXML GesturePane pane;

  ObservableList<String> list =
      FXCollections.observableArrayList(
          "Conference Room Request Form",
          "Flowers Request Form",
          "Furniture Request Form",
          "Meal Request Form",
          "Office Supplies Request Form");

  ObservableList<String> importList =
      FXCollections.observableArrayList("Nodes", "Edges", "LocationName", "Moves");

  ObservableList<String> exportList =
      FXCollections.observableArrayList("Nodes", "Edges", "LocationName", "Moves");

  @FXML
  public void initialize() {
    serviceRequestChoiceBox.setItems(list);
    importDrop.setItems(importList);
    exportDrop.setItems(exportList);
    signagePageButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE_PAGE));
    backToHomeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    exitButton.setOnMouseClicked(event -> exit());
    // importButton.setOnAction(event -> fileChooser());

    serviceRequestChoiceBox.setOnAction(event -> loadServiceRequestForm());
    importDrop.setOnAction(event -> fileChooser());
    exportDrop.setOnAction(
        event -> fileChooser()); // TODO change to an export method that pushes to downloads
    // fileLabel.getText();
    pathFindButton.setOnMouseClicked(
        event -> {
          try {
            processAStarAlg();
          } catch (SQLException e) {
            throw new RuntimeException(e);
          }
        });

    startLoc.getText();
    endLoc.getText();
    String imgPath = Main.class.getResource("images/00_thelowerlevel1.png").toString();
    ImageView image = new ImageView(new Image(imgPath));
    pane.setContent(image);
    // pane.setMaxScale();

    pane.setMinScale(.001);
    pane.zoomTo(.000001, new Point2D(2500, 1700));
    pane.zoomTo(.000001, new Point2D(2500, 1700));
  }

  public void loadServiceRequestForm() {
    switch(serviceRequestChoiceBox.getValue()){
      case "Meal Request Form":
        Navigation.navigate(Screen.MEAL_REQUEST);
        break;
      case "Furniture Request Form":
        Navigation.navigate(Screen.FURNITURE_REQUEST);
        break;
      case "Conference Room Request Form":
        Navigation.navigate(Screen.ROOM_REQUEST);
        break;
      case "Flowers Request Form":
        Navigation.navigate(Screen.FLOWERS_REQUEST);
        break;
      case "Office Supplies Request Form":
        Navigation.navigate(Screen.SUPPLIES_REQUEST);
        break;
      default:
        return;
    }
  }

  public void processAStarAlg() throws SQLException {
    ArrayList<String> path = new ArrayList<>();

    //    NodeDAO nodeDAO = new NodeDAO();

    //    List<Node> nodeList = nodeDAO.getAll();

    int startNode = Integer.parseInt(startLoc.getText());
    int endNode = Integer.parseInt(endLoc.getText());

    edu.wpi.teamg.pathFinding.Node[] N1 = new edu.wpi.teamg.pathFinding.Node[10];
    Random r = new Random(5591);
    for (int i = 0; i < 10; i++) {
      N1[i] =
          new edu.wpi.teamg.pathFinding.Node(
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
  /*ObservableList<String>  =
            FXCollections.observableArrayList(
                    "Conference Room Request Form",
                    "Flowers Request Form",
                    "Furniture Request Form",
                    "Meal Request Form",
                    "Office Supplies Request Form");
  */
  @FXML
  void fileChooser() {
    switch (importDrop.getValue()) {
      case "Nodes":
        NodeDAO nodeDAO = new NodeDAO();
        chooserHelper(nodeDAO);
        break;
      case "Edges":
        EdgeDAO edgeDAO = new EdgeDAO();
        chooserHelper(edgeDAO);
        break;
      case "LocationName":
        LocationNameDAO locationNameDAO = new LocationNameDAO();
        chooserHelper(locationNameDAO);
        break;
      case "Moves":
        MoveDAO moveDAO = new MoveDAO();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters()
            .add(new FileChooser.ExtensionFilter("Comma Seperated Values", "*.csv"));
        File f = fc.showOpenDialog(null);
        if (f != null) {
          try {
            moveDAO.importCSV(f.getAbsolutePath());
          } catch (SQLException e) {
            throw new RuntimeException(e);
          }
        }
        break;
      default:
        break;
    }
  }

  public void setPath(ArrayList<String> path) {
    results.setText(String.valueOf(path));
  }

  public void exit() {
    Platform.exit();
  }

  void chooserHelper(
      LocationDAO dao) { // note because of move dao's uniqueness, this won't use that
    FileChooser fc = new FileChooser();
    fc.getExtensionFilters()
        .add(new FileChooser.ExtensionFilter("Comma Seperated Values", "*.csv"));
    File f = fc.showOpenDialog(null);
    if (f != null) {
      // fileLabel.setText("Selected File::" + f.getAbsolutePath());
      try {
        dao.importCSV(f.getAbsolutePath());
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
