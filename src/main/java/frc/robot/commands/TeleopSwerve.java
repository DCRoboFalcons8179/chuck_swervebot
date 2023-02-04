package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.pVision;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class TeleopSwerve extends CommandBase {    

    private Swerve s_Swerve;    

    private DoubleSupplier translationSup;
    private DoubleSupplier strafeSup;
    private DoubleSupplier rotationSup;
    private BooleanSupplier robotCentricSup;

    // Troubleshooting
    private BooleanSupplier atSpeed;




    public TeleopSwerve(Swerve s_Swerve, DoubleSupplier translationSup, 
        DoubleSupplier strafeSup, DoubleSupplier rotationSup, 
        BooleanSupplier robotCentricSup, BooleanSupplier atSpeed) {
        this.s_Swerve = s_Swerve;
        addRequirements(s_Swerve);

        this.translationSup = translationSup;
        this.strafeSup = strafeSup;
        this.rotationSup = rotationSup;
        this.robotCentricSup = robotCentricSup;
        
        // Troubleshooting
        this.atSpeed = atSpeed;


    }

    @Override
    public void execute() {
        /* Get Values, Deadband*/
        double translationVal = s_Swerve.filterInput(MathUtil.applyDeadband(translationSup.getAsDouble(), Constants.stickDeadband));
        double strafeVal = s_Swerve.filterInput(MathUtil.applyDeadband(strafeSup.getAsDouble(), Constants.stickDeadband));
        double rotationVal = s_Swerve.filterInput(MathUtil.applyDeadband(rotationSup.getAsDouble(), Constants.stickDeadband));

        
        // Initalize Drive Translation 2D values. These are the speeds fed to the controller.
        // These are scaled by the MAX_SPEED and maxAngleVelocity in constants.        
        
        Translation2d setDriveTranslate = new Translation2d(translationVal, strafeVal).times(Constants.Swerve.maxSpeed);
        Double setDriveRotation = rotationVal * Constants.Swerve.maxAngularVelocity;
        boolean isOpenLoop = true;

        // go straight forward
        if (atSpeed.getAsBoolean()) {

            translationVal = 0.5;
            strafeVal = 0;
            rotationVal = 0;
            isOpenLoop = false;

            
            setDriveTranslate = new Translation2d(translationVal, strafeVal).times(Constants.Swerve.maxSpeed);
            setDriveRotation = rotationVal * Constants.Swerve.maxAngularVelocity;

        }


    

        /* Drive */
        s_Swerve.drive(
            setDriveTranslate,
            setDriveRotation,
            !robotCentricSup.getAsBoolean(), 
            isOpenLoop
        );
    }
}