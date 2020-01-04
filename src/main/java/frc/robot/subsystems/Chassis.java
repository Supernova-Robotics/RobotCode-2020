/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.math.MathFunctions;
import frc.robot.Constants;


public class Chassis extends SubsystemBase {
  private Spark lfMotor;
  private Spark rfMotor;
  private Spark lbMotor;
  private Spark rbMotor;

  public Chassis() {
    lfMotor = new Spark(Constants.lfMotorAddress);
    rfMotor = new Spark(Constants.rfMotorAddress);
    lbMotor = new Spark(Constants.lbMotorAddress);
    rbMotor = new Spark(Constants.rbMotorAddress);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void tankDrive(double left, double right){
    left = MathFunctions.clipToOne(left);
    right = MathFunctions.clipToOne(right);
    
    lfMotor.set(left);
    rfMotor.set(right);
    lbMotor.set(left);
    rbMotor.set(right);
  }

  public void povDrive(double forward, double turnRight){
    tankDrive(forward + turnRight, forward - turnRight);
  }
}
