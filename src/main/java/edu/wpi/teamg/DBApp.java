package edu.wpi.teamg;

import edu.wpi.teamg.DAOs.MealRequestDAO;
import edu.wpi.teamg.DAOs.NodeDAO;
import edu.wpi.teamg.ORMClasses.MealRequest;
import edu.wpi.teamg.ORMClasses.Node;
import java.sql.SQLException;
import java.util.List;

public class DBApp {
  public static void main(String[] args) throws SQLException {

    NodeDAO nodedao = new NodeDAO();

    List<Node> allNodes = nodedao.getAll();

    for (Node node : allNodes) {
      System.out.println("id = " + node.getNodeID());
    }

    Node newNode = new Node();
    newNode.setNodeID(99);
    newNode.setXcoord(99);
    newNode.setYcoord(99);
    newNode.setBuilding("Test");
    newNode.setFloor("T1");

    MealRequestDAO mealRequestDAO = new MealRequestDAO();

    List<MealRequest> allMealRequests = mealRequestDAO.getAll();

    for (MealRequest mealRequest : allMealRequests) {
      System.out.println("id = " + mealRequest.getReqid());
    }

    MealRequest newMealRequest = new MealRequest();
    newMealRequest.setLocation(105);
    newMealRequest.setRecipient("Viet Hung Pham");
    newMealRequest.setServ_by(1);
    newMealRequest.setNote("Test");
    newMealRequest.setOrder("Test");
    newMealRequest.setStatus("blank");

    mealRequestDAO.insert(newMealRequest);

    nodedao.insert(newNode);
    nodedao.delete(newNode);
    mealRequestDAO.delete(allMealRequests.get(allMealRequests.size() - 1));
  }
}
