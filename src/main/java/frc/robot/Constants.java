package frc.robot;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import frc.lib.util.COTSFalconSwerveConstants;
import frc.lib.util.SwerveModuleConstants;

public final class Constants {
    public static final double stickDeadband = 0.1;


    public static final class Swerve {
        public static final int pigeonID = 1;  
        public static final boolean invertGyro = true; // Always ensure Gyro is CCW+ CW-

        //TODO: This must be tuned to specific robot - CHECK TURN PID
        public static final COTSFalconSwerveConstants chosenModule =  
            COTSFalconSwerveConstants.SWERVEX(COTSFalconSwerveConstants.driveGearRatios.SWERVEX_L2);

        /* Drivetrain Constants */
        public static final double trackWidth = Units.inchesToMeters(10.625); //TODO: This must be tuned to specific robot
        public static final double wheelBase = Units.inchesToMeters(18.5); //TODO: This must be tuned to specific robot
        public static final double wheelCircumference = chosenModule.wheelCircumference;

        /* Swerve Kinematics 
         * No need to ever change this unless you are not doing a traditional rectangular/square 4 module swerve */
         public static final SwerveDriveKinematics swerveKinematics = new SwerveDriveKinematics(
            new Translation2d(wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(wheelBase / 2.0, -trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, -trackWidth / 2.0));

        /* Module Gear Ratios */
        public static final double driveGearRatio = chosenModule.driveGearRatio;
        public static final double angleGearRatio = chosenModule.angleGearRatio;

        /* Motor Inverts */
        public static final boolean angleMotorInvert = chosenModule.angleMotorInvert;
        public static final boolean driveMotorInvert = chosenModule.driveMotorInvert;

        /* Angle Encoder Invert */
        public static final boolean canCoderInvert = chosenModule.canCoderInvert;

        /* Swerve Current Limiting */
        public static final int angleContinuousCurrentLimit = 25;
        public static final int anglePeakCurrentLimit = 40;
        public static final double anglePeakCurrentDuration = 0.1;
        public static final boolean angleEnableCurrentLimit = true;

        public static final int driveContinuousCurrentLimit = 35;
        public static final int drivePeakCurrentLimit = 60;
        public static final double drivePeakCurrentDuration = 0.1;
        public static final boolean driveEnableCurrentLimit = false;

        /* These values are used by the drive falcon to ramp in open loop and closed loop driving.
         * We found a small open loop ramp (0.25) helps with tread wear, tipping, etc */
        public static final double openLoopRamp = 0.25;
        public static final double closedLoopRamp = 1.0;

        /* Angle Motor PID Values */
        public static final double angleKP = 0.27;
        public static final double angleKI = 0;
        public static final double angleKD = 0;
        public static final double angleKF = 0;
    
        /* Drive Motor PID Values */
        public static final double driveKP = 0.005; //TODO: This must be tuned to specific robot
        public static final double driveKI = 0.0;
        public static final double driveKD = 0.0;
        public static final double driveKF = 0.067;

        /* Drive Motor Characterization Values 
         * Divide SYSID values by 12 to convert from volts to percent output for CTRE */
        public static final double driveKS = 0.1388 / 12 ; //TODO: This must be tuned to specific robot
        public static final double driveKV = 0.25173 / 12;
        public static final double driveKA = 0.014411 / 12;

        /* Swerve Profiling Values */
        /** Meters per Second */
        public static final double maxSpeed = 1.5; //TODO: This must be tuned to specific robot
        /** Radians per Second */
        public static final double maxAngularVelocity = 3.0; //TODO: This must be tuned to specific robot

        /* Neutral Modes */
        public static final NeutralMode angleNeutralMode = NeutralMode.Coast;//break
        public static final NeutralMode driveNeutralMode = NeutralMode.Brake;

        /* Module Specific Constants */
        /* Front Left Module - Module 0 */
        public static final class Mod0 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 1;
            public static final int angleMotorID = 2;
            public static final int canCoderID = 10;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(0.703);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }

        /* Front Right Module - Module 1 */
        public static final class Mod1 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 3;
            public static final int angleMotorID = 4;
            public static final int canCoderID = 11;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(209.092);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }
        
