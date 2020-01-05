/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import frc.robot.commands.Teleop;
import frc.robot.commands.TurnAround;
import frc.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private Joystick joystick1;
  private Joystick joystick2;

  private final Chassis m_chassis = new Chassis();

  private final TurnAround turnAround = new TurnAround(m_chassis);
  private final Teleop mTeleop = new Teleop(m_chassis, joystick1, joystick2);


  public RobotContainer() {
    joystick1 = new Joystick(0);
    joystick2 = new Joystick(1);
    // Configure the button bindings
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    JoystickButton x = new JoystickButton(joystick1, 0);
  }

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return turnAround;
  }

  public Command getTeleopCommand(){
    return mTeleop;
  }
}
