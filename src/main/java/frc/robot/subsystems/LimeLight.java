/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimeLight extends SubsystemBase {
    private NetworkTable limelightTable;

    private NetworkTableEntry anyValidEntry;
    private NetworkTableEntry xAngleEntry;
    private NetworkTableEntry yAngleEntry;
    private NetworkTableEntry lightEntry;

    public LimeLight() {
        limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
        anyValidEntry = limelightTable.getEntry("tv");
        xAngleEntry = limelightTable.getEntry("tx");
        yAngleEntry = limelightTable.getEntry("ty");
        lightEntry = limelightTable.getEntry("ledMode");
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("any valid", anyValidEntry.getDouble(0));
        SmartDashboard.putNumber("x angle", xAngleEntry.getDouble(0));
        SmartDashboard.putNumber("y angle", yAngleEntry.getDouble(0));
    }

    public boolean anyValid(){
        if(anyValidEntry.getDouble(0) > 0.5){
            return true;
        } else{
            return false;
        }
    }

    public double getX(){
        return xAngleEntry.getDouble(0);
    }

    public double getY(){
        return yAngleEntry.getDouble(0);
    }

    public void turnLight(boolean on){
        if(on){
            lightEntry.setNumber(3);
        } else{
            lightEntry.setNumber(1);
        }
    }
}
