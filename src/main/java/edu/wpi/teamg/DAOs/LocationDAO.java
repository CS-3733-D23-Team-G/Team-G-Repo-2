package edu.wpi.teamg.DAOs;

import java.sql.SQLException;

public interface LocationDAO<T> extends DAO {

  // void or boolean
  public void importCSV(String path) throws SQLException;

  public void exportCSV() throws SQLException;
}
