package com.frc5113.library.utils;

import edu.wpi.first.math.util.Units;

/**
 * Do Limelight Distance Calculations easily. All constructors parameters should be in inches, while
 * all calculations are done in meters.
 */
public class LimelightDistance {
  private final double targetHeight;
  private final double limelightHeight;
  private final double limelightAngle;

  /**
   * Information about the limelight's mounting
   *
   * @param targetHeight Height of the target (Inches)
   * @param limelightHeight Height of the limelight (from ground to camera) (Inches)
   * @param limelightAngle Angle of the limelight relative to the ground in +X (Degrees)
   */
  public LimelightDistance(double targetHeight, double limelightHeight, double limelightAngle) {
    this.targetHeight = targetHeight;
    this.limelightHeight = limelightHeight;
    this.limelightAngle = limelightAngle;
  }

  public double distanceInches(double targetAngle) {
    return Units.metersToInches(distanceMeters(targetAngle));
  }

  public double distanceFeet(double targetAngle) {
    return Units.metersToFeet(distanceMeters(targetAngle));
  }

  public double distanceMeters(double targetAngle) {
    double distanceToTarget =
        ((targetHeight - limelightHeight) / Math.tan(limelightAngle + Math.toRadians(targetAngle)));
    return distanceToTarget;
  }
}
