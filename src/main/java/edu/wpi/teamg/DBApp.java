package edu.wpi.teamg;

import edu.wpi.teamg.DAOs.*;
import edu.wpi.teamg.ORMClasses.Edge;
import java.sql.*;
import java.sql.SQLException;
import java.util.List;

public class DBApp {
  public static void main(String[] args) throws SQLException {

    EdgeDAO edgedao = new EdgeDAO();
    List<Edge> allEdges = edgedao.getAll();
    for (Edge edge : allEdges) {
      System.out.println(
          "id="
              + edge.getEdgeID()
              + ", Start node="
              + edge.getStartNode()
              + ", End node="
              + edge.getEndNode());
    }
  }
}
