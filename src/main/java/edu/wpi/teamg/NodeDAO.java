package edu.wpi.teamg;

import edu.wpi.teamg.ORMClasses.Node;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NodeDAO implements LocationDAO {

  private Connection connection;
  private String SQL;

  @Override
  public void setConnection() {
    try {
      connection =
          DriverManager.getConnection(
              "jdbc:postgresql://database.cs.wpi.edu:5432/teamgdb", "teamg", "teamg70");
      System.out.print(" Successfully connect to the database!\n");
    } catch (SQLException e) {
      //      e.printStackTrace();
      System.err.println("SQL exception");
      // printSQLException(e);
    }
  }

  @Override
  public void closeConnection() {
    try {
      connection.close();
    } catch (SQLException e) {

      System.err.println("SQL exception");
      // printSQLException(e);
    }
  }

  @Override
  public void importCSV() {}

  @Override
  public void exportCSV() {}

  @Override
  public void insert(Object obj) throws SQLException {}

  @Override
  public void update(Object obj) throws SQLException {}

  @Override
  public void delete(Object obj) throws SQLException {}

  @Override
  public List<Node> getAll() throws SQLException {

    PreparedStatement ps;
    ResultSet rs = null;

    SQL = "select * from proto2.node";

    try {
      ps = connection.prepareStatement(SQL);
      rs = ps.executeQuery();
    } catch (SQLException e) {
      System.err.println("SQL exception");
      // printSQLException(e);
    }

    List<Node> allNodes = new ArrayList<Node>();

    while (rs.next()) {
      Node node = new Node();

      int node_id = rs.getInt("nodeid");
      node.setNodeID(node_id);

      int xcoord = rs.getInt("xcoord");
      node.setXcoord(xcoord);

      int ycoord = rs.getInt("ycoord");
      node.setXcoord(ycoord);

      String floor = rs.getString("floor");
      node.setFloor(floor);

      String building = rs.getString("building");
      node.setBuilding(building);

      allNodes.add(node);
    }

    return allNodes;
  }
}
