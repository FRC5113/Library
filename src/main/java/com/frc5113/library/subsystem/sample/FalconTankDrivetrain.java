package com.frc5113.library.subsystem.sample;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.frc5113.library.loops.ILooper;
import com.frc5113.library.loops.Loop;
import com.frc5113.library.motors.SmartFalcon;
import com.frc5113.library.primative.GyroState;
import com.frc5113.library.primative.motorselector.TankMotorSelector;
import com.frc5113.library.subsystem.SmartSubsystem;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** A Drivetrain class for a tank drive consisting of two falcons per side */
public class FalconTankDrivetrain extends SmartSubsystem {

  public SmartFalcon leftParent;
  public SmartFalcon rightParent;
  private SmartFalcon leftChild;
  private SmartFalcon rightChild;
  private final DifferentialDrive driveBase;
  private final DifferentialDriveOdometry odometry;
  private final AHRS gyro;

  private GyroState gyroState;

  /**
   * Create tank with custom deadband, rampRate, gear ratio, output diameter parameters.
   *
   * <p>Location is based on the forward moving direction of the robot (stand facing the same way
   * the front of the robot is facing)
   *
   * @param leftParentId CAN ID of left parent
   * @param rightParentId CAN ID of right parent
   * @param leftChildId CAN ID of left child
   * @param rightChildId CAN ID of right child
   * @param deadBand Deadband of differential drive
   * @param rampRate Motor ramp rate
   * @param neutralMode What to do in neutral mode
   * @param gearRatio Gear ratio between shaft and output
   * @param outputDiameter Diameter of the output wheel
   */
  public FalconTankDrivetrain(
      int leftParentId,
      int rightParentId,
      int leftChildId,
      int rightChildId,
      double deadBand,
      double rampRate,
      NeutralMode neutralMode,
      double gearRatio,
      double outputDiameter) {
    super();
    leftParent = new SmartFalcon(leftParentId, false, neutralMode);
    leftParent = leftParent.setRampRate(rampRate);
    rightParent = new SmartFalcon(rightParentId, false, neutralMode);
    rightParent = rightParent.setRampRate(rampRate);
    leftChild = new SmartFalcon(leftChildId, false, neutralMode);
    leftChild = leftChild.setRampRate(rampRate);
    rightChild = new SmartFalcon(rightChildId, false, neutralMode);
    rightChild = rightChild.setRampRate(rampRate);

    rightChild.set(ControlMode.Follower, rightParent.getDeviceID());
    leftChild.set(ControlMode.Follower, leftParent.getDeviceID());

    driveBase = new DifferentialDrive(leftParent, rightParent);
    driveBase.setDeadband(deadBand);
    driveBase.setSafetyEnabled(false);

    gyro = new AHRS(SPI.Port.kMXP);
    gyro.enableLogging(true);
    updateGyroState();

    odometry =
        new DifferentialDriveOdometry(
            gyroState.getRotation2d(),
            leftParent.getOutputDistance(),
            rightChild.getOutputDistance());
  }

  /**
   * Create tank with default parameters (deadband = 0.1), but a custom neutral mode, motor ramp
   * rate, gear ratio, output diameter.
   *
   * <p>Location is based on the forward moving direction of the robot (stand facing the same way
   * the front of the robot is facing)
   *
   * @param leftParentId CAN ID of left parent
   * @param rightParentId CAN ID of right parent
   * @param leftChildId CAN ID of left child
   * @param rightChildId CAN ID of right child
   * @param rampRate Rate at which motor accelerates
   * @param neutralMode What to do in neutral mode
   * @param gearRatio Gear ratio between shaft and output
   * @param outputDiameter Diameter of the output wheel
   */
  public FalconTankDrivetrain(
      int leftParentId,
      int rightParentId,
      int leftChildId,
      int rightChildId,
      double rampRate,
      NeutralMode neutralMode,
      double gearRatio,
      double outputDiameter) {
    this(
        leftParentId,
        rightParentId,
        leftChildId,
        rightChildId,
        0.1,
        rampRate,
        neutralMode,
        gearRatio,
        outputDiameter);
  }

