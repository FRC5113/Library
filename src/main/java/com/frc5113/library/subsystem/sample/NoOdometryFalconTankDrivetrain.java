package com.frc5113.library.subsystem.sample;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.frc5113.library.motors.SmartFalcon;
import com.frc5113.library.primative.motorselector.TankMotorSelector;
import com.frc5113.library.subsystem.SmartSubsystem;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A Drivetrain class for a tank drive consisting of two falcons per side, without any gyro or odometry code
 */
public class NoOdometryFalconTankDrivetrain extends SmartSubsystem {

    public final SmartFalcon leftParent;
    public final SmartFalcon rightParent;
    private final SmartFalcon leftChild;
    private final SmartFalcon rightChild;
    private final DifferentialDrive driveBase;

    /**
     * Create tank with custom deadband and rampRate parameters.
     * <p> Location is based on the forward moving direction of the robot (stand facing the same way the front of the robot is facing)
     *
     * @param leftParentId CAN ID of left parent
     * @param rightParentId CAN ID of right parent
     * @param leftChildId CAN ID of left child
     * @param rightChildId CAN ID of right child
     * @param deadBand Deadband of differential drive
     * @param rampRate Motor ramp rate
     * @param neutralMode What to do in neutral mode
     */
    public NoOdometryFalconTankDrivetrain(int leftParentId, int rightParentId, int leftChildId, int rightChildId, double deadBand, double rampRate, NeutralMode neutralMode) {
        leftParent = new SmartFalcon(leftParentId);
        rightParent = new SmartFalcon(rightParentId);
        leftChild = new SmartFalcon(leftChildId);
        rightChild = new SmartFalcon(rightChildId);

        configureMotor(leftParent, true, rampRate, neutralMode);
        configureMotor(rightParent, false, rampRate, neutralMode);
        configureMotor(leftChild, true, rampRate, neutralMode);
        configureMotor(rightChild, false, rampRate, neutralMode);

        rightChild.set(ControlMode.Follower, rightParent.getDeviceID());
        leftChild.set(ControlMode.Follower, leftParent.getDeviceID());

        driveBase = new DifferentialDrive(leftParent, rightParent);
        driveBase.setDeadband(deadBand);
        driveBase.setSafetyEnabled(false);
    }

    /**
     * Create tank with default parameters (deadband = 0.1), but a custom neutral mode and motor ramp rate.
     * <p> Location is based on the forward moving direction of the robot (stand facing the same way the front of the robot is facing)
     *
     * @param leftParentId CAN ID of left parent
     * @param rightParentId CAN ID of right parent
     * @param leftChildId CAN ID of left child
     * @param rightChildId CAN ID of right child
     * @param rampRate      Rate at which motor accelerates
     * @param neutralMode What to do in neutral mode
     */
    public NoOdometryFalconTankDrivetrain(int leftParentId, int rightParentId, int leftChildId, int rightChildId, double rampRate, NeutralMode neutralMode) {
        this(leftParentId, rightParentId, leftChildId, rightChildId, 0.1, rampRate, neutralMode);
    }

    /**
     * Create tank with default parameters (deadband = 0.1, ramp_rate = 0.1), but a custom neutral mode.
     * <p> Location is based on the forward moving direction of the robot (stand facing the same way the front of the robot is facing)
     *
     * @param leftParentId CAN ID of left parent
     * @param rightParentId CAN ID of right parent
     * @param leftChildId CAN ID of left child
     * @param rightChildId CAN ID of right child
     * @param neutralMode What to do in neutral mode
     */
    public NoOdometryFalconTankDrivetrain(int leftParentId, int rightParentId, int leftChildId, int rightChildId, NeutralMode neutralMode) {
        this(leftParentId, rightParentId, leftChildId, rightChildId, 0.1, 0.1, neutralMode);
    }


    /**
     * Create tank with default parameters (deadband = 0.1, ramp_rate = 0.1, neutral_mode = coast).
     * <p> Location is based on the forward moving direction of the robot (stand facing the same way the front of the robot is facing)
     *
     * @param leftParentId CAN ID of left parent
     * @param rightParentId CAN ID of right parent
     * @param leftChildId CAN ID of left child
     * @param rightChildId CAN ID of right child
     */
    public NoOdometryFalconTankDrivetrain(int leftParentId, int rightParentId, int leftChildId, int rightChildId) {
        this(leftParentId, rightParentId, leftChildId, rightChildId, 0.1, 0.1, NeutralMode.Coast);
    }

