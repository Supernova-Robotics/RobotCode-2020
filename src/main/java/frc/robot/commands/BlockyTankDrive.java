/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.math.MathFunctions;
import frc.robot.subsystems.Chassis;

public class BlockyTankDrive extends CommandBase {
  private Joystick joystick1;
  private Chassis chassis;

  private double[] thresholds = { 0, 0.1, 0.4, 0.8, 1 };
  private double[] powers = { 0, 0.2, 0.4, 0.6, 0.6 };

  public BlockyTankDrive(Chassis c, Joystick j1) {
    // Use addRequirements() here to declare subsystem dependencies.
    joystick1 = j1;
    chassis = c;
    addRequirements(chassis);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double left = -joystick1.getY(Hand.kLeft);
    double right = -joystick1.getRawAxis(5);

    double leftPower = powers[MathFunctions.fitInterval(thresholds, Math.abs(left))];
    double rightPower = powers[MathFunctions.fitInterval(thresholds, Math.abs(right))];

    if (left < 0) {
    leftPower *= -1.0;
    }
    if (right < 0) {
    rightPower *= -1.0;
    }
    SmartDashboard.putNumber("left", joystick1.getY(Hand.kLeft));
    SmartDashboard.putNumber("right", joystick1.getY(Hand.kRight));
    chassis.tankDrive(leftPower, rightPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    chassis.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
