package edu.wpi.teamg.DAOs;

import edu.wpi.teamg.DBConnection;
import edu.wpi.teamg.ORMClasses.Edge;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EdgeDAO implements LocationDAO {
  static DBConnection connection = new DBConnection();
  private String sql;

  @Override
  public List<Edge> getAll() throws SQLException {
    connection.setConnection();
    sql = "";
    PreparedStatement ps;
    ResultSet rs = null;
    sql = "Select * from teamgdb.proto2.edge";

    try {
      ps = connection.getConnection().prepareStatement(sql);
      rs = ps.executeQuery();
    } catch (SQLException e) {
      System.err.println("SQL error exception");
    }
    List<Edge> allEdges = new ArrayList<Edge>();

    while (rs.next()) {
      Edge edge = new Edge();

      int startNode = rs.getInt("startnode");
      edge.setStartNode(startNode);

      int endNode = rs.getInt("endnode");
      edge.setEndNode(endNode);

      allEdges.add(edge);
    }
    connection.closeConnection();
    return allEdges;
  }

  @Override
  public void update(Object obj) throws SQLException {}

  @Override
  public void insert(Object obj) throws SQLException {
    connection.setConnection();
    sql = "";
    sql = "INSERT INTO teamgdb.proto2.edge (startnode, endnode) VALUES (?,?)";
    PreparedStatement ps;
    try {
      ps = connection.getConnection().prepareStatement(sql);
      ps.setInt(1, ((Edge) obj).getStartNode());
      ps.setInt(2, ((Edge) obj).getEndNode());
      ps.executeUpdate();

    } catch (SQLException e) {
      System.err.println("SQL Exception");
      e.printStackTrace();
    }
    connection.closeConnection();
  }

  @Override
  public void delete(Object obj) throws SQLException {
    connection.setConnection();
    sql = "";
    sql = "DELETE FROM teamgdb.proto2.edge WHERE startnode = ? AND endnode = ?";
    PreparedStatement ps = connection.getConnection().prepareStatement(sql);
    try {
      ps.setInt(1, ((Edge) obj).getStartNode());
      ps.setInt(2, ((Edge) obj).getEndNode());
      ps.executeUpdate();
    } catch (SQLException e) {
      System.err.println("SQL exception");
      e.printStackTrace();
    }
    connection.closeConnection();
  }

  @Override
  public void importCSV() throws SQLException {}

  @Override
  public void exportCSV() throws SQLException {}
}
