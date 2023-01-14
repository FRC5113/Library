package com.frc5113.library.motors;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;

/** Basic configuration for TalonFX (Falcon) motors 400W Power @ 40A / 12VDC (20A optimal) */
public class TalonFXWrench {
  /**
   * Performs a (factory) default setup for a TalonFX (commonly used with a Neo500). The script does
   * the following:
   *
   * <ul>
   *   <li>Set factory defaults
   *   <li>Set the feedback device as the integrated sensor
   *   <li>Enables and sets the compensation at 12V. Note, this may be a know <a
   *       href="https://www.chiefdelphi.com/t/lag-in-voltage-compensation-mode-on-talon-srx/161055/2">issue</a>
   *   <li>Applies a current limiter at the <code>currentLimit</code> voltage
   *   <li>Inverts the motor (if requested)
   *   <li>Reduces update rate of unimportant updates to reduce CAN usage. Read more <a
   *       href="https://docs.ctre-phoenix.com/en/stable/ch18_CommonAPI.html#setting-status-frame-periods">here</a>
   * </ul>
   *
   * @param motor The motor to configure
   * @param isInverted Should the motor invert voltage
   * @param currentLimit Draw current limiter (Amps)
   */
  public static void defaultSetup(TalonFX motor, boolean isInverted, double currentLimit) {
    motor.configFactoryDefault();
    motor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    motor.configVoltageCompSaturation(12); // default 12v voltage compensation for motors
    motor.enableVoltageCompensation(true); // enable voltage compensation
    simpleCurrentLimit(motor, currentLimit);
    motor.setInverted(isInverted);
    defaultStatusFrames(motor);
  }

  /**
   * Applies a preconfigured {@link TalonFXConfiguration} to a Talon motor. Use for parents.
   *
   * @param motor Motor to apply configuration to
   * @param config (Set up) configuration
   */
  public static void configAllSetup(TalonFX motor, TalonFXConfiguration config) {
    motor.configFactoryDefault();
    motor.configAllSettings(config);
    pidStatusFrames(motor);
  }

  /**
   * Applies a preconfigured {@link TalonFXConfiguration} to a Talon motor. Use for followers.
   *
   * @param motor Motor to apply configuration to
   * @param config (Set up) configuration
   */
  public static void configFollowerSetup(TalonFX motor, TalonFXConfiguration config) {
    motor.configFactoryDefault();
    motor.configAllSettings(config);
    defaultStatusFrames(motor);
  }

  /**
   * Apply a voltage limit to the draw of a Talon
   *
   * @param motor Motor to restrict
   * @param limit Upper limit (volts)
   */
  public static void simpleCurrentLimit(TalonFX motor, double limit) {
    SupplyCurrentLimitConfiguration supplyCurrentLimit =
        new SupplyCurrentLimitConfiguration(true, limit, limit, 0.5);
    motor.configSupplyCurrentLimit(supplyCurrentLimit);
  }

  /**
   * Update the speed of updates to reduce CAN usage to a fast / slow profile. Good for motors with
   * low / no need to update status info (followers)
   *
   * @param motor Motor to update speed for
   */
  public static void defaultStatusFrames(TalonFX motor) {
    // Default Status Rates are listed here:
    // https://docs.ctre-phoenix.com/en/stable/ch18_CommonAPI.html
    // int fastTime = 100;
    int slowTime = 500;
    motor.setStatusFramePeriod(StatusFrame.Status_1_General, 10);
    motor.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 50);
    motor.setStatusFramePeriod(StatusFrame.Status_4_AinTempVbat, slowTime);
    motor.setStatusFramePeriod(StatusFrame.Status_10_MotionMagic, slowTime);
    motor.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, slowTime);
    motor.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, slowTime);
    motor.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1, slowTime);
    motor.setStatusFramePeriod(StatusFrame.Status_17_Targets1, slowTime);
  }

  /**
   * Update the speed of updates to reduce CAN usage to a fast / medium / slow profile Good for
   * motors with medium need to update status info (parents)
   *
   * @param motor Motor to update speed for
   */
  public static void pidStatusFrames(TalonFX motor) {
    // Default Status Rates are listed here:
    // https://docs.ctre-phoenix.com/en/stable/ch18_CommonAPI.html
    int fastTime = 100;
    int slowTime = 500;
    motor.setStatusFramePeriod(StatusFrame.Status_1_General, 10);
    motor.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 20);
    motor.setStatusFramePeriod(StatusFrame.Status_4_AinTempVbat, fastTime);
    motor.setStatusFramePeriod(StatusFrame.Status_10_MotionMagic, fastTime);
    motor.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, slowTime);
    motor.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, fastTime);
    motor.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1, slowTime);
    motor.setStatusFramePeriod(StatusFrame.Status_17_Targets1, slowTime);
  }
}
