package edu.wpi.teamg;

import edu.wpi.teamg.ORMClasses.Edge;
import edu.wpi.teamg.ORMClasses.LocationName;
import edu.wpi.teamg.ORMClasses.Move;
import edu.wpi.teamg.ORMClasses.Node;

import java.sql.SQLException;
import java.util.List;

public interface LocationDAO<T> extends DAO{

    // void or boolean
    public void importCSV() throws SQLException;
    public void exportCSV() throws SQLException;
    public <T> List<T> getAll() throws SQLException;
    public void insert(T obj) throws SQLException;
    public void update(T obj) throws SQLException;
    public void delete(T obj) throws SQLException;
}
