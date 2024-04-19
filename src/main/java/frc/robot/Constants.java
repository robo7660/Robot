// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.util.PIDConstants;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import swervelib.math.Matter;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static final boolean kLeftFrontDriveInverted = false;
  public static final boolean kLeftBackDriveInverted = false;
  public static final boolean kRightBackDriveInverted = false;
  public static final boolean kRightFrontDriveInverted = false;
  public static final int driveStatorCurrentLimit = 100;
  public static final int driveSupplyCurrentLimit = 100;

  public static final double LOOP_TIME = 0.13;
  public static final double ROBOT_MASS = 115 * 0.453592;
  public static final Matter CHASSIS =
      new Matter(new Translation3d(0, 0, Units.inchesToMeters(0)), ROBOT_MASS);

  public static final PIDConstants autoTranslationPID = new PIDConstants(9, 0, 0);
  public static final PIDConstants autoRotationPID = new PIDConstants(3, 0, 0);

  public static class Index {
    public static final int whooperCANID = 41;
    public static final int upperCANID = 42;
    public static final boolean upperInverted = true;
    public static final boolean whooperInverted = false;
    public static final int upperBeam = 1;
    public static final int lowerBeam = 0;
    public static final double whooperSpeed = 0.9;
    public static final double upperSpeed = 0.3;
  }

  public static class Intake {
    public static final int leftCANID = 30;
    public static final int centerCANID = 31;
    public static final int rightCANID = 32;
    public static final boolean leftInverted = false;
    public static final boolean centerInverted = true;
    public static final boolean rightInverted = true;
    public static final double speed = 0.45;
  }

  public static class Launch {
    public static final double launcherP = 0.0005;
    public static final double launcherI = 0;
    public static final double launcherD = 0;
    public static final double launcherFF = 0.000156;
    public static final double angleP = 0.0045;
    public static final double angleI = 0;
    public static final double angleD = 0;
    public static final double angleFF = 0;
    public static final int lowerLauncherID = 44;
    public static final int upperLauncherID = 45;
    public static final int angleID = 46;
    public static final double allowedVeloPercent = 10;
    public static final double allowedDifferencePercent = 5;
    public static final double closeLaunchPosition = 820;
    public static final double farLaunchPosition = 752;
    public static final double angleMin = 750;
    public static final double angleMax = 828;
    public static final boolean angleMotorInverted = true;
    public static final boolean angleEncoderInverted = false;
    public static final boolean lowerMotorInverted = true;
    public static final boolean upperMotorInverted = false;
    public static final double speedFarSpeaker = 2800;
    public static final double angleConversionFactor = 1000;
    public static final double launcherConversionFactor = 1;
    public static final double safeLaunchVelo = 5400;
    public static final double subwooferLaunchVelo = 3500;
    public static final double ampLaunchVelo = 2800;

    public enum LaunchPosition {
      CLOSE,
      FAR
    }

    public enum LaunchPreset {
      SUBWOOFER,
      SAFE,
      AMP,
      OFF
    }
  }

  public static class Climb {
    public static final int leftCANID = 50;
    public static final int rightCANID = 51;
    public static final float winchTopLimit = 280;
    // These are break beam sensor IDS
    public static final int winchLimitLeft = 2;
    public static final int winchLimitRight = 3;
    public static final double motorSpeedFactor = -0.9;
    public static final double deadzone = 0.1;
  }

  public static class Transfer {
    public static final boolean motorInverted = true;
    public static final double motorSpeed = 0.3;
    public static final int motorID = 40;
  }

  public static final String limelightName = "limelight";

  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final double LEFT_X_DEADBAND = 0.01;
    public static final double LEFT_Y_DEADBAND = 0.01;
    public static final double RIGHT_X_DEADBAND = 0.1;
    public static final int TURN_CONSTANT = 6;
  }
}
