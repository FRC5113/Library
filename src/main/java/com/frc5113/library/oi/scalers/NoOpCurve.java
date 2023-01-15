package com.frc5113.library.oi.scalers;

/*
Latex function definitions (Desmos):
y = x
*/

/**
 * This class maps the value of a stick input to a linear curve.
 *
 * <p>An example can be found on <a href="https://www.desmos.com/calculator/du1hojvpm8">Desmos</a>.
 *
 * @author Vladimir Bondar (5113)
 * @version 0.0.1
 */
public class NoOpCurve extends Curve {
  /**
   * Constructs an No Op Curve object which can be used in place of a curve to do nothing (y = x).
   * Initialized with default values:
   */
  public NoOpCurve() {
    setOffset(0.0);
    setScalar(1.0);
    setDeadDiameter(0.0);
  }

  /**
   * @param input value to be mapped
   */
  @Override
  public double calculateMappedVal(double input) {
    return input;
  }
}
