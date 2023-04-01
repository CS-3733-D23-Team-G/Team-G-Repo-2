package edu.wpi.teamname.ORMClasses;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.Date;
import java.sql.Time;

public class ConferenceRoomRequest {
    @Getter @Setter private int reqid;
    @Getter @Setter private int location;
    @Getter @Setter private int serv_by;
    @Getter @Setter private String status;
    @Getter @Setter private Date meeting_date;
    @Getter @Setter private Time meeting_time;

    //@Getter @Setter private LocalDate meeting_date;
   // @Getter @Setter private LocalDate meeting_time;

    @Getter @Setter private String purpose;

    public ConferenceRoomRequest() {}
}
