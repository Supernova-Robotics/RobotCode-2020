/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class AdvancedTankDrive extends CommandBase {

    @Override
    public void initialize() {
        RobotContainer.chassis.tankDrive(0, 0);
    }

    @Override
    public void execute() {
        double left = -RobotContainer.joystick0.getY(Hand.kLeft) * 0.5;
        double right = -RobotContainer.joystick0.getY(Hand.kRight) * 0.5;
        double forward = (RobotContainer.joystick0.getTriggerAxis(Hand.kLeft) + RobotContainer.joystick0.getTriggerAxis(Hand.kRight)) * 0.5;
        
        if (Math.abs(left) < Constants.tankDriveDeadZone) {
            left = 0;
        }
        if (Math.abs(right) < Constants.tankDriveDeadZone) {
            right = 0;
        }
        if (Math.abs(forward) < Constants.tankDriveDeadZone) {
            forward = 0;
        }

        RobotContainer.chassis.tankDrive(left + forward, right + forward);
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.chassis.tankDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
