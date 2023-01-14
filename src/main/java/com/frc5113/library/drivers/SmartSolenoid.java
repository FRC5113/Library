package com.frc5113.library.drivers;

import com.frc5113.library.primative.RobotState;
import com.frc5113.library.primative.StatefulRobot;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Minor modifications to the default solenoid to allow for faster performance
 *
 * @author Cheezy Poofs (254), Spectrum (3847), Vladimir Bondar (5113)
 */
public class SmartSolenoid extends Solenoid {
  private boolean isOn = false;
  private boolean wasDisabled = true;

  private final StatefulRobot robot;

  // Allen - Allows the use of 0-13 to set solenoid number, ports 0-7 on module 2
  // or solenoids 8-13.
  public SmartSolenoid(int channel, StatefulRobot robot) {
    super(
        (channel > 7 ? 1 : 0), PneumaticsModuleType.CTREPCM, (channel > 7 ? channel - 8 : channel));
    this.robot = robot;
  }

  // Allen - Allows the use of 0-13 to set solenoid number, ports 0-7 on module 2
  // or solenoids 8-13.
  public SmartSolenoid(final PneumaticsModuleType moduleType, int channel, StatefulRobot robot) {
    super(moduleType, channel);
    this.robot = robot;
  }

  @Override
  // Allen - Only update solenoid if we need to send a new command or the robot
  // state changed from or to Disabled
  public void set(boolean value) {
    boolean is_disabled = robot.getState() == RobotState.DISABLED;
    if ((!is_disabled && wasDisabled) || value != isOn) {
      super.set(value);
    }
    isOn = value;
    wasDisabled = is_disabled;
  }

  @Override
  // Allen - Don't waste time with a CAN command to check the state
  public boolean get() {
    return isOn;
  }
}
