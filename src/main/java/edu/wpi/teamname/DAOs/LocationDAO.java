package edu.wpi.teamname.DAOs;

import java.sql.SQLException;

public interface LocationDAO<T> extends DAO {

  // void or boolean
  public void importCSV() throws SQLException;

  public void exportCSV() throws SQLException;
}
