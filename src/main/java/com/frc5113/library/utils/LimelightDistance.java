package com.frc5113.library.utils;

import edu.wpi.first.math.util.Units;

/**
 * Sets up Limelight Distance Calculations.
 * all Constructors should be in inches, all calculations are done in Meters.
*/
public class LimelightDistance {
    private final double targetHeight;
    private final double limelightHeight;
    private final double limelightAngle;
    private double distanceToTarget;

    /**
     * Information about the limelight's mounting
     * @param targetHeight Height of the target (Inches)
     * @param limelightHeight Height of the limelight (from ground to camera) (Inches)
     * @param limelightAngle Angle of the limelight relative to the ground in +X (Degrees)
     */
    public LimelightDistance(double targetHeight, double limelightHeight, double limelightAngle){
        this.targetHeight = targetHeight;
        this.limelightHeight = limelightHeight;
        this.limelightAngle = limelightAngle;
    }
    public double distanceInches(double targetAngle){
        distanceToTarget = ((targetHeight - limelightHeight) / Math.tan(limelightAngle + Math.toRadians(targetAngle)));
        return Units.metersToInches(distanceToTarget);
    }
    public double distanceFeet(double targetAngle){
        distanceToTarget = ((targetHeight - limelightHeight) / Math.tan(limelightAngle + Math.toRadians(targetAngle)));
        return Units.metersToFeet(distanceToTarget);
    }
    public double distanceMeters(double targetAngle){
        distanceToTarget = ((targetHeight - limelightHeight) / Math.tan(limelightAngle + Math.toRadians(targetAngle)));
        return distanceToTarget;
    }
}
