package edu.wpi.teamg.DAOs;

import java.io.File;
import java.sql.SQLException;

public interface LocationNameDAO extends DAO{

    public void Import(File file) throws SQLException;
    public File Export() throws SQLException;
}
