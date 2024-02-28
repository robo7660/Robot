// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.SwerveSubsystem;

public class AlignLaunchAuto extends Command {
  private final SwerveSubsystem swerve;
  private final Launcher launcher;
  private final double velo;
  private final double launchTime;
  private final Timer timer;


  /** Creates a new AlignLaunchAuto. */
  public AlignLaunchAuto(SwerveSubsystem swerve, Launcher launcher, double velo, double launchTime) {
    this.swerve = swerve;
    this.launcher = launcher;
    this.velo = velo;
    this.launchTime = launchTime;
    timer = new Timer();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(swerve, launcher);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (swerve.align()){
      launcher.setLaunchVelocity(velo);
      timer.start();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    launcher.setLaunchVelocity(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.hasElapsed(launchTime);
  }
}
