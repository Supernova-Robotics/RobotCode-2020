/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ControlPanelTurner;

public class TurnPanelToColor extends CommandBase {
    private boolean ending;
    private ControlPanelTurner.PanelColor targetColor;

    public TurnPanelToColor(ControlPanelTurner.PanelColor color){
        targetColor = color;
    }

    @Override
    public void initialize() {
        ending = false;
    }

    @Override
    public void execute() {
        RobotContainer.controlPanelTurner.set(true);
        if(RobotContainer.controlPanelTurner.getColor() == targetColor){
            RobotContainer.controlPanelTurner.set(false);
            ending = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.controlPanelTurner.set(false);
    }

    @Override
    public boolean isFinished() {
        return ending;
    }
}
