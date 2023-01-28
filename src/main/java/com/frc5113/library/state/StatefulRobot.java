package com.frc5113.library.state;

public interface StatefulRobot {
  RobotState getState();

  void setState(RobotState newState);
}
