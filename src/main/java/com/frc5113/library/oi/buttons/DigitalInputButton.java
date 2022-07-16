package com.frc5113.library.oi.buttons;

import edu.wpi.first.wpilibj2.command.button.Button;
import com.frc5113.library.drivers.InvertibleDigitalInput;

public class DigitalInputButton extends Button {

    InvertibleDigitalInput input;

    public DigitalInputButton(InvertibleDigitalInput input) {
        this.input = input;
    }

    public boolean get(){
        return !input.get();
    }
}