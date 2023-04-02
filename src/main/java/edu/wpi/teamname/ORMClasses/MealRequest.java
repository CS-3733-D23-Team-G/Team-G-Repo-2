package edu.wpi.teamname.ORMClasses;

import java.sql.Time;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class MealRequest {
  @Getter @Setter private int reqid;
  @Getter @Setter private int location;
  @Getter @Setter private int serv_by;
  @Getter @Setter private String status;
  @Getter @Setter private String recipient;
  @Getter @Setter private String order;
  @Getter @Setter private String note;
  @Getter @Setter private Date deliveryDate;
  @Getter @Setter private Time deliveryTime;

  public MealRequest() {}
}
