package edu.wpi.teamg.DAOs;

import edu.wpi.teamg.DBConnection;
import edu.wpi.teamg.ORMClasses.Node;
import java.io.*;
import java.sql.*;
import java.util.HashMap;

public class NodeDAO implements LocationDAO {
  private HashMap<Integer, Node> nodeHash = new HashMap<Integer, Node>();
  private static DBConnection db = new DBConnection();
  private String SQL;
  private HashMap<Integer, Node> Nodes = new HashMap<>();

  @Override
  public void importCSV(String path) throws SQLException {
    try {
      db.setConnection();

      SQL = "INSERT INTO proto2.node (nodeid, xcoord, ycoord, floor, building) VALUES (?,?,?,?,?)";
      PreparedStatement ps = db.getConnection().prepareStatement(SQL);

      BufferedReader br = new BufferedReader(new FileReader(path));
      String line = null;

      br.readLine(); // skip line
      while ((line = br.readLine()) != null) {
        String[] data = line.split(",");

        String nodeID = data[0];
        String xcoord = data[1];
        String ycoord = data[2];
        String floor = data[3];
        String building = data[4];

        int iNodeID = Integer.parseInt(nodeID);
        ps.setInt(1, iNodeID);

        int iXCoord = Integer.parseInt(xcoord);
        ps.setInt(2, iXCoord);

        int iYCoord = Integer.parseInt(ycoord);
        ps.setInt(3, iYCoord);

        ps.setString(4, floor);
        ps.setString(5, building);
        ps.addBatch();
      }
      br.close();
      ps.executeBatch();
    } catch (IOException e) {
      System.err.println(e);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void exportCSV() throws SQLException {
    String csvFilePath = "Node.csv";

    try {
      SQL = "SELECT * FROM teamgdb.proto2.node";
      PreparedStatement ps = db.getConnection().prepareStatement(SQL);
      ResultSet rs = ps.executeQuery(SQL);

      BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
      fileWriter.write("nodeid, xcoord, ycoord, floor, building");
      while (rs.next()) {
        int nodeID = rs.getInt("nodeid");
        int xCoord = rs.getInt("xcoord");
        int yCoord = rs.getInt("ycoord");
        String floor = rs.getString("floor");
        String building = rs.getString("building");

        String line =
            String.format("\"%d\", %d, %d, %s, %s", nodeID, xCoord, yCoord, floor, building);

        fileWriter.newLine();
        fileWriter.write(line);
      }
      db.closeConnection();
      fileWriter.close();

    } catch (SQLException e) {
      System.err.println("Database error");
    } catch (IOException e) {
      System.err.println("File IO error");
    }
  }

  @Override
  public void insert(Object obj) throws SQLException {
    db.setConnection();

    PreparedStatement ps;
    SQL = "insert into proto2.node(nodeid, xcoord, ycoord, floor, building) values (?, ?, ?, ?, ?)";

    try {
      ps = db.getConnection().prepareStatement(SQL);
      ps.setInt(1, ((Node) obj).getNodeID());
      ps.setInt(2, ((Node) obj).getXcoord());
      ps.setInt(3, ((Node) obj).getYcoord());
      ps.setString(4, ((Node) obj).getFloor());
      ps.setString(5, ((Node) obj).getBuilding());
      ps.executeUpdate();
      nodeHash.put(((Node) obj).getNodeID(), (Node) obj);

    } catch (SQLException e) {
      System.err.println("SQL exception");
      e.printStackTrace();
      // printSQLException(e);
    }

    db.closeConnection();
  }

  @Override
  public void update(Object obj, Object update) throws SQLException {}

  @Override
  public void delete(Object obj) throws SQLException {
    db.setConnection();

    PreparedStatement ps;

    SQL = "delete from proto2.node where nodeid = ?";

    try {
      ps = db.getConnection().prepareStatement(SQL);
      ps.setInt(1, ((Node) obj).getNodeID());
      ps.executeUpdate();
      nodeHash.remove(((Node) obj).getNodeID());

    } catch (SQLException e) {
      System.err.println("SQL exception");
      e.printStackTrace();
      // printSQLException(e);
    }

    db.closeConnection();
  }

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

      nodeHash.put(node.getNodeID(), node);
    }
    db.closeConnection();

    return nodeHash;
  }
}
