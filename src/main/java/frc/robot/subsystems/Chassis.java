/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.math.MathFunctions;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {
  private VictorSPX lfMotor;
  private VictorSPX rfMotor;
  private VictorSPX lbMotor;
  private VictorSPX rbMotor;

  private AnalogInput ultrasonic;

  public Chassis() {
    lfMotor = new VictorSPX(Constants.lfMotorAddress);
    rfMotor = new VictorSPX(Constants.rfMotorAddress);
    lbMotor = new VictorSPX(Constants.lbMotorAddress);
    rbMotor = new VictorSPX(Constants.rbMotorAddress);

    ultrasonic = new AnalogInput(Constants.frontUltrasonicPort);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void tankDrive(double left, double right) {
    left = MathFunctions.clipToOne(left);
    right = -MathFunctions.clipToOne(right);

    lfMotor.set(ControlMode.PercentOutput, left);
    lbMotor.set(ControlMode.PercentOutput, left);
    rfMotor.set(ControlMode.PercentOutput, right);
    rbMotor.set(ControlMode.PercentOutput, right);
  }

  public void povDrive(double forward, double turnRight) {
    tankDrive(forward + turnRight, forward - turnRight);
  }

  public double getFrontDistance(){
    return ultrasonic.getVoltage() / 9.77 * 1000.0;
  }
}
