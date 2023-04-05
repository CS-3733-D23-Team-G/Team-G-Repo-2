package edu.wpi.teamg.DAOs;

import edu.wpi.teamg.DBConnection;
import edu.wpi.teamg.ORMClasses.Request;
import java.sql.*;
import java.util.HashMap;

public class RequestDAO implements DAO {

  private static DBConnection db = new DBConnection();
  private String SQL_mealRequest;
  private String sql;
  private String SQL_confRoomRequest;
  private HashMap<Integer, Request> requestHash = new HashMap<Integer, Request>();

  @Override
  public HashMap getAll() throws SQLException {
    db.setConnection();

    System.out.println("Connection Set");

    PreparedStatement ps_conf;
    PreparedStatement ps_meal;
    // ResultSet rs = null;
    ResultSet rs_conf = null;
    ResultSet rs_meal = null;

    SQL_mealRequest =
        "select * from proto2.request join proto2.conferenceroomrequest on proto2.request.reqid = proto2.conferenceroomrequest.reqid";

    SQL_confRoomRequest =
        "select * from proto2.request join proto2.mealrequest "
            + "on proto2.request.reqid = proto2.mealrequest.reqid";

    // sql = "select * from proto2.request";

    try {
      ps_meal = db.getConnection().prepareStatement(SQL_mealRequest);
      rs_meal = ps_meal.executeQuery();

      ps_conf = db.getConnection().prepareStatement(SQL_confRoomRequest);
      rs_conf = ps_conf.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("SQL Exception");
    }

    Request cReq = new Request();

    while (rs_meal.next()) {

      int reqID = rs_meal.getInt("reqid");
      int empID = rs_meal.getInt("empid");
      int location = rs_meal.getInt("location");
      int serv_by = rs_meal.getInt("serv_by");
      String status = rs_meal.getString("status");

      Date deliverydate = rs_meal.getDate("deliverydate");
      Time deliverytime = rs_meal.getTime("deliverytime");

      String recipient = rs_meal.getString("recipient");
      String mealorder = rs_meal.getString("mealorder");
      String note = rs_meal.getString("note");

      Date meetingdate = rs_conf.getDate("meeting_date");
      Time meetingtime = rs_conf.getTime("meeting_time");
      String purpose = rs_conf.getString("purpose");

      cReq.setReqid(reqID);
      cReq.setLocation(location);
      cReq.setEmpid(empID);
      cReq.setServ_by(serv_by);
      cReq.setStatus(status);
      cReq.setDeliveryDate(deliverydate);
      cReq.setDeliveryTime(deliverytime);
      cReq.setOrder(mealorder);
      cReq.setRecipient(recipient);
      cReq.setNote(note);

      cReq.setMeeting_date(meetingdate);
      cReq.setMeeting_time(meetingtime);
      cReq.setPurpose(purpose);

      requestHash.put(reqID, cReq);
    }

    db.closeConnection();
    return requestHash;
  }

  @Override
  public void update(Object obj, Object update) throws SQLException {}

  @Override
  public void insert(Object obj) throws SQLException {}

  @Override
  public void delete(Object obj) throws SQLException {}
}
