package edu.wpi.teamg;

import edu.wpi.teamg.DAOs.LocationDAO;
import java.io.IOException;
import java.sql.SQLException;

public class Main {

  public static void main(String[] args) throws SQLException, IOException {

    LocationDAO lq = new LocationDAO();
    lq.Import("C:\\Users\\nshem\\Downloads\\Move.csv\\");
    // App.launch(App.class, args);
  }

  // shortcut: psvm

}
