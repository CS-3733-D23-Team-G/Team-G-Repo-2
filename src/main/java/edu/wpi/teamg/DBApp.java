package edu.wpi.teamg;

import edu.wpi.teamg.ORMClasses.Node;
import java.sql.*;
import java.sql.SQLException;
import java.util.List;
import edu.wpi.teamg.DAOs.*;
import edu.wpi.teamg.NodeDAO;
public class DBApp {
  public static void main(String[] args) throws SQLException {

    NodeDAO nodedao = new NodeDAO();

    List<Node> allNodes = nodedao.getAll();

    for (Node node : allNodes) {
      System.out.println("id = " + node.getNodeID());
    }
  }
}