  /**
   * Create tank with default parameters (deadband = 0.1, ramp_rate = 0.1), but a custom neutral
   * mode, gear ratio, output diameter.
   *
   * <p>Location is based on the forward moving direction of the robot (stand facing the same way
   * the front of the robot is facing)
   *
   * @param leftParentId CAN ID of left parent
   * @param rightParentId CAN ID of right parent
   * @param leftChildId CAN ID of left child
   * @param rightChildId CAN ID of right child
   * @param neutralMode What to do in neutral mode
   * @param gearRatio Gear ratio between shaft and output
   * @param outputDiameter Diameter of the output wheel
   */
  public FalconTankDrivetrain(
      int leftParentId,
      int rightParentId,
      int leftChildId,
      int rightChildId,
      NeutralMode neutralMode,
      double gearRatio,
      double outputDiameter) {
    this(
        leftParentId,
        rightParentId,
        leftChildId,
        rightChildId,
        0.1,
        0.1,
        neutralMode,
        gearRatio,
        outputDiameter);
  }

  /**
   * Create tank with default parameters (deadband = 0.1, ramp_rate = 0.1, neutral_mode = coast).
   *
   * <p>Location is based on the forward moving direction of the robot (stand facing the same way
   * the front of the robot is facing)
   *
   * @param leftParentId CAN ID of left parent
   * @param rightParentId CAN ID of right parent
   * @param leftChildId CAN ID of left child
   * @param rightChildId CAN ID of right child
   * @param gearRatio Gear ratio between shaft and output
   * @param outputDiameter Diameter of the output wheel
   */
  public FalconTankDrivetrain(
      int leftParentId,
      int rightParentId,
      int leftChildId,
      int rightChildId,
      double gearRatio,
      double outputDiameter) {
    this(
        leftParentId,
        rightParentId,
        leftChildId,
        rightChildId,
        0.1,
        0.1,
        NeutralMode.Coast,
        gearRatio,
        outputDiameter);
  }

  /**
   * Create tank with default parameters (deadband = 0.1, ramp_rate = 0.1, neutral_mode = coast,
   * gear_ratio = 1, output_diameter = 1).
   *
   * <p>Location is based on the forward moving direction of the robot (stand facing the same way
   * the front of the robot is facing)
   *
   * @param leftParentId CAN ID of left parent
   * @param rightParentId CAN ID of right parent
   * @param leftChildId CAN ID of left child
   * @param rightChildId CAN ID of right child
   */
  public FalconTankDrivetrain(
      int leftParentId, int rightParentId, int leftChildId, int rightChildId) {
    this(leftParentId, rightParentId, leftChildId, rightChildId, 0.1, 0.1, NeutralMode.Coast, 1, 1);
  }

  /**
   * Drive the left and right side at custom speeds 0-1.
   *
   * <p>Location is based on the forward moving direction of the robot (stand facing the same way
   * the front of the robot is facing)
   *
   * @param leftSpeed Speed of the left side [0.0, 1.0]
   * @param rightSpeed Speed of the right side [0.0, 1.0]
   */
  public void tankDrive(double leftSpeed, double rightSpeed) {
    leftParent.set(leftSpeed);
    rightParent.set(rightSpeed);
  }

  /**
   * Drive the left and right side at custom speeds 0-1, <b>but using voltage control</b>. <b>NOT
   * set it and forget it, must be updated</b>.
   *
   * <p>Location is based on the forward moving direction of the robot (stand facing the same way
   * the front of the robot is facing)
   *
   * @param leftSpeed Speed of the left side [0.0, 1.0]
   * @param rightSpeed Speed of the right side [0.0, 1.0]
   */
  public void tankDriveVolts(double leftSpeed, double rightSpeed) {
    leftParent.setVoltage(12.4 * leftSpeed);
    rightParent.setVoltage(-12.4 * rightSpeed);
    driveBase.feed(); // make sure the motor safety watchdog doesn't do anything stupid
  }

  /**
   * Drive the left and right side <b>using voltage control</b>. <b>NOT set it and forget it, must
   * be updated</b>.
   *
   * <p>Location is based on the forward moving direction of the robot (stand facing the same way
   * the front of the robot is facing)
   *
   * @param leftSpeed Speed of the left side [0.0, ~12.4]
   * @param rightSpeed Speed of the right side [0.0, ~12.4]
   */
  public void tankDrivePureVolts(double leftSpeed, double rightSpeed) {
    leftParent.setVoltage(12.4 * leftSpeed);
    rightParent.setVoltage(-12.4 * rightSpeed);
    driveBase.feed(); // make sure the motor safety watchdog doesn't do anything stupid
  }

