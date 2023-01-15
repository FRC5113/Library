package com.frc5113.library.utils.tunable;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.function.DoubleSupplier;

/**
 * Class for a tunable number. Gets value from dashboard in tuning mode, returns default if not or
 * value not in dashboard.
 */
public class TunableNumber implements DoubleSupplier {
  private static final String tableKey = "TunableNumbers";

  private final String key;
  private double defaultValue;
  private double lastHasChangedValue = defaultValue;
  private boolean tuningMode = true;

  /**
   * Create a new TunableNumber
   *
   * @param dashboardKey Key on dashboard
   */
  public TunableNumber(String dashboardKey) {
    this.key = tableKey + "/" + dashboardKey;
  }

  /**
   * Create a new TunableNumber with the default value
   *
   * @param dashboardKey Key on dashboard
   * @param defaultValue Default value
   */
  public TunableNumber(String dashboardKey, double defaultValue) {
    this(dashboardKey);
    setDefault(defaultValue);
  }

  /**
   * Create a new TunableNumber with the default value that can (but doesn't have to be) tunable
   *
   * @param dashboardKey Key on dashboard
   * @param defaultValue Default value
   * @param tuningMode Should be able to be tuned
   */
  public TunableNumber(String dashboardKey, double defaultValue, boolean tuningMode) {
    this(dashboardKey, defaultValue);
    setTuningMode(tuningMode);
  }

  /**
   * Get the default value for the number that has been set
   *
   * @return The default value
   */
  public double getDefault() {
    return defaultValue;
  }

  /**
   * Set the default value of the number
   *
   * @param defaultValue The default value
   */
  public void setDefault(double defaultValue) {
    this.defaultValue = defaultValue;
    if (tuningMode) {
      // This makes sure the data is on NetworkTables but will not change it
      SmartDashboard.putNumber(key, SmartDashboard.getNumber(key, defaultValue));
    } else {
      SmartDashboard.clearPersistent(key);
    }
  }

  /**
   * Get the current value, from dashboard if available and in tuning mode
   *
   * @return The current value
   */
  public double get() {
    return tuningMode ? SmartDashboard.getNumber(key, defaultValue) : defaultValue;
  }

  /**
   * Get the current value, from dashboard if available and in tuning mode
   *
   * @return The current value
   */
  @Override
  public double getAsDouble() {
    return get();
  }

  /**
   * Checks whether the number has changed since our last check
   *
   * @return True if the number has changed since the last time this method was called, false
   *     otherwise
   */
  public boolean hasChanged() {
    double currentValue = get();
    if (currentValue != lastHasChangedValue) {
      lastHasChangedValue = currentValue;
      return true;
    }

    return false;
  }

  /**
   * Set whether the number should be able to be tuned
   *
   * @param tuningMode Tuning Mode (y/n?)
   */
  public void setTuningMode(boolean tuningMode) {
    this.tuningMode = tuningMode;
  }
}
