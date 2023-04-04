package edu.wpi.teamg.ORMClasses;

import java.sql.Time;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class ConferenceRoomRequest extends Request {

  @Getter @Setter private int reqid;
  @Getter @Setter private int location;
  @Getter @Setter private int serv_by;
  @Getter @Setter private String status;
  @Getter @Setter private Date meeting_date;
  @Getter @Setter private Time meeting_time;
  @Getter @Setter private String purpose;

  public ConferenceRoomRequest() {}
}
