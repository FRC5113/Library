package com.frc5113.library.oi.sticks;

import com.frc5113.library.oi.scalers.Curve;
import com.frc5113.library.oi.scalers.NoOpCurve;
import com.frc5113.library.primative.Axis;
import com.frc5113.library.primative.CoordinatePair;
import edu.wpi.first.wpilibj.Joystick;

/**
 * Thumbstick provides an expression of the thumbstick found on any device.
 * @author Bob (319), Vladimir Bondar (5113)
 */
public class ThumbStick {
    Joystick controller;
    Axis xAxis;
    Axis yAxis;
    public final Curve xCurve;
    public final Curve yCurve;

    public ThumbStick(Joystick controller, Axis xAxis, Axis yAxis) {
        this.controller = controller;
        this.xAxis = xAxis;
        this.yAxis = yAxis;

        xCurve = new NoOpCurve();
        yCurve = new NoOpCurve();
    }

    public ThumbStick(Joystick controller, Axis xAxis, Axis yAxis, Curve xCurve, Curve yCurve) {
        this.controller = controller;
        this.xAxis = xAxis;
        this.yAxis = yAxis;

        this.xCurve = xCurve;
        this.yCurve = yCurve;
    }

    public double getX() {
        double value = 0;
        if (this.controller.isConnected()){
            value = this.controller.getRawAxis(xAxis.value);
            value = xCurve.calculateMappedVal(value);
        }
        return value;
    }

    public double getY() {
        double value = 0;
        if (this.controller.isConnected()){
            value = this.controller.getRawAxis(yAxis.value);
            value = yCurve.calculateMappedVal(value);
        }
        return  value;
    }

    public double getDirectionRadians() {
        return Math.atan2(getX(), -getY());
    }

    public double getDirectionDegrees() {
        return Math.toDegrees(getDirectionRadians());
    }

    /**
     * Get state of the thumbstick as the pair <X, Y>
     * @return {@link CoordinatePair} <X, Y>
     */
    public CoordinatePair<Double, Double> getAsPair() {
        return new CoordinatePair<>(getX(), getY());
    }
}
