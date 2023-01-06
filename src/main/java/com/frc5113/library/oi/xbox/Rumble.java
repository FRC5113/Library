package com.frc5113.library.oi.xbox;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

/**
 * Manage the current states of the rumble motors on the Xbox controller
 *
 * @author Vladimir Bondar (5113)
 */
public class Rumble {
    XboxController joystick;
    double rumbleRight;
    double rumbleLeft;

    /**
     * Create a new rumble state container
     * @param joystick Xbox controller with rumble 
     */
    public Rumble(XboxController joystick) {
        // set joystick
        this.joystick = joystick;

        // update rumble to zero in class and on controller
        setRumble(0, 0);
    }

    /**
     * Update the rumble on both the left and right side
     * To update individually, use setRumbleRight or setRumbleLeft
     * @param newRight new right rumble value
     * @param newLeft new left rumble value
     */
    public void setRumble(int newRight, int newLeft) {
        setRumbleRight(newRight);
        setRumbleLeft(newLeft);
    }

    /**
     * Update the rumble on <b>only</b> the right
     * To update both, use setRumble
     * @param newRight new right rumble value
     */
    public void setRumbleRight(int newRight) {
        this.rumbleRight = newRight;
        joystick.setRumble(GenericHID.RumbleType.kRightRumble, newRight);
    }

    /**
     * Update the rumble on <b>only</b> the left
     * To update both, use setRumble
     * @param newLeft new left rumble value
     */
    public void setRumbleLeft(int newLeft) {
        this.rumbleLeft = newLeft;
        joystick.setRumble(GenericHID.RumbleType.kLeftRumble, newLeft);
    }

    /**
     * Get the current right rumble
     * @return right rumble
     */
    public double getRumbleRight() {
        return rumbleRight;
    }

    /**
     * Get the current left rumble
     * @return left rumble
     */
    public double getRumbleLeft() {
        return rumbleLeft;
    }
}
