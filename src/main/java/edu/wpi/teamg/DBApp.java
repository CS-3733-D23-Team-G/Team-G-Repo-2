package edu.wpi.teamg;

import edu.wpi.teamg.DAOs.NodeDAO;
import edu.wpi.teamg.ORMClasses.Node;
import java.sql.*;
import java.sql.SQLException;
import java.util.List;

public class DBApp {
  public static void main(String[] args) throws SQLException {

    NodeDAO nodedao = new NodeDAO();

    List<Node> allNodes = nodedao.getAll();

    for (Node node : allNodes) {
      System.out.println("id = " + node.getNodeID());
    }
  }
}
