// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.lang.ModuleLayer.Controller;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj.simulation.XboxControllerSim;
import frc.robot.commands.BackwardsIndexer;
import frc.robot.commands.DriveTank;
import frc.robot.commands.IntakeRun;
import frc.robot.commands.PutIntakeDown;
import frc.robot.commands.RunIndexer;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.DropIntake;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Drive m_drive = new Drive();
  private final Intake m_intake = new Intake();
  private final DropIntake m_dropIntake = new DropIntake();
  private final Indexer m_index = new Indexer(); 

  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem)

  private XboxController controller1 = new XboxController(0); 
  private XboxController controller2 = new XboxController(1);

 

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings

    m_drive.setDefaultCommand(new DriveTank(m_drive, controller1::getLeftY, controller2::getLeftY));

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //Creating new joystick buttons
    JoystickButton x2 = new JoystickButton(controller2, XboxController.Button.kX.value);
    JoystickButton y2 = new JoystickButton(controller2, XboxController.Button.kY.value);
    JoystickButton a2 = new JoystickButton(controller2, XboxController.Button.kA.value);
    JoystickButton b2 = new JoystickButton(controller2, XboxController.Button.kB.value);
    //Binding buttons to commands :D
    x2.whileHeld(new IntakeRun(m_intake));
    //y2.whenPressed(new PutIntakeDown(m_dropIntake));
    a2.whileHeld(new RunIndexer(m_index));
    b2.whileHeld(new BackwardsIndexer(m_index));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  //public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    //return m_autoCommand;
  //}
}
