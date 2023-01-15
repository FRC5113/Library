package com.frc5113.library.oi.buttons;

import com.frc5113.library.oi.buttons.pure.AxisButtonSupplier;
import com.frc5113.library.primative.Axis;
import com.frc5113.library.primative.ThresholdType;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/** Treat an axis as if it was a button */
public class AxisButton extends Trigger {

  public AxisButton(GenericHID joystick, int axis, double threshold, ThresholdType thresholdType) {
    super(new AxisButtonSupplier(joystick, axis, threshold, thresholdType));
  }

  public AxisButton(GenericHID joystick, Axis axis, double threshold, ThresholdType thresholdType) {
    this(joystick, axis.getValue(), threshold, thresholdType);
  }
}
