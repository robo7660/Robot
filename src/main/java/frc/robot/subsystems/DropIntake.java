// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DropIntake extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public DropIntake() {}

  //defining variables
  private Talon intakeDropper = new Talon(6);
  private boolean isDown = false;

  //Creating a function that Runs the intake dropper
  public void runDropper(){
    intakeDropper.set(1);
  }

  //creating a function that stops the dropper
  public void stopDropper(){
    intakeDropper.set(0);
  }

  //tells current intake state
  public boolean intakeDown(){
    return isDown;
  }

  //set intake position to down
  public void dropIntake(){
    isDown= true;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
