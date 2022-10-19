package com.frc5113.library.primative;

import com.frc5113.library.config.ConfigLibrary;
import com.frc5113.library.utils.Alert;
import com.frc5113.library.utils.Alert.AlertType;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * The {@link SmartTimedRobot} abstract class provides additional scaffolding to the Timed Robot class.
 * Its goal is to ensure that any robot code contains additional modifiers
 * that are required by libraries to function. While there are no implementation
 * details, they are required (due to the abstract clause) to be @Override-ed in the
 * Robot class.
 *
 * <b>It is important to call super.{methodName} for background changes.</b>
 *
 * @author Vladimir Bondar (5113)
 */
public abstract class SmartTimedRobot extends TimedRobot {
    /**
     * Manages the current state of the robot
     */
    public RobotState state = RobotState.DISABLED;

    /**
     * The unique hexadecimal combination that identifies network equipment.
     * See <a href="https://en.wikipedia.org/wiki/MAC_address">Wikipedia</a> for more information
     */
    public static String MAC = "";
    public static boolean notMainBot = false;

    // Alerts
    public static Alert batteryAlert = new Alert("Low Battery", AlertType.WARNING);
    public static Alert notMainBotAlert = new Alert("Code is not running on the comp bot", Alert.AlertType.WARNING);
    public static Alert FMSConnectedAlert = new Alert("FMS Connected", Alert.AlertType.WARNING);

    private static boolean checkIfPracticeRobot() {
        if (MAC.equals("")) {
            getMACAddress();
        }
        if (!MAC.equals(ConfigLibrary.getMainBotMac())) {
            notMainBot = true;
            notMainBotAlert.set(true);
//            PracticeConstants.practiceBotConstantsOverride();
        }
        return notMainBot;
    }

    private static void getMACAddress() {
        InetAddress localHost;
        NetworkInterface ni;
        byte[] hardwareAddress;
        try {
            localHost = InetAddress.getLocalHost();
            ni = NetworkInterface.getByInetAddress(localHost);
            hardwareAddress = ni.getHardwareAddress();
            String[] hexadecimal = new String[hardwareAddress.length];
            for (int i = 0; i < hardwareAddress.length; i++) {
                hexadecimal[i] = String.format("%02X", hardwareAddress[i]);
            }
            MAC = String.join(":", hexadecimal);
        } catch (SocketException | NullPointerException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public boolean checkFMS() {
        if (DriverStation.isFMSAttached()) {
            FMSConnectedAlert.set(true);
            return true;
        } else {
            FMSConnectedAlert.set(false);
            return false;
        }
    }

    public boolean checkBattery() {
        return RobotController.getBatteryVoltage() < 12.0;
    }

    /*
     * VALUE FOR CHANGE BATTERY ON DASHBOARD
     * Should return true if robot is disabled and voltage is less than 12
     */
    /**
     * Check if the battery should be changed, default under 12 volts
     * @return Whether battery should be changed
     */
    public boolean changeBattery() {
        return (this.state == RobotState.DISABLED && RobotController.getInputVoltage() < 12);
    }

    /**
     * Check if the battery should be changed
     * @param voltage (optional) voltage - default 12
     * @return Whether battery should be changed
     */
    public boolean changeBattery(int voltage) {
        return (this.state == RobotState.DISABLED && RobotController.getInputVoltage() < voltage);
    }

    /**
     * Get the current state of the robot
     * @return {@link RobotState} currently
     */
    public RobotState getState() {
        return state;
    }

    /**
     * Set the current state of the robot
     * @param newState New State to set
     */
    public void setState(RobotState newState) {
        state = newState;
    }

    // Overwrite the base classes to ensure that logic can be placed
    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {}

    /**
     * This function is called every robot packet, no matter the mode. Use this for items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before LiveWindow and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {}

    /** This function is called once each time the robot enters Disabled mode. */
    @Override
    public void disabledInit() {
        setState(RobotState.DISABLED);
//        printNormal("Start disabledInit(), MAC Address:" + MAC);
    }

    @Override
    public void disabledPeriodic() {}

    /** This autonomous runs the autonomous command selected by your RobotContainer class. */
    @Override
    public void autonomousInit() {
        setState(RobotState.AUTONOMOUS);
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {
        setState(RobotState.TELEOP);
    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {}

    @Override
    public void testInit() {
        setState(RobotState.TEST);
    }

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {}

    @Override
    public void simulationInit() {}

    @Override
    public void simulationPeriodic() {}
}
