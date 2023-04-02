package edu.wpi.teamg.DAOs;

import edu.wpi.teamg.DBConnection;
import edu.wpi.teamg.ORMClasses.LocationName;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
public class LocationDAO implements LocationNameDAO {
    private static DBConnection connection=new DBConnection();
    private String SQL;

    @Override
    public List getAll() throws SQLException {
        connection.setConnection();
        PreparedStatement ps;
        ResultSet rs=null;

        SQL="select * from proto2.locationname";

        try{
            ps=connection.getConnection().prepareStatement(SQL);
            rs=ps.executeQuery();
        }catch(SQLException e){
            System.err.println("SQL exeption");
        }
        List<LocationName> allLocations= new ArrayList<>();
        while(rs.next()){
            LocationName loc= new LocationName();

            String longname= rs.getString("longname");
            loc.setLongName(longname);

            String shortname=rs.getString("shortname");
            loc.setShortName(shortname);

            String nodeT= rs.getString("nodetype");
            loc.setNodeType(nodeT);

            allLocations.add(loc);
        }
        connection.closeConnection();

        return allLocations;
    }

    @Override
    public void update(Object obj) throws SQLException {
        connection.setConnection();
        PreparedStatement ps;
        LocationName l1= (LocationName) obj;
        SQL="UPDATE proto2.locationname SET shortname=?, nodetype=? Where longname=? ";

        try{
            ps=connection.getConnection().prepareStatement(SQL);
            ps.setString(1,l1.getShortName());
            ps.setString(2,l1.getNodeType());
            ps.setString(3,l1.getLongName());
            ps.executeQuery();
        }catch(SQLException e){
            System.err.println("SQL exeption");
        }
        connection.closeConnection();

    }

    @Override
    public void insert(Object obj) throws SQLException {
        connection.setConnection();
        PreparedStatement ps;
        LocationName l1= (LocationName) obj;
        SQL="INSERT INTO proto2.locationname (longname, shortname, nodetype) VALUES (?,?,?)";

        try{
            ps=connection.getConnection().prepareStatement(SQL);
            ps.setString(2,l1.getShortName());
            ps.setString(3,l1.getNodeType());
            ps.setString(1,l1.getLongName());
            ps.executeQuery();
        }catch(SQLException e){
            System.err.println("SQL exeption");
        }
        connection.closeConnection();

    }

    @Override
    public void delete(Object obj) throws SQLException {
        connection.setConnection();
        PreparedStatement ps;
        LocationName l1= (LocationName) obj;
        SQL="DELETE FROM proto2.locationname WHERE longname=? OR shortname=?";

        try{
            ps=connection.getConnection().prepareStatement(SQL);
            ps.setString(1,l1.getLongName());
            ps.setString(2,l1.getShortName());
            ps.executeQuery();
        }catch(SQLException e){
            System.err.println("SQL exeption");
        }
        connection.closeConnection();


    }

    @Override
    public void Import(File file) throws SQLException {

    }

    @Override
    public File Export() throws SQLException {
        return null;
    }
}
