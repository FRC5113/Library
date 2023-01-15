package com.frc5113.library.oi.buttons.logic;

import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * Treat two buttons as one (where the fake button is "pressed" when either of the real buttons is
 * pressed)
 */
public class ButtonOr extends Trigger {
  Trigger b1;
  Trigger b2;

  public ButtonOr(Trigger button, Trigger button2) {
    b1 = button;
    b2 = button2;
  }

  public boolean get() {
    return b1.getAsBoolean() || b2.getAsBoolean();
  }

  @Override
  public boolean getAsBoolean() {
    return b1.getAsBoolean() || b2.getAsBoolean();
  }
}
