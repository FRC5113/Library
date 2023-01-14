package com.frc5113.library.primative;

/** Extend me to implement flags in FlagLogger in the Logging package */
public interface BaseLoggerFlags {
  public final String tag = "";

  public BaseLoggerFlags fromTag(String mTag);

  public BaseLoggerFlags[] getValues();
}
