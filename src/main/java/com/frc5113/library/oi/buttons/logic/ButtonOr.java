package com.frc5113.library.oi.buttons.logic;

import edu.wpi.first.wpilibj2.command.button.Button;

/**
 * Treat two buttons as one (where the fake button is "pressed" when either of the real buttons is pressed)
 */
public class ButtonOr extends Button {
    Button b1;
    Button b2;

    public ButtonOr(Button button, Button button2) {
        b1 = button;
        b2 = button2;
    }

    public boolean get() {
        return b1.get() || b2.get();
    }
}
