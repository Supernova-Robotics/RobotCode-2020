/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    public boolean lock = false;

    public enum IntakeDirection {
        IN, OUT, STOP
    }

    private Talon motor;

    public Intake() {
        motor = new Talon(Constants.intakeMotorPort);
    }

    @Override
    public void periodic() {
    }

    public void set(IntakeDirection direction) {
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
