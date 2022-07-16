// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.frc5113.robot;

import com.frc5113.library.config.ConfigLibrary;
import com.frc5113.library.primative.SmartTimedRobot;
import com.frc5113.library.sim.PhysicsSim;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
//import frc.lib.sim.PhysicsSim;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project. <br/><br/>
 *
 * The robot class has been extended to include background updates (ie to state) in {@link SmartTimedRobot}.
 * To make sure that the background updates still happen, make sure to leave the super.{methodName} in place.
 */
public class Robot extends SmartTimedRobot {
  /**
   * The command that will be run in autonomous
   */
  private Command autonomousCommand;

  /**
   * Robot Container holds the grith of the logic that robot performs,
   * while Robot only calls the specific commands and the period loop actions
   */
  private RobotContainer robotContainer;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    super.robotInit();
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    robotContainer = new RobotContainer();

    // make sure to configure the internal workings of the lemons library
    // or else you may get unexpressed alerts / errors
    ConfigLibrary.setMainBotMAC(""); // TODO
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    super.robotPeriodic();
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    super.disabledInit();
  }

  @Override
  public void disabledPeriodic() {
    super.disabledPeriodic();
  }

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    super.autonomousInit();
    autonomousCommand = robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (autonomousCommand != null) {
      autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    super.autonomousPeriodic();
  }

  @Override
  public void teleopInit() {
    super.teleopInit();
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    super.teleopPeriodic();
  }

  @Override
  public void testInit() {
    super.testInit();
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    super.testPeriodic();
  }

  @Override
  public void simulationInit() {
    super.simulationInit();
    Sim.initialization();
  }

  @Override
  public void simulationPeriodic() {
    super.simulationPeriodic();
    PhysicsSim.getInstance().run();
  }
}
