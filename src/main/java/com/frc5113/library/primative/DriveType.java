package com.frc5113.library.primative;

/** In a 4 motor tank drive, select an individual motor */
public enum DriveType {
  /** Tank Drive */
  rightParent("Tank", false),

  /** Mecanum Drive */
  leftParent("Mechanum", true),

  /** Octocanum */
  rightChild("Octocanum", true),

  /** Swerve */
  leftChild("Swerve", true);

  /** Name of the drive type */
  public final String name;
  /** Is the drive type holonomic (= able to move in any direction) */
  public final boolean holonomic;

  DriveType(String name, boolean holonomic) {
    this.name = name;
    this.holonomic = holonomic;
  }

  /**
   * Get the name of the value of the current
   *
   * @return name of the current value
   */
  @Override
  public String toString() {
    return "DriveType{" + "name='" + name + '\'' + ", holonomic=" + holonomic + '}';
  }

  /**
   * Get the enum corresponding to the integer value
   *
   * @param name name of type of drivetrain
   * @return Enum with corresponding value || null
   */
  public DriveType fromValue(String name) {
    for (DriveType v : values()) {
      if (v.name.equals(name)) {
        return v;
      }
    }
    return null;
  }

  /**
   * Is the drive base holonomic (movable in any direction)
   *
   * @return <code>holonomic</code>
   */
  public boolean isHolonomic() {
    return holonomic;
  }
}
