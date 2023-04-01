package edu.wpi.teamg;

import java.sql.SQLException;
import java.util.List;

public interface LocationDAO<T> extends DAO {

  // void or boolean
  public void importCSV() throws SQLException;

  public void exportCSV() throws SQLException;

  public List<T> getAll() throws SQLException;

  public void insert(T obj) throws SQLException;

  public void update(T obj) throws SQLException;

  public void delete(T obj) throws SQLException;
}
