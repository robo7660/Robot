// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Launch.LaunchPosition;
import frc.robot.subsystems.Launcher;

public class SetLaunchAnglePosition extends Command {
  /** Creates a new SetLaunchAnglePosition. */
  private Launcher launcher;

  private LaunchPosition launchDistance;

  public SetLaunchAnglePosition(Launcher launcher, LaunchPosition launchDistance) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.launcher = launcher;
    this.launchDistance = launchDistance;
    addRequirements(launcher);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // FIXMEEEE
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
