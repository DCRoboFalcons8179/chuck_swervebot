// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.util.Units;
import frc.robot.Constants;

/** List of all used trajectories. We must calculate all of these beforehand, 
 * otherwise it will bog down the robot loops for traj calculations  */
public final class traj {

        // This is the default config - Feel free to change it!!!!! Pass it on in place of the config 
        // Variable in the Trajectory.
        public final static TrajectoryConfig config =
            new TrajectoryConfig(
                    Constants.AutoConstants.kMaxSpeedMetersPerSecond,
                    Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
                .setKinematics(Constants.Swerve.swerveKinematics);


        public final static TrajectoryConfig slowAndSteady = 
            new TrajectoryConfig(3.0, 1.6)
                .setKinematics(Constants.Swerve.swerveKinematics);

        // // not th real example. pulled from development ExampleAuto.
        // public final static Trajectory exampleTrajectory =
        // TrajectoryGenerator.generateTrajectory(
        //     // Start at the origin facing the +X direction
        //     new Pose2d(0, 0, new Rotation2d(0)),
        //     // Pass through these two interior waypoints, making an 's' curve path
        //     List.of(new Translation2d(2, 0), new Translation2d(1, 2)),
        //     // End 3 meters straight ahead of where we started, facing forward
        //     new Pose2d(3.0, 0.0, new Rotation2d(-1 * Math.PI)),
        //     config);

        // // Makes a Z. Can be called constantly and in conjunction with Zflipped to move
        // // back and forth.
        // public final static Trajectory makeZ = 
        // TrajectoryGenerator.generateTrajectory(
        //     // Start at the origin facing the +X direction
        //     new Pose2d(0, 0, new Rotation2d(0)),
        //     // Pass through these two interior waypoints, making an 's' curve path
        //     List.of(new Translation2d(2, 0), new Translation2d(1, 2)),
        //     new Pose2d(2.0, 2.0, new Rotation2d(-1 * Math.PI)),
        //     config);

        // // Same as Z, just spins the opposite way.
        // public final static Trajectory makeZFlipped = 
        // TrajectoryGenerator.generateTrajectory(
        //     // Start at the origin facing the +X direction
        //     new Pose2d(0, 0, new Rotation2d(0)),
        //     // Pass through these two interior waypoints, making an 's' curve path
        //     List.of(new Translation2d(2, 0), new Translation2d(1, 2)),
        //     new Pose2d(2.0, 2.0, new Rotation2d( 1 * Math.PI)),
        //     config);


        // // Traj for shuffiling right one grid slot
        // public final static Trajectory shuffleRight = 
        // TrajectoryGenerator.generateTrajectory(
        //     // Start at the origin facing the +X direction
        //     new Pose2d(0, 0, new Rotation2d(0)),
        //     // Pass through these two interior waypoints, making an 's' curve path
        //     List.of(new Translation2d(0, -Units.inchesToMeters(2)), new Translation2d(0, -Units.inchesToMeters(4))),
        //     // End 3 meters straight ahead of where we started, facing forward
        //     new Pose2d(0, -Units.inchesToMeters(6), new Rotation2d(0)),
        //     config);

        // // Traj for shuffiling left one grid slot
        // public final static Trajectory shuffleLeft = 
        // TrajectoryGenerator.generateTrajectory(
        //     // Start at the origin facing the +X direction
        //     new Pose2d(0, 0, new Rotation2d(0)),
        //     // Pass through these two interior waypoints, making an 's' curve path
        //     List.of(new Translation2d(0, Units.inchesToMeters(2)), new Translation2d(0, Units.inchesToMeters(4))),
        //     // End 3 meters straight ahead of where we started, facing forward
        //     new Pose2d(0, Units.inchesToMeters(6), new Rotation2d(0)),
        //     config);
    
        // public final static Trajectory straight = 
        // TrajectoryGenerator.generateTrajectory(
        //     // Start at the origin facing the +X direction
        //     new Pose2d(0, 0, new Rotation2d(0)),
        //     // Pass through these two interior waypoints, making an 's' curve path
        //     List.of(new Translation2d(1, 0), new Translation2d(0, Units.inchesToMeters(4))),
        //     // End 3 meters straight ahead of where we started, facing forward
        //     new Pose2d(2, Units.inchesToMeters(0), new Rotation2d(0)),
        //     config);


        // public final static Trajectory wireBump = 
        // TrajectoryGenerator.generateTrajectory(
        //     new Pose2d(0, 0, new Rotation2d(0)),
        //         List.of(new Translation2d(-0.25, 0.25), new Translation2d(-0.25, 4.0)),
        //         new Pose2d(-0.25, 4.25, new Rotation2d(Math.PI)),
        //             config);
        
        // public final static Trajectory rotate =

        // TrajectoryGenerator.generateTrajectory(
        //     new Pose2d(0, 0, new Rotation2d(0)),
        //         List.of(
        //             new Translation2d(0.25, 0),
        //             new Translation2d(1.0, 0)),

        //         new Pose2d(3.0, 0.25, Rotation2d.fromDegrees(90)),
        //             slowAndSteady);
        
        static Rotation2d zero = Rotation2d.fromDegrees(0);
        
        static List<Pose2d> noBumpOutToPiece = // RED
            List.of( 
            new Pose2d(1.87, -3.88, zero), 
            new Pose2d(2.53, -4.88, zero),
            new Pose2d(4.63, -4.88, zero),
            new Pose2d(6.41, -4.80, zero)
            // new Pose2d(8, 3, zero), 
            // new Pose2d(9, 2, zero), 
            // new Pose2d(10, 1, zero)
            
            );

        public final static Trajectory rotate =
        TrajectoryGenerator.generateTrajectory(
            noBumpOutToPiece,

            slowAndSteady);





        static List<Pose2d> noBumpOutToPieceBlue = 
        List.of( 
        new Pose2d(1.87, 3.88, zero), 
        new Pose2d(2.53, 4.88, zero),
        new Pose2d(4.63, 4.88, zero),
        new Pose2d(6.41, 4.80, zero)
        // new Pose2d(8, 3, zero), 
        // new Pose2d(9, 2, zero), 
        // new Pose2d(10, 1, zero)
        
        );

        public final static Trajectory rotateBlue =
        TrajectoryGenerator.generateTrajectory(
            noBumpOutToPieceBlue,

            slowAndSteady);


        static List<Pose2d> humpBlueList = 
        List.of(
            new Pose2d(1.91, 1.63, zero), 
            new Pose2d(2.44, 0.80, zero), 
            new Pose2d(3.88, 0.80, zero), 
            new Pose2d(6.6,  0.64, zero) 

        );


        static List<Pose2d> humpRedList = 
        List.of(
            // new Pose2d(1.91, -1.63, zero), 
            // new Pose2d(2.44, -0.85, zero), 
            // new Pose2d(3.88, -0.85, zero), 
            // new Pose2d(6.6, -0.64, zero) 
            new Pose2d(1.85, 4.4, zero), 
            new Pose2d(2, 5, zero), 
            new Pose2d(6.41, 5.177, zero)

        );

        public final static Trajectory humpBlue = 
        TrajectoryGenerator.generateTrajectory(
            humpBlueList,

            slowAndSteady);




        public final static Trajectory humpRed = 
        TrajectoryGenerator.generateTrajectory(
            humpRedList,

            slowAndSteady);





}
