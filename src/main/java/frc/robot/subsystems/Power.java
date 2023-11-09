// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Power extends SubsystemBase {
  private final PowerDistribution m_pdp = new PowerDistribution();
  private int counter = 0;
  private int numChannels = 0;
  private final int updateFreq = 5;

  /** Creates a new Power. */
  public Power() {
    numChannels = m_pdp.getNumChannels();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (counter % updateFreq == 0) {
      update();
    }
    counter++;
  }

  public void update() {
    for (int i = 0; i < numChannels; i++) {
      SmartDashboard.putNumber("PDP" + i, m_pdp.getCurrent(i));
    }
  }
}
