package com.frc5113.library.subsystem;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Use this class when creating a subsystem. This will allow it to be loaded into the subsystem manager
 * for easy telemetry and debugging
 */
public abstract class SmartSubsystem extends SubsystemBase {
    public SmartSubsystem() {
        super();
    }

    /**
     * Do telemetry, for example shuffleboard
     */
    abstract public void outputTelemetry();

    /**
     * Have the subsystem make sure that everything is good. Should print debug data if something fails
     * @return Check status (success - true / fail - false)
     */
    abstract public boolean checkSubsystem();

    /**
     * Stop everything in the subsystem (now)
     */
    abstract public void stop();

    /**
     * Reset all the motors
     */
    abstract public void zeroSensors();
}
