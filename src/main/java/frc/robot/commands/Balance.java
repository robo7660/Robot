// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.DifferentialDriveFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RealConstants;
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

    SmartDashboard.putNumber("Balance P", RealConstants.kBalanceP);
    SmartDashboard.putNumber("Balance I", 0);
    SmartDashboard.putNumber("Balance D", 0);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    double kP = SmartDashboard.getNumber("Balance P", RealConstants.kBalanceP);
    double kI = SmartDashboard.getNumber("Balance I", 0);
    double kD = SmartDashboard.getNumber("Balance D", 0);

    m_lPID = new PIDController(kP, kI, kD);
    m_lPID.setTolerance(12, 10);

    m_rPID = new PIDController(kP, kI, kD);
    m_rPID.setTolerance(2, 2);

    m_drive.setBrakeMode();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = m_lPID.calculate(m_drive.getPitch(), -5);
    m_drive.tankDriveVolts(speed, speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.setCoastMode();
    m_drive.setArcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_lPID.atSetpoint() && m_rPID.atSetpoint();
  }
}
