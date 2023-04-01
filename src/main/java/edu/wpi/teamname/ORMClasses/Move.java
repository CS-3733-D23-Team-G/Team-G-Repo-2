package edu.wpi.teamname.ORMClasses;

import lombok.Getter;
import lombok.Setter;
public class Move {


    @Getter @Setter private Node node;
    @Getter @Setter private LocationName longName;
    @Getter @Setter private String date;

    public Move() {}
}
