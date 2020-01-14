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

public class Intake extends SubsystemBase {
  public enum Direction {
    IN, OUT, STOP
  }

  private Spark motor;

  public Intake() {
    motor = new Spark(Constants.intakeMotorPort);
  }

  @Override
  public void periodic() {
  }

  public void set(Direction direction) {
    switch (direction) {
    case IN:
      motor.set(Constants.intakeSpeed);
      break;
    case OUT:
      motor.set(-Constants.intakeSpeed);
      break;
    case STOP:
      motor.set(0);
      break;
    }

  }
}
