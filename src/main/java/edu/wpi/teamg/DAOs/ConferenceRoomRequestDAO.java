package edu.wpi.teamg.DAOs;

import edu.wpi.teamg.DBConnection;
import edu.wpi.teamg.ORMClasses.ConferenceRoomRequest;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class ConferenceRoomRequestDAO implements DAO{

    private static DBConnection db = new DBConnection();
    private String SQL_maxID;
    private String SQL_confRoomRequest;
    private String SQL_Request;

    @Override
    public HashMap<String, ConferenceRoomRequest> getAll() throws SQLException {
        return null;
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
