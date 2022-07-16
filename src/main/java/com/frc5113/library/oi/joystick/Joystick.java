package com.frc5113.library.oi.joystick;

import com.frc5113.library.oi.Dpad;
import com.frc5113.library.oi.buttons.Button;
import com.frc5113.library.oi.sticks.Slider;
import com.frc5113.library.oi.sticks.Stick;
import com.frc5113.library.oi.xbox.XboxGamepad;
import com.frc5113.library.primative.Axis;

public class Joystick extends edu.wpi.first.wpilibj.Joystick {
    public Joystick(int port) {
        super(port);
    }

    public Joystick(int port, double xDeadband, double yDeadband) {
        this(port);
//        this.stick.setDeadband(xDeadband, yDeadband);
    }

    public Button triggerButton = new Button(this, JoystickButton.k1);
    public Button thumbButton = new Button(this, JoystickButton.k2);
    public Button headBottomLeftButton = new Button(this, JoystickButton.k3);
    public Button headBottomRightButton = new Button(this, JoystickButton.k4);
    public Button headTopLeftButton = new Button(this, JoystickButton.k5);
    public Button headTopRightButton = new Button(this, JoystickButton.k6);
    public Button baseTopLeftButton = new Button(this, JoystickButton.k7);
    public Button baseTopRightButton = new Button(this, JoystickButton.k8);
    public Button baseMiddleLeftButton = new Button(this, JoystickButton.k9);
    public Button baseMiddleRightButton = new Button(this, JoystickButton.k10);
    public Button baseBottomLeftButton = new Button(this, JoystickButton.k11);
    public Button baseBottomRightButton = new Button(this, JoystickButton.k12);

    public Dpad dPad = new Dpad(this, JoystickAxis.POV);

    public Stick stick = new Stick(this, JoystickAxis.X, JoystickAxis.Y, JoystickAxis.Twist);

    public Slider slider = new Slider(this, JoystickAxis.Slider);

    public enum JoystickButton {
        k1(1),
        k2(2),
        k3(3),
        k4(4),
        k5(5),
        k6(6),
        k7(7),
        k8(8),
        k9(9),
        k10(10),
        k11(11),
        k12(12);

        public final int value;
        JoystickButton(int value) {
            this.value = value;
        }
    }

    public enum JoystickAxis implements Axis {
        X(1),
        Y(2),
        Twist(3),
        Slider(4),
        POV(5);

        public final int value;

        JoystickAxis(int value) {
            this.value = value;
        }

        /**
         * Get the name of the value of the current
         *
         * @return name of the current value
         */
        @Override
        public String toString() {
            return this.name();
        }

        /**
         * Get the enum corresponding to the integer value
         *
         * @return Enum with corresponding value || null
         */
        public JoystickAxis fromValue(int mValue) {
            for (JoystickAxis v : values()) {
                if (v.value == mValue) {
                    return v;
                }
            }
            return null;
        }

        /**
         * Get the value of the value
         * @return <code>value</code>
         */
        public int getValue() {
            return value;
        }
    }
}
