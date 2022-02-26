// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Climb;

/** A complex auto command that drives forward, releases a hatch, and then drives backward. */
public class ExtendArm extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   *
   * @param drive The drive subsystem this command will run on
   * @param hatch The hatch subsystem this command will run on
   */
  public ExtendArm(Climb climb_sys) {
    addCommands(new ReleaseRatchet(climb_sys), new StartWench(climb_sys));
  }
}