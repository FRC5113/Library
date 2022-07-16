package com.frc5113.library.oi.buttons;

import com.frc5113.library.oi.joystick.Joystick;
import com.frc5113.library.oi.xbox.XboxGamepad;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import com.frc5113.library.oi.xbox.XboxGamepad.XboxButton;

public class Button extends JoystickButton {

    public Button(GenericHID joystick, int buttonNumber) {
        super(joystick, buttonNumber);
    }

    public Button(edu.wpi.first.wpilibj.XboxController joystick, XboxButton button) {
        super(joystick, button.value);
    }

    public Button(XboxGamepad joystick, XboxButton button) {
        super(joystick, button.value);
    }

    public Button(Joystick joystick, Joystick.JoystickButton button) {super(joystick, button.value);}

}