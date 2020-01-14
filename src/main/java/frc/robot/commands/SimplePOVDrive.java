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
import frc.robot.subsystems.Chassis;

public class SimplePOVDrive extends CommandBase {
  Chassis chassis;
  XboxController joystick;

  public SimplePOVDrive(Chassis c, XboxController j) {
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
    double forward = -joystick.getY(Hand.kLeft);
    double turn = joystick.getX(Hand.kRight);
    chassis.povDrive(forward, turn);
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
