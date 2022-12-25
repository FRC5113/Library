package com.frc5113.library.oi;

import com.frc5113.library.oi.buttons.AxisButton;
import com.frc5113.library.primative.Axis;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import com.frc5113.library.oi.buttons.AxisButton.ThresholdType;

public class Dpad {
    public final GenericHID joy;

    public final Axis axis;
    public AxisButton Up;
    public AxisButton Down;
    public AxisButton Left;
    public AxisButton Right;
    public AxisButton UpLeft;
    public AxisButton UpRight;
    public AxisButton DownLeft;
    public AxisButton DownRight;

    /**
     * Define the value on the POV hat (DPad) at which the dpad direction is activated.
     * This can be though of as passing a threshold, at which that button is active
     * <code>
     *     .value
     * </code>
     * return the value on the hat.
     */
    public enum DPadButtons {
        UNPRESSED(-1), UP(0), UP_RIGHT(45), RIGHT(90), DOWN_RIGHT(135), DOWN(180), DOWN_LEFT(225), LEFT(270),
        UP_LEFT(315);

        public final int value;

        DPadButtons(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public Dpad(GenericHID joystick, Axis axis) {
        this.joy = joystick;
        this.axis = axis;
        this.Up = new AxisButton(joy, axis, DPadButtons.UP.value, ThresholdType.POV);
        this.Down = new AxisButton(joy, axis, DPadButtons.DOWN.value, ThresholdType.POV);
        this.Left = new AxisButton(joy, axis, DPadButtons.LEFT.value, ThresholdType.POV);
        this.Right = new AxisButton(joy, axis, DPadButtons.RIGHT.value, ThresholdType.POV);
        this.UpLeft = new AxisButton(joy, axis, DPadButtons.UP_LEFT.value, ThresholdType.POV);
        this.UpRight = new AxisButton(joy, axis, DPadButtons.UP_RIGHT.value, ThresholdType.POV);
        this.DownLeft = new AxisButton(joy, axis, DPadButtons.DOWN_LEFT.value, ThresholdType.POV);
        this.DownRight = new AxisButton(joy, axis, DPadButtons.DOWN_RIGHT.value, ThresholdType.POV);
    }

    /**
     * get the raw axis value of the POV hat
     * @return scalar of POV hat
     */
    public double getValue() {
        return joy.getRawAxis(axis.getValue());
    }
}