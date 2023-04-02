package edu.wpi.teamname.ORMClasses;

import lombok.Getter;
import lombok.Setter;

public class Node {
  @Getter @Setter private int nodeID;
  @Getter @Setter private int xcoord;
  @Getter @Setter private int ycoord;
  @Getter @Setter private String floor;
  @Getter @Setter private String building;

  public Node() {}
}
