/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // chassis //
    public static final int lfMotorAddress = 10;
    public static final int rfMotorAddress = 12;
    public static final int lbMotorAddress = -1;
    public static final int rbMotorAddress = -1;
    public static final boolean twoMotorChassis = true;

    public static final int frontUltrasonicPort = 0;

    public static final double kForward = 0.4;
    public static final double kTurn = 0.4;

    // intake //
    public static final int intakeMotorPort = 1;
    public static final double intakeSpeed = 0.5;

    // storage //
    public static final int beltMotorAddress = 18;
    public static final double beltMotorSpeed = 0.4;

    public static final int enterenceSensorPort = 1;
    public static final int exitSensorPort = 2;
    
    public static final boolean enableAutoMove = true;

    // turret //
    public static final int shooterMotorAddress0 = 19;
    public static final int shooterMotorAddress1 = 20;
    public static final double defaultShooterPower = 0.9;

    // Control Panel Turner // Currently not used
    public static final int panelTurnerPort = 2;
    public static final double panelTurnerSpeed = 0.5;
    public static final double colorConfidenceThreshold = 0.5;

    // Power manager // Currently not used
    public static final double lowVotageLimit = 8;
    public static final double powerLimitthreshold = 10;

    // driver preferences // Currently not used
    public static final double tankDriveDeadZone = 0.05;
}
