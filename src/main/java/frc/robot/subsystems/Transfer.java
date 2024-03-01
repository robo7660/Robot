// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Transfer extends SubsystemBase {
  /** Creates a new Transfer. */
  CANSparkMax motor = new CANSparkMax(Constants.Index.lowerCANID, MotorType.kBrushless);
  private double currentSpeed;

  public Transfer() {
    motor.setInverted(Constants.Index.lowerInverted);
    currentSpeed = 0;
  }

  public void start() {
    motor.set(Constants.Index.lowerSpeed);
    currentSpeed = Constants.Index.lowerSpeed;
  }

  public void stop() {
    motor.set(0);
    currentSpeed = 0;
  }

  public void toggle() {
    if (currentSpeed == 0.0) {
      start();
    } else {
      stop();
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
