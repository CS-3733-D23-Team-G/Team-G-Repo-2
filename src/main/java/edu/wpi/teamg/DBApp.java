package edu.wpi.teamg;

import edu.wpi.teamg.DAOs.MoveDAO;
import edu.wpi.teamg.DAOs.NodeDAO;
import edu.wpi.teamg.ORMClasses.Move;
import edu.wpi.teamg.ORMClasses.Node;
import java.sql.*;
import java.sql.SQLException;
import java.util.List;

public class DBApp {
  public static void main(String[] args) throws SQLException {

    NodeDAO nodedao = new NodeDAO();

    List<Node> allNodes = nodedao.getAll();

    for (Node node : allNodes) {
      System.out.println("id = " + node.getNodeID());
    }

    MoveDAO moveDAO = new MoveDAO();
    Date date1 = new Date(2002, 9, 8);

    //    List<Move> allMoveID = moveDAO.getAll();
    //    for (Move move : allMoveID) {
    //      System.out.println("id = " + move.getNodeID());
    //    }

    Move move = new Move(100, "Hall 5 Level uno", date1);
    moveDAO.update(move);
  }
}
