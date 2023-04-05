package edu.wpi.teamg.DAOs;

import edu.wpi.teamg.DBConnection;
import edu.wpi.teamg.ORMClasses.Request;
import edu.wpi.teamg.ORMClasses.StatusTypeEnum;
import java.sql.*;
import java.util.HashMap;

public class RequestDAO implements DAO {

  private static DBConnection db = new DBConnection();
  private String sql;
  private HashMap<Integer, Request> requestHash = new HashMap<Integer, Request>();

  public static <E extends Enum<E>> E getEnum(Class<E> enumClass, ResultSet rs, String columnName)
      throws SQLException {
    String value = rs.getString(columnName);
    if (value == null) {
      return null;
    } else {
      /*from ww w.  j  av  a 2s  . c  o m*/
      return Enum.valueOf(enumClass, value);
    }
  }

  @Override
  public HashMap<Integer, Request> getAll() throws SQLException {
    db.setConnection();

    PreparedStatement ps;
    ResultSet rs = null;

    sql = "select * from proto2.request";

    try {
      ps = db.getConnection().prepareStatement(sql);
      rs = ps.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("SQL Exception");
    }

    while (rs.next()) {
      Request cReq = new Request();

      int reqID = rs.getInt("reqid");
      int empID = rs.getInt("empid");
      int location = rs.getInt("location");
      int serv_by = rs.getInt("serv_by");
      StatusTypeEnum status = StatusTypeEnum.valueOf(rs.getString("status"));

      cReq.setReqid(reqID);
      cReq.setLocation(location);
      cReq.setEmpid(empID);
      cReq.setServ_by(serv_by);

      cReq.setStatus(status);

      requestHash.put(reqID, cReq);
    }

    db.closeConnection();
    return requestHash;
  }

  @Override
  public void update(Object obj, Object update) throws SQLException {
   /* db.setConnection();

    PreparedStatement ps;
    Request r1= (Request) update;

    sql = "update proto2.request SET 'location'=?, 'serv_by'=? where 'reqid'=? ";

    try {
      ps = db.getConnection().prepareStatement(sql);
      ps.setInt(1,r1.getLocation());
      ps.setInt(2, r1.getServ_by());
      ps.setInt(3,r1.getReqid());
      ps.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("SQL Exception");
    }
*/
  }

  @Override
  public void insert(Object obj) throws SQLException {}

  @Override
  public void delete(Object obj) throws SQLException {}
}
