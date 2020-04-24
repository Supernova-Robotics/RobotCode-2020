/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Storage.StorageDirection;

public class ShootBall extends CommandBase {
    private Timer timer = new Timer();

    @Override
    public void initialize() {
        RobotContainer.storage.setAutoMove(false);
        RobotContainer.storage.setKeepBall(false);
        RobotContainer.turret.set(true);
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        if (timer.get() > 1) {
            RobotContainer.storage.set(StorageDirection.UP);
        }
        RobotContainer.turret.set(true);
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.storage.set(StorageDirection.STOP);
        RobotContainer.turret.set(false);
        RobotContainer.storage.setKeepBall(true);
        RobotContainer.storage.setAutoMove(true);
        timer.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
