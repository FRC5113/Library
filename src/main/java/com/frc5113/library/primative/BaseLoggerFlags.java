package com.frc5113.library.primative;

/**
 * Extend me to implement flags in {@link com.frc5113.library.logging.FlagLogger}
 */
public interface BaseLoggerFlags {
    public final String tag = "";
    public BaseLoggerFlags fromTag(String mTag);

    public BaseLoggerFlags[] getValues();
}
