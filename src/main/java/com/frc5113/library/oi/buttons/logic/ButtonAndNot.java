package com.frc5113.library.oi.buttons.logic;

import edu.wpi.first.wpilibj2.command.button.Button;

public class ButtonAndNot extends Button {

    Button b1;
    Button b2;

    public ButtonAndNot(Button trueButton, Button falseButton) {
        b1 = trueButton;
        b2 = falseButton;
    }

    @Override
    public boolean get(){
        return b1.get() && !b2.get();
    }

}
