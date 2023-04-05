package edu.wpi.teamg.DAOs;

import edu.wpi.teamg.DBConnection;
import edu.wpi.teamg.ORMClasses.Edge;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
  public void importCSV(String filename) throws SQLException {
    connection.setConnection();
    sql = "";
    sql = "insert into teamgdb.proto2.edge (startnode, endnode) values (?,?)";
    PreparedStatement ps = connection.getConnection().prepareStatement(sql);
    try {
      BufferedReader br = new BufferedReader(new FileReader(filename));
      String line = null;
      br.readLine();

      while ((line = br.readLine()) != null) {
        String data[] = line.split(",");

        int sNode = Integer.parseInt(data[0]);
        int eNode = Integer.parseInt(data[1]);

        ps.setInt(1, sNode);
        ps.setInt(2, eNode);

        ps.addBatch();
      }
      br.close();
      ps.executeUpdate();

    } catch (FileNotFoundException e) {
      System.err.println("File Not Found Exception");
      e.printStackTrace();
    } catch (IOException e) {
      System.err.println("IO Exception");
      e.printStackTrace();
    } catch (SQLException e) {
      System.err.println("SQL Exception");
      e.printStackTrace();
    }
    connection.closeConnection();
  }

  @Override
  public void exportCSV() throws SQLException {
    String csvFilePath = "Edge.csv";

    try {
      sql = "SELECT * FROM teamgdb.proto2.edge";
      PreparedStatement ps = connection.getConnection().prepareStatement(sql);
      ResultSet rs = ps.executeQuery(sql);

      BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
      fileWriter.write("startnode, endnode");
      while (rs.next()) {
        String startNode = rs.getString("startnode");
        String endNode = rs.getString("endnode");

        String line = String.format("\"%s\", %s", startNode, endNode);

        fileWriter.newLine();
        fileWriter.write(line);
      }
      connection.closeConnection();
      fileWriter.close();

    } catch (SQLException e) {
      System.err.println("Database error");
    } catch (IOException e) {
      System.err.println("File IO error");
    }
  }
}
