package com.frc5113.library.oi.xbox;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import com.frc5113.library.oi.xbox.XboxGamepad.XboxAxis;
import edu.wpi.first.wpilibj.XboxController;

/**
 * Represent the state of the physical triggers (on the back of the XBox controller)
 */
public class Triggers {
    GenericHID controller;

    public Triggers(XboxController controller) {
        this.controller = controller;
    }

    public double getLeft() {
        if (this.controller.isConnected()){
            return this.controller.getRawAxis(XboxAxis.leftTrigger.value);
        } else{
            return 0;
        }
    }

    public double getRight() {
        if (this.controller.isConnected()){
            return this.controller.getRawAxis(XboxAxis.rightTrigger.value);
        } else{
            return 0;
        }
    }

    public double getTwist() {
        return - getLeft() + getRight();
    }
}
