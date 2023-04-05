package edu.wpi.teamg.controllers;

import edu.wpi.teamg.DAOs.EdgeDAO;
import edu.wpi.teamg.DAOs.NodeDAO;
import edu.wpi.teamg.Main;
import edu.wpi.teamg.navigation.Navigation;
import edu.wpi.teamg.navigation.Screen;
import edu.wpi.teamg.pathFinding.Edge;
import edu.wpi.teamg.pathFinding.Graph;
import edu.wpi.teamg.pathFinding.Node;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.kurobako.gesturefx.GesturePane;

public class SignagePageController {

  @FXML MFXButton backToHomeButton;
  @FXML ChoiceBox<String> serviceRequestChoiceBox;
  @FXML MFXButton signagePageButton;
  @FXML MFXButton exitButton;
  @FXML MFXButton goToAdminSign;

  @FXML MFXButton pathFindButton;

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

  @FXML
  public void initialize() {
    serviceRequestChoiceBox.setItems(list);
    signagePageButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE_PAGE));
    backToHomeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    goToAdminSign.setOnMouseClicked(event -> Navigation.navigate(Screen.ADMIN_SIGNAGE_PAGE));
    exitButton.setOnMouseClicked(event -> exit());
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

    // ArrayList<edu.wpi.teamg.ORMClasses.Node> L1nodeKeys = new ArrayList<>();
    // ArrayList<edu.wpi.teamg.ORMClasses.Node> L1edgeKeys = new ArrayList<>();

    NodeDAO nodeDAO = new NodeDAO();
    EdgeDAO edgeDAO = new EdgeDAO();

    HashMap<Integer, edu.wpi.teamg.ORMClasses.Node> nodeMap = nodeDAO.getAll();
    HashMap<String, edu.wpi.teamg.ORMClasses.Edge> edgeMap = edgeDAO.getAll();

    ArrayList<edu.wpi.teamg.ORMClasses.Node> L1nodes = new ArrayList<>(nodeMap.values());
    ArrayList<edu.wpi.teamg.ORMClasses.Edge> L1edges = new ArrayList<>(edgeMap.values());

    ArrayList<edu.wpi.teamg.ORMClasses.Node> L1NodeFinal = new ArrayList<>();
    ArrayList<edu.wpi.teamg.ORMClasses.Edge> L1EdgeFinal = new ArrayList<>();

    // L1nodes = (ArrayList<edu.wpi.teamg.ORMClasses.Node>) nodeMap.values();

    for (int i = 0; i < L1nodes.size(); i++) {
      if (L1nodes.get(i).getFloor().equals("L1")) {
        L1NodeFinal.add(L1nodes.get(i));
      }
    }


    for (int i = 0; i < L1edges.size(); i++) {
      // For each edge
      // If the start and end node are both on floor 1
      // Add edge to final edge array
      // If only start and end node are on floor 1
      // print out "error"
      System.out.println(L1edges.get(i));

      if ((nodeMap.get(L1edges.get(i).getStartNode()).getFloor()).equals("L1")
          && (nodeMap.get(L1edges.get(i).getEndNode()).getFloor()).equals("L1")) {
        L1EdgeFinal.add(L1edges.get(i));
        System.out.println("Success");
      }
      if (!Objects.equals(nodeMap.get(L1edges.get(i).getStartNode()).getFloor(), "L1")
          && (nodeMap.get(L1edges.get(i).getEndNode()).getFloor()).equals("L1")) {
        System.out.println("ERROR1234");
      }
      if ((nodeMap.get(L1edges.get(i).getStartNode()).getFloor()).equals("L1")
          && !Objects.equals(nodeMap.get(L1edges.get(i).getEndNode()).getFloor(), "L1")) {
        System.out.println("ERROR1234");
      }
    }

    /*
    ArrayList<edu.wpi.teamname.ORMClass.Node> L1 = new ArrayList<>();


    System.out.println(nodeList.get(0).getNodeID());

    for (int i = 0; i < nodeList.size(); i++) {
      if (Objects.equals(nodeList.get(i).getFloor(), "L1")) {
        L1.add(nodeList.get(i));
      }
    }

    for (int i = 0; i < L1.size(); i++) {
      System.out.println(L1.get(i).getFloor());
    }

     */

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
    results.setText(String.valueOf(path));
  }

  public void exit() {
    Platform.exit();
  }
}
