package com.frc5113.library.oi.buttons.logic;

import edu.wpi.first.wpilibj2.command.button.Button;

/**
 * Treat two buttons as one (where the fake button is "pressed" when both real buttons are pressed)
 */
public class ButtonAnd extends Button {
  Button b1;
  Button b2;

  public ButtonAnd(Button andButton1, Button andButton2) {
    b1 = andButton1;
    b2 = andButton2;
  }

  public boolean get() {
    return b1.getAsBoolean() && b2.getAsBoolean();
  }

  @Override
  public boolean getAsBoolean() {
    return b1.getAsBoolean() && b2.getAsBoolean();
  }
}
