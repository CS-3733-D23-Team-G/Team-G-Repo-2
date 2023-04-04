package edu.wpi.teamg.DAOs;

import java.sql.SQLException;

public interface LocationDAO<T> extends DAO {

  // void or boolean
  public void importCSV(String filename) throws SQLException;

  public void exportCSV() throws SQLException;

}
