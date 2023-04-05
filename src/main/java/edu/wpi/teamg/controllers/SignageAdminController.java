package edu.wpi.teamg.controllers;

import edu.wpi.teamg.DAOs.NodeDAO;
import edu.wpi.teamg.Main;
import edu.wpi.teamg.ORMClasses.LocationName;
import edu.wpi.teamg.ORMClasses.Move;
import edu.wpi.teamg.ORMClasses.Node;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
  @FXML Label fileLabel;
  @FXML MFXTextField startLoc;
  @FXML MFXTextField endLoc;
  @FXML MFXTextField results;
  @FXML GesturePane pane;

  @FXML MFXButton imp;
  // @FXML MFXButton export;

  @FXML MFXButton nodes;
  @FXML MFXButton edges;

  @FXML MFXButton nodeLoc;
  @FXML MFXButton move;

  @FXML TableView<Node> nodeTable;
  @FXML TableView<Edge> edgeTable;
  @FXML TableView<Move> moveTable;
  @FXML TableView<LocationName> nodeLocTable;

  // Nodes
  @FXML TableColumn<Node, Integer> nodeNodeID;
  @FXML TableColumn<Node, Integer> nodeXcoord;
  @FXML TableColumn<Node, Integer> nodeYcoord;
  @FXML TableColumn<Node, String> nodeFloor;
  @FXML TableColumn<Node, String> nodeBuilding;

  // Edges
  @FXML TableColumn<Edge, String> edgeEdgeID;
  @FXML TableColumn<Edge, Integer> edgeEndNode;
  @FXML TableColumn<Edge, Integer> edgeStartNode;

  // Move

  @FXML TableColumn<Move, String> moveNodeID;
  @FXML TableColumn<Move, Integer> moveDate;
  @FXML TableColumn<Move, Integer> moveLongName;

  // NodeLoc

  @FXML TableColumn<LocationName, String> locLongName;
  @FXML TableColumn<LocationName, Integer> locShortName;
  @FXML TableColumn<LocationName, Integer> locNodeType;

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
    imp.setOnAction(event -> fileChooser());
    fileLabel.getText();
    serviceRequestChoiceBox.setOnAction(event -> loadServiceRequestForm());
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


    move.setOnMouseClicked(event -> loadMoveTable());
    nodeLoc.setOnMouseClicked(event -> loadLocTable());

    nodeNodeID.setCellValueFactory(new PropertyValueFactory<>("Node ID"));
    nodeXcoord.setCellValueFactory(new PropertyValueFactory<>("X-Coord"));
    nodeYcoord.setCellValueFactory(new PropertyValueFactory<>("Y-Coord"));
    nodeFloor.setCellValueFactory(new PropertyValueFactory<>("Floor"));
    nodeBuilding.setCellValueFactory(new PropertyValueFactory<>("Building"));

    edgeEdgeID.setCellValueFactory(new PropertyValueFactory<>("Edge ID"));
    edgeStartNode.setCellValueFactory(new PropertyValueFactory<>("Start Loc"));
    edgeEndNode.setCellValueFactory(new PropertyValueFactory<>("End Loc"));

    moveNodeID.setCellValueFactory(new PropertyValueFactory<>("Node ID"));
    moveDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
    moveLongName.setCellValueFactory(new PropertyValueFactory<>("Long-Name"));

    locLongName.setCellValueFactory(new PropertyValueFactory<>("Long-Name"));
    locShortName.setCellValueFactory(new PropertyValueFactory<>("Short-Name"));
    locNodeType.setCellValueFactory(new PropertyValueFactory<>("Type"));
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

  @FXML
  void fileChooser() {
    FileChooser fc = new FileChooser();

    NodeDAO nodeDAO = new NodeDAO();

    fc.getExtensionFilters()
        .add(new FileChooser.ExtensionFilter("Comma Separated Values", "*.csv"));
    File f = fc.showOpenDialog(null);

    if (f != null) {
      fileLabel.setText("Selected File::" + f.getAbsolutePath());
      try {
        nodeDAO.importCSV(f.getAbsolutePath());
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public void loadNodeTable() {
    nodeTable.setVisible(true);
    edgeTable.setVisible(false);
    moveTable.setVisible(false);
    nodeLocTable.setVisible(false);
  }

  public void loadEdgeTable() {
    nodeTable.setVisible(false);
    edgeTable.setVisible(true);
    moveTable.setVisible(false);
    nodeLocTable.setVisible(false);
  }

  public void loadMoveTable() {
    nodeTable.setVisible(false);
    edgeTable.setVisible(false);
    moveTable.setVisible(true);
    nodeLocTable.setVisible(false);
  }

  public void loadLocTable() {
    nodeTable.setVisible(false);
    edgeTable.setVisible(false);
    moveTable.setVisible(false);
    nodeLocTable.setVisible(true);
  }

  public void setPath(ArrayList<String> path) {
    results.setText(String.valueOf(path));
  }

  public void exit() {
    Platform.exit();
  }
}
