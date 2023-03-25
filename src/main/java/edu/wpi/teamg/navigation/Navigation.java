package edu.wpi.teamg.navigation;

import edu.wpi.teamg.GApp;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class Navigation {

  public static void navigate(final Screen screen) {
    final String filename = screen.getFilename();

    try {
      final var resource = GApp.class.getResource(filename);
      final FXMLLoader loader = new FXMLLoader(resource);

      GApp.getRootPane().setCenter(loader.load());
    } catch (IOException | NullPointerException e) {
      e.printStackTrace();
    }
  }
}
