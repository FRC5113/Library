package com.frc5113.library.oi.joystick;

import com.frc5113.library.oi.Dpad;
import com.frc5113.library.oi.buttons.Button;
import com.frc5113.library.oi.scalers.Curve;
import com.frc5113.library.oi.sticks.Slider;
import com.frc5113.library.oi.sticks.Stick;
import com.frc5113.library.primative.Axis;

/**
 * Equivalent to a flight simulator joystick commonly used in FRC: <a
 * href="https://docs.wpilib.org/en/stable/_images/joystick.webp">Example Image</a>
 */
public class Joystick extends edu.wpi.first.wpilibj.Joystick {

  public Curve xCurve;
  public Curve yCurve;
  public Curve tCurve;

  /**
   * Create simple joystick (with no-op curves)
   *
   * @param port DS Port of Joystick
   */
  public Joystick(int port) {
    super(port);
  }

  /**
   * Create with curves
   *
   * @param port DS Port of Joystick
   * @param xCurve Curve to adjust X Axis
   * @param yCurve Curve to adjust Y Axis
   * @param tCurve Curve to adjust Twist Axis
   */
  public Joystick(int port, Curve xCurve, Curve yCurve, Curve tCurve) {
    this(port);
    this.xCurve = xCurve;
    this.yCurve = yCurve;
    this.tCurve = tCurve;
  }

  /** Trigger index finger button */
  public Button triggerButton = new Button(this, JoystickButton.k1);
  /** Button on thumb rest */
  public Button thumbButton = new Button(this, JoystickButton.k2);
  /** Button 3 located in the bottom left of the head */
  public Button headBottomLeftButton = new Button(this, JoystickButton.k3);
  /** Button 4 located in the bottom right of the head */
  public Button headBottomRightButton = new Button(this, JoystickButton.k4);
  /** Button 5 located in the top left of the head */
  public Button headTopLeftButton = new Button(this, JoystickButton.k5);
  /** Button 6 located in the top right of the head */
  public Button headTopRightButton = new Button(this, JoystickButton.k6);
  /** Button 7 located in the top left of the base */
  public Button baseTopLeftButton = new Button(this, JoystickButton.k7);
  /** Button 8 located in the top right of the base */
  public Button baseTopRightButton = new Button(this, JoystickButton.k8);
  /** Button 9 located in the middle left of the base */
  public Button baseMiddleLeftButton = new Button(this, JoystickButton.k9);
  /** Button 10 located in the middle right of the base */
  public Button baseMiddleRightButton = new Button(this, JoystickButton.k10);
  /** Button 11 located in the bottom left of the base */
  public Button baseBottomLeftButton = new Button(this, JoystickButton.k11);
  /** Button 12 located in the bottom right of the base */
  public Button baseBottomRightButton = new Button(this, JoystickButton.k12);

  /** POV Hat DPad */
  public Dpad dPad = new Dpad(this, JoystickAxis.POV);

  /** Controller stick */
  public Stick stick =
      new Stick(this, JoystickAxis.X, JoystickAxis.Y, JoystickAxis.Twist, xCurve, yCurve, tCurve);

  /** Base slider */
  public Slider slider = new Slider(this, JoystickAxis.Slider);

  /** Button mapping to joystick buttons */
  public enum JoystickButton {
    k1(1),
    k2(2),
    k3(3),
    k4(4),
    k5(5),
    k6(6),
    k7(7),
    k8(8),
    k9(9),
    k10(10),
    k11(11),
    k12(12);

    public final int value;

    JoystickButton(int value) {
      this.value = value;
    }
  }

  /** Movement (axis of freedom) axis */
  public enum JoystickAxis implements Axis {
    X(1),
    Y(2),
    Twist(3),
    Slider(4),
    POV(5);

    public final int value;

    JoystickAxis(int value) {
      this.value = value;
    }

    /**
     * Get the name of the value of the current
     *
     * @return name of the current value
     */
    @Override
    public String toString() {
      return this.name();
    }

    /**
     * Get the enum corresponding to the integer value
     *
     * @return Enum with corresponding value || null
     */
    public JoystickAxis fromValue(int mValue) {
      for (JoystickAxis v : values()) {
        if (v.value == mValue) {
          return v;
        }
      }
      return null;
    }

    /**
     * Get the value of the value
     *
     * @return <code>value</code>
     */
    public int getValue() {
      return value;
    }
  }
}
