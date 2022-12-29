package com.frc5113.library.vision.photon;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.targeting.TargetCorner;

import java.util.List;
import java.util.ArrayList;

public class PhotonSubsystem extends SubsystemBase {

    private final PhotonCamera camera;
    private List<PhotonTrackedTarget> targets = new ArrayList<>();
    private PhotonTrackedTarget bestTarget;

    /** Creates a new PhotonVision. */
    public PhotonSubsystem(String cameraName) {
        camera = new PhotonCamera(cameraName);
    }

    public void updateBestTarget(PhotonPipelineResult result){
        if (!result.hasTargets()){
            bestTarget = null;
            return;
        }
        bestTarget = result.getBestTarget();
    }

    public void updateTargets(PhotonPipelineResult result){
        if (!result.hasTargets()){
            targets.clear();
            return;
        }
        targets = result.getTargets();
    }

    public PhotonTrackedTarget getBestTarget(){
        return bestTarget;
    }

    public double getTargetYaw(PhotonTrackedTarget target){
        return target.getYaw();
    }

    public double getTargetPitch(PhotonTrackedTarget target){
        return target.getPitch();
    }

    public double getTargetArea(PhotonTrackedTarget target){
        return target.getArea();
    }

    public int getId(PhotonTrackedTarget target){
        return 0; // return target.getFiducialId();
    }

    /**
     * Acts as the PhotonVision equivalent of Limelight::getTx()
     @return Amount of pixels between center of "screen" and center of bounding box horizontally.
     */
    public double getTx(double centerX){
        PhotonTrackedTarget target = getBestTarget();
        List<TargetCorner> corners = target.getCorners();
        double leftCorner = corners.get(0).x;
        double rightCorner = corners.get(3).x;

        return (leftCorner-rightCorner)/2 - centerX;
    }
    /**
     * Acts as the PhotonVision equivalent of Limelight::getTy()
     @return Amount of pixels between center of "screen" and center of bounding box vertically.
     */
    public double getTy(double centerY){

        PhotonTrackedTarget target = getBestTarget();
        List<TargetCorner> corners = target.getCorners();

        double leftCorner = corners.get(0).y;
        double rightCorner = corners.get(3).y;

        return (leftCorner-rightCorner)/2 - centerY;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        var result = camera.getLatestResult();
        updateBestTarget(result);
        updateTargets(result);
    }
}