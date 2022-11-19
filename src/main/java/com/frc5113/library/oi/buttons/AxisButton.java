package com.frc5113.library.oi.buttons;

import com.frc5113.library.primative.Axis;
import edu.wpi.first.wpilibj2.command.button.Button;
import com.frc5113.library.oi.xbox.XboxGamepad.XboxAxis;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import com.frc5113.library.oi.joystick.Joystick.JoystickAxis;

public class AxisButton extends Button {
    private final GenericHID controller;
    private final int axis;
    private final double targetVal;
    private final ThresholdType thresholdType;

    public enum ThresholdType
    {
        LESS_THAN, GREATER_THAN, EXACT, POV, DEADBAND;
    }

    public AxisButton(GenericHID joystick, int axis, double threshold, ThresholdType thresholdType) {
        this.controller = joystick;
        this.axis = axis;
        this.targetVal = threshold;
        this.thresholdType = thresholdType;
    }

    public AxisButton(GenericHID joystick, Axis axis, double threshold, ThresholdType thresholdType) {
        this(joystick, axis.value, threshold, thresholdType);
    }


    public boolean get() {
        switch (this.thresholdType) {
            case EXACT:
                //System.out.println("axis value: " + joy.getRawAxis(this.axis));
                return controller.getRawAxis(this.axis) == this.targetVal;
            case LESS_THAN:
                return controller.getRawAxis(this.axis) < this.targetVal;
            case GREATER_THAN:
                return controller.getRawAxis(this.axis) > this.targetVal;
            case POV:
                return controller.getPOV() == this.targetVal;
            case DEADBAND:
                return Math.abs(controller.getRawAxis(this.axis)) > this.targetVal;
            default:
                return false;
        }
    }

}