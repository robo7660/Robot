// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drive extends SubsystemBase {

  private static CANSparkMax leftFrontDriveMotor;
  private static CANSparkMax leftFrontTurnMotor;
  private static CANSparkMax rightFrontDriveMotor;
  private static CANSparkMax rightFrontTurnMotor;
  private static CANSparkMax leftBackDriveMotor;
  private static CANSparkMax leftBackTurnMotor;
  private static CANSparkMax rightBackDriveMotor;
  private static CANSparkMax rightBackTurnMotor;

  private static SwerveDriveWheel leftFrontWheel;
  private static SwerveDriveWheel rightFrontWheel;
  private static SwerveDriveWheel leftBackWheel;
  private static SwerveDriveWheel rightBackWheel;

  private static SwerveDriveCoordinator swerveCoordinator;

  private static CANcoder leftFrontTurnEncoder;
  private static CANcoder leftBackTurnEncoder;
  private static CANcoder rightFrontTurnEncoder;
  private static CANcoder rightBackTurnEncoder;

  private static AHRS gyro;

  public Drive() {
    leftFrontDriveMotor = new CANSparkMax(4, MotorType.kBrushless);
    leftBackDriveMotor = new CANSparkMax(10, MotorType.kBrushless);
    rightFrontDriveMotor = new CANSparkMax(6, MotorType.kBrushless);
    rightBackDriveMotor = new CANSparkMax(8, MotorType.kBrushless);

    leftFrontTurnMotor = new CANSparkMax(3, MotorType.kBrushless);
    leftBackTurnMotor = new CANSparkMax(9, MotorType.kBrushless);
    rightFrontTurnMotor = new CANSparkMax(5, MotorType.kBrushless);
    rightBackTurnMotor = new CANSparkMax(7, MotorType.kBrushless);

    double wheelP = 0.01;
    double wheelI = 0.001;
    double wheelD = 0.0;

    leftFrontTurnEncoder = new CANcoder(11);
    leftBackTurnEncoder = new CANcoder(14);
    rightFrontTurnEncoder = new CANcoder(12);
    rightBackTurnEncoder = new CANcoder(13);

    gyro = new AHRS(SerialPort.Port.kMXP);

    leftFrontWheel =
      new SwerveDriveWheel(
        wheelP,
        wheelI,
        wheelD,
        leftFrontTurnEncoder,
        leftFrontTurnMotor,
        leftFrontDriveMotor,
        Constants.kLeftFrontDriveInverted);
    leftBackWheel =
      new SwerveDriveWheel(
        wheelP,
        wheelI,
        wheelD,
        leftBackTurnEncoder,
        leftBackTurnMotor,
        leftBackDriveMotor,
        Constants.kLeftBackDriveInverted);
    rightFrontWheel =
      new SwerveDriveWheel(
        wheelP,
        wheelI,
        wheelD,
        rightFrontTurnEncoder,
        rightFrontTurnMotor,
        rightFrontDriveMotor,
        Constants.kRightFrontDriveInverted);
    rightBackWheel =
      new SwerveDriveWheel(
        wheelP,
        wheelI,
        wheelD,
        rightBackTurnEncoder,
        rightBackTurnMotor,
        rightBackDriveMotor,
        Constants.kRightBackDriveInverted);
swerveCoordinator =
    new SwerveDriveCoordinator(leftFrontWheel, leftBackWheel, rightFrontWheel, rightBackWheel);

  }

  public double getGyroPosition() {
    return gyro.getAngle();
  }

  public void setCoordinator(double dir, double speed, double twist) {
    if (twist < 0.2 && speed > -0.2){
      twist = 0;
    }
    if (speed > -0.2 && speed < 0.2){
      speed = 0;
    }
    swerveCoordinator.setSwerveDrive(dir, twist, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double wheelP = SmartDashboard.getNumber("Turn P", 0);
    double wheelI = SmartDashboard.getNumber("Turn I", 0);
    double wheelD = SmartDashboard.getNumber("Turn D", 0);

    leftFrontWheel.setP(wheelP);
    leftFrontWheel.setI(wheelI);
    leftFrontWheel.setD(wheelD);

    leftBackWheel.setP(wheelP);
    leftBackWheel.setI(wheelI);
    leftBackWheel.setD(wheelD);

    rightFrontWheel.setP(wheelP);
    rightFrontWheel.setI(wheelI);
    rightFrontWheel.setD(wheelD);

    rightBackWheel.setP(wheelP);
    rightBackWheel.setI(wheelI);
    rightBackWheel.setD(wheelD);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
