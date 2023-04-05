package edu.wpi.teamg.ORMClasses;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

public class Move {

  @Getter @Setter public int nodeID;
  @Getter @Setter public String longName;
  @Getter @Setter public Date date;

  public Move() {}
}
