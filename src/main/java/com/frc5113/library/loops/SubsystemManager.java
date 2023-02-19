package com.frc5113.library.loops;

import com.frc5113.library.subsystem.SmartSubsystem;
import edu.wpi.first.wpilibj.DriverStation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Used to reset, start, stop, and update all subsystems at once */
public class SubsystemManager implements ILooper {
  private List<SmartSubsystem> mAllSubsystems = new ArrayList<>();
  private List<Loop> mLoops = new ArrayList<>();

  public SubsystemManager() {}

  public void outputToSmartDashboard() {
    mAllSubsystems.forEach(SmartSubsystem::outputTelemetry);
  }

  public boolean checkSubsystems() {
    boolean ret_val = true;

    for (SmartSubsystem s : mAllSubsystems) {
      boolean check = s.checkSubsystem();
      if (!check)
        DriverStation.reportError("Subsystem " + s.getClass().getName() + " failed check", false);
      ret_val &= check;
    }

    return ret_val;
  }

  public boolean checkSubsystemsPeriodic(){
    boolean ret_val = true;

    for (SmartSubsystem s: mAllSubsystems){
      boolean check = s.checkSubsystem();
      if (!check){
        DriverStation.reportError("Subsystem " + s.getClass().getName() + " failed check", false);
      }
      ret_val &= check;
    }

    return ret_val;

  }

  public void stop() {
    mAllSubsystems.forEach(SmartSubsystem::stop);
  }

  public List<SmartSubsystem> getSubsystems() {
    return mAllSubsystems;
  }

  public void setSubsystems(SmartSubsystem... allSubsystems) {
    mAllSubsystems = Arrays.asList(allSubsystems);
  }

  private class EnabledLoop implements Loop {
    @Override
    public void onStart(double timestamp) {
      mLoops.forEach(l -> l.onStart(timestamp));
    }

    @Override
    public void onLoop(double timestamp) {
      mAllSubsystems.forEach(SmartSubsystem::readPeriodicInputs);
      mLoops.forEach(l -> l.onLoop(timestamp));
      mAllSubsystems.forEach(SmartSubsystem::writePeriodicOutputs);
    }

    @Override
    public void onStop(double timestamp) {
      mLoops.forEach(l -> l.onStop(timestamp));
    }
  }

  private class DisabledLoop implements Loop {
    @Override
    public void onStart(double timestamp) {}

    @Override
    public void onLoop(double timestamp) {
      mAllSubsystems.forEach(SmartSubsystem::readPeriodicInputs);
    }

    @Override
    public void onStop(double timestamp) {}
  }

  public void registerEnabledLoops(Looper enabledLooper) {
    mAllSubsystems.forEach(s -> s.registerEnabledLoops(this));
    enabledLooper.register(new EnabledLoop());
  }

  public void registerDisabledLoops(Looper disabledLooper) {
    disabledLooper.register(new DisabledLoop());
  }

  public void registerCheckLoops(Looper checkLooper){
    mAllSubsystems.forEach(s -> s.registerPeriodicSubsystemCheck(this));
  }

  @Override
  public void register(Loop loop) {
    mLoops.add(loop);
  }
}
