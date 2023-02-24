// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.Drive;

/** A command that will move the robot forward */
public class TurnDegrees extends CommandBase {

  private Drive m_drive;
  private PIDController m_PidControl;
  private double m_angle;
  private double targetAngle;

  /**
   * Turns the robot a given number of degrees.
   *
   * @param distance Distance in inches to drive.
   * @param drive The drive subsystem to use
   */
  public TurnDegrees(double angle, Drive drive) {
    m_angle = angle;

    m_PidControl =
        new PIDController(DriveConstants.kTurnP, DriveConstants.kTurnI, DriveConstants.kTurnD);
    m_PidControl.setTolerance(
        Constants.DriveConstants.kTurnAngleToleranceDegrees,
        Constants.DriveConstants.kTurnAngleToleranceDegreesPerS);

    addRequirements(drive);
  }

  @Override
  public void initialize() {

    targetAngle = m_angle + m_drive.getHeading();
    System.out.println("At " + m_drive.getHeading() + " going to " + targetAngle);


    // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
    // setpoint before it is considered as having reached the reference
  }

  @Override
  public void execute() {
    m_drive.setArcadeDrive(
        m_PidControl.calculate(m_drive.getHeading(), targetAngle), 0);
    SmartDashboard.putNumber(
        "Turn Speed via PID",
        m_PidControl.calculate(m_drive.getHeading(), targetAngle));
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println(
        "At " + m_drive.getHeading() + " targetAngle " + targetAngle + " Done!");
  }

  @Override
  public boolean isFinished() {
    // End when the controller is at the reference.
    return m_PidControl.atSetpoint();
  }
}
