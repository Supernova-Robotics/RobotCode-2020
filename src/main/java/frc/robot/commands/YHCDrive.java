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
import frc.robot.Constants;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Intake.IntakeDirection;

/**
 * This is a special drive mode used by the previous driver YHC, and may be
 * found comfortable for some people.
 */

public class YHCDrive extends CommandBase {
    Chassis chassis;
    Turret turret;
    Intake intake;
    XboxController joystick0;
    XboxController joystick1;

    public YHCDrive(Chassis c, Turret t, Intake i, XboxController j0, XboxController j1) {
        chassis = c;
        turret = t;
        intake = i;
        joystick0 = j0;
        joystick1 = j1;
        addRequirements(chassis);
        addRequirements(turret);
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        chassis.tankDrive(0, 0);
    }

    @Override
    public void execute() {
        // chassis
        double forward = -joystick1.getY(Hand.kLeft) * Constants.kForward;
        double turn = -(joystick1.getTriggerAxis(Hand.kLeft) - joystick1.getTriggerAxis(Hand.kRight)) * Constants.kTurn;
        chassis.povDrive(forward, turn);

        // shooter
        if (joystick0.getAButton()) {
            turret.set(true);
        } else {
            turret.set(false);
        }

        // intake
        if (joystick0.getBumper(Hand.kLeft)) {
            intake.set(IntakeDirection.IN);
        } else if (joystick0.getBumper(Hand.kRight)) {
            intake.set(IntakeDirection.OUT);
        } else {
            intake.set(IntakeDirection.STOP);
        }

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
