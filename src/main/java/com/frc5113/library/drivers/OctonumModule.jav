import edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import com.frc5113.library.motors.SmartFalcon;
import com.frc5113.library.primative.OctonumType;



public class OctonumModule {
    public SmartFalcon motor;
    public DoubleSolenoid solenoid;

    public OctonumType current;

    public OctonumModule(SmartFalcon motor, DoubleSolenoid solenoid, OctonumType startState) {
        this.motor = motor;
        this.solenoid = solenoid;
        current = startState;

        if(current == OctonumType.tank) {
            solenoid.set(kForward);
        } else {
            solenoid.set(kReverse);
        }
    }

    public void set(OctonumType type) {
        if(current != type) {
            solenoid.toggle();
        }
        current = driveType;
    }
    
    public void toggle() {
        solenoid.toggle();
    }

    public void solenoidOff() {
        solenoid.off();
    }

    public void brake() {
        solenoid.set(kForward);
        motor.stopMotor();
    }


}