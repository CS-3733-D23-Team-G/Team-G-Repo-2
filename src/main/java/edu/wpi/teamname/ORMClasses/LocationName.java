package edu.wpi.teamname.ORMClasses;

import lombok.Getter;
import lombok.Setter;

public class LocationName {
  @Getter @Setter private String longName;

  @Getter @Setter private String shortName;

  @Getter @Setter private String nodeType;

  public LocationName() {}
}
