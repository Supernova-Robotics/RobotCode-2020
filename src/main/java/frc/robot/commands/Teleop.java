/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.math.MathFunctions;
import frc.robot.subsystems.Chassis;

public class Teleop extends CommandBase {
  /**
   * Creates a new Teleop.
   */
  private Joystick mJoystick1;
  private Joystick mJoystick2;
  private Chassis mChassis;

  public Teleop(Chassis chassis, Joystick j1, Joystick j2) {
    // Use addRequirements() here to declare subsystem dependencies.
    mJoystick1 = j1;
    mJoystick2 = j2;
    mChassis = chassis;
    addRequirements(chassis);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double[] thresholds = { 0, 0.1, 0.4, 0.8, 1 };
    double[] powers = { 0, 0.3, 0.6, 1, 1 };
    double left = mJoystick1.getY(Hand.kLeft);
    double right = mJoystick1.getY(Hand.kRight);

    double leftPower = powers[MathFunctions.fitInterval(thresholds, Math.abs(left))];
    double rightPower = powers[MathFunctions.fitInterval(thresholds, Math.abs(right))];

    if (left < 0) {
      leftPower *= -1.0;
    }
    if (right < 0) {
      rightPower *= -1.0;
    }

    mChassis.tankDrive(leftPower, rightPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mChassis.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
