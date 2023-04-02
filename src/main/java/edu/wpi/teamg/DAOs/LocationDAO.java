package edu.wpi.teamg.DAOs;

import edu.wpi.teamg.DBConnection;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
public class LocationDAO implements LocationNameDAO {
    private static DBConnection connection;
    private String SQL;

    @Override
    public List getAll() throws SQLException {
        connection.setConnection();

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

    @Override
    public void Import(File file) throws SQLException {

    }

    @Override
    public File Export() throws SQLException {
        return null;
    }
}
