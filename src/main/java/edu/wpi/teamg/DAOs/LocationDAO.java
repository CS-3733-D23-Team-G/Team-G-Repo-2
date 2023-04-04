package edu.wpi.teamg.DAOs;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public interface LocationDAO extends DAO {

  public void Import(String filename) throws SQLException, IOException;

  public File Export() throws SQLException;
}
