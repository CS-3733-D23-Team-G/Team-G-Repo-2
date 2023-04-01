package edu.wpi.teamg;

import java.sql.*;

public class Gdb {
  static Connection connection;

  public void createConnection() {
    try {
      connection =
          DriverManager.getConnection(
              "jdbc:postgresql://database.cs.wpi.edu:5432/teamgdb", "teamg", "teamg70");
    } catch (SQLException e) {
      //      e.printStackTrace();
      System.err.println("SQL exception");
      printSQLException(e);
    }
  }

  public ResultSet createStatements(String sql) {
    PreparedStatement ps;
    ResultSet rs = null;

    try {
      ps = connection.prepareStatement(sql);
      rs = ps.executeQuery();
    } catch (SQLException e) {
      System.err.println("SQL exception");
      printSQLException(e);
    }

    return rs;
  }

  public void createUpdateStatements(String sql, String nodeID, int xcoord, int ycoord) {
    PreparedStatement ps;

    try {
      ps = connection.prepareStatement(sql);
      ps.setString(3, nodeID);
      ps.setInt(1, xcoord);
      ps.setInt(2, ycoord);
      ps.executeUpdate();
    } catch (SQLException e) {
      System.err.println("SQL exception");
      printSQLException(e);
    }
  }

  public void createLocationUpdate(String sql, String nodeID, String building) {
    PreparedStatement ps;

    try {
      ps = connection.prepareStatement(sql);
      ps.setString(2, nodeID);
      ps.setString(1, building);
      ps.executeUpdate();
    } catch (SQLException e) {
      System.err.println("SQL exception");
      printSQLException(e);
    }
  }

  public void closeConnection() {
    try {
      connection.close();
    } catch (SQLException e) {

      System.err.println("SQL exception");
      printSQLException(e);
    }
  }

  public void deleteRow() {
    String query = "DELETE FROM proto1.l1nodes WHERE nodeid = ?";
    System.out.println("Row deleted");
  }

  public static void printSQLException(SQLException ex) {
    for (Throwable e : ex) {
      if (e instanceof SQLException) {
        if (ignoreSQLException(((SQLException) e).getSQLState()) == false) {
          e.printStackTrace(System.err);
          System.err.println("SQLState: " + ((SQLException) e).getSQLState());
          System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
          System.err.println("Message: " + e.getMessage());
          Throwable t = ex.getCause();
          while (t != null) {
            System.out.println("Cause: " + t);
            t = t.getCause();
          }
        }
      }
    }
  }

  public static boolean ignoreSQLException(String sqlState) {
    if (sqlState == null) {
      System.out.println("The SQL state is not defined!");
      return false;
    }
    // X0Y32: Jar file already exists in schema
    if (sqlState.equalsIgnoreCase("X0Y32")) return true;
    // 42Y55: Table already exists in schema
    if (sqlState.equalsIgnoreCase("42Y55")) return true;
    return false;
  }

  public static Connection getConnection() {
    return connection;
  }
}
