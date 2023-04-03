package edu.wpi.teamg.DAOs;

import com.sun.scenario.effect.impl.prism.PrReflectionPeer;
import edu.wpi.teamg.DBConnection;
import edu.wpi.teamg.ORMClasses.ConferenceRoomRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ConferenceRoomRequestDAO implements DAO{

    private static DBConnection db = new DBConnection();
    private String SQL_maxID;
    private String SQL_confRoomRequest;
    private String SQL_Request;
    private HashMap<Integer,ConferenceRoomRequest> conferenceRequestHash =
            new HashMap<Integer,ConferenceRoomRequest>();

    @Override
    public HashMap<Integer, ConferenceRoomRequest> getAll() throws SQLException {
        db.setConnection();
        PreparedStatement ps;
        ResultSet rs = null;

        SQL_confRoomRequest = "select * from teamgdb.proto2.request join " +
                "teamgdb.proto2.conferenceroomrequest on teamgdb.proto2.request.reqid = " +
                "teamgdb.proto2.conferenceroomrequest.reqid";

        try{
            ps = db.getConnection().prepareStatement(SQL_confRoomRequest);
            rs=ps.executeQuery();
        }catch(SQLException e){
            System.err.println("SQL Exception");
        }

        while(rs.next()){
            ConferenceRoomRequest cReq = new ConferenceRoomRequest();
            int reqID = rs.getInt("reqid");
            Date reqDate = rs.getDate("meetingdate");
            Time reqTime = rs.getTime("meetingtime");
            String confPurpose = rs.getString("purpose");

            cReq.setPurpose(confPurpose);
            cReq.setMeeting_time(reqTime);
            cReq.setMeeting_date(reqDate);

            conferenceRequestHash.put(reqID,cReq);
        }
        db.closeConnection();
        return conferenceRequestHash;
    }

    @Override
    public void update(Object obj) throws SQLException {

    }

    @Override
    public void insert(Object obj) throws SQLException {
        db.setConnection();
        PreparedStatement ps_getMaxID;
        PreparedStatement ps_getRoomReq;
        PreparedStatement ps_Req;

        ResultSet rs = null;

        SQL_maxID = "select reqID from teamgdb.proto2.request order by reqid desc limit 1";

        try{
            ps_getMaxID = db.getConnection().prepareStatement(SQL_maxID);
            rs = ps_getMaxID.executeQuery();
        }catch (SQLException e){
            System.err.println("SQL Exception");
            e.printStackTrace();
        }
        int maxID = 0;
        while(rs.next()){
            maxID = rs.getInt("reqID");
            maxID++;
        }
        SQL_confRoomRequest = "insert  into teamgdb.proto2.conferenceroomrequest(reqid,meetingdate,meetingtime,purpose) values (?,?,?,?)";
        SQL_Request =
            "insert into teamgdb.proto2.request(reqid,location,serv_by,status) values (?,?,?,?)";

        try{
            ps_Req = db.getConnection().prepareStatement(SQL_Request);
            ps_Req.setInt(1,maxID);
            ps_Req.setInt(2,((ConferenceRoomRequest)obj).getLocation());
            ps_Req.setInt(3,((ConferenceRoomRequest)obj).getServ_by());
            ps_Req.setObject(4,((ConferenceRoomRequest) obj).getStatus(),java.sql.Types.OTHER);
            ps_Req.executeUpdate();

            ps_getRoomReq = db.getConnection().prepareStatement(SQL_confRoomRequest);
            ps_getRoomReq.setInt(1,maxID);
            ps_getRoomReq.setDate(2, (java.sql.Date)((ConferenceRoomRequest)obj).getMeeting_date());
            ps_getRoomReq.setTime(3,((ConferenceRoomRequest)obj).getMeeting_time());
            ps_getRoomReq.setString(4,((ConferenceRoomRequest)obj).getPurpose());
            ps_getRoomReq.executeUpdate();
        }catch(SQLException e){
            System.err.println("SQL Exception");
            e.printStackTrace();
        }

        db.closeConnection();
    }



    @Override
    public void delete(Object obj) throws SQLException {

    }
}
