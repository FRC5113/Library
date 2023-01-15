package com.frc5113.library.oi.buttons.logic;

import com.frc5113.library.oi.buttons.pure.AndSupplier;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * Treat two buttons as one (where the fake button is "pressed" when both real buttons are pressed)
 */
public class ButtonAnd extends Trigger {
  public ButtonAnd(Trigger andButton1, Trigger andButton2) {
    super(new AndSupplier(andButton1, andButton2));
  }
}
