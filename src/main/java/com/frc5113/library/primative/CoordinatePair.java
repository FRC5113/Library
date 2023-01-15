package com.frc5113.library.primative;

import edu.wpi.first.math.util.Units;

/**
 * Cartesian coordinate pair
 *
 * @param <A> X coordinate type
 * @param <B> Y coordinate type
 */
public class CoordinatePair<A extends Number, B extends Number> {
  public A X;
  public B Y;

  /**
   * Create a Coordinate Pair
   *
   * @param X X coordinate
   * @param Y Y coordinate
   */
  public CoordinatePair(A X, B Y) {
    this.X = X;
    this.Y = Y;
  }

  /**
   * Get X coordinate
   *
   * @return X coordinate
   */
  public A getX() {
    return X;
  }

  /**
   * Get Y coordinate
   *
   * @return Y coordinate
   */
  public B getY() {
    return Y;
  }

  /**
   * Create a new pair of coordinates
   *
   * @param X X coordinate
   * @param Y Y coordinate
   * @return a new pair with the coordinates (A) x and (B) y
   * @param <A> Type of x
   * @param <B> Type of y
   */
  public static <A extends Number, B extends Number> CoordinatePair<A, B> of(A X, B Y) {
    return new CoordinatePair<A, B>(X, Y);
  }

  /**
   * Get the magnitude (of the vector)
   *
   * @return Magnitude
   */
  public double magnitude() {
    return Math.hypot((double) X, (double) Y);
  }

  /**
   * Get the angle formed by the coordinates (X, Y) around the origin
   *
   * @return Angle in Radians
   */
  public double angleRad() {
    return Math.atan2((double) X, (double) Y);
  }

  /**
   * Get the angle formed by the coordinates (X, Y) around the origin
   *
   * @return Angle in Degrees
   */
  public double angleDeg() {
    return Units.radiansToDegrees(angleRad());
  }
}
