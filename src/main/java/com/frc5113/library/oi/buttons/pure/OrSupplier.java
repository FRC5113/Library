package com.frc5113.library.oi.buttons.pure;

import edu.wpi.first.wpilibj2.command.button.Trigger;

import java.util.function.BooleanSupplier;

/**
 * Treat two buttons as one (where the fake button is "pressed" when either of the real buttons is
 * pressed)
 */
public class OrSupplier implements BooleanSupplier {
  Trigger b1;
  Trigger b2;

  public OrSupplier(Trigger button, Trigger button2) {
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
