package edu.wpi.teamg.ORMClasses;

import lombok.Getter;
import lombok.Setter;

public class ConferenceRoomRequest {
    @Getter @Setter
    private int reqid;
    @Getter @Setter
    private int location;
    @Getter @Setter
    private int serv_by;

    @Getter @Setter
    private String status;
    @Getter @Setter
    private String meeting_date;
    @Getter @Setter
    private String meeting_time;
    @Getter @Setter
    private String purpose;


}
