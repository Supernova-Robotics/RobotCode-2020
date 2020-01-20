/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake.IntakeDirection;
import frc.robot.subsystems.Storage.StorageDirection;

public class UpperBodyDrive extends CommandBase {
    private Command shootBall = new ShootBall();
    private Command autoAim = new AutoAim();
    
    @Override
    public void initialize() {
        RobotContainer.storage.set(StorageDirection.STOP);
        RobotContainer.turret.set(false);
        RobotContainer.storage.setKeepBall(true);
        RobotContainer.storage.setAutoMove(true);
    }

    @Override
    public void execute() {
        // shooter
        if (RobotContainer.joystick1.getAButtonPressed()) {
            if (!shootBall.isScheduled()) {
                shootBall.schedule();
            }
        } else if(RobotContainer.joystick1.getAButtonReleased()){
            if (shootBall.isScheduled()) {
                shootBall.cancel();
            }
        }

        // autoAim
        if (RobotContainer.joystick1.getYButtonPressed()) {
            if (!autoAim.isScheduled()) {
                autoAim.schedule();
            }
        } else if(RobotContainer.joystick1.getYButtonReleased()){
            if (autoAim.isScheduled()) {
                autoAim.cancel();
            }
        }

        // intake
        if (RobotContainer.joystick1.getBumper(Hand.kLeft)) {
            RobotContainer.intake.set(IntakeDirection.IN);
        } else if (RobotContainer.joystick1.getBumper(Hand.kRight)) {
            RobotContainer.intake.set(IntakeDirection.OUT);
        } else {
            RobotContainer.intake.set(IntakeDirection.STOP);
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
