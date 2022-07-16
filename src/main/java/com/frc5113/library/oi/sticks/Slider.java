package com.frc5113.library.oi.sticks;

import com.frc5113.library.oi.scalers.Curve;
import com.frc5113.library.oi.scalers.NoOpCurve;
import com.frc5113.library.primative.Axis;
import edu.wpi.first.wpilibj.Joystick;

public class Slider {
    Joystick controller;
    Axis slider;
    Curve curve = new NoOpCurve();

    public Slider(Joystick controller, Axis slider) {
        this.controller = controller;
        this.slider = slider;
    }

    public Slider(Joystick controller, Axis slider, Curve curve) {
        this(controller, slider);
        this.curve = curve;
    }

    public double getValue() {
        double value = 0;
        if (this.controller.isConnected()){
            value = this.controller.getRawAxis(slider.value);
            value = curve.calculateMappedVal(value);
        }
        return value;
    }
}
