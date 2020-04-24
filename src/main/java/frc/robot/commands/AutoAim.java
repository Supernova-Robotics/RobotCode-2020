/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.LinearFilter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.math.MathFunctions;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class AutoAim extends CommandBase {
    private boolean ending = false;
    private Timer timer = new Timer();
    private PIDController pid = new PIDController(0.02, 0.002, 0.002);
    private LinearFilter inputFilter = LinearFilter.movingAverage(5);

    @Override
    public void initialize() {
        RobotContainer.limeLight.turnLight(true);

        if (RobotContainer.chassis.usingTeleop) {
            RobotContainer.chassis.usingTeleop = false;
        } else {
            ending = true;
        }
        timer.reset();
        timer.start();
        pid.reset();
        pid.setIntegratorRange(-0.3, 0.3);
        inputFilter.reset();
    }

    @Override
    public void execute() {
        if (!RobotContainer.limeLight.anyValid()) {
            pid.reset();
            return;
        } else {
            double ax = inputFilter.calculate(RobotContainer.limeLight.getX());
            // double d = Constants.autoAimForwardTarget - RobotContainer.chassis.getFrontDistance();
            if (Math.abs(ax) > Constants.autoAimTurnRange) {
                timer.reset();
                timer.start();
            }
            double powerTurn = -pid.calculate(ax, Constants.autoAimTurnTarget);
            // double powerForward = d * Constants.autoAimKForward;
            RobotContainer.chassis.povDrive(0, MathFunctions.clip(powerTurn, Constants.autoAimMotorLimit, -Constants.autoAimMotorLimit));
            if (timer.get() > Constants.autoAimStableTime) {
                ending = true;
            }
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        RobotContainer.chassis.usingTeleop = true;
        ending = false;
        RobotContainer.limeLight.turnLight(false);

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return ending;
    }
}
