package edu.wpi.teamname.ORMClasses;

import lombok.Getter;
import lombok.Setter;

public class Account extends edu.wpi.teamname.ORMClasses.Employee {
  @Getter @Setter private int empid;
  @Getter @Setter private String password;
  @Getter @Setter private boolean is_admin;

  public Account() {}
}
