package edu.wpi.teamg.ORMClasses;

import lombok.Getter;
import lombok.Setter;

public class Move extends Node{

  @Getter @Setter private Node node;
  @Getter @Setter private LocationName longName;
  @Getter @Setter private String date;

  public Move() {}
}
