package edu.wpi.teamg.DAOs;

import edu.wpi.teamg.DBConnection;
import edu.wpi.teamg.ORMClasses.Edge;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class EdgeDAO implements LocationDAO {
  static DBConnection connection = new DBConnection();
  private String sql;
  private HashMap<String, Edge> edgeHash = new HashMap<String, Edge>();

  @Override
  public HashMap<String, Edge> getAll() throws SQLException {
    connection.setConnection();
    PreparedStatement ps;
    ResultSet rs = null;
    sql = "Select * from teamgdb.proto2.edge";

    try {
      ps = connection.getConnection().prepareStatement(sql);
      rs = ps.executeQuery();
    } catch (SQLException e) {
      System.err.println("SQL error exception");
    }

    while (rs.next()) {
      Edge edge = new Edge();

      int startNode = rs.getInt("startnode");
      edge.setStartNode(startNode);

      int endNode = rs.getInt("endnode");
      edge.setEndNode(endNode);
      edgeHash.put(edge.getEdgeID(), edge);
    }
    connection.closeConnection();
    return edgeHash;
  }

  @Override
  public void update(Object obj, Object update) throws SQLException {}

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
      edgeHash.put(((Edge) obj).getEdgeID(), (Edge) obj);

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
      edgeHash.remove(((Edge) obj).getEdgeID());
    } catch (SQLException e) {
      System.err.println("SQL exception");
      e.printStackTrace();
    }
    connection.closeConnection();
  }

  @Override
  public void importCSV(String filename) throws SQLException {}

  @Override
  public void exportCSV() throws SQLException {}
}
