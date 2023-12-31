// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Swerve;

public class zeroTag extends CommandBase {
  /** Creates a new zeroTag. */

  Swerve swerve;
  Limelight lime;
  boolean isDone;

  // MUST TUNE THESE NUMBERS IN FIELD

  final double ty_atTarget = -15;
  final double ty_slowdown = ty_atTarget + 20;
  
  public zeroTag(Swerve swerve, Limelight lime) {

    this.swerve = swerve;
    this.lime = lime;

    addRequirements(swerve, lime);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    isDone = false;

    // lime.april_init();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double target = 0;

    double distanceFromLimeLightTarget = lime.get_TagTX();

    double vertToTarget = lime.get_TagTY();
    

    // side to side speed
    double speed = -0.5;
    double slowspeedscale = 0.5;


    if (distanceFromLimeLightTarget > target) {
      speed = speed * -1;
    }


    // forward speed
    double movingForwardSpeed = -1.3;


    if (vertToTarget < ty_slowdown) {
      movingForwardSpeed = movingForwardSpeed * 0.5;
    }
    if (vertToTarget < ty_atTarget) { // aka forward enough
      movingForwardSpeed = 0;
    }

    // System.out.println("==============");

    // System.out.println(distanceFromLimeLightTarget);
    // System.out.println(speed);

    // System.out.println(Math.abs(distanceFromLimeLightTarget - target));

    // System.out.println(vertToTarget);
    // System.out.println(movingForwardSpeed);


    if ((Math.abs(distanceFromLimeLightTarget - target) > 8)) {

      swerve.drive(new Translation2d(movingForwardSpeed, speed), 0 , true, false);
    }
    else if ((Math.abs(distanceFromLimeLightTarget - target) > 2)) {

      swerve.drive(new Translation2d(movingForwardSpeed, slowspeedscale*speed), 0, true, false);
    
    }

    else{

      // make sure that 
      swerve.drive(new Translation2d(movingForwardSpeed, 0), 0, true, false);

      if(Math.abs(movingForwardSpeed) < 0.05 || vertToTarget < -17){
      // debounce, then end command.
        swerve.stop();
        isDone = true;
        this.cancel();

        System.out.print("aligned With Tag");
      }

    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

        // stop your drivetrain
        swerve.stop();

        lime.goToDriverCam();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isDone;
  }
}
