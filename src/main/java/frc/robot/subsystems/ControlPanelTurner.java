/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ControlPanelTurner extends SubsystemBase {
    private boolean extending;
    private Spark turnerMotor;

    public enum PanelColor{
        RED, YELLOW, BLUE, GREEN
    }

    public ControlPanelTurner() {
        extending = false;
        turnerMotor = new Spark(Constants.panelTurnerPort);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void turn(boolean status){
        if(status){
            turnerMotor.set(Constants.panelTurnerSpeed);
        }else{
            turnerMotor.set(0);
        }
    }

    public void extend(boolean extend){
        throw new UnsupportedOperationException("Not impliment yet");
    }

    public boolean isExtending(){
        return extending;
    }

    public PanelColor getColor(){
        throw new UnsupportedOperationException("Not impliment yet");
    }
}
