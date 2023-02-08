package com.frc5113.library.primative;

import edu.wpi.first.math.geometry.Rotation2d;

public class GyroState {
  private double yaw;
  private double pitch;
  private double roll;

  public GyroState(double yaw, double pitch, double roll) {
    this.yaw = yaw;
    this.pitch = pitch;
    this.roll = roll;
  }

  /**
   * Add a new measurement from the gyro (replace old one)
   *
   * @param yaw Yaw (X)
   * @param pitch Pitch (Y)
   * @param roll Roll (Z)
   */
  public void setMeasurement(double yaw, double pitch, double roll) {
    this.yaw = yaw;
    this.pitch = pitch;
    this.roll = roll;
  }

  /**
   * Get yaw (rotation in the X plane)
   *
   * @return Yaw
   */
  public double getYaw() {
    return yaw;
  }

  /**
   * Get rotation relative to the ground
   *
   * @return Rotation
   */
  public double getRotation() {
    return getYaw();
  }

  /**
   * Get rotation relative to the ground as a 2D object
   *
   * @return Rotation (as Rotation2d)
   */
  public Rotation2d getRotation2d() {
    // Get my gyro angle. We are negating the value because gyros return positive
    // values as the robot turns clockwise. This is not standard convention that is
    // used by the WPILib classes.
    return Rotation2d.fromDegrees(-getRotation());
  }

  /**
   * Get pitch (rotation in the Y plane)
   *
   * @return Pitch
   */
  public double getPitch() {
    return pitch;
  }

  /**
   * Get roll (rotation in the Z plane)
   *
   * @return Roll
   */
  public double getRoll() {
    return roll;
  }
}