    /**
     * Configure the motors to make sure that their settings are correct.
     * <p> Location is based on the forward moving direction of the robot (stand facing the same way the front of the robot is facing)
     *
     * @param motor Motor
     * @param left whether it's located on the left side of the robot in the direction it is facing
     */
    private void configureMotor(WPI_TalonFX motor, boolean left, double rampRate, NeutralMode neutralMode) {
        motor.configFactoryDefault(); // Resetting the motors to make sure there's no junk on there
        // before configuring
        // motor.configVoltageCompSaturation(DRIVE_MAX_VOLTAGE); // only use 12.3 volts regardless of
        // battery voltage
        // motor.enableVoltageCompensation(true); // enable ^
        motor.setInverted(!left);
        motor.setNeutralMode(
                neutralMode); // set it so that when the motor is getting no input, it stops
        motor.configSelectedFeedbackSensor(
                FeedbackDevice.IntegratedSensor); // configure the encoder (it's inside)
        motor.setSelectedSensorPosition(0); // reset the encoder to have a value of 0
        motor.configOpenloopRamp(rampRate); // how long it takes to go from 0 to the set speed
        motor.setSensorPhase(true);
        // motor.config_kP(0, 0.001);
        // motor.config_kI(0, 0);
        // motor.config_kD(0, 0);
        // motor.config_kF(0, 0);
        // Make sure that both sides' encoders are getting positive values when going
        // forward
    }

    /**
     * Drive the left and right side at custom speeds 0-1.
     * <p>
     * Location is based on the forward moving direction of the robot (stand facing the same way the front of the robot is facing)
     * @param leftSpeed Speed of the left side [0.0, 1.0]
     * @param rightSpeed Speed of the right side [0.0, 1.0]
     */
    public void tankDrive(double leftSpeed, double rightSpeed) {
        leftParent.set(leftSpeed);
        rightParent.set(rightSpeed);
    }

    /**
     * Drive the left and right side at custom speeds 0-1, <b>but using voltage control</b>. <b>NOT set it and forget it, must be updated</b>.
     * <p>
     * Location is based on the forward moving direction of the robot (stand facing the same way the front of the robot is facing)
     * @param leftSpeed Speed of the left side [0.0, 1.0]
     * @param rightSpeed Speed of the right side [0.0, 1.0]
     */
    public void tankDriveVolts(double leftSpeed, double rightSpeed) {
        leftParent.setVoltage(12.4 * leftSpeed);
        rightParent.setVoltage(-12.4 * rightSpeed);
        driveBase.feed(); // make sure the motor safety watchdog doesn't do anything stupid
    }

    /**
     * Drive the left and right side <b>using voltage control</b>. <b>NOT set it and forget it, must be updated</b>.
     * <p> Location is based on the forward moving direction of the robot (stand facing the same way the front of the robot is facing)
     * @param leftSpeed Speed of the left side [0.0, ~12.4]
     * @param rightSpeed Speed of the right side [0.0, ~12.4]
     */
    public void tankDrivePureVolts(double leftSpeed, double rightSpeed) {
        leftParent.setVoltage(12.4 * leftSpeed);
        rightParent.setVoltage(-12.4 * rightSpeed);
        driveBase.feed(); // make sure the motor safety watchdog doesn't do anything stupid
    }

    /**
     * Arcade drive (speed and turn). The calculated values will be squared to decrease sensitivity at low speeds.
     *
     * @param speed The robot's speed along the X axis [-1.0, 1.0]. Forward is positive.
     * @param rotation The robot's rotation rate around the Z axis [-1.0, 1.0]. Clockwise is positive.
     */
    public void arcadeDrive(double speed, double rotation) {
        // How fast you want to go forward, and how much you want to turn
        driveBase.arcadeDrive(speed, rotation);
    }

    /**
     * Curvature drive method for differential drive platform.
     *
     * <p>The rotation argument controls the curvature of the robot's path rather than its rate of
     * heading change. This makes the robot more controllable at high speeds.</p>
     *
     * <p>This version turns in place only at low speeds, shouldn't be an issue
     *
     * @param speed The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
     * @param rotation The normalized curvature [-1.0..1.0]. Clockwise is positive.
     */
    public void curvatureDrive(double speed, double rotation) {
        /*
         * These should help to control curvature drive, but testing needs to be done
         * driveBase.setQuickStopAlpha(something); driveBase.setQuickStopThreshold(something);
         */

        driveBase.curvatureDrive(speed, rotation, speed < 0.25);
    }

