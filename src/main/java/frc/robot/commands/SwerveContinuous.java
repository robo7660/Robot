// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class SwerveContinuous extends CommandBase {
  /** Creates a new SwerveContinuous. */
  DoubleSupplier dir;
  DoubleSupplier speed;
  DoubleSupplier twist;
  Drive drive;

  public SwerveContinuous(DoubleSupplier direction, DoubleSupplier speed, DoubleSupplier turn, Drive drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    dir = direction;
    this.speed = speed;
    twist = turn;
    this.drive = drive;

    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drive.setCoordinator(dir.getAsDouble() - drive.getGyroPosition(), speed.getAsDouble(), twist.getAsDouble());
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
