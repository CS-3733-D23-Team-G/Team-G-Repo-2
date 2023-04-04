package edu.wpi.teamg.DAOs;

import edu.wpi.teamg.DBConnection;
import edu.wpi.teamg.ORMClasses.Node;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class NodeDAO implements LocationDAO {

  static DBConnection db = new DBConnection();
  private String SQL;
  private HashMap<Integer, Node> Nodes = new HashMap<>();

  @Override
  public void Import(String filename) throws SQLException, IOException {}

  @Override
  public File Export() {
    return null;
  }

  @Override
  public void insert(Object obj) throws SQLException {}

  @Override
  public void delete(Object obj) throws SQLException {}

  @Override
  public HashMap<Integer, Node> getAll() throws SQLException {

    db.setConnection();

    PreparedStatement ps;
    ResultSet rs = null;

    SQL = "select * from proto2.node";

    try {
      ps = db.getConnection().prepareStatement(SQL);
      rs = ps.executeQuery();
    } catch (SQLException e) {
      System.err.println("SQL exception");
      // printSQLException(e);
    }

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

      Nodes.put(node.getNodeID(), node);
    }
    db.closeConnection();
    return Nodes;
  }

  @Override
  public void update(Object old, Object update) throws SQLException {}
}
