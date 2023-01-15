package com.frc5113.library.oi.buttons.pure;

import edu.wpi.first.wpilibj2.command.button.Trigger;

import java.util.function.BooleanSupplier;

/**
 * Treat two buttons as one (where the fake button is "pressed" when both real buttons are pressed)
 */
public class AndSupplier implements BooleanSupplier {
  Trigger b1;
  Trigger b2;

  public AndSupplier(Trigger andButton1, Trigger andButton2) {
    super();
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
