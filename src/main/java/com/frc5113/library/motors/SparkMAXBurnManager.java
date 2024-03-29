package com.frc5113.library.motors;

import edu.wpi.first.wpilibj.RobotBase;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Determines whether to burn SparkMAX configs to flash. Must be configured with a build date before
 * use, will default to burn if not set
 */
public class SparkMAXBurnManager {
  private static final String buildDateFile = "/home/lvuser/build-date.txt";
  private static boolean shouldBurn = false;
  private static String buildDate;

  private SparkMAXBurnManager() {}

  public static boolean shouldBurn() {
    return shouldBurn;
  }

  public static void update() {
    if (RobotBase.isSimulation()) {
      System.out.println(
          "[SparkMAXBurnManager] Running in simulation, will not burn SparkMAX flash.");
      shouldBurn = false;
      return;
    }

    // if the class wasn't configured properly, just burn
    if (SparkMAXBurnManager.buildDate == null) {
      shouldBurn = true;
      return;
    }

    File file = new File(buildDateFile);
    if (!file.exists()) {

      // No build date file, burn flash
      shouldBurn = true;
    } else {

      // Read previous build date
      String previousBuildDate = "";
      try {
        previousBuildDate = Files.readString(Paths.get(buildDateFile));
      } catch (IOException e) {
        e.printStackTrace();
      }

      shouldBurn = !previousBuildDate.equals(buildDate);
    }

    try {
      FileWriter fileWriter = new FileWriter(buildDateFile);
      fileWriter.write(buildDate);
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (shouldBurn) {
      System.out.println("[SparkMAXBurnManager] Build date changed, burning SparkMAX flash");
    } else {
      System.out.println(
          "[SparkMAXBurnManager] Build date unchanged, will not burn SparkMAX flash");
    }
  }

  public static void setBuildDate(String buildDate) {
    SparkMAXBurnManager.buildDate = buildDate;
  }
}
