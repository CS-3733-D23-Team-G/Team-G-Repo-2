package edu.wpi.teamg.DAOs;

import edu.wpi.teamg.DAOs.IEDAO;
import edu.wpi.teamg.DBConnection;
import edu.wpi.teamg.ORMClasses.Node;

import java.sql.SQLException;

public interface NodeDAO extends IEDAO {
    DBConnection conn = DBConnection.startConnection();
    public Node getID(int id) throws SQLException;
    Node node = null;

    String sql = " SE"
}
