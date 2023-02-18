// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxRelativeEncoder;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RealConstants;
import java.util.function.DoubleSupplier;

public class RealDrive extends Drive {

  public RealDrive() {
    leftGroup.setInverted(true);

    leftEnc.setPositionConversionFactor(RealConstants.kMetersPerRev);
    rightEnc.setPositionConversionFactor(RealConstants.kMetersPerRev);
  }

  private CANSparkMax leftFront = new CANSparkMax(1, MotorType.kBrushless);
  private CANSparkMax leftBack = new CANSparkMax(2, MotorType.kBrushless);
  private CANSparkMax rightFront = new CANSparkMax(3, MotorType.kBrushless);
  private CANSparkMax rightBack = new CANSparkMax(4, MotorType.kBrushless);

  private double leftZero = 0;
  private double rightZero = 0;

  private MotorControllerGroup leftGroup = new MotorControllerGroup(leftBack, leftFront);
  private MotorControllerGroup rightGroup = new MotorControllerGroup(rightFront, rightBack);

  private RelativeEncoder leftEnc =
      leftFront.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, RealConstants.kCPR);
  private RelativeEncoder rightEnc =
      rightFront.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, RealConstants.kCPR);

  private final AHRS m_gyro = new AHRS(SerialPort.Port.kMXP);

  private DifferentialDriveOdometry m_odometry =
      new DifferentialDriveOdometry(
          m_gyro.getRotation2d(), leftEnc.getPosition(), rightEnc.getPosition());

  private DifferentialDrive driveTrain = new DifferentialDrive(leftGroup, rightGroup);

  @Override
  public void setTankDrive(DoubleSupplier lSpeed, DoubleSupplier rSpeed, double pOutput) {

    driveTrain.tankDrive(lSpeed.getAsDouble() * pOutput, rSpeed.getAsDouble() * pOutput);
  }

  @Override
  public void setArcadeDrive(double speed, double rotation) {

    driveTrain.arcadeDrive(speed, rotation);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_odometry.update(m_gyro.getRotation2d(), leftEnc.getPosition(), rightEnc.getPosition());
    SmartDashboard.putNumber("leftPositionMeters", leftEnc.getPosition());
    SmartDashboard.putNumber("rightPositionMeters", rightEnc.getPosition());
    SmartDashboard.putNumber("degrees", m_gyro.getAngle());
    SmartDashboard.putNumber("yaw", m_gyro.getYaw());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  @Override
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  @Override
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_odometry.resetPosition(
        m_gyro.getRotation2d(), leftEnc.getPosition(), rightEnc.getPosition(), pose);
  }

  @Override
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    leftGroup.setVoltage(leftVolts);
    rightGroup.setVoltage(rightVolts);
    driveTrain.feed();
  }

  @Override
  public void resetEncoders() {
    leftEnc.setPosition(0);
    rightEnc.setPosition(0);
  }

  @Override
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(
        leftEnc.getVelocity() * RealConstants.speedConversionFactor,
        rightEnc.getVelocity() * RealConstants.speedConversionFactor);
  }

  @Override
  public void zeroHeading() {
    m_gyro.reset();
  }

  @Override
  public double getHeading() {
    return Math.IEEEremainder(m_gyro.getAngle(), 360) * (RealConstants.kGyroReversed ? -1.0 : 1.0);
  }

  @Override
  public double getTurnRate() {
    return m_gyro.getRate() * (RealConstants.kGyroReversed ? -1.0 : 1.0);
  }

  public double getRightRotations() {
    return rightEnc.getPosition();
  }

  public double getLeftRotations() {
    return leftEnc.getPosition();
  }

  public void setRightRotations(double target_rotations) {
    rightEnc.setPosition(target_rotations);
  }

  public void setLeftRotations(double target_rotations) {
    leftEnc.setPosition(target_rotations);
  }
}
