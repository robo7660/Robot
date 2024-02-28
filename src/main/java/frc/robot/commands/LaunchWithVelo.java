// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Launcher;

public class LaunchWithVelo extends Command {
  /** Creates a new LaunchWithVelo. */
  private Launcher launcher;

  private Index index;
  private boolean fromDash;

  private double launchVelo;

  public LaunchWithVelo(Launcher launcher, Index index, double velocity, boolean fromDash) {
    this.launcher = launcher;
    this.index = index;
    this.fromDash = fromDash;
    launchVelo = velocity;
    addRequirements(launcher);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (fromDash) {
      launcher.setLaunchVelocity(SmartDashboard.getNumber("Launch Velo", 0));
      launchVelo = SmartDashboard.getNumber("Launch Velo", 0);
    } else {
      launcher.setLaunchVelocity(launchVelo);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (launcher.readyToLaunch(launchVelo)) {
      index.feed();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    launcher.setLauncherSpeed(0);
    index.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // Only called on button hold and done when button released
    return false;
  }
}
