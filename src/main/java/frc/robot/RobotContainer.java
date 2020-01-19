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
    public static XboxController joystick0;
    public static XboxController joystick1;

    public static CameraStream cam;
    public static Chassis chassis;
    public static Intake intake;
    public static Storage storage;
    public static Turret turret;
    public static LimeLight limeLight;
    
    public static AdvancedTankDrive advancedTankDrive;
    public static BlockyTankDrive blockyTankDrive;
    public static SimplePOVDrive simplePOVDrive;
    public static YHCDrive yhcDrive;
    public static UpperBodyDrive upperBodyDrive;
    public static ShootBall shootBall;

    public static SendableChooser<Command> teleopChooser;

    public RobotContainer() {
        // Joysticks
        joystick0 = new XboxController(0);
        joystick1 = new XboxController(1);

        // subsystems
        cam = new CameraStream();
        intake = new Intake();
        chassis = new Chassis();
        storage = new Storage();
        turret = new Turret();
        limeLight = new LimeLight();

        // Commands
        advancedTankDrive = new AdvancedTankDrive();
        blockyTankDrive = new BlockyTankDrive();
        simplePOVDrive = new SimplePOVDrive();
        yhcDrive = new YHCDrive();
        upperBodyDrive = new UpperBodyDrive();
        shootBall = new ShootBall();

        // teleop chooser
        teleopChooser = new SendableChooser<Command>();
        teleopChooser.addOption("Advanced tank drive", advancedTankDrive);
        teleopChooser.addOption("Blocky tank drive", blockyTankDrive);
        teleopChooser.addOption("Simple POV drive", simplePOVDrive);
        teleopChooser.addOption("YHC drive", yhcDrive);
        teleopChooser.addOption("Upper drive", upperBodyDrive);
        teleopChooser.setDefaultOption("YHC drive", yhcDrive);
        SmartDashboard.putData(teleopChooser);
    }
}
