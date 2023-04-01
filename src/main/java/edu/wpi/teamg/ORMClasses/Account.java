package edu.wpi.teamg.ORMClasses;

import lombok.Getter;
import lombok.Setter;

public class Account {
    @Getter @Setter
    private int empid;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private boolean is_admin;

    public Account() {
    }
}
