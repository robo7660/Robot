// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.GroupMotorControllers;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  private TalonSRX rightFront = new TalonSRX(4);
  private VictorSPX rightBack = new VictorSPX(5);

  private TalonSRX leftFront = new TalonSRX(2);
  private VictorSPX leftBack = new VictorSPX(3);

  

  public Drive() {}

  public void tankDrive(DoubleSupplier leftSpeed, DoubleSupplier rightSpeed){
    leftFront.set(ControlMode.PercentOutput, -leftSpeed.getAsDouble());
    rightFront.set(ControlMode.PercentOutput, rightSpeed.getAsDouble());

    leftBack.follow(leftFront);
    rightBack.follow(rightFront);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
