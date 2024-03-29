package com.frc5113.library.oi.generic;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class SimpleGenericController extends GenericHID {
  /**
   * Create a new Simple Generic Controller
   *
   * @param port DS port (indicated in controller settings / preview)
   */
  public SimpleGenericController(int port) {
    super(port);
  }

  /**
   * Get the value at a specified boolean input
   *
   * @param button Port of desired button value
   * @return Current value of the button
   */
  public boolean getButtonValue(int button) {
    return this.getRawButton(button);
  }

  /**
   * Get the object representation of a button
   *
   * @param button Port of desired button value
   * @return Object of button
   */
  public Trigger getButton(int button) {
    return new JoystickButton(this, button);
  }

  /**
   * Set the current value of a boolean output (ie LED)
   *
   * @param port Port of desired output
   * @param value boolean value to set the output to (false =&gt; low, true =&gt; high)
   */
  public void setBooleanOutput(int port, boolean value) {
    this.setOutput(port, value);
  }
}
