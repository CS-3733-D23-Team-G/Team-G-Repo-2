package edu.wpi.teamg.ORMClasses;

import lombok.Getter;
import lombok.Setter;

public class Edge {

  @Getter @Setter private int startNode;
  @Getter @Setter private int endNode;
  @Getter @Setter private String edgeID = startNode + "_" + endNode;

  public Edge() {}

  public int distance(edu.wpi.teamg.pathFinding.Node A, edu.wpi.teamg.pathFinding.Node B) {
    double x1 = A.getNodeX();
    double x2 = B.getNodeX();
    double y1 = A.getNodeY();
    double y2 = B.getNodeY();
    int distance = (int) Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    return distance;
  }

  public int getEndNode() {
    return endNode;
  }

  public int getStartNode() {
    return startNode;
  }
}
