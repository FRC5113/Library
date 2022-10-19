package com.frc5113.library.sim;

/**
 * Holds information about a simulated device.
 */
public class SimProfile {

    private long _lastTime;
    private boolean _running = false;

    /**
     * Runs the simulation profile.
     * Implemented by device-specific profiles.
     */
    public void run() {
    }

    /**
     * @return The time since last call, in milliseconds.
     */
    protected double getPeriod() {
        // set the start time if not yet running
        if (!_running) {
            _lastTime = System.nanoTime();
            _running = true;
        }

        long now = System.nanoTime();
        final double period = (now - _lastTime) / 1000000.;
        _lastTime = now;

        return period;
    }

    /* scales a random domain of [0, 2pi] to [min, max] while prioritizing the peaks */
    static double random(double min, double max) {
        return ((max - min) / 2 * Math.sin(Math.IEEEremainder(Math.random(), 2 * 3.14159)) + (max + min) / 2);
    }

    public double random(double max) {
        return random(0, max);
    }
}