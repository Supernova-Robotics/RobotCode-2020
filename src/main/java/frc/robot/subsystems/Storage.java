/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.math.CustomButton;
import frc.robot.Constants;

public class Storage extends SubsystemBase {
    public boolean lock = false;

    private Talon beltMotor;
    private DigitalInput enterenceSensor;
    private DigitalInput exitSensor;
    private DoubleSolenoid keepball;

    private boolean autoMove;

    private CustomButton enterenceButton;
    private CustomButton exitButton;
    private Timer timer;

    public enum StorageDirection {
        UP, DOWN, STOP
    }

    public Storage() {

        beltMotor = new Talon(Constants.beltMotorPort);
        enterenceSensor = new DigitalInput(Constants.enterenceSensorPort);
        exitSensor = new DigitalInput(Constants.exitSensorPort);
        keepball = new DoubleSolenoid(0, 1);

        autoMove = Constants.enableAutoMove;

        enterenceButton = new CustomButton();
        exitButton = new CustomButton();
        timer = new Timer();
        timer.start();
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("auto move storage", autoMove);
        SmartDashboard.putBoolean("Sensor", !enterenceSensor.get());
        if (autoMove) {
            if (!getKeepBall()) {
                setKeepBall(true);
            }

            enterenceButton.update(!enterenceSensor.get());
            exitButton.update(!exitSensor.get());

            if (enterenceButton.isPressed()) {
                set(StorageDirection.UP);
            } else if (!enterenceButton.get() && timer.get() > 0.15) {
                set(StorageDirection.STOP);
            }
            if (enterenceButton.get()){
                timer.reset();
                timer.start();
            }

        }
    }

    public void setAutoMove(boolean status) {
        enterenceButton.reset();
        SmartDashboard.delete("Balls in mouth");
        autoMove = status;
    }

    public void setSpeed(double speed) {
        if (!autoMove) {
            beltMotor.set(-speed);
        }
    }

    public void set(StorageDirection dir) {

        switch (dir) {
        case DOWN:
            beltMotor.set(-Constants.beltMotorSpeed);
            break;
        case UP:
            beltMotor.set(Constants.beltMotorSpeed);
            break;
        case STOP:
            beltMotor.set(0);
            break;
        }

    }

    public void setKeepBall(boolean extending) {
        if (extending) {
            keepball.set(Value.kReverse);
        } else {
            keepball.set(Value.kForward);
        }
    }

    public boolean getKeepBall() {
        Value v = keepball.get();
        if (v == Value.kForward) {
            return true;
        } else {
            return false;
        }
    }
}
