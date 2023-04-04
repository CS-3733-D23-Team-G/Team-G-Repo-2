package edu.wpi.teamg.ORMClasses;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

public class Move extends Node{

  @Getter @Setter private int nodeid;
  @Getter @Setter private String longName;
  @Getter @Setter private Date date;

  public Move() {}
}
