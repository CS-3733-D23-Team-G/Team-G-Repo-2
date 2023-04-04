package edu.wpi.teamname.ORMClasses;

import lombok.Getter;
import lombok.Setter;


public class Move extends Node {

  @Getter @Setter private Node node;
  @Getter @Setter private String longName;
  @Getter @Setter private String date;

  public Move() {}
}
