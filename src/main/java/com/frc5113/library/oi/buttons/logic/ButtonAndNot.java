package com.frc5113.library.oi.buttons.logic;

import com.frc5113.library.oi.buttons.pure.AndNotSupplier;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * Treat two buttons as one (where the fake button is "pressed" when one real buttons is pressed and
 * the other is not)
 */
public class ButtonAndNot extends Trigger {
  public ButtonAndNot(Trigger button, Trigger notButton) {
    super(new AndNotSupplier(button, notButton));
  }
}
