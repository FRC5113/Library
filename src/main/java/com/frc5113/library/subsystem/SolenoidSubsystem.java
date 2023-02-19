package com.frc5113.library.subsystem;

import com.frc5113.library.drivers.SmartSolenoid;
import com.frc5113.library.loops.ILooper;
import com.frc5113.library.loops.Loop;
import com.frc5113.library.state.StatefulRobot;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.RunCommand;

/** A subsystem consisting of a single solenoid that can be activated and deactivated */
public class SolenoidSubsystem extends SmartSubsystem {
  public final SmartSolenoid solenoid;

  /**
   * Create a new single solenoid subsystem
   *
   * @param name Name of the subsystem
   * @param port Solenoid connection port (on distribution panel)
   * @param robot Main robot instance for deactivation detection
   */
  public SolenoidSubsystem(String name, int port, StatefulRobot robot) {
    setName(name);
    solenoid = new SmartSolenoid(PneumaticsModuleType.REVPH, port, robot);

    // Default to down
    this.setDefaultCommand(new RunCommand(this::off, this));
  }

  /**
   * Create a new single solenoid subsystem
   *
   * @param name Name of the subsystem
   * @param port Solenoid connection port (on distribution panel)
   * @param robot Main robot instance for deactivation detection
   * @param type Type of Pneumatics hub
   */
  public SolenoidSubsystem(String name, int port, StatefulRobot robot, PneumaticsModuleType type) {
    setName(name);
    solenoid = new SmartSolenoid(type, port, robot);

    // Default to down
    this.setDefaultCommand(new RunCommand(this::off, this));
  }

  public void on() {
    solenoid.set(true);
  }

  public void off() {
    solenoid.set(false);
  }

  @Override
  public boolean checkSubsystem() {
    solenoid.get(); // check that solenoid is alive
    return true;
  }

  @Override
  public void registerPeriodicSubsystemCheck(ILooper mCheckLooper) {
    mCheckLooper.register(
      new Loop() {
        @Override
        public void onStart(double timestamp) {}
        
        @Override
        public void onLoop(double timestamp) {}

        @Override
        public void onStop(double timestamp) {}
      });
  }

  @Override
  public void outputTelemetry() {
    SmartDashboard.putBoolean(getName() + " Activated", solenoid.get());
  }

  @Override
  public void stop() {
    // nothing to "stop"
  }

  @Override
  public void zeroSensors() {
    // nothing to zero (possibly fold solenoid)
  }

  @Override
  public void readPeriodicInputs() {
    solenoid.get();
  }

  @Override
  public void writePeriodicOutputs() {}

  @Override
  public void registerEnabledLoops(ILooper mEnabledLooper) {
    mEnabledLooper.register(
        new Loop() {
          @Override
          public void onStart(double timestamp) {
            solenoid.set(false);
          }

          @Override
          public void onLoop(double timestamp) {}

          @Override
          public void onStop(double timestamp) {
            stop();
          }
        });
  }
}
