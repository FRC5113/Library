package com.frc5113.library.subsystem;

import com.frc5113.library.drivers.SmartSolenoid;
import com.frc5113.library.primative.SmartTimedRobot;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * A subsystem consisting of a single solenoid that can be activated and deactivated
 */
public class SolenoidSubsystem extends SubsystemBase{
    public final SmartSolenoid solenoid;

    /**
     * Create a new single solenoid subsystem
     * @param name Name of the subsystem
     * @param port Solenoid connection port (on distribution panel)
     * @param robot Main robot instance for deactivation detection
     */
    public SolenoidSubsystem(String name, int port, SmartTimedRobot robot){
        setName(name);
        solenoid = new SmartSolenoid(PneumaticsModuleType.REVPH, port, robot);

        //Default to down
        this.setDefaultCommand(new RunCommand(this::off, this));
    }

    public void on(){
        solenoid.set(true);
    }

    public void off(){
        solenoid.set(false);
    }
}