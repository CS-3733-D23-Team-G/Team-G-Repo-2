package edu.wpi.teamg.DAOs;

import java.sql.SQLException;
import java.util.List;

public interface LocationDAO<T> extends DAO{

  // void or boolean
  public void importCSV() throws SQLException;

  public void exportCSV() throws SQLException;

}
