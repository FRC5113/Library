package com.frc5113.library.motors;

import com.revrobotics.CANSparkMax;

/**
 * Modifications to CANSparkMax-esq <b>brushless</b> motors (commonly the Neo)
 *
 * <ul>
 *   <li>Auto-wrenching default talon configs
 *   <li>Chainable configuration
 *   <li>Readability / usability improvements
 * </ul>
 */
public class SmartNeo extends CANSparkMax {
  public SmartNeo(int canID, IdleMode idleMode) {
    super(canID, MotorType.kBrushless);
    NeoWrench.defaultSetup(this, idleMode, 60);
  }

  /**
   * Set a speed between -100% and 100% [-1.0 and 1.0]
   *
   * @param percent Value between [-1.0, 1.0]
   */
  public void setSpeedPercent(double percent) {
    super.set(percent);
  }
}
