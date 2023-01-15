package com.frc5113.library.primative;

public enum ThresholdType {
    /**
     * The current value is <b>less then</b> the set value
     */
    LESS_THAN,
    /**
     * The current value is <b>greater then</b> the set value
     */
    GREATER_THAN,
    /**
     * The current value is <b>exactly</b> the set value
     */
    EXACT,
    /**
     * For use only with POV hats
     */
    POV,
    /**
     * The current value is <b>outside</b> the set deadband
     */
    DEADBAND;
}