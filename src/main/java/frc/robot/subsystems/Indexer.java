// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public Indexer() {}


  private Talon index = new Talon(7); 

  public void runIndexer() {
    index.set(1); 
    
  }
  public void stopIndexer() {
    index.set(0);
  }
  public void backwardIndexer() {
    index.set(-1);
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
