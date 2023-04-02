package edu.wpi.teamg.DAOS;

import edu.wpi.teamg.DAOs.LocationNameDAO;
import edu.wpi.teamg.DBConnection;
import edu.wpi.teamg.ORMClasses.Node;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NodeDAO implements LocationNameDAO {

  static DBConnection db = new DBConnection();
  private String SQL;

  @Override
  public void Import(File file) {}

  @Override
  public File Export() {}

  @Override
  public void insert(Object obj) throws SQLException {}

  @Override
  public void update(Object obj) throws SQLException {}

  @Override
  public void delete(Object obj) throws SQLException {}

  @Override
  public List<Node> getAll() throws SQLException {

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

    db.closeConnection();

    return allNodes;
  }
}
