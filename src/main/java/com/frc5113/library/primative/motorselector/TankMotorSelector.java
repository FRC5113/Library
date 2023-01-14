package com.frc5113.library.primative.motorselector;

/** In a 4 motor tank drive, select an individual motor */
public enum TankMotorSelector {
  //    /**
  //     * Right parent motor. Same as the right parent.
  //     */
  //    frontRight(true, true),
  /** Right parent motor. Same as the front right motor. */
  rightParent(true, true),
  //    /**
  //     * Left parent motor. Same as the left parent.
  //     */
  //    frontLeft(true, false),
  /** Left parent motor. Same as the front left motor. */
  leftParent(true, false),
  //    /**
  //     * Right child motor. Same as the right child.
  //     */
  //    backRight(false, true),
  /** Right child motor. Same as the back right motor. */
  rightChild(false, true),
  //    /**
  //     * Left child motor. Same as the left child.
  //     */
  //    backLeft(false, false),
  /** Left child motor. Same as the back left motor. */
  leftChild(false, false);

  public final boolean parent;
  public final boolean right;

  TankMotorSelector(boolean parent, boolean right) {
    this.parent = parent;
    this.right = right;
  }

  /**
   * Get the name of the value of the current
   *
   * @return name of the current value
   */
  @Override
  public String toString() {
    return "Motor " + this.name() + "{" + "parent=" + parent + ", right=" + right + '}';
  }

  /**
   * Get the enum corresponding to the integer value
   *
   * @param right is on the right?
   * @param parent is a parent?
   * @return Enum with corresponding value || null
   */
  public TankMotorSelector fromValue(boolean parent, boolean right) {
    for (TankMotorSelector v : values()) {
      if (v.parent == parent && v.right == right) {
        return v;
      }
    }
    return null;
  }

  /**
   * Is the motor a parent
   *
   * @return <code>parent</code>
   */
  public boolean isParent() {
    return parent;
  }

  /**
   * Is the motor a child
   *
   * @return <code>!parent</code>
   */
  public boolean isChild() {
    return !parent;
  }

  /**
   * Is the motor on the right
   *
   * @return <code>right</code>
   */
  public boolean isOnRight() {
    return right;
  }

  /**
   * Is the motor on the left
   *
   * @return <code>!right</code>
   */
  public boolean isOnLeft() {
    return !right;
  }
}
