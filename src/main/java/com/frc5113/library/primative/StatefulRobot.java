package com.frc5113.library.primative;

public interface StatefulRobot {
  public RobotState state = RobotState.DISABLED;

  public abstract RobotState getState();

  public abstract void setState(RobotState newState);
}
