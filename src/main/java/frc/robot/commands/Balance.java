// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class Balance extends CommandBase {
  /** Creates a new Balance. */
  private Drive m_drive;

  private PIDController m_lPID;
  private PIDController m_rPID;

  public Balance(Drive drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive = drive;
    addRequirements(drive);

    SmartDashboard.putNumber("Balance P", 0);
    SmartDashboard.putNumber("Balance I", 0);
    SmartDashboard.putNumber("Balance D", 0);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    double kP = SmartDashboard.getNumber("Balance P", 0);
    double kI = SmartDashboard.getNumber("Balance I", 0);
    double kD = SmartDashboard.getNumber("Balance D", 0);

    m_lPID = new PIDController(kP, kI, kD);
    m_lPID.setTolerance(0, 0);

    m_rPID = new PIDController(kP, kI, kD);
    m_rPID.setTolerance(0, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double lSpeed = m_lPID.calculate(m_drive.getPitch(), 0);
    double rSpeed = m_rPID.calculate(m_drive.getPitch(), 0);
    m_drive.tankDriveVolts(lSpeed, rSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_lPID.atSetpoint() && m_rPID.atSetpoint();
  }
}
