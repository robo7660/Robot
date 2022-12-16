// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DropIntake;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class PutIntakeDown extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DropIntake m_subsystem;
  private Timer m_timer = new Timer();

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public PutIntakeDown(DropIntake subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //resetting and starting the timer
    m_timer.reset();
    m_timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    //Ran the intake drop motor
    m_subsystem.runDropper();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    //stopped the intake and told the isDown boolean its down
    m_subsystem.stopDropper();
    m_subsystem.intakeDown();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    //Test if the intake is down or command has run for 1 second
    if(m_timer.get() >= 1000 || m_subsystem.intakeDown()){
      return true;
    }

    else{
      return false;
    }
  }
}
