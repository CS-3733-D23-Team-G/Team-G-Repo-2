package edu.wpi.teamg.DAOs;

import edu.wpi.teamg.DBConnection;
import edu.wpi.teamg.ORMClasses.Move;
import edu.wpi.teamg.ORMClasses.Node;

import java.io.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.*;


public class MoveDAO implements LocationDAO {
  static DBConnection db = new DBConnection();
  private String sql;

  @Override
  public List getAll() throws SQLException {
    db.setConnection();

    PreparedStatement ps;
    ResultSet rs = null;

    sql = "select * from proto2.move"; // sql statement

    try {
      ps = db.getConnection().prepareStatement(sql); // ps = prepare statement
      rs = ps.executeQuery(); // rs = result statement; sql query

    } catch (SQLException e) {
      System.err.println("SQL exception");
    }

    List<Move> moves = new ArrayList<Move>(); // Move classes get stored in ArrayLists

    while (rs.next()) {
      Move move = new Move();

      int node_id = rs.getInt("nodeid");
      move.setNodeID(node_id); // set attribute

      String longname = rs.getString("longname");
      move.setLongName(longname); // set attribute

      Date date = rs.getDate("date");
      move.setDate(date); // set attribute

      moves.add(move); // creates database table without objects by default
    }
    db.closeConnection();
    return moves; // terminate pull-up
  }

  @Override
  public int update(Move move) throws SQLException {
    db.setConnection();


    PreparedStatement ps = db.getConnection().prepareStatement(sql);
    int result;

    sql = "UPDATE proto2.move set nodeID = ?, longName = ?, date = ?";

    ps.setInt(1,move.getNodeID());
    ps.setString(2, move.getLongName());
    ps.setDate(3,move.getDate());

    result = ps.executeUpdate(sql);
    db.closeConnection();

    return result;
  }

  @Override
  public  int  insert(Object move1) throws SQLException {
    db.setConnection();

    Move move =(Move)move1;

    PreparedStatement ps = db.getConnection().prepareStatement(sql);
    int result;

   sql = "INSERT INTO proto2.move (nodeid, longname, date) VALUES (?,?,?)";

   ps.setInt(1,move.getNodeID());
   ps.setString(2, move.getLongName());
   ps.setDate(3,move.getDate());

   result = ps.executeUpdate(sql);
   db.closeConnection();
   return result;

  }

  @Override
  public int delete(Move move) throws SQLException {
    db.setConnection();


    PreparedStatement ps = db.getConnection().prepareStatement(sql);
    int result;

    sql = "DELETE FROM proto2.move WHERE nodeID = ?";

    ps.setInt(1,move.getNodeID());


    result = ps.executeUpdate(sql);
    db.closeConnection();
    return result;

  }

  ArrayList<Node> nodes = new ArrayList<>();
  @Override
  public void importCSV(String filep) throws SQLException, FileNotFoundException {
    Scanner scanner = new Scanner(file);
    scanner.useDelimiter("\n");


    while(scanner.hasNext()) {
      String line = scanner.next();
      String[] lines = line.split(",");
      int nodeID = Integer.parseInt(lines[0]);
      int nodeX = Integer.parseInt(lines[1]);
      int nodeY = Integer.parseInt(lines[2]);
      String floor = lines[3];
      String building = lines[4];

      Node listedNode = new Node(nodeID, nodeX, nodeY, floor, building);
      nodes.add(listedNode);
    }
    scanner.close();
  }

  @Override
  public void exportCSV(File file) throws SQLException, IOException {

    sql = "SELECT * FROM proto2.move";
    PreparedStatement ps = db.getConnection().prepareStatement(sql);
    ResultSet result = ps.executeQuery(sql);
    BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));

    fileWriter.write("node, longname, date");
    while(result.next()) {
        int nodeID = result.getInt("nodeid");
        String longName =result.getString("longname");
        Date date  = result.getDate("date");

        String line = String.format("\"%d\",%s,%t",
                nodeID,longName,date);
        fileWriter.newLine();
        fileWriter.write(line);

      ps.close();
      fileWriter.close();
      }
  }
}
