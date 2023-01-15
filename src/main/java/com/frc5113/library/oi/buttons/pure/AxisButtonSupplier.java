package com.frc5113.library.oi.buttons.pure;

import com.frc5113.library.primative.Axis;
import com.frc5113.library.primative.ThresholdType;
import edu.wpi.first.wpilibj.GenericHID;

import java.util.function.BooleanSupplier;

/** Treat an axis as if it was a button */
public class AxisButtonSupplier implements BooleanSupplier {
    private final GenericHID controller;
    private final int axis;
    private final double targetVal;
    private final ThresholdType thresholdType;



    public AxisButtonSupplier(GenericHID joystick, int axis, double threshold, ThresholdType thresholdType) {
        this.controller = joystick;
        this.axis = axis;
        this.targetVal = threshold;
        this.thresholdType = thresholdType;
    }

    public AxisButtonSupplier(GenericHID joystick, Axis axis, double threshold, ThresholdType thresholdType) {
        this(joystick, axis.getValue(), threshold, thresholdType);
    }

    /**
     * Get the value of an axis in true/false form
     *
     * @return true/false in accordance with set rules
     */
    @Override
    public boolean getAsBoolean() {
        switch (this.thresholdType) {
            case EXACT:
                // System.out.println("axis value: " + joy.getRawAxis(this.axis));
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

    public boolean get() {
        return getAsBoolean();
    }
}
