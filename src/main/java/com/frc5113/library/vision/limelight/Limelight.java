package com.frc5113.library.vision.limelight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Notifier;
import com.frc5113.library.vision.limelight.LimelightControlModes.*;

/**
 * Utility class to speed up work with the "fruit camera" Limelight. <br><br>
 *
 * The Limelight Class was started by Corey Applegate of Team 3244
 * Granite City Gearheads. We Hope you enjoy the Limelight
 * camera. Further modifications by Spectrum and the Combustible Lemons.
 *
 * @author Applegate (3244), Spectrum (3847), Vladimir Bondar (5113)
 */

public class Limelight {

    private final NetworkTable table;
    private String tableName = "";
    private Boolean isConnected = false;
    private final double heartBeatPeriod = 0.1;

    class HeartbeatRunnable implements java.lang.Runnable {
        public void run() {
            resetPipelineLatency();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isConnected = getPipelineLatency() != 0.0;
        }
    }

    Notifier _heartBeat = new Notifier(new HeartbeatRunnable());

    /**
     * Using the Default Limelight NT table
     */
    public Limelight() {
        tableName = "limelight";
        table = NetworkTableInstance.getDefault().getTable(tableName);
        _heartBeat.startPeriodic(heartBeatPeriod);
    }

    /**
     * If you changed the name of your Limelight, make sure to connect to that table
     * @param tableName name of the limelight table
     */
    public Limelight(String tableName) {
        this.tableName = tableName;
        table = NetworkTableInstance.getDefault().getTable(this.tableName);
        _heartBeat.startPeriodic(heartBeatPeriod);
    }

    /**
     * Create a connection to the limelight based on a specific network table
     * @param table Network table used for connection
     */
    public Limelight(NetworkTable table) {
        this.table = table;
        _heartBeat.startPeriodic(heartBeatPeriod);

    }

    /**
     * Check the connection to the camera
     * @return camera connection
     */
    public boolean isConnected() {
        return isConnected;
    }

    /**
     * Whether the limelight has any valid targets (0 or 1)
     *
     * @return valid targets
     */
    public boolean getIsTargetFound() {
        NetworkTableEntry tv = table.getEntry("tv");
        double v = tv.getDouble(0);
        return v != 0.0f;
    }

    /**
     * tx Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
     *
     * @return Offset in degrees
     */
    public double getDegreeRotationToTarget() {
        NetworkTableEntry tx = table.getEntry("tx");
        return tx.getDouble(0.0);
    }

    /**
     * ty Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
     *
     * @return Offset in degrees
     */
    public double getdegVerticalToTarget() {
        NetworkTableEntry ty = table.getEntry("ty");
        double y = ty.getDouble(0.0);
        return y;
    }

    /**
     * ta Target Area (0% of image to 100% of image)
     *
     * @return target area [0, 1]
     */
    public double getTargetArea() {
        NetworkTableEntry ta = table.getEntry("ta");
        return ta.getDouble(0.0);
    }

    /**
     * ts Skew or rotation (-90 degrees to 0 degrees)
     *
     * @return Rotation in degrees
     */
    public double getSkew_Rotation() {
        NetworkTableEntry ts = table.getEntry("ts");
        return ts.getDouble(0.0);
    }

    /**
     * tl The pipeline’s latency contribution (ms).
     * Make sure to add at least 11ms for image capture latency.
     *
     * @return latency (ms)
     */
    public double getPipelineLatency() {
        NetworkTableEntry tl = table.getEntry("tl");
        return tl.getDouble(0.0);
    }

    /**
     * Set the pipeline latency value to zero
     */
    private void resetPipelineLatency() {
        table.getEntry("tl").setValue(0.0);
    }

    // Setters
    /**
     * LedMode Sets limelight’s LED state
     * <ul>
     * <li>kPipeline</li>
     * <li>kForceOff</li>
     * <li>kForceBlink</li>
     * <li>kForceOn</li>
     * </ul>
     * @param ledMode preferred operation mode
     */
    public void setLEDMode(LedMode ledMode) {
        table.getEntry("ledMode").setValue(ledMode.getValue());
    }

    /**
     * Returns current LED mode of the Limelight
     *
     * @return LedMode Current mode of the LED
     */
    public LedMode getLEDMode() {
        NetworkTableEntry ledMode = table.getEntry("ledMode");
        double led = ledMode.getDouble(0.0);
        return LedMode.getByValue(led);
    }

    /**
     * camMode Sets limelight’s operation mode.
     * <ul>
     * <li>kVision</li>
     * <li>kDriver (Increases exposure, disables vision processing)</li>
     * </ul>
     * @param camMode Preferred mode of operation
     */

    public void setCamMode(CamMode camMode) {
        table.getEntry("camMode").setValue(camMode.getValue());
    }

    /**
     * Returns current Cam mode of the Limelight
     *
     * @return CamMode Current mode of operation
     */
    public CamMode getCamMode() {
        NetworkTableEntry camMode = table.getEntry("camMode");
        double cam = camMode.getDouble(0.0);
        return CamMode.getByValue(cam);
    }


