/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chassis;


public class SimplePOVDrive extends CommandBase {

    @Override
    public void initialize() {
        RobotContainer.chassis.tankDrive(0, 0);
    }

    @Override
    public void execute() {
        double forward = -RobotContainer.joystick0.getY(Hand.kLeft);
        double turn = RobotContainer.joystick0.getX(Hand.kRight);
        RobotContainer.chassis.povDrive(forward, turn);
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
