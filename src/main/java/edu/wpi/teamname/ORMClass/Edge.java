package edu.wpi.teamname.ORMClass;

import lombok.Getter;
import lombok.Setter;

public class Edge {

  @Getter @Setter private int startNode;
  @Getter @Setter private int endNode;
  @Getter @Setter private String edgeID = startNode + "_" + endNode;

  public Edge() {}
}