        /* Back Left Module - Module 2 */
        public static final class Mod2 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 5;
            public static final int angleMotorID = 6;
            public static final int canCoderID = 12;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(63.809);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }

        /* Back Right Module - Module 3 */
        public static final class Mod3 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 7;
            public static final int angleMotorID = 8;
            public static final int canCoderID = 13;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(60.029);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }
    }

    public static final class CamConstants {

        // CONSTANTS FOR SETTING UP MAPPING and TRIG
        
        public static final double CAMERA_HEIGHT_METERS = Units.inchesToMeters(13);
        public static final double TARGET_HEIGHT_METERS = Units.inchesToMeters(17.5);
        public static final double CAMERA_PITCH_RADIANS = Units.degreesToRadians(0);
        
        public static final double GOAL_RANGE_METERS = Units.feetToMeters(3);
        public static final double GOAL_OFSET_METERS = Units.feetToMeters(0);
        public static final double GOAL_ANGLE_RAD = Units.degreesToRadians(0);

    /**
         * Physical location of the apriltag camera on the robot, relative to the center of the robot.
         */
        public static final Transform3d CAMERA_TO_ROBOT =
            new Transform3d(new Translation3d(Units.inchesToMeters(6), Units.inchesToMeters(4), CAMERA_HEIGHT_METERS), new Rotation3d());
        public static final Transform3d ROBOT_TO_CAMERA = CAMERA_TO_ROBOT.inverse();        
            
        // PID constants should be tuned per robot

        public static final double LINEAR_P = 0.2;
        public static final double LINEAR_D = 0.0;
        public static final double LINEAR_I = 0.0;


        public static final double ANGULAR_P = 1.0;
        public static final double ANGULAR_D = 0.0;
        public static final double ANGULAR_I = 0.0;


        public static final double kMaxSpeedMetersPerSecond = 2;
        public static final double kMaxAccelerationMetersPerSecondSquared = 2;

        public static final double kMaxAngularSpeedRadiansPerSecond = 3;
        public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI * 2;



    }

    public static final class AutoConstants { //TODO: The below constants are used in the example auto, and must be tuned to specific robot
        public static final double kMaxSpeedMetersPerSecond = 2;
        public static final double kMaxAccelerationMetersPerSecondSquared = 2;
        public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI*4;
        public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI * 4;
    
        public static final double kPXController = 1.2;
        public static final double kPYController = 3.25;
        public static final double kPThetaController = 1;
    
        /* Constraint for the motion profilied robot angle controller */
        public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
            new TrapezoidProfile.Constraints(
                kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
    }


    public static final class kBalance {

        public static final double StillThreshold = 0.050000; // in G's
        public static final double BalanceAccelThreshold = 0.85; // In angle's
        public static final double power = 0.8; // Speed demanded to the wheels, relitive to MAX Speed.

        // Averaging window for accels when determining if the robot is still. 
        // total time of averaging ~= 0.02s * averageWindow.
        // should absolutely be not be more than 30 (or 0.60 second averaging window).
        // Note that this is not the witing time - this is the amount of time it must be still
        // before it moves.
        public static final int averageWindow = 20;




    }

    public static final class kArm {

        //CAN IDs//
        public static final int kShoulderMotorIDRight = 20;
        public static final int kShoulderMotorIDLeft = 21;
        public static final int kElbowMotorIDRight = 22;
        public static final int kElbowMotorIDLeft = 23;

        // Shoulder Invert//
        public static final TalonFXInvertType shoulderMotorLeftInvert = TalonFXInvertType.Clockwise;
        public static final TalonFXInvertType shoulderMotorRightInvert = TalonFXInvertType.Clockwise;

        // Shoulder Phase//
        public static final boolean shoulderLeftPhase = false;
        public static final boolean shoulderRightPhase = false;

        //Shoulder PIDG//
        public static double kShoulderP = 0;
        public static double kShoulderI = 0;
        public static double kShoulderD = 0;
        public static double kShoulderG = 0;

        //Shoulders//
        public static double kShoulderForward = 0;
        public static double kShoulderBackward = 0;
        public static final double kShoulderHighCube = 0;
        public static final double kShoulderMediumCube = 0;
        public static final double kShoulderLowCube = 0;
        public static final double kShoulderMediumCone = 0;
        public static final double kShoulderLowCone = 0;
        public static final double kShoulderHighCone = 0;
        public static final double kShoulderEncoderAt90Degrees = 0;
        public static final double kElbowEncoderAt90Degrees = 0;

        // Elbow Invert/
        public static final InvertType elbowMotorLeftInvert = InvertType.None;
        public static final InvertType elbowMotorRightInvert = InvertType.None;

        // Elbow Phase//
        public static final boolean elbowLeftPhase = false;
        public static final boolean elbowRightPhase = false;
    
        //Elbow PIDG//
        public static double kElbowP = 0;
        public static double kElbowI = 0;
        public static double kElbowD = 0;
        public static double kElbowG = 0;

        //Setting Elbow Motor Values//
        public static double kElbowForward = 0;
        public static double kElbowBackward = 0;

        //Elbow Measures//
        public static final double kElbowIn = 0;
        public static final double kElbowHighCube = 0;
        public static final double kElbowMediumCube = 0;
        public static final double kElbowLowCube = 0;
        public static final double kElbowMediumCone = 0;
        public static final double kElbowLowCone = 0;
        public static final double kElbowHighCone = 0;

        //Invert Switch//
        public static final double kInvertShoulderF = 0;
        public static final double kInvertShoulderB = 0;
        public static final double kInvertElbow = 0;
        
    }

    public static final class kGrabber{
        //CAN ID's//
        public static final int kGrabberMotorID = 24;

        // Grabber Invert//
        public static final boolean grabberMotorInvert = false;

        // Grabber Phase//
        public static final boolean grabberMotorPhase = false;

        // Grabber PID//
        public static final double kGrabberP = 0.04;
        public static final double kGrabberI = 0;
        public static final double kGrabberD = 0;
        public static final double kGrabberF = 0.65;

        // Grabber Motor Values//
        public static final double kConeGrab = 0;
        public static final double kConeRelease = 0;
        public static final double kSquareGrab = 0;
        public static final double kSquareRelease = 0;

        //Power Levels//
        public static final double backPower = 0;
        public static final double squeezeCurrent = 0;

        //Safety Limit Currenting//
        public static final int ampLimit = 5;
        public static final int timeout = 500; //ms

        // Timeout for the open claw command. Needed if the 
        // limit switch breaks.
        public static final double openTimeout = 5;



    }
}
    


