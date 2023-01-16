package com.frc5113.library.oi.sticks;

import com.frc5113.library.oi.scalers.Curve;
import com.frc5113.library.primative.Axis;
import edu.wpi.first.wpilibj.Joystick;

/**
 * Larger sticks (full hand ones) also come with twist functionality. This class also adds
 * representation for the twist axis, but is still based on a 2d joystick.
 *
 * @author Vladimir Bondar
 */
public class Stick extends ThumbStick {
  Axis twistAxis;
  Curve twistCurve;

  public Stick(Joystick controller, Axis xAxis, Axis yAxis, Axis twistAxis) {
    super(controller, xAxis, yAxis);
    this.twistAxis = twistAxis;
  }

  public Stick(
      Joystick controller,
      Axis xAxis,
      Axis yAxis,
      Axis twistAxis,
      Curve xCurve,
      Curve yCurve,
      Curve twistCurve) {
    super(controller, xAxis, yAxis, xCurve, yCurve);
    this.twistAxis = twistAxis;
    this.twistCurve = twistCurve;
  }

  public double getTwist() {
    double value = 0;
    if (this.controller.isConnected()) {
      value = this.controller.getRawAxis(twistAxis.getValue());
      value = twistCurve.calculateMappedVal(value);
    } else {
      System.err.println(
          "WARNING: Controller on port " + this.controller.getPort() + " disconnected");
    }
    return value;
  }
}
