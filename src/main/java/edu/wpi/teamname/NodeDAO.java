package edu.wpi.teamname;

import edu.wpi.teamg.DBConnection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class NodeDAO {

  private String query;
  static DBConnection db = new DBConnection();

  public void importCSV(String filePath) throws SQLException, IOException {
    try {
      db.setConnection();

      query =
          "INSERT INTO proto2.node (nodeid, xcoord, ycoord, floor, building) VALUES (?,?,?,?,?)";
      PreparedStatement ps = db.getConnection().prepareStatement(query);

      BufferedReader br = new BufferedReader(new FileReader(filePath));
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
}
