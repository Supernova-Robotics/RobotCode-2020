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

/**
 * This is a special drive mode used by the previous driver YHC, and may be
 * found comfortable for some people.
 */

public class YHCDrive extends CommandBase {

    @Override
    public void initialize() {
        RobotContainer.chassis.tankDrive(0, 0);
    }

    @Override
    public void execute() {
        // chassis
        double forward = -RobotContainer.joystick0.getY(Hand.kLeft) * Constants.kForward;
        double turn = -(RobotContainer.joystick0.getTriggerAxis(Hand.kLeft)
                - RobotContainer.joystick0.getTriggerAxis(Hand.kRight)) * Constants.kTurn;
        if (RobotContainer.chassis.usingTeleop) {
            RobotContainer.chassis.povDrive(forward, turn);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
