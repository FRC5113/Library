package com.frc5113.library.oi.xbox;

import com.frc5113.library.oi.Dpad;
import com.frc5113.library.oi.buttons.AxisButton;
import com.frc5113.library.oi.buttons.Button;
import com.frc5113.library.oi.scalers.SmoothCubicCurve;
import com.frc5113.library.oi.scalers.Curve;
import com.frc5113.library.oi.scalers.NoOpCurve;
import com.frc5113.library.oi.sticks.ThumbStick;
import com.frc5113.library.oi.buttons.AxisButton.ThresholdType;
import edu.wpi.first.wpilibj.XboxController;

public class XboxGamepad extends XboxController {

    public SmoothCubicCurve smoothCubicRX = new SmoothCubicCurve();
    public SmoothCubicCurve smoothCubicRY = new SmoothCubicCurve();
    public SmoothCubicCurve smoothCubicLX = new SmoothCubicCurve();
    public SmoothCubicCurve smoothCubicLY = new SmoothCubicCurve();

    public NoOpCurve noOp = new NoOpCurve();

    public Curve lxCurve;
    public Curve lyCurve;
    public Curve rxCurve; 
    public Curve ryCurve; 

    public com.frc5113.library.oi.buttons.Button xButton = new com.frc5113.library.oi.buttons.Button(this, XboxButton.X);
    public com.frc5113.library.oi.buttons.Button yButton = new com.frc5113.library.oi.buttons.Button(this, XboxButton.Y);
    public com.frc5113.library.oi.buttons.Button aButton = new com.frc5113.library.oi.buttons.Button(this, XboxButton.A);
    public com.frc5113.library.oi.buttons.Button bButton = new com.frc5113.library.oi.buttons.Button(this, XboxButton.B);
    public com.frc5113.library.oi.buttons.Button rightBumper = new com.frc5113.library.oi.buttons.Button(this, XboxButton.rightBumper);
    public com.frc5113.library.oi.buttons.Button leftBumper = new com.frc5113.library.oi.buttons.Button(this, XboxButton.leftBumper);
    public com.frc5113.library.oi.buttons.Button startButton = new com.frc5113.library.oi.buttons.Button(this, XboxButton.Start);
    public com.frc5113.library.oi.buttons.Button backButton = new com.frc5113.library.oi.buttons.Button(this, XboxButton.Back);
    public com.frc5113.library.oi.buttons.Button leftStickButton = new com.frc5113.library.oi.buttons.Button(this, XboxButton.leftStick);
    public com.frc5113.library.oi.buttons.Button rightStickButton = new com.frc5113.library.oi.buttons.Button(this, XboxButton.rightStick);

    public AxisButton leftTriggerButton = new AxisButton(this, XboxAxis.leftTrigger, .05, ThresholdType.GREATER_THAN);
    public AxisButton rightTriggerButton = new AxisButton(this, XboxAxis.rightTrigger, .05,
            ThresholdType.GREATER_THAN);
    public Dpad Dpad = new Dpad(this, XboxAxis.dPad);

    public ThumbStick leftStick = new ThumbStick(this, XboxAxis.leftX, XboxAxis.leftY, lxCurve, lyCurve);
    public ThumbStick rightStick = new ThumbStick(this, XboxAxis.rightX, XboxAxis.rightY, rxCurve, ryCurve);

    public Triggers triggers = new Triggers(this);

    public Rumble rumble = new Rumble(this);

    /**
     * Create with no Scaler
     * @param port DS Port of gamepad
     */
    public XboxGamepad(int port) {
        super(port);
        lxCurve = noOp;
        lyCurve = noOp;
        rxCurve = noOp;
        rxCurve = noOp;
    }

    /**
     * Create with default smoothCubic Curve
     * @param port DS Port of gamepad
     * @param xDeadband deadband value for x axis
     * @param yDeadband deadband value for y axis
     */
    public XboxGamepad(int port, double xDeadband, double yDeadband) {
        this(port);
        lxCurve = smoothCubicLX;
        lyCurve = smoothCubicLY;
        rxCurve = smoothCubicRX;
        ryCurve = smoothCubicRY;
        lxCurve.setDeadDiameter(xDeadband);
        lyCurve.setDeadDiameter(yDeadband);
        rxCurve.setDeadDiameter(xDeadband);
        ryCurve.setDeadDiameter(yDeadband);
    }
    /**
     * Create with default smoothCubic Curve with seperate left and right deadbands
     * @param port DS Port of gamepad
     * @param leftXDeadband deadband value for left stick x axis
     * @param leftYDeadband deadband value for left stick y axis
     * @param rightXDeadband deadband value for right stick x axis
     * @param rightYDeadband deadband value for right stick y axis
     */
    public XboxGamepad(int port, double leftXDeadband, double leftYDeadband, double rightXDeadband,
                       double rightYDeadband) {
        this(port);
        lxCurve = smoothCubicLX;
        lyCurve = smoothCubicLY;
        rxCurve = smoothCubicRX;
        ryCurve = smoothCubicRY;
        lxCurve.setDeadDiameter(leftXDeadband);
        lyCurve.setDeadDiameter(leftYDeadband);
        rxCurve.setDeadDiameter(rightXDeadband);
        ryCurve.setDeadDiameter(rightYDeadband);
    }
    /**
     * Create with curves
     * @param port DS Port of gamepad
     * @param lxCurve Curve to adjust X Axis
     * @param lyCurve Curve to adjust Y Axis
     */
    public XboxGamepad(int port, Curve xCurve, Curve yCurve) {
        this(port);
        this.lxCurve = xCurve;
        this.lyCurve = yCurve;
        this.rxCurve = xCurve;
        this.ryCurve = yCurve;
    }
    /**
     * Create with seperate curves for left and right
     * @param port DS Port of gamepad
     * @param lxCurve Curve to adjust left thumbstick X Axis
     * @param lyCurve Curve to adjust left thumstick Y Axis
     * @param rxCurve Curve to adjust right thumbstick X Axis
     * @param ryCurve Curve to adjust right thumstick Y Axis
     * @param tCurve Curve to adjust Twist Axis
     */
    public XboxGamepad(int port, Curve lxCurve, Curve lyCurve, Curve rxCurve, Curve ryCurve) {
        this(port);
        this.lxCurve = lxCurve;
        this.lyCurve = lyCurve;
        this.rxCurve = rxCurve;
        this.ryCurve = ryCurve;
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
         * @param mValue The integer value corresponding to a enum
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


    public enum XboxAxis implements com.frc5113.library.primative.Axis {
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