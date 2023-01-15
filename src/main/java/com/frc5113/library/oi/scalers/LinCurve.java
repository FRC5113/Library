package com.frc5113.library.oi.scalers;

/*
Latex function definitions (Desmos):
e_{v}=3.4
\left\{e_{v}x>1:1\right\}
\left\{e_{v}x<-1:-1\right\}
\left\{-1<e_{v}x<1:e_{v}x\right\}
*/

/**
 * This class maps the value of a stick input to a linear curve.
 *
 * <p>An example can be found on <a href="https://www.desmos.com/calculator/yntq1twk1m">Desmos</a>.
 *
 * @author Justin Babilino (3847), Vladimir Bondar (5113)
 * @version 0.0.4
 */
public class LinCurve extends Curve {
  /**
   * Constructs an Linear Curve object which can be used to map a stick input proportionally.
   * Initialized with default values: <code>
   *     offset = 0.0;
   *     scalar = 1.0;
   *     deadzone = 0.0;
   * </code>
   */
  public LinCurve() {
    setOffset(0.0);
    setScalar(1.0);
    setDeadDiameter(0.0);
  }

  /**
   * Constructs a Linear Curve object which can be used to map a stick input proportionally.
   * Initialized with values provided.
   *
   * @param offset value used to offset the final curve
   * @param scalar value used to scale the value before offset
   * @param deadzone value for the width of the deadband in the center of the curve
   */
  public LinCurve(double offset, double scalar, double deadzone) {
    setOffset(offset);
    setScalar(scalar);
    setDeadDiameter(deadzone);
  }

  /**
   * @param input value to be mapped
   */
  @Override
  public double calculateMappedVal(double input) {
    double val = calculateOffset(calculateScalar(calculateDeadzone(input)));
    if (val > 1.0) {
      val = 1.0;
    } else if (val < -1.0) {
      val = -1.0;
    }
    return val;
  }
}
