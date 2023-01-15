package com.frc5113.library.oi.scalers;

/*
Latex function definitions (Desmos):
e_{v}=2
\left\{e_{v}>1:\frac{\left(e_{v}^{\left|x\right|}-1\right)}{e_{v}-1}\cdot\operatorname{sign}\left(x\right)\right\}
\left\{e_{v}<1:\frac{\left(e_{v}^{\left|x\right|}-1\right)}{e_{v}-1}\cdot\operatorname{sign}\left(x\right)\right\}
\left\{e_{v}=1:x\right\}
*/

/**
 * This class maps the value of a stick input to an exponential curve.
 *
 * <p>An example can be found on <a href="https://www.desmos.com/calculator/zxr2hgwbny">Desmos</a>.
 *
 * @author Justin Babilino (3847), Vladimir Bondar (5113)
 * @version 0.0.4
 */
public class ExpCurve extends Curve {
  /** The value of the base of the exponent used in calculating the curve. */
  private double expVal;

  /**
   * Constructs an Exponential Curve object which can be used to map a stick input exponentially.
   * Initialized with default values: <code>
   *     expVal = 1.0;
   *     offset = 0.0;
   *     scalar = 1.0;
   *     deadDiameter = 0.0;
   * </code>
   */
  public ExpCurve() {
    setExpVal(1.0);
    setOffset(0.0);
    setScalar(1.0);
    setDeadDiameter(0.0);
  }

  /**
   * Constructs an Exponential Curve object which can be used to map a stick input exponentially.
   * Initialized with values provided.
   *
   * @param expVal value of the base of the exponent used in the curve
   * @param offset value used to offset the final curve
   * @param scalar value used to scale the value before offset
   * @param deadDiamter value for the width of the deadband in the center of the curve
   */
  public ExpCurve(double expVal, double offset, double scalar, double deadDiamter) {
    setExpVal(expVal);
    setOffset(offset);
    setScalar(scalar);
    setDeadDiameter(deadDiamter);
  }

  /**
   * @param input value to be mapped
   */
  @Override
  public double calculateMappedVal(double input) {
    double val = calculateOffset(calculateScalar(calculateExpVal(calculateDeadzone(input))));
    if (val > 1.0) {
      val = 1.0;
    } else if (val < -1.0) {
      val = -1.0;
    }
    return val;
  }

  /**
   * Returns the value of the input mapped by an exponential curve of base <code>expVal</code>.
   *
   * @param input the input value to be mapped
   * @return mapped value
   */
  private double calculateExpVal(double input) {
    double val = input;
    if (expVal != 1.0) {
      val = (Math.pow(expVal, Math.abs(input)) - 1.0) / (expVal - 1.0) * Math.signum(input);
    }
    return val;
  }

  /**
   * Sets the value of <code>expVal</code>, the base of the exponent used to map the input.
   *
   * @param expVal the new value of <code>expVal</code>
   */
  public void setExpVal(double expVal) {
    if (expVal <= 0.0) {
      expVal = 1.0;
    }
    this.expVal = expVal;
  }

  /**
   * Returns the value of <code>expVal</code>, the base of the exponent used to map the input.
   *
   * @return the current value of <code>expVal</code>
   */
  public double getExpVal() {
    return expVal;
  }
}
