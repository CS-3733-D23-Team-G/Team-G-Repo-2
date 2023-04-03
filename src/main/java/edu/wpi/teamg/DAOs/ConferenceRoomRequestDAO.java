package edu.wpi.teamg.DAOs;

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

    }

    @Override
    public void delete(Object obj) throws SQLException {

    }
}