    /**
     * Curvature drive method for differential drive platform.
     *
     * <p>The rotation argument controls the curvature of the robot's path rather than its rate of
     * heading change. This makes the robot more controllable at high speeds.</p>
     *
     * @param speed The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
     * @param rotation The normalized curvature [-1.0..1.0]. Clockwise is positive.
     * @param inPlaceTurns Turn in place? (should be a yes)
     */
    public void curvatureDrive(double speed, double rotation, boolean inPlaceTurns) {
        /*
         * These should help to control curvature drive, but testing needs to be done
         * driveBase.setQuickStopAlpha(something); driveBase.setQuickStopThreshold(something);
         */

        driveBase.curvatureDrive(speed, rotation, inPlaceTurns);
    }

    /**
     * Arcade drive (speed and turn), but the speed is controlled by two triggers (forward and backward - where the speed is <code>forward - backward</code>). The calculated values will be squared to decrease sensitivity at low speeds.
     *
     * @param forward The robot's speed along the X axis [0, 1.0]. Moves robot forward by this amount.
     * @param reverse The robot's speed along the X axis [0, 1.0]. Moves robot backward by this amount.
     * @param rotation The robot's rotation rate around the Z axis [-1.0, 1.0]. Clockwise is positive.
     */
    public void triggerDrive(double forward, double reverse, double rotation) {
        // Basically how driving works in Forza, uses triggers
        driveBase.arcadeDrive(forward - reverse, rotation);
    }

    /**
     * Set the max output of the motors on the drivetrain (useful for a global limiter)
     * @param max Multiplied with the output percentage computed by the drive functions.
     */
    public void setMaxOutput(double max) {
        driveBase.setMaxOutput(max);
    }

    /**
     * Check if a motors works
     * @param motor Motor to test on a tank drive (format {@link TankMotorSelector})
     */
    public void motorTest(TankMotorSelector motor) {
        switch (motor) {
            case rightParent:
                this.rightParent.set(0.5);
            case leftParent:
                this.leftParent.set(0.5);
            case rightChild:
                this.rightChild.set(0.5);
            case leftChild:
                this.leftChild.set(0.5);
        }
    }

    public void setAllToCoast() {
        leftParent.setNeutralMode(NeutralMode.Coast);
        rightParent.setNeutralMode(NeutralMode.Coast);
        leftChild.setNeutralMode(NeutralMode.Coast);
        rightChild.setNeutralMode(NeutralMode.Coast);
    }

    public void setAllToBrake() {
        leftParent.setNeutralMode(NeutralMode.Brake);
        rightParent.setNeutralMode(NeutralMode.Brake);
        leftChild.setNeutralMode(NeutralMode.Brake);
        rightChild.setNeutralMode(NeutralMode.Brake);
    }

    public void resetEncoders() {
        leftParent.setSelectedSensorPosition(0);
        leftChild.setSelectedSensorPosition(0);
        rightParent.setSelectedSensorPosition(0);
        rightChild.setSelectedSensorPosition(0);
    }

    /**
     * Output telemetry
     */
    @Override
    public void outputTelemetry() {
        SmartDashboard.putData("Drivetrain/RightParent", rightParent);
        SmartDashboard.putData("Drivetrain/LeftParent", leftParent);
        SmartDashboard.putData("Drivetrain/RightChild", rightParent);
        SmartDashboard.putData("Drivetrain/LeftChild", leftChild);
    }

    /**
     * Have the subsystem make sure that everything is good
     *
     * @return Check status (success - true / fail - false)
     */
    @Override
    public boolean checkSubsystem() {
        return rightParent.isConnected() && leftParent.isConnected() && rightChild.isConnected() && leftChild.isConnected();
    }

    /**
     * Stop everything in the subsystem (now)
     */
    @Override
    public void stop() {
        leftParent.set(0);
        leftChild.set(0);
        rightParent.set(0);
        rightChild.set(0);
    }

    /**
     * Reset all the motors
     */
    @Override
    public void zeroSensors() {
        resetEncoders();
    }
}
