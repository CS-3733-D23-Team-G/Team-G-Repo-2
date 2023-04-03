package edu.wpi.teamname.ORMClasses;

import java.sql.Time;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class ConferenceRoomRequest extends Request {

  @Getter @Setter private Date meeting_date;
  @Getter @Setter private Time meeting_time;
  @Getter @Setter private String purpose;

  public ConferenceRoomRequest() {}
}
