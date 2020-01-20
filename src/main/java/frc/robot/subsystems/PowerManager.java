/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PowerManager extends SubsystemBase {

    private PowerDistributionPanel pdp;
    private final double kP;
    private double powerDecreasePercentage;

    private boolean needCalc;

    public PowerManager() {
        pdp = new PowerDistributionPanel();
        kP = 1.0 / (Constants.powerLimitthreshold - Constants.lowVotageLimit);
        needCalc = true;
    }

    @Override
    public void periodic() {
        needCalc = true;
    }

    public PowerDistributionPanel getPDPInstance() {
        return pdp;
    }

    public double getPowerDecreasePercentage() {
        if (needCalc) {
            double votage = pdp.getVoltage();
            if (votage < Constants.powerLimitthreshold) {
                double delta = Constants.powerLimitthreshold - votage;
                powerDecreasePercentage = delta * kP;
            } else {
                powerDecreasePercentage = 0;
            }
            needCalc = false;
        }
        return powerDecreasePercentage;
    }
}
