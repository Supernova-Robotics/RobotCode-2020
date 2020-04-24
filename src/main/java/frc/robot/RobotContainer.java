/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.SimplePOVDrive;
import frc.robot.commands.UpperBodyDrive;
import frc.robot.commands.YHCDrive;
import frc.robot.commands.AdvancedTankDrive;
import frc.robot.commands.BlockyTankDrive;
import frc.robot.commands.ShootBall;
import frc.robot.subsystems.CameraStream;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.ControlPanelTurner;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.PowerManager;
import frc.robot.subsystems.Storage;
import frc.robot.subsystems.Turret;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
    // Joysticks
    public static final XboxController joystick0 = new XboxController(0);
    public static final XboxController joystick1 = new XboxController(1);

    // Subsystems
    public static final CameraStream cam = new CameraStream();
    public static final Chassis chassis = new Chassis();
    public static final Intake intake = new Intake();
    public static final Storage storage = new Storage();
    public static final Turret turret = new Turret();
    public static final LimeLight limeLight = new LimeLight();
    public static final ControlPanelTurner controlPanelTurner = new ControlPanelTurner();

    // Commands
    private Command advancedTankDrive;
    private Command blockyTankDrive;
    private Command simplePOVDrive;
    private Command yhcDrive;

    // Teleop and autonomous command group
    public static SendableChooser<Command> teleopChooser;

    public RobotContainer() {
        // Commands
        advancedTankDrive = new AdvancedTankDrive().alongWith((new UpperBodyDrive()));
        blockyTankDrive = new BlockyTankDrive().alongWith((new UpperBodyDrive()));
        simplePOVDrive = new SimplePOVDrive().alongWith((new UpperBodyDrive()));
        yhcDrive = new YHCDrive().alongWith((new UpperBodyDrive()));

        // teleop chooser
        teleopChooser = new SendableChooser<Command>();
        teleopChooser.addOption("Advanced tank drive", advancedTankDrive);
        teleopChooser.addOption("Blocky tank drive", blockyTankDrive);
        teleopChooser.addOption("Simple POV drive", simplePOVDrive);
        teleopChooser.addOption("YHC drive", yhcDrive);
        teleopChooser.setDefaultOption("YHC drive", yhcDrive);
        SmartDashboard.putData(teleopChooser);
    }

    public static Command getTeleopCommand() {
        return teleopChooser.getSelected();
    }

    public static Command getAutonomousCommand(){
        return null;
    }
}
