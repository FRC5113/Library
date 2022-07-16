package com.frc5113.library.oi.buttons.logic;

import edu.wpi.first.wpilibj2.command.button.Button;

public class ButtonAnd extends Button {
    Button b1;
    Button b2;

    public ButtonAnd(Button andButton1, Button andButton2) {
        b1 = andButton1;
        b2 = andButton2;
    }
    @Override
    public boolean get() {
        return b1.get() && b2.get();
    }
}