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
    db.setConnection();

    BufferedReader br = new BufferedReader(new FileReader(filePath));
    String line = br.readLine();

    while ((line = br.readLine()) != null) {

      String[] data = line.split(",");
      query =
          "INSERT INTO proto2.node (nodeid, xcoord, ycoord, floor, building) VALUES (?,?,?,?,?)";
      PreparedStatement ps = db.getConnection().prepareStatement(query);

      ps.setInt(1, Integer.parseInt(data[0]));
      ps.setInt(2, Integer.parseInt(data[1]));
      ps.setInt(3, Integer.parseInt(data[2]));
      ps.setString(4, data[3]);
      ps.setString(5, data[4]);
      ps.addBatch();
    }
    br.close();
  }
}
