package com.frc5113.library.drivers;

import edu.wpi.first.wpilibj.DigitalInput;

public class InvertibleDigitalInput extends DigitalInput {

    private boolean inverted = false;

    public InvertibleDigitalInput(int channel) {
        super(channel);
    }

    public InvertibleDigitalInput(int channel, boolean isInverted) {
        super(channel);
        setInverted(isInverted);
    }

    public boolean get(boolean isInvert) {
        if (isInvert) {
            return !this.get();
        } else {
            return this.get();
        }
    }

    public boolean isInverted() {
        return inverted;
    }

    public void setInverted(boolean isInvert) {
        this.inverted = isInvert;
    }
}
