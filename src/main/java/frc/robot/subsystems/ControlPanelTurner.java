/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ControlPanelTurner extends SubsystemBase {
    public boolean lock = false;

    private Talon turnerMotor;
    private double confidenceThreshold;

    private ColorSensorV3 colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
    private ColorMatch colorMatcher = new ColorMatch();

    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    public enum PanelColor {
        RED, YELLOW, BLUE, GREEN, UNKNOWN
    }

    public ControlPanelTurner() {
        turnerMotor = new Talon(Constants.panelTurnerPort);
        confidenceThreshold = Constants.colorConfidenceThreshold;

        colorMatcher.addColorMatch(kBlueTarget);
        colorMatcher.addColorMatch(kGreenTarget);
        colorMatcher.addColorMatch(kRedTarget);
        colorMatcher.addColorMatch(kYellowTarget);
    }

    @Override
    public void periodic() {
    }

    public void set(boolean turning) {
        if (turning) {
            turnerMotor.set(Constants.panelTurnerSpeed);
        } else {
            turnerMotor.set(0);
        }
    }

    public PanelColor getColor() {
        Color detectedColor = colorSensor.getColor();
        ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);

        if(match.confidence < confidenceThreshold){
            return PanelColor.UNKNOWN;
        }

        if (match.color == kBlueTarget) {
            return PanelColor.BLUE;
        } else if (match.color == kRedTarget) {
            return PanelColor.RED;
        } else if (match.color == kGreenTarget) {
            return PanelColor.GREEN;
        } else if (match.color == kYellowTarget) {
            return PanelColor.YELLOW;
        } else {
            return PanelColor.UNKNOWN;
        }
    }

    public double getThreshold(){
        return confidenceThreshold;
    }

    public void setThreshold(double thrshold){
        confidenceThreshold = thrshold;
    }
}
