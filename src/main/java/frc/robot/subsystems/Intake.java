/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    public enum IntakeDirection {
        IN, OUT, STOP
    }

    private VictorSPX motor;

    public Intake() {
        motor = new VictorSPX(Constants.intakeMotorAddress);
    }

    @Override
    public void periodic() {
    }

    public void set(IntakeDirection direction) {
        switch (direction) {
        case IN:
            motor.set(ControlMode.PercentOutput, Constants.intakeSpeed);
            break;
        case OUT:
            motor.set(ControlMode.PercentOutput, -Constants.intakeSpeed);
            break;
        case STOP:
            motor.set(ControlMode.PercentOutput, 0);
            break;
        }

    }
}
