// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.grabCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Grabber;

public class closeClaw extends CommandBase {


  Grabber claw;

  
  public closeClaw(Grabber claw_out) {

    claw = claw_out;

    addRequirements(claw);



  }
  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    claw.isSqueezing = false;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    claw.closeClawSubsystem();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // test and see if we need to squeeze the whole time.
    claw.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
