// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.DoubleSubscriber;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drive;

/** A command that will move the robot forward */
public class DriveBalanceDistance extends CommandBase {

  private Drive m_drive;
  private PIDController m_PidControl;
  private double m_distance;
  private double setPoint;
  private Timer m_timer;

  /**
   * Moves the robot a specified distance forward.
   *
   * @param distance Distance in inches to drive.
   * @param drive The drive subsystem to use
   */
  public DriveBalanceDistance(double distance, Drive drive) {
    m_drive = drive;
    m_distance = distance;

    m_timer = new Timer();

    SmartDashboard.putNumber("Drive P", Constants.DriveConstants.kDriveBalanceP);
    SmartDashboard.putNumber("Drive I", Constants.DriveConstants.kDriveBalanceI);
    SmartDashboard.putNumber("Drive D", Constants.DriveConstants.kDriveP);

    addRequirements(drive);
  }

  @Override
  public void initialize() {

    double p = SmartDashboard.getNumber("Drive P", Constants.DriveConstants.kDriveBalanceP);
    double i = SmartDashboard.getNumber("Drive I", Constants.DriveConstants.kDriveBalanceI);
    double d = SmartDashboard.getNumber("Drive D", Constants.DriveConstants.kDriveD);

    m_PidControl =
        new PIDController(
            p,
            i,
            d);
    m_PidControl.setTolerance(
        Constants.DriveConstants.kDriveDistanceToleranceMeters,
        Constants.DriveConstants.kDriveDistanceRateToleranceMetersPerS);

    double target = m_distance + m_drive.getAverageEncoderDistance();
    System.out.println("At " + m_drive.getAverageEncoderDistance() + " going to " + target);

    SmartDashboard.putNumber("Actual P", m_PidControl.getP());

    setPoint = m_drive.getAverageEncoderDistance() + m_distance;

    m_drive.setBrakeMode();

    m_timer.reset();
    m_timer.start();

    // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
    // setpoint before it is considered as having reached the reference
  }

  @Override
  public void execute() {
    double pidSpeed = m_PidControl.calculate(m_drive.getAverageEncoderDistance(), setPoint);
    double runSpeed = MathUtil.clamp(pidSpeed, -0.6, 0.6);
    m_drive.setArcadeDrive(runSpeed, 0);
    SmartDashboard.putNumber("distance", m_drive.getAverageEncoderDistance());
    SmartDashboard.putNumber(
        "Wheel Speed via PID",
        m_PidControl.calculate(m_drive.getAverageEncoderDistance(), setPoint));
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.setArcadeDrive(0, 0);
    m_drive.setCoastMode();
    System.out.println(
        "At " + m_drive.getAverageEncoderDistance() + " target " + setPoint + " Done!");
  }

  @Override
  public boolean isFinished() {
    // End when the controller is at the reference.
    return (m_PidControl.atSetpoint() || m_timer.hasElapsed(5));
  }
}
