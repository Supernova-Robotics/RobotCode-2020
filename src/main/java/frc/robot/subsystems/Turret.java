/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turret extends SubsystemBase {
    private TalonSRX shooterMotor0;
    private TalonSRX shooterMotor1;
    private double shooterPower;

    public Turret() {
        shooterMotor0 = new TalonSRX(Constants.shooterMotorAddress0);
        shooterMotor1 = new TalonSRX(Constants.shooterMotorAddress1);
        shooterPower = Constants.defaultShooterPower;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Shooter Power", shooterPower);
    }

    public void set(boolean shooting) {
        if (shooting) {
            shooterMotor0.set(ControlMode.PercentOutput, shooterPower);
            shooterMotor1.set(ControlMode.PercentOutput, shooterPower);
        } else {
            shooterMotor0.set(ControlMode.PercentOutput, 0);
            shooterMotor1.set(ControlMode.PercentOutput, 0);
        }
    }

    public double getShootPower() {
        return shooterPower;
    }

    public void setShooterPower(double power) {
        shooterPower = power;
    }

    public void changeShooterPower(double delta) {
        shooterPower += delta;
    }

}
