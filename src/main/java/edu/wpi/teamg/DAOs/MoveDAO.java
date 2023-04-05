package edu.wpi.teamg.DAOs;

import edu.wpi.teamg.DBConnection;
import edu.wpi.teamg.ORMClasses.Move;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoveDAO implements LocationMoveDao {
  static DBConnection db = new DBConnection();
  private String sql;

  @Override
  public List getAll() throws SQLException {
    db.setConnection();

    PreparedStatement ps;
    ResultSet rs = null;

    sql = "select * from proto2.move";

    try {
      ps = db.getConnection().prepareStatement(sql);
      rs = ps.executeQuery();

    } catch (SQLException e) {
      System.err.println("SQL exception");
    }

    List<Move> moves = new ArrayList<Move>();

    while (rs.next()) {
      Move move = new Move();

      int node_id = rs.getInt("nodeid");
      move.setNodeID(node_id);

      String longname = rs.getString("longname");
      move.setLongName(longname);

      Date date = rs.getDate("date");
      move.setDate(date);

      moves.add(move);
    }
    db.closeConnection();
    return moves;
  }

  @Override
  public void update(Object obj, Object update) throws SQLException {
    Move move = (Move) obj;
    db.setConnection();
    PreparedStatement ps = db.getConnection().prepareStatement(sql);
    sql = "UPDATE proto2.move set nodeID = ?, longName = ?, date = ?";

    try {
      ps.setInt(1, move.getNodeID());
      ps.setString(2, move.getLongName());
      ps.setDate(3, move.getDate());
      ps.executeUpdate(sql);
    } catch (SQLException e) {
      System.err.println("SQL Exception");
    }
    db.closeConnection();
  }

  @Override
  public void insert(Object obj) throws SQLException {
    Move move = (Move) obj;
    db.setConnection();
    sql = "INSERT INTO proto2.move (nodeid, longname, date) VALUES (?,?,?);";
    PreparedStatement ps = db.getConnection().prepareStatement(sql);

    try {
      ps.setInt(1, move.getNodeID());
      ps.setString(2, move.getLongName());
      ps.setDate(3, move.getDate());
      ps.executeUpdate();
    } catch (SQLException e) {
      System.err.println("SQL Exception");
      e.printStackTrace();
    }
    db.closeConnection();
  }

  @Override
  public void delete(Object obj) throws SQLException {
    Move move = (Move) obj;
    db.setConnection();
    PreparedStatement ps = db.getConnection().prepareStatement(sql);
    sql = "DELETE FROM proto2.move WHERE nodeID = ?";
    try {
      ps.setInt(1, move.getNodeID());
    } catch (SQLException e) {
      System.err.println("SQL Exception");
    }
    db.closeConnection();
  }

  @Override
  public void importCSV(String filePath) throws SQLException {
    db.setConnection();
    sql="insert into teamgdb.proto2.move (nodeid, longname, date) values (?,?,?)";
    PreparedStatement ps = db.getConnection().prepareStatement(sql);
    try {
      BufferedReader br = new BufferedReader(new FileReader(filePath));
      String line = null;

      br.readLine();
      while((line= br.readLine())!=null){
        String[] data = line.split(",");

        String nodeID = data[0];
        String longname = data[1];
        String dateString = data[2];

        int inodeid = Integer.parseInt(nodeID);
        ps.setInt(1,inodeid);

        ps.setString(2,longname);

        ps.setDate(3,stringToDate(dateString));

        ps.addBatch();

      }
      br.close();
      ps.executeBatch();

    } catch (FileNotFoundException e) {
      System.err.println("File not found");
      e.printStackTrace();
    } catch (IOException e) {
      System.err.println("Input output exception");
      e.printStackTrace();
    }catch(SQLException e){
      System.err.println("SQL exception");
      e.printStackTrace();
    }
    db.closeConnection();
  }

  public Date stringToDate(String input){
    String[] data = input.split("/");
    Date returnDate = new Date(Integer.parseInt(data[2]),Integer.parseInt(data[1]),Integer.parseInt(data[0]));
    return returnDate;
  }

  @Override
  public void exportCSV() throws SQLException {
    String csvFilePath = "Move.csv";

    try {
      sql = "SELECT * FROM teamgdb.proto2.move";
      PreparedStatement ps = db.getConnection().prepareStatement(sql);
      ResultSet rs = ps.executeQuery(sql);

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
}
