/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.math.CustomButton;
import frc.robot.Constants;

public class Storage extends SubsystemBase {
  private Spark beltMotor;
  private DigitalInput enterenceSensor;
  private DigitalInput exitSensor;

  private boolean autoMove;
  private int count;

  private CustomButton enterenceButton;
  private CustomButton exitButton;

  public Storage() {
    beltMotor = new Spark(Constants.beltMotorPort);
    enterenceSensor = new DigitalInput(Constants.enterenceSensorPort);
    exitSensor = new DigitalInput(Constants.exitSensorPort);

    count = Constants.initialCount;
    autoMove = Constants.enableAutoMove;

    enterenceButton = new CustomButton();
    exitButton = new CustomButton();
  }

  @Override
  public void periodic() {
    if (autoMove) {
      enterenceButton.update(enterenceSensor.get());
      exitButton.update(exitSensor.get());

      if (enterenceButton.isPressed()) {
        count += 1;
        beltMotor.set(Constants.beltMotorSpeed);
      }
      if (exitButton.isPressed()) {
        count -= 1;
      }

      if (enterenceButton.isReleased()) {
        beltMotor.set(0);
      }
    }
  }

  public void setAutoMove(boolean status) {
    enterenceButton.reset();
    autoMove = status;
  }

  public void move(double speed) {
    if (!autoMove) {
      beltMotor.set(speed);
    }
  }

  public int getCount() {
    return count;
  }

  public void setCount(int c) {
    count = c;
  }
}
