package com.frc5113.library.subsystem;

import com.frc5113.library.loops.ILooper;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Use this class when creating a subsystem. This will allow it to be loaded into the subsystem
 * manager for easy telemetry and debugging. It also allows the subsystem to be put into a subsystem
 * manager.
 */
public abstract class SmartSubsystem extends SubsystemBase {
  public SmartSubsystem() {
    super();
  }

  /** Do telemetry, for example shuffleboard */
  public abstract void outputTelemetry();

  /**
   * Have the subsystem make sure that everything is good. Should print debug data if something
   * fails
   *
   * @return Check status (success - true / fail - false)
   */
  public abstract boolean checkSubsystem();

  /**
   * Adds functions to be executed at the start, periodic, and end of the robot life cycle.
   * Used to perform simple checks to verify the correct operation of a subsystem. Should the checks fail, errors must be reported.
   */
  public abstract void registerPeriodicSubsystemCheck(ILooper mCheckLooper);

  /** Stop everything in the subsystem (now) */
  public abstract void stop();

  /** Reset all the motors */
  public abstract void zeroSensors();

  /**
   * Add functions to be executed at the start, periodic, and end of the robot lifecycle. Optional.
   */
  public abstract void registerEnabledLoops(ILooper mEnabledLooper);

  /** Read information from constricted resources (ie CAN) */
  public abstract void readPeriodicInputs();

  /** Write periodic information to constricted resources (ie CAN) */
  public abstract void writePeriodicOutputs();

  // Periodic doesn't need to exist
  @Override
  public void periodic() {}
}
