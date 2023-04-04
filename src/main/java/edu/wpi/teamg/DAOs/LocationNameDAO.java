package edu.wpi.teamg.DAOs;

import edu.wpi.teamg.DBConnection;
import edu.wpi.teamg.ORMClasses.LocationName;
import java.io.*;
import java.sql.*;
import java.sql.SQLException;
import java.util.HashMap;

public class LocationNameDAO implements LocationDAO {
    private static DBConnection connection = new DBConnection();
    private String SQL;
    private HashMap<String, LocationName> Location;

    public LocationNameDAO() {}

    @Override
    public HashMap<String, LocationName> getAll() throws SQLException {
        connection.setConnection();
        PreparedStatement ps;
        ResultSet rs = null;

        SQL = "select * from proto2.locationname";

        try {
            ps = connection.getConnection().prepareStatement(SQL);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println("SQL exeption");
        }
        while (rs.next()) {
            LocationName loc = new LocationName();

            String longname = rs.getString("longname");
            loc.setLongName(longname);

            String shortname = rs.getString("shortname");
            loc.setShortName(shortname);

            String nodeT = rs.getString("nodetype");
            loc.setNodeType(nodeT);

            Location.put(longname, loc);
        }
        connection.closeConnection();

        return Location;
    }

    @Override
    public void update(Object old, Object update) throws SQLException {/*
    connection.setConnection();
    PreparedStatement ps;
    SQL =
        "UPDATE proto2.locationname SET shortname=?, nodetype=?, longname=? Where longname=? AND shortname=? AND nodetype=? ";
    LocationName ol = (LocationName) old;
    LocationName up = (LocationName) update;
    try {
      ps = connection.getConnection().prepareStatement(SQL);
      ps.setString(1, up.getShortName());
      ps.setString(2, up.getNodeType());
      ps.setString(3, up.getLongName());
      ps.setString(4, ol.getLongName());
      ps.setString(5, ol.getShortName());
      ps.setString(6, ol.getNodeType());
      ps.executeQuery();
      Location.remove(ol.getLongName());
      Location.put(up.getLongName(), up);
    } catch (SQLException e) {
      System.err.println("SQL exeption");
    }
    connection.closeConnection();*/
    }

    @Override
    public void insert(Object obj) throws SQLException {
        connection.setConnection();
        PreparedStatement ps;
        LocationName l1 = (LocationName) obj;
        SQL = "INSERT INTO proto2.locationname (longname, shortname, nodetype) VALUES (?,?,?)";

        try {
            ps = connection.getConnection().prepareStatement(SQL);
            ps.setString(2, l1.getShortName());
            ps.setString(3, l1.getNodeType());
            ps.setString(1, l1.getLongName());
            ps.executeQuery();

            Location.put(l1.getLongName(), l1);
        } catch (SQLException e) {
            System.err.println("SQL exeption");
        }
        connection.closeConnection();
    }

    @Override
    public void delete(Object obj) throws SQLException {
        connection.setConnection();
        PreparedStatement ps;
        LocationName l1 = (LocationName) obj;
        SQL = "DELETE FROM proto2.locationname WHERE longname=? OR shortname=?";

        try {
            ps = connection.getConnection().prepareStatement(SQL);
            ps.setString(1, l1.getLongName());
            ps.setString(2, l1.getShortName());
            ps.executeQuery();
            Location.remove(l1.getLongName());

        } catch (SQLException e) {
            System.err.println("SQL exeption");
        }
        connection.closeConnection();
    }


    @Override
    public void importCSV(String filename) throws SQLException{
        LocationName l1 = new LocationName();
        String line = "";
        String split = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                String[] loca = line.split(split);
                LocationName loc = new LocationName(loca[0], loca[1], loca[2]);
                this.insert(loc);
            }
            br.close();
        }catch(SQLException c){
            System.err.println("SQL Exception");
        }catch(IOException e){
            System.err.println("IO exception");

        }
    }
    @Override
    public void exportCSV() throws SQLException {

    }
}