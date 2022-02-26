// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Climb extends SubsystemBase {
  
  //public static final int kClimbSolenoidModule = 0;
  private final DoubleSolenoid m_climbSolenoid = new DoubleSolenoid(12, PneumaticsModuleType.CTREPCM, 4, 5);
  private CANSparkMax wench = new CANSparkMax(8, MotorType.kBrushless); //motor

  private double extendArmSpeed = 0.2; //needs to be COUNTERCLOCKWISE
  private double retractArmSpeed = -0.2; //needs to be CLOCKWISE (check +/-)

  private boolean m_ratchetEngaged = true; //true is engaged, false is not engaged
  /** Creates a new ExampleSubsystem. */
  public Climb() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    System.out.println("solenoid state: " + m_climbSolenoid.get());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void printSolenoidState(){
    System.out.println("solenoid state: " + m_climbSolenoid.get());
  }

  public boolean isRatchetEngaged(){ return m_ratchetEngaged; }
  public void setRatchetState(boolean state){ m_ratchetEngaged = state; }
  
  public void forward() {
    m_climbSolenoid.set(kForward); //solenoid goes out
  }
  public void off() {
    m_climbSolenoid.set(kOff); //solenoid off
  }

  public void reverse() {
    m_climbSolenoid.set(kReverse); //solenoid goes in
  }

  public void wenchOn() {
    wench.set(extendArmSpeed);
  }

  public void wenchOff() {
    wench.set(0.0);
  }
}
