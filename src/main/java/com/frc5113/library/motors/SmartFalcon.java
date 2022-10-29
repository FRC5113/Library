package com.frc5113.library.motors;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.math.geometry.Translation2d;

/**
 * Modifications to TalonFX-esq motors (commonly the Talon)
 * <ul>
 *     <li>Auto-wrenching default talon configs</li>
 *     <li>Chainable configuration</li>
 *     <li>Encoder improvements</li>
 *     <li>Readability / usability improvements</li>
 * </ul>
 */
public class SmartFalcon extends TalonFX {

    public static final int TICKS_PER_ROTATION = 2048;
    private Double outputRadius = null;
    private Double gearRatio = null;

    private Translation2d position;

    /**
     * Create a new Smart TalonFX with Falcon based parameters
     * @param canID integer CAN network ID
     * @param inverted spin backwards?
     * @param neutralMode Whether to break or coast when .set(0);
     */
    public SmartFalcon(int canID, boolean inverted, NeutralMode neutralMode) {
        super(canID);
        TalonFXWrench.defaultSetup(this, inverted, 40);
        this.setNeutralMode(neutralMode);
    }

    public SmartFalcon(int canID, boolean inverted, NeutralMode neutralMode, Translation2d position) {
        super(canID);
        TalonFXWrench.defaultSetup(this, inverted, 40);
        this.setNeutralMode(neutralMode);
        this.position = position;
    }

    /**
     * Chainable, configure parameters for a PID
     * @param P Proportional Coefficient
     * @param I Integral Coefficient
     * @param D Derivative Coefficient
     * @return configured falcon
     */
    public SmartFalcon configurePID(double P, double I, double D) {
        this.config_kP(0, P);
        this.config_kI(0, I);
        this.config_kD(0, D);
        return this;
    }

    /**
     * Chainable, configure parameters for a PID using default parameters (P: 0.3, I: 0, D: 0)
     * @return configured falcon
     */
    public SmartFalcon configurePID() {
        return configurePID(0.3, 0,0);
    }

    /**
     * Chainable, set the amount of time (sec) from 0 to full speed
     * @param rate Rate at which to ramp motor, seconds from 0 to full
     * @return configured falcon
     */
    public SmartFalcon setRampRate(double rate){
        this.configClosedloopRamp(rate);
        return this;
    }

    // simplify some existing calls
    /**
     * Get the selected sensor position (in raw sensor units).
     *
     * @return Position of selected sensor (in raw sensor units).
     */
    public double getEncoderTicks() {
        return this.getSelectedSensorPosition();
    }

    /**
     * Get the selected sensor velocity.
     *
     * @return selected sensor (in raw sensor units) per 100ms.
     * See Phoenix-Documentation for how to interpret.
     */
    public double getEncoderVelocityMS() {
        return this.getSelectedSensorVelocity();
    }

    /**
     * Get the selected sensor velocity.
     *
     * @return selected sensor (in raw sensor units) per second.
     * See Phoenix-Documentation for how to interpret.
     */
    public double getEncoderVelocity() {
        return this.getSelectedSensorVelocity() * 10;
    }

    /**
     * Gets the supply/input current of the motor controller.
     *
     * @return The supply/input current (in amps).
     */
    public double getCurrent() {
        return this.getSupplyCurrent();
    }

    /**
     * Set the position of the encoder back to zero ticks
     */
    public void resetEncoder() {
        this.setSelectedSensorPosition(0);
    }

    // speed setting shortcuts

    /**
     * Set the speed to a percentage [-1 to 1]
     * @param input Percent max voltage (between (inc.) -1 and 1)
     */
    public void setSpeedPercent(double input) {
        this.set(ControlMode.PercentOutput, input);
    }


    // encoder nicety functions, for verification see: https://github.com/Spectrum3847/Infrared-2022/blob/main/src/main/java/frc/lib/util/Conversions.java
    /**
     * Get the total number of rotations that the encoder has taken
     * @return Total number of rotations
     */
    public double getEncoderRotations() {
        return getEncoderTicks() / TICKS_PER_ROTATION;
    }

    /**
     * Configure the radius / gear ratio of the output of the motor (ie a wheel)
     * @param radius Radius of the output mechanism
     * @param gearRatio Gear ratio between the input and output (&gt;1 means output
     *                  is going slower than input, &lt;1 means output is going faster
     *                  than input)
     * @return Configured motor
     */
    public SmartFalcon configureOutput(double radius, double gearRatio) {
        this.outputRadius = radius;
        this.gearRatio = gearRatio;
        return this;
    }

    /**
     * Compute the travel speed (units per second) on the output
     * @return Travel speed on output
     */
    public double getOutputSpeed() {
        return (2 * Math.PI * outputRadius) * getEncoderVelocity() / gearRatio;
    }

    /**
     * Compute the traveled distance (∫velocity) of the output
     * @return Traveled distance of the output
     */
    public double getOutputDistance() {
        return (2 * Math.PI * outputRadius) * getEncoderRotations() / gearRatio;
    }

    /**
     * return the position of a drive motor relative to the center of the robot
     * @return
     */
    public Translation2d getRelativePosition() {
        return position;
    }
}
