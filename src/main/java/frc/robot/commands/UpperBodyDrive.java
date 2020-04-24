/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.fasterxml.jackson.databind.util.RootNameLookup;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ControlPanelTurner.PanelColor;
import frc.robot.subsystems.Intake.IntakeDirection;
import frc.robot.subsystems.Storage.StorageDirection;

public class UpperBodyDrive extends CommandBase {
    private Command shootBall = new ShootBall();
    private Command autoAim = new AutoAim();
    private Command turnPanelToColor = new TurnPanelToColor(PanelColor.RED);
    
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

        // colorTurner
        if(RobotContainer.joystick1.getBButtonPressed()){
            turnPanelToColor.schedule();
        }
        if(RobotContainer.joystick1.getBButtonReleased()){
            turnPanelToColor.cancel();
        }

        // storage
        if(RobotContainer.joystick1.getXButtonPressed()){
            RobotContainer.storage.setAutoMove(false);
            RobotContainer.storage.set(StorageDirection.DOWN);
        } else if (RobotContainer.joystick1.getXButtonReleased()){
            RobotContainer.storage.setAutoMove(true);
            RobotContainer.storage.set(StorageDirection.STOP);
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
