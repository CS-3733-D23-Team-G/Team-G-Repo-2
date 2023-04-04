package edu.wpi.teamg.DAOs;

import edu.wpi.teamg.DBConnection;
import edu.wpi.teamg.ORMClasses.Move;
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
  }

  @Override
  public void exportCSV() throws SQLException {}
}
