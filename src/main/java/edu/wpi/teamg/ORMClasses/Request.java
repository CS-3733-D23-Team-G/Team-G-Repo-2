package edu.wpi.teamg.ORMClasses;

import java.sql.Date;
import java.sql.Time;
import lombok.Getter;
import lombok.Setter;

public class Request {

  @Getter @Setter private int reqid;
  @Getter @Setter private int empid;
  @Getter @Setter private int location;
  @Getter @Setter private int serv_by;
  @Getter @Setter private String status;

  @Getter @Setter private Date deliveryDate;
  @Getter @Setter private Time deliveryTime;

  @Getter @Setter private String recipient;
  @Getter @Setter private String order;
  @Getter @Setter private String note;

  @Getter @Setter private Date meeting_date;
  @Getter @Setter private Time meeting_time;
  @Getter @Setter private String purpose;
}
