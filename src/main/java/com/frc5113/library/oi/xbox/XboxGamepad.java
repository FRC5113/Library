package com.frc5113.library.oi.xbox;

import com.frc5113.library.oi.Dpad;
import com.frc5113.library.oi.buttons.AxisButton;
import com.frc5113.library.oi.buttons.Button;
import com.frc5113.library.oi.scalers.SmoothCubicCurve;
import com.frc5113.library.oi.sticks.ThumbStick;
import com.frc5113.library.primative.Axis;
import edu.wpi.first.wpilibj.Joystick;
import com.frc5113.library.oi.buttons.AxisButton.ThresholdType;

public class XboxGamepad extends Joystick {

    public SmoothCubicCurve smoothCubicRX = new SmoothCubicCurve();
    public SmoothCubicCurve smoothCubicRY = new SmoothCubicCurve();
    public SmoothCubicCurve smoothCubicLX = new SmoothCubicCurve();
    public SmoothCubicCurve smoothCubicLY = new SmoothCubicCurve();

    public Button xButton = new Button(this, XboxButton.X);
    public Button yButton = new Button(this, XboxButton.Y);
    public Button aButton = new Button(this, XboxButton.A);
    public Button bButton = new Button(this, XboxButton.B);
    public Button rightBumper = new Button(this, XboxButton.rightBumper);
    public Button leftBumper = new Button(this, XboxButton.leftBumper);
    public Button startButton = new Button(this, XboxButton.Start);
    public Button backButton = new Button(this, XboxButton.Back);
    public Button leftStickButton = new Button(this, XboxButton.leftStick);
    public Button rightStickButton = new Button(this, XboxButton.rightStick);

    public AxisButton leftTriggerButton = new AxisButton(this, XboxAxis.leftTrigger, .05, ThresholdType.GREATER_THAN);
    public AxisButton rightTriggerButton = new AxisButton(this, XboxAxis.rightTrigger, .05,
            ThresholdType.GREATER_THAN);
    public Dpad Dpad = new Dpad(this, XboxAxis.dPad);

    public ThumbStick leftStick = new ThumbStick(this, XboxAxis.leftX, XboxAxis.leftY);
    public ThumbStick rightStick = new ThumbStick(this, XboxAxis.rightX, XboxAxis.rightY);

    public Triggers triggers = new Triggers(this);

    public Rumble rumble = new Rumble(this);

    public XboxGamepad(int port) {
        super(port);
    }

    public XboxGamepad(int port, double xDeadband, double yDeadband) {
        this(port);
        smoothCubicLX.setDeadDiameter(xDeadband);
        smoothCubicLY.setDeadDiameter(yDeadband);
        smoothCubicRX.setDeadDiameter(xDeadband);
        smoothCubicRY.setDeadDiameter(yDeadband);
    }

    public XboxGamepad(int port, double leftXDeadband, double leftYDeadband, double rightXDeadband,
                       double rightYDeadband) {
        this(port);
        smoothCubicLX.setDeadDiameter(leftXDeadband);
        smoothCubicLY.setDeadDiameter(leftYDeadband);
        smoothCubicRX.setDeadDiameter(rightXDeadband);
        smoothCubicRY.setDeadDiameter(rightYDeadband);
    }


     public enum XboxButton {
        A(1),
        B(2),
        X(3),
        Y(4),
        leftBumper(5),
        rightBumper(6),
        Back(7),
        Start(8),
        leftStick(9),
        rightStick(10);

        public final int value;

        XboxButton(int value) {
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
        public XboxButton fromValue(int mValue) {
            for (XboxButton v : values()) {
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


    public enum XboxAxis implements Axis {
        leftX(0),
        leftY(1),
        leftTrigger(2),
        rightTrigger(3),
        rightX(4),
        rightY(5),
        dPad(6);

        public final int value;

        XboxAxis(int value) {
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
        public XboxAxis fromValue(int mValue) {
            for (XboxAxis v : values()) {
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