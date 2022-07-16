package com.frc5113.library.primative;

public interface Axis {
    int value = -1;

    public Axis fromValue(int mValue);

    int getValue();

    String toString();
}
