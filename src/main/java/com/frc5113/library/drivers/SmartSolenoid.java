package com.frc5113.library.drivers;

import com.frc5113.library.state.StatefulRobot;
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

  /** Set the value of the solenoid */
  @Override
  public void set(boolean value) {
    if (value != isOn) {
      super.set(value);
    }
    isOn = value;
  }

  /** Don't waste time with a CAN command to check the com.frc5113.library.state */
  @Override
  public boolean get() {
    return isOn;
  }
}
