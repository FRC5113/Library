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

    // enforce telemetry
    abstract public void shuffleboardTelemetry();
}
