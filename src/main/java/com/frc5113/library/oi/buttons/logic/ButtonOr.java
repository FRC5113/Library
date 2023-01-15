package com.frc5113.library.oi.buttons.logic;

import com.frc5113.library.oi.buttons.pure.AndSupplier;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * Treat two buttons as one (where the fake button is "pressed" when either of the real buttons is
 * pressed)
 */
public class ButtonOr extends Trigger {
  public ButtonOr(Trigger orButton1, Trigger orButton2) {
    super(new AndSupplier(orButton1, orButton2));
  }
}
