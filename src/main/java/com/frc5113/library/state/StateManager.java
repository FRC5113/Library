package com.frc5113.library.state;

public class StateManager implements StatefulRobot {
    RobotState state = RobotState.DISABLED;

    public RobotState getState() {
        return state;
    };

    public void setState(RobotState newState) {
        state = newState;
    };
}
