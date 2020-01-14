/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Chassis;

public class AdvancedTankDrive extends CommandBase {
  Chassis chassis;
  XboxController joystick;

  public AdvancedTankDrive(Chassis c, XboxController j) {
    chassis = c;
    joystick = j;
    addRequirements(chassis);
  }

  @Override
  public void initialize() {
    chassis.tankDrive(0, 0);
  }

  @Override
  public void execute() {
    double left = -joystick.getY(Hand.kLeft) * 0.5;
    double right = -joystick.getY(Hand.kRight) * 0.5;
    double forward = (joystick.getTriggerAxis(Hand.kLeft) + joystick.getTriggerAxis(Hand.kRight)) * 0.5; // get the two top trigger
    if (Math.abs(left) < Constants.tankDriveDeadZone) {
      left = 0;
    }
    if (Math.abs(right) < Constants.tankDriveDeadZone) {
      right = 0;
    }
    if (Math.abs(forward) < Constants.tankDriveDeadZone) {
      forward = 0;
    }
    
    chassis.tankDrive(left + forward, right + forward);
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
