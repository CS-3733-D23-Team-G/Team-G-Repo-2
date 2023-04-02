package edu.wpi.teamg.DAOs;

import edu.wpi.teamg.DBConnection;
import edu.wpi.teamg.ORMClasses.Move;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MoveDAO implements LocationDAO {
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

  @Override
  public void importCSV() throws SQLException {


  }

  @Override
  public void exportCSV() throws SQLException {}
}
