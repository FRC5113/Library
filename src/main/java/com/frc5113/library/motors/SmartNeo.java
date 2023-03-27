package com.frc5113.library.motors;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

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
  public RelativeEncoder encoder;

  public SmartNeo(int canID, IdleMode idleMode) {
    this(canID, idleMode, false);
  }

  public SmartNeo(int canID, IdleMode idleMode, boolean inverted) {
    super(canID, MotorType.kBrushless);
    NeoWrench.defaultSetup(this, idleMode, 45);
    encoder = getEncoder();
    this.setInverted(inverted);
  }

  /**
   * Set a speed between -100% and 100% [-1.0 and 1.0]
   *
   * @param percent Value between [-1.0, 1.0]
   */
  public void setSpeedPercent(double percent) {
    super.set(percent);
  }

  /**
   * Get the relative position of the motor encoder
   *
   * @return Relative Position
   */
  public double getPosition() {
    return encoder.getPosition();
  }

  /** Set the encoder position to 0, effectively resetting it */
  public void resetEncoder() {
    encoder.setPosition(0);
  }
}
