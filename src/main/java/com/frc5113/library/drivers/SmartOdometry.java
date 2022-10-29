package com.frc5113.library.drivers;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveOdometry;
import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.wpilibj.Timer;

import com.frc5113.library.primative.OdometryType;
import com.frc5113.library.motors.SmartFalcon;



public class SmartOdometry{


    private final DifferentialDriveOdometry diffOdometry;
    private final MecanumDriveOdometry mecOdometry;
    private final MecanumDriveKinematics mecKinematics;

    private MecanumDriveWheelSpeeds mecWheelSpeeds;


    private final SmartFalcon frontLeft;
    private final SmartFalcon backLeft;
    private final SmartFalcon frontRight;
    private final SmartFalcon backRight;
    private final AHRS gyro;

    private Pose2d position;
    private double tankWheelDiameter;
    private double mecWheelDiameter;

    private OdometryType type;

    private Timer mecTimer;
    private double startTime;


    public SmartOdometry(
    OdometryType type, 
    Pose2d startPos,
    double wheelDiameter,
    SmartFalcon frontLeft, 
    SmartFalcon backLeft, 
    SmartFalcon frontRight, 
    SmartFalcon backRight) {

        this.frontLeft = frontLeft;
        this.backLeft = backLeft;
        this.frontRight = frontRight;
        this.backRight = frontRight;
        position = startPos;
        gyro = new AHRS();
        // gyro.setAngleAdjustment(90); // add 90deg to getAngle calls
        gyro.reset();
        tankWheelDiameter = wheelDiameter;

        this.type = type;
        //Setting up position tracking for tankdrive
        diffOdometry = new DifferentialDriveOdometry(gyro.getRotation2d(), position);
        mecWheelSpeeds = new MecanumDriveWheelSpeeds();

        mecTimer = new Timer();
        mecTimer.start();

        // Sets up mecanum position tracking. 
        mecKinematics =
            new MecanumDriveKinematics(
            new Translation2d(frontLeft.getRelativePosition().getX(), frontLeft.getRelativePosition().getY()),
            new Translation2d(frontRight.getRelativePosition().getX(), frontRight.getRelativePosition().getY()),
            new Translation2d(backLeft.getRelativePosition().getX(), backLeft.getRelativePosition().getY()),
            new Translation2d(backRight.getRelativePosition().getX(), backRight.getRelativePosition().getY())); 
        mecOdometry = new MecanumDriveOdometry(mecKinematics, gyro.getRotation2d(), position);
    }

    public SmartOdometry(
    OdometryType type, 
    Pose2d startPos, 
    double wheelDiameter,
    double mecWheelDiameter,
    SmartFalcon frontLeft, 
    SmartFalcon backLeft, 
    SmartFalcon frontRight, 
    SmartFalcon backRight) { 
        this(type, startPos, wheelDiameter, frontLeft, backLeft, frontRight, backRight);
        this.mecWheelDiameter = mecWheelDiameter;
    }


    public Pose2d update() {
        switch (type) {
            case differential:
                /*Question: differential drive odometry update method takes the meters travelled by the encoders as its 
                 * second and third parameters, not the meters travelled by the wheels. Does that multiplication have to
                 * happen after the calculations done. Check Twist2d class to see if this is a problem
                */
                diffOdometry.update(
                    gyro.getRotation2d(), 
                    frontLeft.getOutputDistance() * tankWheelDiameter, 
                    frontRight.getOutputDistance() * tankWheelDiameter);
                position = diffOdometry.getPoseMeters();
                break;
            case mecWheelDifferential:
            diffOdometry.update(
                gyro.getRotation2d(), 
                frontLeft.getOutputDistance() * mecWheelDiameter, 
                frontRight.getOutputDistance() * mecWheelDiameter);
                position = diffOdometry.getPoseMeters();
                break;
            case mecanum:
                //BUG: MecanumDriveWheelSpeeds parameters call for numbers between -1 and 1 and getSelectedSensor velocity does not return those.
                mecWheelSpeeds = new MecanumDriveWheelSpeeds(
                frontLeft.getSelectedSensorVelocity(), 
                frontRight.getSelectedSensorVelocity(),
                backLeft.getSelectedSensorVelocity(),
                backRight.getSelectedSensorVelocity());
                mecOdometry.updateWithTime(mecTimer.getFPGATimestamp() - startTime, gyro.getRotation2d(), mecWheelSpeeds);
                position = mecOdometry.getPoseMeters();
                break;
        }   
        return position;
    }

    public void switchType(OdometryType type) {
        this.type = type;
        frontLeft.resetEncoder();
        frontRight.resetEncoder();
        backLeft.resetEncoder();
        backRight.resetEncoder();
        startTime = mecTimer.getFPGATimestamp();
    }
}