package com.frc5113.library.oi.xbox;

import com.frc5113.library.oi.Dpad;
import com.frc5113.library.oi.buttons.AxisButton;
import com.frc5113.library.oi.scalers.Curve;
import com.frc5113.library.oi.scalers.NoOpCurve;
import com.frc5113.library.oi.scalers.SmoothCubicCurve;
import com.frc5113.library.oi.sticks.ThumbStick;
import com.frc5113.library.primative.ThresholdType;
import edu.wpi.first.wpilibj.XboxController;

public class XboxGamepad extends XboxController {

  public SmoothCubicCurve smoothCubicRX = new SmoothCubicCurve();
  public SmoothCubicCurve smoothCubicRY = new SmoothCubicCurve();
  public SmoothCubicCurve smoothCubicLX = new SmoothCubicCurve();
  public SmoothCubicCurve smoothCubicLY = new SmoothCubicCurve();

  public NoOpCurve noOp = new NoOpCurve();

  public Curve lxCurve;
  public Curve lyCurve;
  public Curve rxCurve;
  public Curve ryCurve;

  public com.frc5113.library.oi.buttons.Button xButton =
      new com.frc5113.library.oi.buttons.Button(this, XboxButton.X);
  public com.frc5113.library.oi.buttons.Button yButton =
      new com.frc5113.library.oi.buttons.Button(this, XboxButton.Y);
  public com.frc5113.library.oi.buttons.Button aButton =
      new com.frc5113.library.oi.buttons.Button(this, XboxButton.A);
  public com.frc5113.library.oi.buttons.Button bButton =
      new com.frc5113.library.oi.buttons.Button(this, XboxButton.B);
  public com.frc5113.library.oi.buttons.Button rightBumper =
      new com.frc5113.library.oi.buttons.Button(this, XboxButton.rightBumper);
  public com.frc5113.library.oi.buttons.Button leftBumper =
      new com.frc5113.library.oi.buttons.Button(this, XboxButton.leftBumper);
  public com.frc5113.library.oi.buttons.Button startButton =
      new com.frc5113.library.oi.buttons.Button(this, XboxButton.start);
  public com.frc5113.library.oi.buttons.Button backButton =
      new com.frc5113.library.oi.buttons.Button(this, XboxButton.back);
  public com.frc5113.library.oi.buttons.Button leftStickButton =
      new com.frc5113.library.oi.buttons.Button(this, XboxButton.leftStick);
  public com.frc5113.library.oi.buttons.Button rightStickButton =
      new com.frc5113.library.oi.buttons.Button(this, XboxButton.rightStick);

  public AxisButton leftTriggerButton =
      new AxisButton(this, XboxAxis.leftTrigger, .1, ThresholdType.GREATER_THAN);
  public AxisButton rightTriggerButton =
      new AxisButton(this, XboxAxis.rightTrigger, .1, ThresholdType.GREATER_THAN);
  public Dpad Dpad = new Dpad(this, XboxAxis.dPad);

  public ThumbStick leftStick;
  public ThumbStick rightStick;

  public Triggers triggers = new Triggers(this);

  public Rumble rumble = new Rumble(this);

  /**
   * Create with no curve. <b>Not recommended, specify deadband</b>
   *
   * @param port DS Port of gamepad
   */
  public XboxGamepad(int port) {
    this(port, new NoOpCurve(), new NoOpCurve(), new NoOpCurve(), new NoOpCurve());
  }

  /**
   * Create with a default smoothCubic Curve
   *
   * @param port DS Port of gamepad
   * @param xDeadband deadband value for x-axis (0.05 recommended)
   * @param yDeadband deadband value for y-axis (0.05 recommended)
   */
  public XboxGamepad(int port, double xDeadband, double yDeadband) {
    this(
        port,
        new SmoothCubicCurve().setDeadDiameter(xDeadband),
        new SmoothCubicCurve().setDeadDiameter(yDeadband),
        new SmoothCubicCurve().setDeadDiameter(xDeadband),
        new SmoothCubicCurve().setDeadDiameter(yDeadband));
  }

  /**
   * Create with default smoothCubic Curve with separate left and right dead bands
   *
   * @param port DS Port of gamepad
   * @param leftXDeadband deadband value for left stick x-axis
   * @param leftYDeadband deadband value for left stick y-axis
   * @param rightXDeadband deadband value for right stick x-axis
   * @param rightYDeadband deadband value for right stick y-axis
   */
  public XboxGamepad(
      int port,
      double leftXDeadband,
      double leftYDeadband,
      double rightXDeadband,
      double rightYDeadband) {
    this(
        port,
        new SmoothCubicCurve().setDeadDiameter(leftXDeadband),
        new SmoothCubicCurve().setDeadDiameter(leftYDeadband),
        new SmoothCubicCurve().setDeadDiameter(rightXDeadband),
        new SmoothCubicCurve().setDeadDiameter(rightYDeadband));
  }

  /**
   * Create with custom user-specified curves
   *
   * @param port DS Port of gamepad
   * @param xCurve Curve to adjust X Axis
   * @param yCurve Curve to adjust Y Axis
   */
  public XboxGamepad(int port, Curve xCurve, Curve yCurve) {
    this(port, xCurve, yCurve, xCurve, yCurve);
  }

  /**
   * Create with separate curves for left and right
   *
   * @param port DS Port of gamepad
   * @param lxCurve Curve to adjust left thumbstick X Axis
   * @param lyCurve Curve to adjust left thumbstick Y Axis
   * @param rxCurve Curve to adjust right thumbstick X Axis
   * @param ryCurve Curve to adjust right thumbstick Y Axis
   */
  public XboxGamepad(int port, Curve lxCurve, Curve lyCurve, Curve rxCurve, Curve ryCurve) {
    super(port);
    this.lxCurve = lxCurve;
    this.lyCurve = lyCurve;
    this.rxCurve = rxCurve;
    this.ryCurve = ryCurve;

    // Define thumbsticks, which rely on the curves
    rightStick = new ThumbStick(this, XboxAxis.rightX, XboxAxis.rightY, rxCurve, ryCurve);
    leftStick = new ThumbStick(this, XboxAxis.leftX, XboxAxis.leftY, lxCurve, lyCurve);
  }

  /** Button IDs of an XBox controller */
  public enum XboxButton {
    A(1),
    B(2),
    X(3),
    Y(4),
    leftBumper(5),
    rightBumper(6),
    back(7),
    start(8),
    leftStick(9),
    rightStick(10);

    public final int value;

    XboxButton(int value) {
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
     * @param mValue The integer value corresponding to a enum
     * @return Enum with corresponding value || null
     */
    public XboxButton fromValue(int mValue) {
      for (XboxButton v : values()) {
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

  /**
   * The axis numerations of the XBox controller. Axis IDs of an XBox controller. An axis is a line
   * of freedom of movement (forward / backward) Should be equivalent to <a
   * href="https://github.wpilib.org/allwpilib/docs/release/java/src-html/edu/wpi/first/wpilibj/XboxController.html#line.57">WPI
   * Impl</a>
   */
  public enum XboxAxis implements com.frc5113.library.primative.Axis {
    leftX(0),
    leftY(1),
    leftTrigger(2),
    rightTrigger(3),
    rightX(4),
    rightY(5),
    dPad(6);

    public final int value;

    XboxAxis(int value) {
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
    public XboxAxis fromValue(int mValue) {
      for (XboxAxis v : values()) {
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
