package com.frc5113.robot.logging;

import com.frc5113.library.primative.BaseLoggerFlags;

/**
 * This enum adds robot specific flags to the FlagLogger class by providing the tags
 */
public enum LogFlags implements BaseLoggerFlags {
    General("GENERAL"),
    Telemetry("TELEMETRY"),
    Controls("CONTROL"),
    Auton("AUTON"),
    Drive("DRIVE"),
    Indexer("INDEXER"),
    Feeder("FEEDER"),
    Intake("INTAKE"),
    Launcher("LAUNCHER"),
    Climber("CLIMBER"),
    Limelight("LIMELIGHT");

    public final String tag;

    LogFlags(String tag) {
        this.tag = tag;
    }

    public BaseLoggerFlags fromTag(String mTag) {
        for (BaseLoggerFlags b : values()) {
            if (b.tag.equals(mTag)) {
                return b;
            }
        }
        return null;
    }

    public BaseLoggerFlags[] getValues() {
        return values();
    }
}
