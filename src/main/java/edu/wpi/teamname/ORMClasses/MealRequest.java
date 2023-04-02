package edu.wpi.teamname.ORMClasses;

import java.time.LocalDateTime;
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
  @Getter @Setter private LocalDateTime dateTime;

  public MealRequest() {}
}
