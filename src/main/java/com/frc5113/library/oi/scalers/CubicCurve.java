package com.frc5113.library.oi.scalers;

/*
Latex function definitions (Desmos):
x^{3}
*/

/**
 * This class maps the value of a stick input to a cubic curve.
 *
 * <p>An example can be found on <a href="https://www.desmos.com/calculator/0qjvkr5rrf">Desmos</a>.
 *
 * @author Vladimir Bondar (5113)
 * @version 0.0.4
 */
public class CubicCurve extends Curve {
  /**
   * Constructs a Cubic Curve object which can be used to map a stick input using the cube of input.
   * Initialized with default values: <code>
   *     offset = 0.0;
   *     scalar = 1.0;
   *     deadDiameter = 0.0;
   * </code>
   */
  public CubicCurve() {
    setOffset(0.0);
    setScalar(1.0);
    setDeadDiameter(0.0);
  }

  /**
   * Constructs a Cubic Curve object which can be used to map a stick input using x^3. Initialized
   * with values provided.
   *
   * @param offset value used to offset the final curve
   * @param scalar value used to scale the value before offset
   * @param deadDiameter value for the width of the deadband in the center of the curve
   */
  public CubicCurve(double offset, double scalar, double deadDiameter) {
    setOffset(offset);
    setScalar(scalar);
    setDeadDiameter(deadDiameter);
  }

  /**
   * @param input value to be mapped
   */
  @Override
  public double calculateMappedVal(double input) {
    double val = calculateOffset(calculateScalar(calculateCubicVal(calculateDeadzone(input))));
    if (val > 1.0) {
      val = 1.0;
    } else if (val < -1.0) {
      val = -1.0;
    }
    return val;
  }

  /**
   * Returns the value of the input mapped by the cube of the <code>input</code>.
   *
   * @param input the input value to be mapped
   * @return mapped value
   */
  private double calculateCubicVal(double input) {
    return Math.pow(input, 3);
  }
}