  /**
   * Arcade drive (speed and turn). The calculated values will be squared to decrease sensitivity at
   * low speeds.
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
   * heading change. This makes the robot more controllable at high speeds.
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
   * heading change. This makes the robot more controllable at high speeds.
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
   * Arcade drive (speed and turn), but the speed is controlled by two triggers (forward and
   * backward - where the speed is <code>forward - backward</code>). The calculated values will be
   * squared to decrease sensitivity at low speeds.
   *
   * @param forward The robot's speed along the X axis [0, 1.0]. Moves robot forward by this amount.
   * @param reverse The robot's speed along the X axis [0, 1.0]. Moves robot backward by this
   *     amount.
   * @param rotation The robot's rotation rate around the Z axis [-1.0, 1.0]. Clockwise is positive.
   */
  public void triggerDrive(double forward, double reverse, double rotation) {
    // Basically how driving works in Forza, uses triggers
    driveBase.arcadeDrive(forward - reverse, rotation);
  }

  /**
   * Set the max output of the motors on the drivetrain (useful for a global limiter)
   *
   * @param max Multiplied with the output percentage computed by the drive functions.
   */
  public void setMaxOutput(double max) {
    driveBase.setMaxOutput(max);
  }

  /**
   * Check if a motors works
   *
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

  /** Output telemetry */
  @Override
  public void outputTelemetry() {
    SmartDashboard.putData("Drivetrain/RightParent", rightParent);
    SmartDashboard.putData("Drivetrain/LeftParent", leftParent);
    SmartDashboard.putData("Drivetrain/RightChild", rightParent);
    SmartDashboard.putData("Drivetrain/LeftChild", leftChild);
  }

  /**
   * Have the subsystem make sure that everything is good. Prints debug data if something fails
   *
   * @return Check status (success - true / fail - false)
   */
  @Override
  public boolean checkSubsystem() {
    boolean rpStatus = rightParent.isConnected();
    boolean lpStatus = leftParent.isConnected();
    boolean rcStatus = rightChild.isConnected();
    boolean lcStatus = leftChild.isConnected();
    boolean gyroStatus = gyro.isConnected();
    if (rpStatus && lpStatus && rcStatus && lcStatus && gyroStatus) {
      return true;
    } else {
      System.out.println(
          "Drivetrain FAIL: Right Parent: "
              + rpStatus
              + "; Left Parent: "
              + lpStatus
              + "; Right Child: "
              + rcStatus
              + "; Left Child: "
              + lcStatus
              + "; Gyro: "
              + gyroStatus);
      return false;
    }
  }

  @Override
  public boolean checkSubsystemPeriodic() {
    return true;
  }

  /** Stop everything in the subsystem (now) */
  @Override
  public void stop() {
    leftParent.set(0);
    leftChild.set(0);
    rightParent.set(0);
    rightChild.set(0);
  }

  /** Reset all the motors */
  @Override
  public void zeroSensors() {
    resetEncoders();
  }

  @Override
  public void readPeriodicInputs() {
    updateGyroState();
  }

  @Override
  public void writePeriodicOutputs() {}

  @Override
  public void registerEnabledLoops(ILooper mEnabledLooper) {
    mEnabledLooper.register(
        new Loop() {

          @Override
          public void onStart(double timestamp) {
            setAllToBrake();
          }

          @Override
          public void onLoop(double timestamp) {
            // Get my gyro angle. We are negating the value because gyros return positive
            // values as the robot turns clockwise. This is not standard convention that is
            // used by the WPILib classes.
            Rotation2d gyroAngle = gyroState.getRotation2d();

            // Update the pose
            odometry.update(
                gyroAngle, leftParent.getOutputDistance(), rightParent.getOutputDistance());
          }

          @Override
          public void onStop(double timestamp) {
            setAllToCoast();
          }
        });
  }

  public void updateGyroState() {
    gyroState.setMeasurement(gyro.getYaw(), gyro.getPitch(), gyro.getRate());
  }

  // getter methods
  public AHRS getGyro() {
    return gyro;
  }

  public DifferentialDriveOdometry getOdometry() {
    return odometry;
  }

  @Override
  public void registerPeriodicSubsystemCheck(ILooper mCheckLooper) {
    mCheckLooper.register(
        new Loop() {
          @Override
          public void onStart(double timestamp) {}

          @Override
          public void onLoop(double timestamp) {
            if (!checkSubsystemPeriodic()) {
              DriverStation.reportError(
                  "Subsystem " + this.getClass().getName() + " failed check", false);
            }
          }

          @Override
          public void onStop(double timestamp) {}
        });
  }
}
