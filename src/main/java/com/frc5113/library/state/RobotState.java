package com.frc5113.library.state;

public enum RobotState {
  /** Robot is turned on, but in a "off" com.frc5113.library.state */
  DISABLED,
  /** Robot is moving by itself */
  AUTONOMOUS,
  /** Robot is moving by the control of a human */
  TELEOP,
  /** The robot is performing a self-test */
  TEST
}
