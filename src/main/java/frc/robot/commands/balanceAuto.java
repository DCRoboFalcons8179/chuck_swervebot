// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Swerve;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class balanceAuto extends SequentialCommandGroup {
  /** Creates a new balanceAuto. */
  public balanceAuto(Swerve s_Swerve) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

    addCommands(
      
      new Balance(s_Swerve).withTimeout(0.3),

      new InstantCommand(() -> s_Swerve.stop()),
      
      new WaitCommand(10).until(() -> (s_Swerve.isRobotStill())) );


      if (s_Swerve.isRobotLevel()) {

        addCommands(new swerveLockPosition(s_Swerve, s_Swerve.pointingUpAngle())); //need to take angle from going up the ramps

        this.end(true); // <- Ends this entire sequential command.
      }
      

      // From Tim: consider an add in a turn the wheels apart (or 90 degrees) 
      // command to the Swerve system that would help prevent movement. 

      // Also look at Swerve.driveManual and Swerve.driveExtraManual
  }

}
