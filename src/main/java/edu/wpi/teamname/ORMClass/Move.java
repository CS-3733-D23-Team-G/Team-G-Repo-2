package edu.wpi.teamname.ORMClass;

import lombok.Getter;
import lombok.Setter;

public class Move extends edu.wpi.teamname.ORMClass.Node {

  @Getter @Setter private edu.wpi.teamname.ORMClass.Node node;
  @Getter @Setter private String longName;
  @Getter @Setter private String date;

  public Move() {}
}
