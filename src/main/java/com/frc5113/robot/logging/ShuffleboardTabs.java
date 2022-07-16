package com.frc5113.robot.logging;

import com.frc5113.library.logging.DataLogger;
import edu.wpi.first.wpilibj.Notifier;

// Class that wraps all of the interaction with the Shuffleboard

// All decisions about content and layout of the Shuffleboard are consolidated in this file
// to make it easier to change things rather than having to look throughout all of the
// classes for subsystems, commands, etc.

// The ShuffleboardTabs class knows about the subsystems, commands, etc. but generally not vice versa.
// Based on Code from FRC# 4141
public class ShuffleboardTabs  extends DataLogger {

    private final double heartBeatPeriod = 1;     // How fast we should run the update methods, most values are set by suppliers so they update quickly
    Notifier _heartBeat = new Notifier(new PeriodicRunnable());

    // Tabs
//  private final SampleTelemetry sampleTelemetry;

    public ShuffleboardTabs() {
        printLow(LogFlags.Telemetry, "Constructing ShuffleboardTabs...");

//      sampleTelemetry = new SampleTelemetry();
    }

    public void initialize() {
        printLow(LogFlags.Telemetry, "Initializing ShuffleboardTabs...");
        //sampleTelemetry.initialize();
        _heartBeat.startPeriodic(heartBeatPeriod);
    }

    /**
     * Update values from Shuffleboard, this is run at the heartBeatPeriod
     * We don't need to assign values every program cycle
     */
    private void update() {
//        sampleTelemetry.update();
    }

    //Controls how often we update values based on shuffleboard not how often data changes are sent to shuffleboard
    class PeriodicRunnable implements java.lang.Runnable {
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            update();
        }
    }
}
