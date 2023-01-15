package com.frc5113.library.motors;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;

/**
 * Basic configuration for Neo (on a CANSpark) motors <br>
 * Nominal Voltage: 12 V<br>
 * Empirical Motor Kv: 473 Kv<br>
 * Empirical Free Speed: 5676 RPM<br>
 * Empirical Free Running Current: 1.8 A<br>
 * Empirical Stall Current: 105 A<br>
 * Empirical Stall Torque: 2.6 Nm<br>
 * Empirical Peak Output Power: 406 W<br>
 */
public class NeoWrench {
  /**
   * Configure a Neo (CANSpark) motor with sane defaults
   *
   * @param motor Motor to configure
   * @param idleMode Whether to break or coast on .set(0);
   * @param maxDraw Maximum draw in amps, 40amps has been ok
   * @param burn Should the parameters actually be put into the flash
   */
  public static void defaultSetup(CANSparkMax motor, IdleMode idleMode, int maxDraw, boolean burn) {
    if (burn) {
      motor.restoreFactoryDefaults();
      motor.setIdleMode(idleMode);
      motor.enableVoltageCompensation(12);
      motor.setSmartCurrentLimit(maxDraw);
      motor.burnFlash();
    }
  }

  /**
   * Configure a Neo (CANSpark) motor with sane defaults. Will consult {@link SparkMAXBurnManager}
   * everytime to check if should burn.
   *
   * @param motor Motor to configure
   * @param idleMode Whether to break or coast on .set(0);
   * @param maxDraw Maximum draw in amps, 40amps has been ok
   */
  public static void defaultSetup(CANSparkMax motor, IdleMode idleMode, int maxDraw) {
    SparkMAXBurnManager.update();
    defaultSetup(motor, idleMode, maxDraw, SparkMAXBurnManager.shouldBurn());
  }
}
