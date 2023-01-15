package com.frc5113.library.oi.buttons.pure;

import edu.wpi.first.wpilibj2.command.button.Trigger;

import java.util.function.BooleanSupplier;

/**
 * Treat two buttons as one (where the fake button is "pressed" when one real buttons is pressed and
 * the other is not)
 */
public class AndNotSupplier implements BooleanSupplier {

  Trigger b1;
  Trigger b2;

  public AndNotSupplier(Trigger trueButton, Trigger falseButton) {
    b1 = trueButton;
    b2 = falseButton;
  }

  public boolean get() {
    return b1.getAsBoolean() && !b2.getAsBoolean();
  }

  @Override
  public boolean getAsBoolean() {
    return b1.getAsBoolean() && !b2.getAsBoolean();
  }
}