    /**
     * pipeline Sets Limelight’s current pipeline:
     * @param pipeline Integer value of preferred pipeline:
     *                 0 - 9	Select pipeline 0-9
     */
    public void setPipeline(Integer pipeline) {
        if (pipeline < 0) {
            throw new IllegalArgumentException("Pipeline can not be less than 0");
        } else if (pipeline > 9) {
            throw new IllegalArgumentException("Pipeline can not be greater than 9");
        }
        table.getEntry("pipeline").setValue(pipeline);
    }

    /**
     * Returns current Pipeline of the Limelight
     *
     * @return Selected pipeline (double)
     */
    public double getPipeline() {
        NetworkTableEntry pipeline = table.getEntry("pipeline");
        return pipeline.getDouble(0.0);
    }

    /**
     * Returns current Pipeline of the Limelight
     *
     * @return Selected pipeline (int)
     */
    public int getPipelineInt() {
        NetworkTableEntry pipeline = table.getEntry("pipeline");
        return (int) pipeline.getDouble(0.0);
    }

    /**
     * stream   Sets limelight’s streaming mode
     * <ul>
     * <li>kStandard - Side-by-side streams if a webcam is attached to Limelight</li>
     * <li>kPiPMain - The secondary camera stream is placed in the lower-right corner of the primary camera stream</li>
     * <li>kPiPSecondary - The primary camera stream is placed in the lower-right corner of the secondary camera stream</li>
     * </ul>
     * @param stream Secondary camera placement
     */
    public void setStream(StreamType stream) {
        table.getEntry("stream").setValue(stream.getValue());
    }

    /**
     * Get the current camera positioning
     * @return Streaming mode
     */
    public StreamType getStream() {
        NetworkTableEntry stream = table.getEntry("stream");
        double st = stream.getDouble(0.0);
        return StreamType.getByValue(st);
    }


    /**
     * snapshot Allows taking a camera render up to 2 times per second.
     * Value <b>MUST</b> be set to zero after each snapshot to take another snapshot, see:
     * <br><br>Fix "snapshot" networktables key. Users must set the "snapshot" key to "0" before setting it to "1" to take a screenshot. (Bug fix changelog 2022.2.3 (3.16.22)) <br><br>
     * <br><br>
     * kon - Take 1 snapshot
     * koff - Do not take a snapshot (default state)
     *
     * @param snapshot Whether to take a snapshot
     */
    public void setSnapshot(Snapshot snapshot) {
        table.getEntry("snapshot").setValue(snapshot.getValue());
    }

    /**
     * Get whether the limelight has taken a snapshot
     * @return Whether the limelight has taken a snapshot
     */
    public Snapshot getSnapshot() {
        NetworkTableEntry snapshot = table.getEntry("snapshot");
        double takeSnapshot = snapshot.getDouble(0.0);
        return Snapshot.getByValue(takeSnapshot);
    }

    // *************** Advanced Usage with Raw Contours *********************

    /**
     * Limelight posts three raw contours to NetworkTables that are not influenced by your grouping mode.
     * That is, they are filtered with your pipeline parameters, but never grouped. X and Y are returned
     * in normalized screen space (-1 to 1) rather than degrees.
     *
     * @param raw Target
     * @return Degree of target
     */
    public double getAdvanced_RotationToTarget(Advanced_Target raw) {
        NetworkTableEntry txRaw = table.getEntry("tx" + Integer.toString(raw.getValue()));
        double x = txRaw.getDouble(0.0);
        return x;
    }

    public double getAdvanced_degVerticalToTarget(Advanced_Target raw) {
        NetworkTableEntry tyRaw = table.getEntry("ty" + Integer.toString(raw.getValue()));
        double y = tyRaw.getDouble(0.0);
        return y;
    }

    public double getAdvanced_TargetArea(Advanced_Target raw) {
        NetworkTableEntry taRaw = table.getEntry("ta" + Integer.toString(raw.getValue()));
        double a = taRaw.getDouble(0.0);
        return a;
    }

    public double getAdvanced_Skew_Rotation(Advanced_Target raw) {
        NetworkTableEntry tsRaw = table.getEntry("ts" + Integer.toString(raw.getValue()));
        double s = tsRaw.getDouble(0.0);
        return s;
    }

    // Raw Crosshairs:
    // If you are using raw targeting data, you can still utilize your calibrated crosshairs:

    public double[] getAdvanced_RawCrosshair(Advanced_Crosshair raw) {
        double[] crosshars = new double[2];
        crosshars[0] = getAdvanced_RawCrosshair_X(raw);
        crosshars[1] = getAdvanced_RawCrosshair_Y(raw);
        return crosshars;
    }

    public double getAdvanced_RawCrosshair_X(Advanced_Crosshair raw) {
        NetworkTableEntry cxRaw = table.getEntry("cx" + Integer.toString(raw.getValue()));
        double x = cxRaw.getDouble(0.0);
        return x;
    }

    public double getAdvanced_RawCrosshair_Y(Advanced_Crosshair raw) {
        NetworkTableEntry cyRaw = table.getEntry("cy" + Integer.toString(raw.getValue()));
        double y = cyRaw.getDouble(0.0);
        return y;
    }
}
