// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Climb extends SubsystemBase {
  public static final int kClimbSolenoidModule = 0;
  //public static final int[] kClimbSolenoidPorts = new int[] {0, 1};

  private final DoubleSolenoid m_climbSolenoid = new DoubleSolenoid(12, PneumaticsModuleType.CTREPCM, 4, 5);



  /** Creates a new ExampleSubsystem. */
  public Climb() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }


  public void forward() {
    m_climbSolenoid.set(kForward);
  }
  public void off() {
    m_climbSolenoid.set(kOff);
  }

  public void reverse() {
    m_climbSolenoid.set(kReverse);
  }

}
