package com.frc5113.library.oi.buttons;

import com.frc5113.library.drivers.InvertibleDigitalInput;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class DigitalInputButton extends Trigger {

  InvertibleDigitalInput input;

  public DigitalInputButton(InvertibleDigitalInput input) {
    super(input::get);
    this.input = input;
  }

  public boolean get() {
    return !input.get();
  }
}
